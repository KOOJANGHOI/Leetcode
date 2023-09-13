import java.util.Stack;

public class Q20 {
    /**
     * validate parentheses
     * 그냥 stask 문제
     */
    // TODO(simon): stack > 20m > time 92, space 7
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if (stack.isEmpty()) {
                return false;
            } else {
                char popped = stack.pop();

                if (!((c == ')' && popped == '(') || (c == ']' && popped == '[') || (c == '}' && popped == '{')))
                    return false;
            }
        }

        return stack.isEmpty();
    }

    public void solve() {
        String s = "((";
        System.out.println(isValid(s));
    }
}
