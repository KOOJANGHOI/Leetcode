import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q417 {
    public static List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> result = new ArrayList<>();

        int m = heights.length;
        int n = heights[0].length;

        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];

        // Visit all cells on the border and perform depth-first search
        for (int i = 0; i < m; i++) {
            dfs(heights, pacific, Integer.MIN_VALUE, i, 0);
            dfs(heights, atlantic, Integer.MIN_VALUE, i, n - 1);
        }
        for (int j = 0; j < n; j++) {
            dfs(heights, pacific, Integer.MIN_VALUE, 0, j);
            dfs(heights, atlantic, Integer.MIN_VALUE, m - 1, j);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j])
                    result.add(Arrays.asList(i,j));
            }
        }

        return result;
    }

    private static void dfs(int[][] heights, boolean[][] ocean, int prevHeight, int i, int j) {
        int m = heights.length;
        int n = heights[0].length;

        // Check for out-of-bounds and height conditions
        if (i < 0 || i >= m || j < 0 || j >= n || heights[i][j] < prevHeight || ocean[i][j]) {
            return;
        }

        ocean[i][j] = true;

        // Recursively visit neighbors
        dfs(heights, ocean, heights[i][j], i - 1, j);
        dfs(heights, ocean, heights[i][j], i + 1, j);
        dfs(heights, ocean, heights[i][j], i, j - 1);
        dfs(heights, ocean, heights[i][j], i, j + 1);
    }

    public static void solve() {
        int[][] height_1 = {
                {1,2,2,3,5},
                {3,2,3,4,4},
                {2,4,5,3,1},
                {6,7,1,4,5},
                {5,1,1,2,4}
        };

        int[][] height_2 = {
                {1,2,3},
                {8,9,4},
                {7,6,5}
        };

        System.out.println(pacificAtlantic(height_1));
    }
}
