public class Q230 {
    /**
     * return k-th smallest value in BST
     * preorder traversal로 풀었는데 BST 특성상 inorder traversal이 효과적이였다
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

    int[] values;

    // TODO(simon): dfs > 10m > time 53, space 99
    public int kthSmallest(TreeNode root, int k) {
        int size = 10001;
        values = new int[size];

        traverse(root);

        for (int i = 0 ; i < size ; i++) {
            if (values[i] > 0 && --k == 0)
                return i;
        }

        return 0;
    }

    private void traverse(TreeNode node) {
        if (node == null)
            return;

        values[node.val]++;
        traverse(node.left);
        traverse(node.right);
    }

    private int count = 0;
    private int result = 0;

    // TODO(simon): solution > dfs > time 100, space 85
    public int kthSmallest2(TreeNode root, int k) {
        count = k;
        inOrderTraversal(root);
        return result;
    }

    private void inOrderTraversal(TreeNode node) {
        if (node == null || count == 0) {
            return;
        }

        inOrderTraversal(node.left);

        count--;
        if (count == 0) {
            result = node.val;
            return;
        }

        inOrderTraversal(node.right);
    }

    public void solve() {
        TreeNode n6 = new TreeNode(1);

        TreeNode n4 = new TreeNode(2, n6, null);
        TreeNode n5 = new TreeNode(1);

        TreeNode n2 = new TreeNode(3, n4, n5);
        TreeNode n3 = new TreeNode(6);

        TreeNode n1 = new TreeNode(5, n2,  n3);

        System.out.println(kthSmallest(n1, 3));
    }
}
