import java.util.*;

public class LRE {

    public static void main(String[] args) {
        String input;
        Scanner in = new Scanner(System.in);
        System.out.println("Enter a grammar: ");
        input = in.nextLine();
        String[] grammar = input.split("->");
        String l = grammar[0];
        String r = grammar[1];
        String[] productions = r.split("\\|");
        int k = 0;
        for (String production : productions) {
            if (production.charAt(0) == l.charAt(0)) {
                k = 1;
                System.out.println(l + "'->" + production.substring(1) + l + "'");
            } else {
                System.out.println(l + "->" + production + l + "'");
            }
        }
        if (k == 1) {
            System.out.println(l + "'->" + "ε");
        } else {
            System.out.println("No left recursions");
        }
    }
}
