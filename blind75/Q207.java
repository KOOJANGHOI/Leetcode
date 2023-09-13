import java.util.ArrayList;
import java.util.List;

public class Q207 {
    /**
     * can finish course schedule from prerequisites of courses
     * 전형적인 cycle detection 문제. 풀때는 많이 헤맸다.
     */
    // solution > time 95, space 62
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjacencyList = new ArrayList<>();

        for (int i = 0 ; i < numCourses ; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        for (int[] prerequisite : prerequisites) {
            adjacencyList.get(prerequisite[0]).add(prerequisite[1]);
        }

        // Check for cycles in the graph using DFS
        boolean[] visited = new boolean[numCourses];
        boolean[] recursionStack = new boolean[numCourses];

        for (int i = 0 ; i < numCourses ; i++) {
            System.out.println("i: " + i);
            if (hasCycle(adjacencyList, i, visited, recursionStack)) {
                return false;
            }
        }

        return true;
    }

    private static boolean hasCycle(List<List<Integer>> adjacencyList, int course, boolean[] visited, boolean[] recursionStack) {
        System.out.println("visit:" +  course);
        // Found a cycle
        if (recursionStack[course])
            return true;

        // Already visited this node (no cycle)
        if (visited[course])
            return false;

        visited[course] = true;
        recursionStack[course] = true;

        for (int dependency : adjacencyList.get(course)) {
            if (hasCycle(adjacencyList, dependency, visited, recursionStack)) {
                return true;
            }
        }

        recursionStack[course] = false;

        return false;
    }

    public static void solve() {
        int numCourses = 4;
        int[][] prerequisites = {{0,1},{3,1}, {1,3}, {3,2}};

        System.out.println(canFinish(numCourses, prerequisites));
    }
}
