import java.util.*;

/**
 * InnerSRparser
 */
class grammar {
    String left, right;

    public grammar(String l, String r) {
        this.left = l;
        this.right = r;
    }
}

public class SRparser {
    public static void main(String args[]) {
        int ruleCount;
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the number of production rules:");// S->S+S|S*S|x
        ruleCount = in.nextInt();
        in.nextLine();

        grammar[] rules = new grammar[ruleCount];
        String temp;
        String[] temp1;
        System.out.println("Enter rules one by one");
        for (int i = 0; i < ruleCount; i++) {
            temp = in.nextLine();
            temp1 = temp.split("->");
            rules[i] = new grammar(temp1[0], temp1[1]);
        }
        String input;
        int stackLength;
        int substringLength, stackTop;
        System.out.println("Enter input grammar");// "x+x+x" 5
        input = in.nextLine();
        in.close();
        int i = 0;
        String stack = "";
        while (true) {
            if (i < input.length()) {
                stack = stack + input.charAt(i);// x
                i++;
                System.out.print(stack + "\t");
                System.out.print(input.substring(i) + "\tShift " + stack.charAt(stack.length() - 1) + "\n");
            }
            for (int j = 0; j < ruleCount; j++) {
                if (stack.endsWith(rules[j].right)) {
                    stackLength = stack.length();
                    substringLength = rules[j].right.length();
                    stackTop = stackLength - substringLength;
                    stack = stack.substring(0, stackTop) + rules[j].left;
                    System.out.print(stack + "\t");
                    System.out.print(input.substring(i) + "\tReduce " + rules[j].left + "->" + rules[j].right + "\n");
                    j = -1; // Restart the loop
                }
            }

            if (stack.equals(rules[0].left) && i == input.length()) {
                System.out.println("Accepted");
                break;
            }

            if (i == input.length()) {
                System.out.println("Not Accepted");
                break;
            }
        }
    }
}
