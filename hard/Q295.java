import java.util.PriorityQueue;

public class Q295 {
    // TODO(simon): priority queue > 30m > Time Limit Exceeded 16/21
    public class MedianFinder {
        PriorityQueue<Integer> queue;

        public MedianFinder() {
            queue = new PriorityQueue<>();
        }

        public void addNum(int num) {
            this.queue.add(num);
        }

        public double findMedian() {
            PriorityQueue<Integer> tempQueue = new PriorityQueue<>(this.queue);
            int N = queue.size() / 2;
            double ret = 0;

            if (queue.size() % 2 == 0) {
                int first = 0;
                int second = 0;

                for (int i = 0 ; i <= N ; ++i) {
                    if (i == N-1) {
                        first = tempQueue.poll();
                    } else if (i == N) {
                        second = tempQueue.poll();
                    } else {
                        tempQueue.poll();
                    }
                }

                ret = (double) (first + second) / 2;
            } else {
                for (int i = 0 ; i <= N ; ++i) {
                    ret = tempQueue.poll();
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
