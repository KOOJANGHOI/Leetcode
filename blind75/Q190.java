public class Q190 {
    /**
     * Reverse bits of a given 32 bits unsigned integer
     * logical shift operator를 몰라서 시간이 조금 걸림
     */
    // TODO(simon): 20m > time 93, space 87
    public static int reverseBits(int n) {
        int ctr = 0;
        int reversed = 0;

        while(ctr < 32) {
            reversed = (reversed << 1) + (n % 2);
            ctr++;
            n >>>= 1;
        }

        return reversed;
    }

    public static void solve() {
        int num = 0xFFFFFFFD;
        System.out.println(reverseBits(num));
    }
}
