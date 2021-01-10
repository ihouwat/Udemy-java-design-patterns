// Flyweight pattern - formatting text

package structural.flyweight;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

// Naive approach: build a list of boolean flags for every character in a string
class FormattedText
{
    private String plainText;
    private boolean [] capitalize;

    public FormattedText(String plainText) {
        this.plainText = plainText;
        // below naive approach, we are allocating a boolean for every single character
        capitalize = new boolean[plainText.length()];
    }

    public void capitalize(int start, int end)
    {
        for (int i = start; i <= end; i++)
        {
            capitalize[i] = true;
        }
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < plainText.length(); i++)
        {
            char c = plainText.charAt(i);
            sb.append(
                capitalize[i]
                ? Character.toUpperCase(c) : c
            );
        }
        return sb.toString();
    }
}

// Flyweight approach
class BetterFormattedText
{
    private String plainText;
    private List<TextRange> formatting = new ArrayList<>();

    public BetterFormattedText(String plainText) {
        this.plainText = plainText;
    }

    // Utility method
    public TextRange getRange(int start, int end)
    {
        TextRange range = new TextRange(start, end);
        formatting.add(range);
        return range;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < plainText.length(); i++)
        {
            char c = plainText.charAt(i);
            for(TextRange range : formatting)
                if (range.covers(i) && range.capitalize)
                    c = Character.toUpperCase(c);
            sb.append(c);
        }
        return sb.toString();
    }

    public class TextRange
    {
        public int start, end;
        public boolean capitalize, bold, italic;

        // Flyweight here. The class that saves us memory
        public TextRange(int start, int end) {
            this.start = start;
            this.end = end;
        }

        // Check whether a position falls between the start and end positions
        public boolean covers(int position)
        {
            return position >= start && position <= end;
        }
    }
}

class TextFormattingDemo
{
    public static void main(String[] args) {
        // Naive approach
        FormattedText ft = new FormattedText("This is a brave new world");
        ft.capitalize(10, 15);
        System.out.println(ft);

        // With flyweight pattern
        BetterFormattedText bft = new BetterFormattedText("Make a pizza pie with pineapples!");
        bft.getRange(13,18).capitalize = true;
        System.out.println(bft);
    }
}