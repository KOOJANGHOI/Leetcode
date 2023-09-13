import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Q297_3 {
    // TODO(simon): solution > time 23, space 5
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    String nullStr = "null";
    String delimiter = ",";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return nullStr;
        return root.val + delimiter + serialize(root.left) + delimiter + serialize(root.right);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Queue<String> queue = new LinkedList<>(Arrays.asList(data.split(",")));
        return helper(queue);
    }

    private TreeNode helper(Queue<String> queue) {
        String s = queue.poll();
        if (s.equals(nullStr)) return null;
        TreeNode root = new TreeNode(Integer.parseInt(s));
        root.left = helper(queue);
        root.right = helper(queue);
        return root;
    }
}
