public class Q226 {
    /**
     * given a tree and return inverted tree
     * 엄청 쉬운 재귀 문제. 참고만
     */
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

    // TODO(simon): recursive-approach > time 25m > time 100, space 7
    public TreeNode invertTree(TreeNode root) {
        if (root == null)
            return null;

        TreeNode temp = root.right;
        root.right = invertTree(root.left);
        root.left = invertTree(temp);

        return root;
    }

    public void solve() {
        TreeNode n7 = new TreeNode(9);
        TreeNode n6 = new TreeNode(6);

        TreeNode n5 = new TreeNode(3);
        TreeNode n4 = new TreeNode(1);

        TreeNode n3 = new TreeNode(7, n6, n7);
        TreeNode n2 = new TreeNode(2, n4, n5);

        TreeNode n1 = new TreeNode(4, n2, n3);

        System.out.println(invertTree(n1));
    }

}
