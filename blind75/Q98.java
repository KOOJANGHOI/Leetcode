public class Q98 {
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

    // TODO(simon): dfs > impl 10m > debug 60m > time 100, space 96
    public boolean isValidBST(TreeNode root) {
        return isValidBSTBy(root, root.left, Integer.MIN_VALUE - 1, root.val) && isValidBSTBy(root, root.right, root.val, Integer.MAX_VALUE + 1);
    }

    public boolean isValidBSTBy(TreeNode parent, TreeNode child, int min, int max) {
        if (child == null)
            return true;

        if (parent.val == child.val || (min == Integer.MAX_VALUE && max == Integer.MIN_VALUE))
            return false;

        if ((min == Integer.MAX_VALUE || min < child.val) && (max == Integer.MIN_VALUE || child.val < max)) {
            return isValidBSTBy(child, child.left, min, child.val) && isValidBSTBy(child, child.right, child.val, max);
        } else {
            return false;
        }
    }

    // TODO(simon): solution > dfs > time 100, space 91
    public boolean isValidBST2(TreeNode root) {
        return isValidBST2(root, null, null);
    }

    private boolean isValidBST2(TreeNode node, Integer lower, Integer upper) {
        if (node == null)
            return true;

        int val = node.val;

        if (lower != null && val <= lower)
            return false;

        if (upper != null && val >= upper)
            return false;

        if (!isValidBST2(node.right, val, upper))
            return false;

        if (!isValidBST2(node.left, lower, val))
            return false;

        return true;
    }

    public void solve() {
    }
}
