import java.util.ArrayList;
import java.util.List;

public class Q323 {
    public int countComponents(int n, int[][] edges) {
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

        // Step 3: Perform DFS to count connected components
        boolean[] visited = new boolean[n];
        int count = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(adjacencyList, visited, i);
                count++;
            }
        }

        return count;
    }

    private void dfs(List<List<Integer>> adjacencyList, boolean[] visited, int current) {
        visited[current] = true;

        for (int neighbor : adjacencyList.get(current)) {
            if (!visited[neighbor]) {
                dfs(adjacencyList, visited, neighbor);
            }
        }
    }
}



