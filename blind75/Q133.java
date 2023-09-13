import java.util.*;

public class Q133 {
    /**
     * return deep-copy of connected undirected graph
     * 그냥 노가다 문제인가 ... solution도 크게 다르지 않고
     */
    public class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    // graph > 50m > time5, space 72
    public Node cloneGraph(Node node) {
        if (node == null)
            return null;

        HashMap<Integer, Node> map = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();

        Node newHead = new Node(node.val);
        map.put(newHead.val, newHead);

        queue.add(node);

        while (!queue.isEmpty()) {
            Node currOriginal = queue.poll();
            Node headExisting = map.get(currOriginal.val);

            for (Node neighborOriginal : currOriginal.neighbors) {
                if (map.containsKey(neighborOriginal.val)) {
                    Node neighborExisting = map.get(neighborOriginal.val);

                    boolean duplicate = false;

                    for (Node n : neighborExisting.neighbors) {
                        if (n.val == currOriginal.val)
                            duplicate = true;
                    }

                    if (!duplicate) {
                        neighborExisting.neighbors.add(headExisting);
                        headExisting.neighbors.add(neighborExisting);
                    }
                } else {
                    Node newNeighbor = new Node();

                    newNeighbor.val = neighborOriginal.val;
                    newNeighbor.neighbors.add(headExisting);

                    headExisting.neighbors.add(newNeighbor);

                    map.put(newNeighbor.val, newNeighbor);
                    queue.add(neighborOriginal);
                }
            }
        }

        return newHead;
    }

    // solution > graph > 50m > time5, space 82
    public Node cloneGraph2(Node node) {
        if (node == null)
            return null;

        Map<Node, Node> visited = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();

        Node cloneNode = new Node(node.val);
        visited.put(node, cloneNode);
        queue.add(node);

        while (!queue.isEmpty()) {
            Node currNode = queue.poll();

            for (Node neighbor : currNode.neighbors) {
                if (!visited.containsKey(neighbor)) {
                    Node cloneNeighbor = new Node(neighbor.val);
                    visited.put(neighbor, cloneNeighbor);
                    queue.add(neighbor);
                }
                visited.get(currNode).neighbors.add(visited.get(neighbor));
            }
        }

        return cloneNode;
    }

    public static void solve() {
    }
}
