import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Q39 {
    /**
     * return all combinations of candidate number which sum is equal to given number
     * dp인줄 알았는데 점수가 안좋음 .. 전형적인 backtracking 문제였다
     */
    public static HashMap<Integer, List<List<Integer>>> dp;

    public static List<List<Integer>> removeDuplicates(List<List<Integer>> list) {
        Set<List<Integer>> set = new HashSet<>();
        List<List<Integer>> uniqueList = new ArrayList<>();

        for (List<Integer> innerList : list) {
            if (!set.contains(innerList)) {
                set.add(innerList);
                uniqueList.add(innerList);
            }
        }

        return uniqueList;
    }

    public static void update(int sum, int candidate) {
        int key = sum - candidate;

        if (dp.containsKey(key)) {
            List<List<Integer>> newList = new ArrayList<>();

            for (List<Integer> old : dp.get(key)) {
                List<Integer> list = new ArrayList<>(old);
                list.add(candidate);
                Collections.sort(list);
                newList.add(list);
            }

            if (dp.containsKey(sum)) {
                List<List<Integer>> oldList = dp.get(sum);
                dp.replace(sum, oldList, removeDuplicates(Stream.concat(oldList.stream(), newList.stream()).collect(Collectors.toList())));
            } else {
                dp.put(sum, removeDuplicates(newList));
            }
        }
    }

    // dp > 25m > time 5, space 6
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        dp = new HashMap<>();

        for (int i : candidates) {
            List<List<Integer>> list = new ArrayList<>();
            List<Integer> small = new ArrayList<>();
            small.add(i);
            list.add(small);
            dp.put(i, list);
        }

        Arrays.sort(candidates);

        for (int i = candidates[0] ; i <= target ; ++i) {
            for (int j : candidates) {
                update(i, j);
            }
        }

        return dp.containsKey(target) ? dp.get(target) : new ArrayList<>();
    }

    // backtracking > solution > time 81, space 16
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> combination = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(result, combination, candidates, target, 0);
        return result;
    }

    private static void backtrack(List<List<Integer>> result, List<Integer> combination, int[] candidates, int target, int start) {
        System.out.println("backtrack target(" + target + ") from " + candidates[start]);

        if (target == 0) {
            System.out.println("found");
            result.add(new ArrayList<>(combination));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] > target) {
                break;
            }

            combination.add(candidates[i]);
            backtrack(result, combination, candidates, target - candidates[i], i);
            combination.remove(combination.size() - 1);
        }

        System.out.println("fail");
    }

    // dp > solution > time 9, space 23
    public List<List<Integer>> combinationSum3(int[] candidates, int target) {
        List<List<Integer>>[] dp = new List[target + 1];
        dp[0] = new ArrayList<>();
        dp[0].add(new ArrayList<>());

        Arrays.sort(candidates);

        for (int i = 1; i <= target; i++) {
            dp[i] = new ArrayList<>();
            for (int j = 0; j < candidates.length && candidates[j] <= i; j++) {
                for (List<Integer> combination : dp[i - candidates[j]]) {
                    if (combination.isEmpty() || candidates[j] >= combination.get(combination.size() - 1)) {
                        List<Integer> newCombination = new ArrayList<>(combination);
                        newCombination.add(candidates[j]);
                        dp[i].add(newCombination);
                    }
                }
            }
        }

        return dp[target];
    }

    public static void solve() {
        int[] candidates = { 2,3,6,7 };
        int target = 7;

        System.out.println(combinationSum2(candidates, target));
    }
}
