import java.util.Arrays;

public class Q297 {
    /**
     * implement function to encode/decode tree structure
     * decoding 과정에서 효율적인 방법을 찾지 못해서 51/52 에서 time limit exceeds ...
     * index += 2 방식으로 recursive하게 encoding 하는 방식이 효율적이였네 ..
     */

    // TODO(simon): 65m > time limit exceeds 51/52 > marked
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    int[] nodeValArr;
    int LEAF_VALUE = 1001;
    int MAX_SIZE;
    String delimiter = ",";

    public int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int leftHeight = getHeight(root.left);
            int rightHeight = getHeight(root.right);
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    public void addNodeValue(TreeNode node, int index) {
        if (index >= MAX_SIZE)
            return;

        if (node == null) {
            nodeValArr[index] = LEAF_VALUE;
            addNodeValue(null, 2*index+1);
            addNodeValue(null, 2*index+2);
        } else {
            nodeValArr[index] = node.val;
            addNodeValue(node.left, 2*index+1);
            addNodeValue(node.right, 2*index+2);
        }
    }

    public int[] convertStringToIntArray(String data) {
        String[] stringArray = data.split(delimiter);
        int[] intArray = new int[stringArray.length];

        for (int i = 0; i < stringArray.length; i++) {
            intArray[i] = Integer.parseInt(stringArray[i]);
        }

        return intArray;
    }

    public String serialize(TreeNode root) {
        int height = getHeight(root);
        MAX_SIZE = (int) Math.pow(2, height) - 1;
        nodeValArr = new int[MAX_SIZE];
        Arrays.fill(nodeValArr, LEAF_VALUE);
        addNodeValue(root, 0);
        String serialized = "";
        for (int i = 0 ; i < nodeValArr.length ; ++i) {
            serialized += nodeValArr[i] + delimiter;
        }
        return serialized;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.length() == 0)
            return null;

        int[] nodeValArr = convertStringToIntArray(data);
        TreeNode root = new TreeNode(nodeValArr[0]);
        constructTree(root, 0, true, nodeValArr);
        constructTree(root, 0, false, nodeValArr);

        return root;
    }

    public void constructTree(TreeNode parent, int parentIndex, boolean isLeft, int[] nodeValArr) {
        int childIndex = isLeft ? 2*parentIndex+1 : 2*parentIndex+2;

        if (childIndex < nodeValArr.length) {
            if (nodeValArr[childIndex] != LEAF_VALUE) {
                TreeNode child = new TreeNode(nodeValArr[childIndex]);

                if (isLeft) {
                    parent.left = child;
                } else {
                    parent.right = child;
                }

                constructTree(child, childIndex, true, nodeValArr);
                constructTree(child, childIndex, false, nodeValArr);
            }
        }
    }

    public void printTree(TreeNode root) {
        if (root != null) {
            System.out.println(root.val);
            printTree(root.left);
            printTree(root.right);
        } else {
            System.out.println("null");
        }
    }

    public void solve() {
        TreeNode n18 = new TreeNode(-2);
        TreeNode n17 = new TreeNode(-4);
        TreeNode n16 = new TreeNode(-1);
        TreeNode n15 = new TreeNode(9);
        TreeNode n14 = new TreeNode(5);
        TreeNode n13 = new TreeNode(6);
        TreeNode n12 = new TreeNode(0);
        TreeNode n11 = new TreeNode(-6);
        TreeNode n10 = new TreeNode(-6);
        TreeNode n9 = new TreeNode(6);
        TreeNode n8 = new TreeNode(-4);
        TreeNode n7 = new TreeNode(-7);
        TreeNode n6 = new TreeNode(9);
        TreeNode n5 = new TreeNode(-3);
        TreeNode n4 = new TreeNode(-9);
        TreeNode n3 = new TreeNode(-3);
        TreeNode n2 = new TreeNode(-7);
        TreeNode n1 = new TreeNode(4);

        n12.right = n16;
        n13.left = n17;
        n15.left = n18;

        n9.left = n12; n9.right = n13;
        n10.left = n14;
        n11.left = n15;

        n6.left = n9;
        n7.left = n10; n7.right = n11;

        n4.left = n6; n4.right = n7;
        n5.left = n8;

        n3.left = n4; n3.right = n5;

        n1.left = n2; n1.right = n3;

        printTree(deserialize(serialize(n1)));
    }
}
