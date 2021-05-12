public interface IAugmentedQueue {

    /**
     * @return the number of elements in the queue
     */
    int size();

    /**
     * Add an item to the back of the queue
     * @param value the integer to be added to the queue
     */
    void enqueue(int value);

    /**
     * Remove and return the item at the front of the queue
     * @return the item at the front of the queue, or null if the queue is empty
     */
    int dequeue();

    /**
     * Return the item at the front of the queue
     * @return the item at the front of the queue, or null if the queue is empty
     */
    int front();

    /**
     * Return the item at the back of the queue, i.e. the last item that was
     * added to the queue
     * @return the item at the back of the queue, or null if the queue is empty
     */
    int back();

    /**
     * Return the median of the items in the queue.  If the queue is empty, null
     * should be returned.
     * If there are an odd number of values in the queue, this is the value in the
     * middle of the values, if they were sorted in order.
     * If there are an even number of values in the queue, the median is the (sum of
     * the middle two values) divided by 2 (using integer division).
     * @return the median value of items in the queue.
     */
    int median();

    /**
     * Return the minimum value in the queue.
     *
     * @return the smallest value in the queue.
     */
    int min();

    /**
     * Return the maximum value in the queue.
     *
     * @return the largest value of items in the queue.
     */
    int max();
}
