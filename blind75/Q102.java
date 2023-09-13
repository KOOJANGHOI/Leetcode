import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Q102 {
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

    private List<List<Integer>> answer;

    // TODO(simon): dfs > think 15m > time 99, space 94
    public List<List<Integer>> levelOrder(TreeNode root) {
        answer = new ArrayList<>();
        traverseByLevel(root, 0);
        return answer;
    }

    private void traverseByLevel(TreeNode node, int level) {
        if (node == null)
            return;

        if (answer.size() <= level) {
            List<Integer> newList = new ArrayList<>();
            newList.add(node.val);
            answer.add(level, newList);
        } else {
            answer.get(level).add(node.val);
        }

        traverseByLevel(node.left, level + 1);
        traverseByLevel(node.right, level + 1);
    }

    // TODO(simon): solution > dfs > time 99, space 94
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> levelNodes = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                levelNodes.add(node.val);

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            result.add(levelNodes);
        }

        return result;
    }

    public void solve() {
        System.out.println(levelOrder(null));
    }
}
