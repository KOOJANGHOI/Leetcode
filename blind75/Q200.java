public class Q200 {
    /**
     * return number of island in m*n grid (land = 1, water = 0)
     * 전형적인 dfs 문제인데, 최적화가 부족해서 점수가 좀 아쉬움
     * 약간의 트릭이 재밌네
     */
    // dfs > 20m > time 51, space 65
    public static int numIslands(char[][] grid) {
        int result = 0;
        boolean[][] marked = new boolean[grid.length][grid[0].length];

        for (int i = 0 ; i < grid.length ; ++i) {
            for (int j = 0 ; j < grid[0].length ; ++j) {
                if (grid[i][j] == '1' && !marked[i][j]) {
                    dfs(grid, marked, i, j);
                    result++;
                }
            }
        }

        return result;
    }

    private static void dfs(char[][] grid, boolean[][] marked, int i , int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length)
            return;

        if (grid[i][j] == '0' || marked[i][j])
            return;

        marked[i][j] = true;

        dfs(grid, marked, i-1,j);
        dfs(grid, marked, i+1,j);
        dfs(grid, marked, i,j-1);
        dfs(grid, marked, i,j+1);
    }

    // solution > dfs > time 97, space 71
    public static int numIslands2(char[][] grid) {
        int result = 0;

        for (int i = 0 ; i < grid.length ; ++i) {
            for (int j = 0 ; j < grid[0].length ; ++j) {
                if (grid[i][j] == '1') {
                    dfs2(grid, i, j);
                    result++;
                }
            }
        }

        return result;
    }

    private static void dfs2(char[][] grid, int i , int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == '0')
            return;

        // instead of visited array
        grid[i][j] = '0';

        dfs2(grid, i-1,j);
        dfs2(grid, i+1,j);
        dfs2(grid, i,j-1);
        dfs2(grid, i,j+1);
    }

    public static void solve() {
        char[][] grid = {
                {'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'}
        };

        System.out.println(numIslands(grid));
    }
}
