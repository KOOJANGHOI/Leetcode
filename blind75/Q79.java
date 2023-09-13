public class Q79 {
    /**
     * Given an m x n grid of characters board and a string word, return true if word exists in the grid
     * 전형적인 dfs 문제. 최적화 방법이 재밌네
     */
    // TODO(simon): dfs > 70m > time 38, space 99
    public static boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        int[][] directions = new int[][]{{-1,0},{0,1},{1,0},{0,-1}};

        for (int i = 0 ; i < m ; ++i) {
            for (int j = 0 ; j < n ; ++j) {
                if (board[i][j] == word.charAt(0)) {
                    boolean[][] visited  = new boolean[m][n];
                    if (wordExists(board, word, 0, i, j, visited, directions))
                        return true;
                }
            }
        }

        return false;
    }

    private static boolean wordExists(char[][] board, String word, int index, int row, int col, boolean[][] visited, int[][] directions) {
        if (visited[row][col])
            return false;

        if (word.length() == index + 1)
            return true;

        visited[row][col] = true;

        for (int[] direction: directions) {
            int nextRow = row + direction[0];
            int nextCol = col + direction[1];

            if (!(nextRow < 0 || nextRow >= board.length || nextCol < 0 || nextCol >= board[0].length || board[nextRow][nextCol] != word.charAt(index+1))) {
                if (wordExists(board, word, index + 1, nextRow, nextCol, visited, directions))
                    return true;
            }
        }

        visited[row][col] = false;
        return false;
    }

    // TODO(simon): solution > time 75, space 89
    public boolean exist2(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0 || word == null || word.length() == 0) {
            return false;
        }

        int rows = board.length;
        int cols = board[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (dfs(board, i, j, word, 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean dfs(char[][] board, int i, int j, String word, int index) {
        if (index == word.length()) {
            return true;
        }

        int rows = board.length;
        int cols = board[0].length;

        if (i < 0 || i >= rows || j < 0 || j >= cols || board[i][j] != word.charAt(index)) {
            return false;
        }

        char temp = board[i][j];
        board[i][j] = '*'; // Mark the current cell as visited

        // Check the adjacent cells
        boolean found = dfs(board, i + 1, j, word, index + 1)
                || dfs(board, i - 1, j, word, index + 1)
                || dfs(board, i, j + 1, word, index + 1)
                || dfs(board, i, j - 1, word, index + 1);

        board[i][j] = temp; // Restore the cell value

        return found;
    }

    public static void solve() {
        char[][] board = {
                {'A','B','C','E'},
                {'S','F','E','S'},
                {'A','D','E','E'}
        };
        String word = "ABCB";

        System.out.println(exist(board, word));
    }
}
