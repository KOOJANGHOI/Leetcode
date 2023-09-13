import java.util.ArrayList;
import java.util.List;

public class Q261 {
    public boolean validTree(int n, int[][] edges) {
        // Step 1: Create adjacency list
        List<List<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        // Step 2: Build the graph
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            adjacencyList.get(u).add(v);
            adjacencyList.get(v).add(u);
        }

        // Step 3: Perform DFS to check for cycles and connectivity
        boolean[] visited = new boolean[n];
        if (hasCycle(adjacencyList, visited, 0, -1)) {
            return false;
        }

        // Step 4: Check for connectivity
        for (boolean nodeVisited : visited) {
            if (!nodeVisited) {
                return false;
            }
        }

        return true;
    }

    private boolean hasCycle(List<List<Integer>> adjacencyList, boolean[] visited, int current, int parent) {
        visited[current] = true;

        for (int neighbor : adjacencyList.get(current)) {
            if (!visited[neighbor]) {
                if (hasCycle(adjacencyList, visited, neighbor, current)) {
                    return true;
                }
            } else if (neighbor != parent) {
                return true;
            }
        }

        return false;
    }
}
