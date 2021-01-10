// Flyweight pattern - storing user names for a game

package structural.flyweight;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

class User
{
    private String fullName;

    public User(String fullName) {
        this.fullName = fullName;
    }
}

// Flyweight here
class User2
{
    static List<String> strings = new ArrayList<>(); // store the names here - note it is static
    private int [] names; // indexes which the current user refers to

    public User2(String fullName)
    {
        // Utility function
        Function<String, Integer> getOrAdd = (String s) -> {
            int idx = strings.indexOf(s);
            if (idx != -1) return idx; // found the name in the list
            else {
                strings.add(s);
                return strings.size()-1; // return index of last element
            }
        };

        // We have the names having the indexes of the proper user strings
        names = Arrays.stream(fullName.split(" ")) // take full name and split at space
            .mapToInt(s -> getOrAdd.apply(s))
            .toArray();
    }
}

class UserDemo
{
    public static void main(String[] args)
    {
        // Here, the similar 'object' is the last name, 'Smith'
        User2 user = new User2("John Smith");
        User2 user2 = new User2("Jane Smith");

        System.out.println(User2.strings); // Returns [John, Smith, Jane]
    }
}