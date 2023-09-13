import java.util.*;

public class Q269 {
    /**
     * topological sort
     */
    // premium > topological sort
    public String alienOrder(String[] words) {
        // Step 1: Create the adjacency list and in-degree map
        Map<Character, List<Character>> graph = new HashMap<>();
        Map<Character, Integer> inDegree = new HashMap<>();
        for (String word : words) {
            for (char c : word.toCharArray()) {
                graph.put(c, new ArrayList<>());
                inDegree.put(c, 0);
            }
        }

        // Step 2: Build the graph
        for (int i = 0; i < words.length - 1; i++) {
            String currentWord = words[i];
            String nextWord = words[i + 1];
            int length = Math.min(currentWord.length(), nextWord.length());

            for (int j = 0; j < length; j++) {
                char currentChar = currentWord.charAt(j);
                char nextChar = nextWord.charAt(j);

                if (currentChar != nextChar) {
                    graph.get(currentChar).add(nextChar);
                    inDegree.put(nextChar, inDegree.get(nextChar) + 1);
                    break;
                }
            }
        }

        // Step 3: Perform topological sort using Kahn's algorithm
        StringBuilder result = new StringBuilder();
        Queue<Character> queue = new LinkedList<>();

        for (char c : inDegree.keySet()) {
            if (inDegree.get(c) == 0) {
                queue.offer(c);
            }
        }

        while (!queue.isEmpty()) {
            char currentChar = queue.poll();
            result.append(currentChar);

            for (char neighbor : graph.get(currentChar)) {
                inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                if (inDegree.get(neighbor) == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        // Step 4: Check for cycles
        if (result.length() != inDegree.size()) {
            return "";
        }

        return result.toString();
    }
}
