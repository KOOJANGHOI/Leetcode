public class Q222_1 {
    // SUCCESS
    public int countNodes(Q222.TreeNode root) {
        if(root == null) {
            return 0;
        }
        return 1 + countNodes(root.left) + countNodes(root.right);
    }
}