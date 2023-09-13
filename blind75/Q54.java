import java.util.ArrayList;
import java.util.List;

public class Q54 {
    /**
     * Given an m x n matrix, return all elements of the matrix in spiral order*
     * simulation 비슷한 dfs 문제. 약간은 복잡하지만 크게 어렵지 않았다
     */
    // recursive approach > 45m > time 100, space 75
    public static List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        boolean[][] visited = new boolean[m][n];
        return spiralSearch(new ArrayList<>(), m, n, matrix, 0, 0, visited, 0);
    }

    public static List<Integer> spiralSearch(List<Integer> accList, int m, int n, int[][] matrix, int row, int col, boolean[][] visited, int direction) {
        if (accList.size() == m * n)
            return accList;

        int nextRow = row;
        int nextCol = col;

        if (direction == 0) {
            for (int j = col ; j < n ; ++j) {
                if (!visited[row][j]) {
                    visited[row][j] = true;
                    accList.add(matrix[row][j]);
                    nextCol = j;
                }
            }
        } else if (direction == 1) {
            for (int i = row ; i < m ; ++i) {
                if (!visited[i][col]) {
                    visited[i][col] = true;
                    accList.add(matrix[i][col]);
                    nextRow = i;
                }
            }
        } else if (direction == 2) {
            for (int j = col ; j >= 0 ; --j) {
                if (!visited[row][j]) {
                    visited[row][j] = true;
                    accList.add(matrix[row][j]);
                    nextCol = j;
                }
            }
        } else {
            for (int i = row ; i >= 0 ; --i) {
                if (!visited[i][col]) {
                    visited[i][col] = true;
                    accList.add(matrix[i][col]);
                    nextRow = i;
                }
            }
        }

        return spiralSearch(accList, m, n, matrix, nextRow, nextCol, visited, (direction + 1) % 4);
    }

    // solution > simple > time 100, space 25
    public List<Integer> spiralOrder2(int[][] matrix) {
        List<Integer> result = new ArrayList<>();

        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return result;
        }

        int rows = matrix.length;
        int columns = matrix[0].length;
        int top = 0;
        int bottom = rows - 1;
        int left = 0;
        int right = columns - 1;

        while (top <= bottom && left <= right) {
            // Traverse top row
            for (int i = left; i <= right; i++) {
                result.add(matrix[top][i]);
            }
            top++;

            // Traverse right column
            for (int i = top; i <= bottom; i++) {
                result.add(matrix[i][right]);
            }
            right--;

            // Traverse bottom row
            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    result.add(matrix[bottom][i]);
                }
                bottom--;
            }

            // Traverse left column
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    result.add(matrix[i][left]);
                }
                left++;
            }
        }

        return result;
    }

    public static void solve() {
        int[][] matrix = {
                {1}
        };

        List<Integer> list = spiralOrder(matrix);

        for (int num : list) {
            System.out.println(num);
        }
    }
}
