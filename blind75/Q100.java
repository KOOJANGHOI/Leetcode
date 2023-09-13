public class Q100 {
    /**
     * determine two trees are same
     * 엄청 쉬운 recursive 문제. 참고만
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

    // TODO(simon): recursive-approach > time 10m > time 100, space 58
    public boolean isSameTree(TreeNode p, TreeNode q) {
        return (p == null && q== null) || (p != null && q != null && p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right));
    }

    public void solve() {
        TreeNode n3 = new TreeNode(3);
        TreeNode n2 = new TreeNode(2);
        TreeNode n1 = new TreeNode(1, n2, n3);

        TreeNode n6 = new TreeNode(3);
        TreeNode n5 = new TreeNode(2);
        TreeNode n4 = new TreeNode(1, n5, n6);

        System.out.println(isSameTree(n1, n4));
    }
}
