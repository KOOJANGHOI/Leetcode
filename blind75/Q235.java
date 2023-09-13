public class Q235 {
    /**
     * find the lowest common ancestor (LCA) node of two given nodes in the BST
     * 쉬운 dfs 문제. 참고만
     */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    TreeNode result;

    // TODO(simon): dfs > 20m > time 98, space 98
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        findAncestor(root, p, q, (int) Math.pow(-10, 9) - 1, (int) Math.pow(10,9) + 1);
        return result;
    }

    public void findAncestor(TreeNode node, TreeNode p, TreeNode q, int min, int max) {
        if (node == null)
            return ;

        if ((p.val > min && p.val < max) && (q.val > min && q.val < max)) {
            result = node;
            findAncestor(node.left, p, q, min, node.val);
            findAncestor(node.right, p, q, node.val, max);
        }
    }

    // TODO(simon): solution > dfs > time 98, space 13
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }

        // If both nodes are smaller than the root, search in the left subtree
        if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor2(root.left, p, q);
        }

        // If both nodes are larger than the root, search in the right subtree
        if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor2(root.right, p, q);
        }

        // If one node is smaller and the other is larger, or one of them is equal to the root
        // then the current root is the LCA
        return root;
    }

    public void solve() {
//        TreeNode n8 = new TreeNode(3);
//        TreeNode n9 = new TreeNode(5);
//
//        TreeNode n4 = new TreeNode(0);
//        TreeNode n5 = new TreeNode(4); n5.left = n8; n5.right = n9;
//        TreeNode n6 = new TreeNode(7);
//        TreeNode n7 = new TreeNode(9);
//
//        TreeNode n2 = new TreeNode(2); n2.left = n4; n2.right = n5;
//        TreeNode n3 = new TreeNode(8); n3.left = n6; n3.right = n7;
//
//        TreeNode n1 = new TreeNode(6); n1.left = n2; n1.right = n3;

        TreeNode n2 = new TreeNode(1);
        TreeNode n1 = new TreeNode(2); n1.left = n2;

        System.out.println(lowestCommonAncestor(n1, n1, n2).val);
    }
}
