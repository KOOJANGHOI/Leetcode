import java.util.PriorityQueue;

public class Q295_2 {
    // TODO(simon): solution > priority queue > time 99, space 43
    class MedianFinder {
        private PriorityQueue<Integer> maxHeap; // Store the lower half of numbers
        private PriorityQueue<Integer> minHeap; // Store the upper half of numbers

        public MedianFinder() {
            maxHeap = new PriorityQueue<>((a, b) -> b - a); // Max heap for the lower half
            minHeap = new PriorityQueue<>(); // Min heap for the upper half
        }

        public void addNum(int num) {
            if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
                maxHeap.offer(num);
            } else {
                minHeap.offer(num);
            }

            // Balance the two heaps
            if (maxHeap.size() > minHeap.size() + 1) {
                minHeap.offer(maxHeap.poll());
            } else if (minHeap.size() > maxHeap.size()) {
                maxHeap.offer(minHeap.poll());
            }
        }

        public double findMedian() {
            if (maxHeap.size() == minHeap.size()) {
                return (double) (maxHeap.peek() + minHeap.peek()) / 2;
            } else {
                return maxHeap.peek();
            }
        }
    }

    public class Main {
        public void main(String[] args) {
            MedianFinder medianFinder = new MedianFinder();
            medianFinder.addNum(1);
            medianFinder.addNum(2);
            System.out.println(medianFinder.findMedian()); // Output: 1.5
            medianFinder.addNum(3);
            System.out.println(medianFinder.findMedian()); // Output: 2.0
        }
    }
}
