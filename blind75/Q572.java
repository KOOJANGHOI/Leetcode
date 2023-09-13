import java.util.LinkedList;
import java.util.Queue;

public class Q572 {
    /**
     * determine tree B is subTree of tree A
     * 쉬운 dfs 문제. 두가지 다른 풀이가 있는점만 참고
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

    // TODO(simon): recursive > 15m > time 92, space 93
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            if (node.val == subRoot.val && isSameTree(node, subRoot))
                return true;

            if (node.left != null)
                queue.add(node.left);

            if (node.right != null)
                queue.add(node.right);
        }

        return false;
    }

    public boolean isSameTree(TreeNode root, TreeNode subRoot) {
        if (root == null && subRoot == null)
            return true;

        if (root == null || subRoot == null)
            return false;

        if (root.val == subRoot.val)
            return isSameTree(root.left, subRoot.left) && isSameTree(root.right, subRoot.right);

        return false;
    }

    // TODO(simon): solution > dfs > time 99, space 40
    public boolean isSubtree2(TreeNode root, TreeNode subRoot) {
        if (root == null) {
            return false;
        }

        if (isSameTree2(root, subRoot)) {
            return true;
        }

        return isSubtree2(root.left, subRoot) || isSubtree2(root.right, subRoot);
    }

    private boolean isSameTree2(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }

        if (p == null || q == null) {
            return false;
        }

        return (p.val == q.val) && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public void solve() {
        TreeNode n4 = new TreeNode(1);
        TreeNode n5 = new TreeNode(2);

        TreeNode n2 = new TreeNode(4, n4, n5);
        TreeNode n3 = new TreeNode(5);

        TreeNode n1 = new TreeNode(3, n2, n3);

        TreeNode nn2 = new TreeNode(1);
        TreeNode nn3 = new TreeNode(2);

        TreeNode nn1 = new TreeNode(4, nn2, nn3);

        System.out.println(isSubtree(n1, nn1));
    }
}
