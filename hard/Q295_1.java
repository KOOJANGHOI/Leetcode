public class Q295_1 {
    // TODO(simon): indexed-approach > 30m > 20/21
    public class MedianFinder {
        int[] cnt;
        int min;
        int max;
        int size;

        public MedianFinder() {
            cnt = new int[200001];
            min = 100001;
            max = -100001;
            size = 0;
        }

        public void addNum(int num) {
            cnt[num+100000]++;
            min = Math.min(min, num);
            max = Math.max(max, num);
            size++;
        }

        public double findMedian() {
            int N = size / 2;
            double ret = 0;

            if (size % 2 == 0) {
                int total = 0;
                int first = 0;
                int second = 0;

                for (int i = min+100000 ; i <= max+100000 ; i++) {
                    if (cnt[i] == 0)
                        continue;

                    total += cnt[i];

                    if (second != 0)
                        break;

                    if (first != 0) {
                        second = i-100000;
                        break;
                    }

                    if (total >= N) {
                        first = i-100000;

                        if (total >= N + 1) {
                            second = first;
                        }
                    }
                }

                return (double) (first + second) / 2;
            } else {
                int total = 0;
                for (int i = min+100000 ; i <= max+100000 ; i++) {
                    total += cnt[i];

                    if (total > N) {
                        ret = i-100000;
                        break;
                    }
                }
            }

            return ret;
        }
    }

    public void solve() {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(1);
        medianFinder.addNum(2);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(3);
        System.out.println(medianFinder.findMedian());
    }
}
