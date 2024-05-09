import java.util.*;

class grammar {
    String Left;
    String Right;

    public grammar(String l, String r) {
        this.Left = l;
        this.Right = r;
    }
}

public class First {
    static grammar[] rules = new grammar[2];
    static HashSet<Character> set = new HashSet<>();

    public static grammar findGrammar(char ch) {
        for (grammar rule : rules) {
            if (rule.Left.charAt(0) == ch) {
                return rule;
            }
        }
        System.out.println(ch + "is a invalid Non-terminal");
        return null;
    }

    public static void findfirst(grammar input) {
        String l = input.Left;
        String r = input.Right;
        // make a hashset of char

        char ch;
        String[] prods = r.split("\\|");
        for (String prod : prods) {
            ch = prod.charAt(0);
            if (Character.isLowerCase(ch) || ch == '#') {

                // checking if character already present for First(ch)
                if (set.contains(ch) == false) {
                    set.add(ch);
                    System.out.print(ch + " ");
                }
            } else {
                // finding grammar starting with ch
                grammar temp = findGrammar(ch);
                findfirst(temp);
            }
        }
    }

    public static void main(String[] args) {

        String input;
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < 2; i++) {
            input = in.nextLine();
            String[] g = input.split("->");
            rules[i] = new grammar(g[0], g[1]);
        }
        for (grammar rule : rules) {
            System.out.println("First(" + rule.Left + ") = ");
            // make hashset set empty
            set.clear();
            findfirst(rule);
        }
    }
}
