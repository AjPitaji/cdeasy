import java.util.*;

class grammar {
    String Left;
    String Right;

    public grammar(String l, String r) {
        this.Left = l;
        this.Right = r;
    }
}

public class Trailing {
    static grammar[] rules = new grammar[2];
    // static HashSet<Character> set = new HashSet<>();

    public static grammar findGrammar(char ch) {
        for (grammar rule : rules) {
            if (rule.Left.charAt(0) == ch) {
                return rule;
            }
        }
        System.out.println(ch + " is a invalid Non-terminal");
        return null;
    }

    public static void findTrail(grammar input) {
        String r = input.Right;
        int indx = 0;
        char[] chars;
        // make a hashset of char
        String[] prods = r.split("\\|"); // {"TS,b","bc"}
        for (String prod : prods) {
            chars = prod.toCharArray();
            for (char ch : chars) {
                if (Character.isUpperCase(ch) == false) {
                    System.out.print(ch + " "); // , Lead(T) , Lead(S)
                    indx = prod.indexOf(ch); // 2
                    break;
                }
            }

            String NTs;
            grammar g;
            if (indx != 0) {
                NTs = prod.substring(0, indx);// TS
                chars = NTs.toCharArray();
                for (char ch : chars) {
                    g = findGrammar(ch);
                    findTrail(g);
                }
            }
        }

    }

    public static String reverse(String st) {
        String rev = "";
        for (int i = st.length() - 1; i >= 0; i--) {
            rev = rev + st.charAt(i);
        }
        return rev;
    }

    public static void main(String[] args) {

        String input;
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < 2; i++) {
            input = in.nextLine();
            String[] g = input.split("->");
            rules[i] = new grammar(g[0], reverse(g[1]));
        }
        in.close();
        for (grammar rule : rules) {
            System.out.print("Trail(" + rule.Left + ") = ");
            // make hashset set empty
            // set.clear();
            findTrail(rule);
        }
    }
}
