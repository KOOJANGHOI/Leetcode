public class Q191 {
    public static int hammingWeight(int n) {
        int ctr = 0;
        while (n != 0) {
            n = n & (n - 1);
            ctr++;
        }
        return ctr;
    }

    public static int hammingWeight2(int n) {
        int ctr = 0;
        while (n != 0) {
            ctr += n % 2;
            n = n >> 1;
        }
        return ctr;
    }

    public static void solve() {
        for (int i = 0 ; i < 16 ; ++i) {
            System.out.println(i + " -> " + hammingWeight2(i));
        }
    }

}
