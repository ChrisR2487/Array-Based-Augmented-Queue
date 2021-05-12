import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Driver {

    public static void main(String[] args) {
        ArrayList<Integer> sorted = new ArrayList<>();
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        Random prng = new Random();
        ArrayBasedAugmentedQueue q = new ArrayBasedAugmentedQueue();
        for (int i = 0; i < 10_000; i++) {
            if (queue.size() == 0 || prng.nextDouble() < 0.8) {
                int val = prng.nextInt();
                //System.err.println("E " + val);
                sorted.add(val);
                Collections.sort(sorted);
                queue.add(val);
                q.enqueue(val);
            }
            else {
                Integer val = queue.remove();
                //System.err.println("D " + val);
                if (val != q.dequeue()) {
                    System.err.println("Dequeue wrong value");
                }
                sorted.remove(val);
            }
            if (queue.size() != q.size()) {
                System.err.println("Wrong size value");
            }
            if (queue.size() > 0) {
                if (queue.getFirst() != q.front()) {
                    System.err.println("Wrong front value " + queue.getFirst() + " vs " + q.front());
                }
                if (queue.getLast() != q.back()) {
                    System.err.println("Wrong last value " + queue.getLast() + " vs " + q.back());
                }
                if (sorted.get(0) != q.min()) {
                    System.err.println("Wrong min value");
                }
                if (sorted.get(sorted.size()-1) != q.max()) {
                    System.err.println("Wrong max value");
                }
                if (queue.size() % 2 == 0) {
                    int med = (sorted.get(sorted.size()/2) + sorted.get((sorted.size()-1)/2))/2;
                    if (med != q.median()) {
                        System.err.println("Wrong median value on even sized collection");
                    }
                }
                else {
                    int med = sorted.get(sorted.size()/2);
                    if (med != q.median()) {
                        System.err.println("Wrong median value on odd sized collection");
                    }
                }
            }
        }
    }
}