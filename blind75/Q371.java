public class Q371 {
    /**
     * Given two integers a and b, return the sum of the two integers without using the operators + and -.
     * summing XOR and AND operator
     */
    /* summing XOR and AND operator */
    // TODO(simon): solution > bit-wise > time 100, space 99
    public int getSum(int a, int b) {
        while (b != 0) {
            // Calculate the carry
            int carry = a & b;

            // Calculate the sum without considering the carry
            a = a ^ b;

            // Shift the carry by 1 position to the left
            b = carry << 1;

            System.out.println("a:" + a + "b:" + b + "carry:" + carry);
        }
        return a;
    }

    public void solve() {
        int a = 3, b = 2;
        System.out.println(getSum(a,b));
    }
}
