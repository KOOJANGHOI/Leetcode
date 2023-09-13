public class Q104 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // TODO(simon): recursive-approach > time 10m > time 100, space 56
    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;

        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    public void solve() {
        TreeNode n5 = new TreeNode(7);
        TreeNode n4 = new TreeNode(15);
        TreeNode n3 = new TreeNode(20, n4, n5);
        TreeNode n2 = new TreeNode(9);
        TreeNode n1 = new TreeNode(3, n2, n3);

        System.out.println(maxDepth(n1));
    }
}
