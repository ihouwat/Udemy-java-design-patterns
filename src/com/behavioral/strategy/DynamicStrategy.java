// Strategy pattern - dynamic - example outputs in different formats

package behavioral.strategy;

import java.util.List;
import java.util.function.Supplier;

enum OutputFormat
{
    MARKDOWN, HTML
}

interface ListStrategy
{
    default void start(StringBuilder sb) {} // adding 'default' feels like cheating
    void addListItem(StringBuilder sb, String item);
    default void end(StringBuilder sb) {}
}

class MarkdownListStrategy implements ListStrategy
{

    @Override
    public void addListItem(StringBuilder sb, String item)
    {
        sb.append(" * ").append(item)
            .append(System.lineSeparator());
    }
}

class HtmlListStrategy implements ListStrategy
{
    @Override
    public void start(StringBuilder sb)
    {
        sb.append("<ul>").append(System.lineSeparator());
    }

    @Override
    public void addListItem(StringBuilder sb, String item)
    {
        sb.append("  <li>")
            .append(item)
            .append("</li>")
            .append(System.lineSeparator());
    }

    @Override
    public void end(StringBuilder sb)
    {
        sb.append("</ul>").append(System.lineSeparator());
    }
}

// To make pattern static, make class generic
class TextProcessor<LS extends ListStrategy>
{
    private StringBuilder sb = new StringBuilder();
//    private ListStrategy listStrategy; // dynamic way to determine what processor we want
    private LS listStrategy; // static approach

    // DYNAMIC APPROACH
//    public TextProcessor(OutputFormat format)
//    {
//        setOutputFormat(format);
//    }
//
//    public void setOutputFormat(OutputFormat format)
//    {
//        switch (format) {
//            case MARKDOWN:
//                listStrategy = new MarkdownListStrategy();
//                break;
//            case HTML:
//                listStrategy = new HtmlListStrategy();
//                break;
//        }
//    }

    // STATIC APPROACH
    public TextProcessor(Supplier<? extends LS> ctor)
    {
        listStrategy = ctor.get();
    }

    // Employ the strategy here
    public void appendList(List<String> items)
    {
        listStrategy.start(sb);
        for(String item : items)
            listStrategy.addListItem(sb, item); // key method of strategy pattern
        listStrategy.end(sb);
    }

    public void clear()
    {
        sb.setLength(0);
    }

    @Override
    public String toString()
    {
        return sb.toString();
    }
}

class DynamicStrategyDemo
{
    public static void main(String[] args)
    {
        // DYNAMIC APPROACH
        // The reason the strategy is dynamic is that you can switch strategies at runtime
//        TextProcessor tp = new TextProcessor(OutputFormat.MARKDOWN);
//        tp.appendList(List.of("liberte", "egalite", "fraternite"));
//        System.out.println(tp);
//
//        tp.clear();
//        tp.setOutputFormat(OutputFormat.HTML); // 'dynamic' as you changed the strategy here
//        tp.appendList(List.of("inheritance", "encapsulation", "polymorphism"));
//        System.out.println(tp);

        // STATIC APPROACH
        TextProcessor<MarkdownListStrategy> tp
            = new TextProcessor<>(MarkdownListStrategy::new); // passing in the constructor
        tp.appendList(List.of("alpha", "beta", "gamma"));
        System.out.println(tp);

        // Cannot switch strategies with static approach. Need to create new processor
        TextProcessor<HtmlListStrategy> tp2
                = new TextProcessor<>(HtmlListStrategy::new);
        tp2.appendList(List.of("alpha", "beta", "gamma"));
        System.out.println(tp2);
    }
}



