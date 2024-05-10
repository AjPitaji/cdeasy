import java.util.Stack;

public class itopo {
    // Helper method to check if a character is an operator
    static boolean isOperator(char c) {
        return (c == '+' || c == '-' || c == '*' || c == '/');
    }

    // Helper method to get the precedence of an operator
    static int precedence(char c) {
        switch (c) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
        }
        return -1;
    }

    // Method to convert infix to postfix
    static String infixToPostfix(String infix) {
        StringBuilder postfix = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (char c : infix.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                postfix.append(c);
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(stack.pop());
                }
                if (!stack.isEmpty() && stack.peek() != '(') {
                    return "Invalid Expression"; // Missing opening parenthesis
                } else {
                    stack.pop(); // Discard the '('
                }
            } else if (isOperator(c)) {
                while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                    postfix.append(stack.pop());
                }
                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            if (stack.peek() == '(') {
                return "Invalid Expression"; // Missing closing parenthesis
            }
            postfix.append(stack.pop());
        }

        return postfix.toString();
    }

    public static void main(String[] args) {
        String infixExpression = "a+b*c-(d/e+f*g)";
        String postfixExpression = infixToPostfix(infixExpression);
        System.out.println("Infix Expression: " + infixExpression);
        System.out.println("Postfix Expression: " + postfixExpression);
    }
}
