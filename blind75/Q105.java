import java.util.Arrays;

public class Q105 {
    /**
     * build binary tree from output of preorder and inorder traversal
     * tree traversal 에 대해서 좀 까먹어서 시간좀 날렸네.. 결국은 쉬운 dfs 문제였다
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

    // TODO(simon): dfs > 65m > time 27, space 5
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0 && inorder.length == 0)
            return null;

        if (preorder.length == 1 && inorder.length == 1)
            return new TreeNode(preorder[0]);

        TreeNode root = new TreeNode(preorder[0]);

        for (int i = 0; i < inorder.length; ++i) {
            if (inorder[i] == preorder[0]) {
                root.left = buildTree(Arrays.copyOfRange(preorder, 1, i + 1), Arrays.copyOfRange(inorder, 0, i));
                root.right = buildTree(Arrays.copyOfRange(preorder, i + 1, preorder.length), Arrays.copyOfRange(inorder, i + 1, inorder.length));
                break;
            }
        }

        return root;
    }

    public void printTree(TreeNode root) {
        if (root != null) {
            System.out.println(root.val);
            printTree(root.left);
            printTree(root.right);
        }
    }

    public void solve() {
        int[] preorder = { 1,2 };
        int[] inorder = { 2,1 };

        printTree(buildTree(preorder, inorder));
    }
}
