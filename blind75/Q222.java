public class Q222 {
    // FALL > 3/18 ... why?
    static int depth = 0;
    static int nullLeafCount = 0;
    static boolean findNullLeaf = false;

    public static void solve() {
//        TreeNode n6 = new TreeNode(6);
//        TreeNode n3 = new TreeNode(3, n6, null);
//
//        TreeNode n4 = new TreeNode(4);
//        TreeNode n5 = new TreeNode(5);
//        TreeNode n2 = new TreeNode(2, n4, n5);
//
//        TreeNode n1 = new TreeNode(1, n2, n3);

        System.out.println(countNodes(new TreeNode()));
    }

    public static int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            depth = findDepth(root);
            traverse(root);
            return (int) Math.pow(2, depth) - 1 - nullLeafCount;
        }
    }

    public static int findDepth(TreeNode node) {
        return node.left == null ? 1 : findDepth(node.left) + 1;
    }

    public static void traverse(TreeNode node) {
        if (node == null) {
            if (!findNullLeaf) {
                findNullLeaf = true;
            }

            nullLeafCount++;
        } else {
            if (node.left != null || node.right != null) {
                traverse(node.right);
                traverse(node.left);
            }
        }
    }

    public static class TreeNode {
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
}


