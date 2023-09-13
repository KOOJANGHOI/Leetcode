import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q212 {
    /**
     * advance of Q79
     * naive하게 풀면 time limit exceeds. 최적화 방법이 재밌네
     */
    // TODO(simon): dfs > 30m > Time Limit Exceeded 62/65
    public List<String> findWords(char[][] board, String[] words) {
        int m = board.length;
        int n = board[0].length;
        List<String> list = new ArrayList<>();

        Arrays.sort(words);

        for (int k = words.length - 1 ; k >= 0 ; k--) {
            String word = words[k];
            boolean found = false;

            for (String s : list) {
                if (s.contains(word)) {
                    list.add(word);
                    found = true;
                    break;
                }
            }

            if (!found) {
                for (int i = 0 ; i < m ; i++) {
                    for (int j = 0 ; j < n ; j++) {
                        if (list.contains(word))
                            break;

                        if (dfs(board, i, j, word, 0))
                            list.add(word);
                    }
                }
            }
        }

        return list;
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
        board[i][j] = '*';

        boolean found = dfs(board, i + 1, j, word, index + 1)
                || dfs(board, i - 1, j, word, index + 1)
                || dfs(board, i, j + 1, word, index + 1)
                || dfs(board, i, j - 1, word, index + 1);

        board[i][j] = temp;

        return found;
    }

    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word;
    }

    // TODO(simon): solution > dfs > time 82, space 98
    public List<String> findWords2(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();

        TrieNode root = buildTrie(words);

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, i, j, root, result);
            }
        }

        return result;
    }

    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();

        for (String word : words) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                if (node.children[index] == null) {
                    node.children[index] = new TrieNode();
                }
                node = node.children[index];
            }
            node.word = word;
        }

        return root;
    }

    private void dfs(char[][] board, int i, int j, TrieNode node, List<String> result) {
        char c = board[i][j];
        int index = c - 'a';

        if (c == '#' || node.children[index] == null) {
            return;
        }

        node = node.children[index];

        if (node.word != null) {
            result.add(node.word);
            node.word = null; // Avoid duplicates
        }

        board[i][j] = '#'; // Mark the cell as visited

        if (i > 0) dfs(board, i - 1, j, node, result);
        if (i < board.length - 1) dfs(board, i + 1, j, node, result);
        if (j > 0) dfs(board, i, j - 1, node, result);
        if (j < board[0].length - 1) dfs(board, i, j + 1, node, result);

        board[i][j] = c; // Restore the cell
    }

    public void solve() {
        char[][] board = {
                {'o','a','b','n'},
                {'o','t','a','e'},
                {'a','h','k','r'},
                {'a','f','l','v'}
        };

        String[] words = {"oa","oaa"};

        System.out.println(findWords(board, words));
    }
}
