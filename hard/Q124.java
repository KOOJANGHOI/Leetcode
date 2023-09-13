import java.util.*;

public class Q124 {
    /**
     * return max sum of value of path in binary tree
     * 복잡하게 생각해서 완벽한 구현은 실패앴는데, 굉장히 간단하게 풀 수 있는 문제였군
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

    // TODO(simon): dfs > 90m > 72/95 > stop 하는 경우 구현 실패
    int[] nodeValArr;
    int max = 0;
    int LEAF_VALUE = 1001;
    int MAX_SIZE = 30000;

    public int maxPathSum(TreeNode root) {
        nodeValArr = new int[MAX_SIZE];
        Arrays.fill(nodeValArr, LEAF_VALUE);

        max = root.val;
        addNodeValue(root, 0);

        int[] dp = new int[MAX_SIZE];
        int[] dpp = new int[MAX_SIZE];
        Arrays.fill(dp, LEAF_VALUE);

        for (int i = 0; i < MAX_SIZE; ++i) {
            if (nodeValArr[i] != LEAF_VALUE)
                maxPathSumValue(i, dp, dpp);
        }

        return max;
    }

    public void addNodeValue(TreeNode node, int index) {
        if (node == null) {
            nodeValArr[index] = LEAF_VALUE;
        } else {
            nodeValArr[index] = node.val;
            addNodeValue(node.left, 2*index+1);
            addNodeValue(node.right, 2*index+2);
        }
    }

    public int maxPathSumValue(int index, int[] dp, int[] dpp) {
        if (dp[index] != LEAF_VALUE)
            return dp[index];

        int val = nodeValArr[index];

        if (val == LEAF_VALUE) {
            dp[index] = 0;
        } else {
            int left = maxPathSumValue(2*index + 1, dp, dpp);
            int right = maxPathSumValue(2*index + 2, dp, dpp);

            dpp[index] = Math.max(val, val + left + right);
            dp[index] = Math.max(val, Math.max(val + left, val + right));
            max = Math.max(dp[index], dpp[index]);
        }

        return dp[index];
    }


    private int maxSum;

    // TODO(simon): solution > dfs > time 100, space 79
    public int maxPathSum2(TreeNode root) {
        maxSum = Integer.MIN_VALUE;
        maxPathDown(root);
        return maxSum;
    }

    private int maxPathDown(TreeNode node) {
        if (node == null)
            return 0;

        int left = Math.max(0, maxPathDown(node.left));
        int right = Math.max(0, maxPathDown(node.right));

        maxSum = Math.max(maxSum, left + right + node.val);

        return Math.max(left, right) + node.val;
    }

    public void solve() {
        TreeNode n9 = new TreeNode(1);
        TreeNode n8 = new TreeNode(2);
        TreeNode n7 = new TreeNode(7);

        TreeNode n6 = new TreeNode(4, null, n9);
        TreeNode n5 = new TreeNode(13);
        TreeNode n4 = new TreeNode(11, n7, n8);

        TreeNode n3 = new TreeNode(8, n5, n6);
        TreeNode n2 = new TreeNode(4, n4, null);

        TreeNode n1 = new TreeNode(5, n2, n3);

        System.out.println(maxPathSum(n1));
    }
}
