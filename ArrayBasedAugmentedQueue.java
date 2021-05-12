import java.util.NoSuchElementException;

public class ArrayBasedAugmentedQueue implements IAugmentedQueue {
    private int[] queue = new int[1];
    private int[] sorted = new int[1];
    private int front = 0;
    private int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public void enqueue(int value) {
        if (size == queue.length) resize();

        queue[getIndex(front + size)] = value;
        // Add item to end of sorted array, and then restore the sorted order
        sorted[size] = value;
        int index = size;
        while (index > 0) {
            if (sorted[index] < sorted[index-1]) {
                int tmp = sorted[index];
                sorted[index] = sorted[index-1];
                sorted[index-1] = tmp;
            }
            index--;
        }
        size++;
    }

    @Override
    public int dequeue() {
        if (size == 0) throw new NoSuchElementException();
        int retVal = queue[front];
        front = getIndex(front+1);
        int lastVal = 0;
        int index = size - 1;
        // Shift elements until reach item to be removed
        while (sorted[index] != retVal) {
            int tmp = sorted[index];
            sorted[index] = lastVal;
            lastVal = tmp;
            index--;
        }
        sorted[index] = lastVal;

        size--;

        return retVal;
    }

    @Override
    public int front() {
        if (size == 0)
            throw new NoSuchElementException();
        return queue[front];
    }

    @Override
    public int back() {
        if (size == 0)
            throw new NoSuchElementException();

        return queue[getIndex(front + size - 1)];
    }

    @Override
    public int median() {
        if (size == 0)
            throw new NoSuchElementException();

        if (size % 2 == 1)
            return sorted[size / 2];
        else
            return (sorted[size / 2] + sorted[size/2 - 1])/2;
    }

    @Override
    public int min() {
        if (size == 0)
            throw new NoSuchElementException();
        return sorted[0];
    }

    @Override
    public int max() {
        if (size == 0)
            throw new NoSuchElementException();
        return sorted[size-1];
    }

    private void resize() {
        int[] biggerQueue = new int[queue.length * 2];
        int[] biggerSorted = new int[queue.length * 2];

        for (int i = 0; i < size; i++) {
            biggerQueue[i] = queue[getIndex(front + i)];
            biggerSorted[i] = sorted[i];
        }
        queue = biggerQueue;
        sorted = biggerSorted;
        front = 0;
    }

    private int getIndex(int x) {
        x = x % queue.length;
        if (x < 0) x += queue.length;
        return x;
    }
}