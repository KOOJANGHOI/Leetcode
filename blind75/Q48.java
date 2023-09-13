import java.util.LinkedList;
import java.util.Queue;

public class Q48 {
    /**
     * rotate n*n matrix in space
     * queue로 풀었는데 점수는 별로. 참신한 풀이가 있었는데!
     * clock wise 90 degree = transpose and reverse
     */
    // TODO(simon): queue > 40m > time 5, space 88
    public static void rotate(int[][] matrix) {
        int n = matrix.length;
        boolean[][] visited = new boolean[n][n];

        for (int k = 0 ; k < n ; ++k) {
            if (visited[k][k])
                break;

            Queue<int[]> queue = new LinkedList<>();

            for (int x = k ; x < n ; ++x) {
                queue.add(new int[]{ k, x, matrix[k][x] });
            }

            while (!queue.isEmpty()) {
                int[] arr = queue.poll();

                int i = arr[1];
                int j = n-1-arr[0];

                if (!visited[i][j]) {
                    visited[i][j] = true;
                    matrix[i][j] = arr[2];
                    queue.add(new int[]{ i, j, matrix[i][j] });
                }
            }
        }
    }

    // solution > transpose + reverse row > time 100, space 78
    public void rotate2(int[][] matrix) {
        int n = matrix.length;

        // Transpose the matrix
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // Reverse each row
        for (int i = 0; i < n; i++) {
            int left = 0;
            int right = n - 1;
            while (left < right) {
                int temp = matrix[i][left];
                matrix[i][left] = matrix[i][right];
                matrix[i][right] = temp;
                left++;
                right--;
            }
        }
    }

    public static void solve() {
        int[][] matrix = {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };

        rotate(matrix);

        for (int i = 0 ; i < matrix.length ; ++i) {
            for (int j = 0 ; j < matrix[0].length ; ++j) {
                System.out.println(matrix[i][j]);
            }
        }
    }
}
