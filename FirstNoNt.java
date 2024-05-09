
public class FirstNoNt {
    public static void main(String[] args) {
        String input = "S->abc|bBc|cC";
        String[] g = input.split("->");
        String l = g[0];
        String r = g[1];
        String[] prods = r.split("\\|");
        System.out.print("First(" + l + ") = ");
        for (String prod : prods) {
            System.out.print(prod.charAt(0) + " ");
        }
    }
}