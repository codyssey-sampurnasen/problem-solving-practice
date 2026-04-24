import java.util.Stack;

public class ValidParentheses {
    public static void main(String[] args) {
        String s = "{[()]}";
        Stack<Character> stack = new Stack<>();

        for(char c : s.toCharArray()) {
            if(c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if(stack.isEmpty()) {
                    System.out.println("Invalid");
                    return;
                }
                char top = stack.pop();
                if((c == ')' && top != '(') ||
                   (c == '}' && top != '{') ||
                   (c == ']' && top != '[')) {
                    System.out.println("Invalid");
                    return;
                }
            }
        }

        System.out.println(stack.isEmpty() ? "Valid" : "Invalid");
    }
}
