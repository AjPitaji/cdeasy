import java.util.*;

public class LFE {

    public static void main(String[] args) {
        // Example input grammar with common prefixes
        String production = "A->ab14|ab2|ab3";
        // Eliminate left factoring
        String[] parts = production.split("->");
        String nonTerminal = parts[0];
        String[] alternatives = parts[1].split("\\|");
        String commonPrefix = findCommonPrefix(alternatives);
        if (!commonPrefix.isEmpty()) {
            System.out.println(nonTerminal + " -> " + commonPrefix + "X");
            System.out.print("X -> ");
            for (String alternative : alternatives) {
                String suffix = alternative.substring(commonPrefix.length());
                System.out.print(suffix + "|");
            }
        } else {
            System.out.println("No common prefix found.");
        }
    }

    public static String findCommonPrefix(String[] alternatives) {
        if (alternatives.length == 0) {
            return "";
        }
        String prefix = alternatives[0];// ab1
        for (String alt : alternatives) {
            while (alt.startsWith(prefix) == false) {
                prefix = prefix.substring(0, prefix.length() - 1); // ab
            }
        }
        return prefix;
    }
}
