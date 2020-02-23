/* *****************************************************************************
 *  Name:    Alan Turing
 *  NetID:   aturing
 *  Precept: P00
 *
 *  Description:  Prints 'Hello, World' to the terminal window.
 *                By tradition, this is everyone's first program.
 *                Prof. Brian Kernighan initiated this tradition in 1974.
 *
 **************************************************************************** */

public class RingBuffer {
    private int size; // the size of the ringbuffer
    private double[] rb; // the ringbuffer
    private int first, last; // cyclic wrap-around variables

    // contructor
    public RingBuffer(int capacity) {
        this.rb = new double[capacity];
        this.first = 0;
        this.last = 0;
        this.size = 0;
    }

    // returns the capacity of this ring buffer
    public int capacity() {
        return rb.length;
    }

    // returns the number of items currently in this ring buffer
    public int size() {
        return this.size;
    }

    // is this ring buffer empty
    public boolean isEmpty() {
        boolean e;
        if (this.size() == 0) {
            e = true;
        }
        else {
            e = false;
        }
        return e;
    }

    // is this ring buffer full
    public boolean isFull() {
        boolean f;
        if (this.size() == this.capacity()) {
            f = true;
        }
        else {
            f = false;
        }
        return f;
    }

    // adds item x to the end of this ring buffer
    public void enqueue(double x) {
        if (this.isFull()) {
            throw new RuntimeException("The ringbuffer is full.");
        }
        if (last == this.capacity()) {
            last = 0;
        }
        this.rb[last] = x;
        last++;
        size++;
    }

    // deletes and returns the item at the front of this ring buffer
    public double dequeue() {
        if (this.isEmpty()) {
            throw new RuntimeException("The ringbuffer is empty.");
        }
        else if (first == this.capacity()) {
            first = 0;
        }
        first++;
        size--;
        return rb[first - 1];
    }

    // returns the item at the front of this ring buffer
    public double peek() {
        if (this.isEmpty()) {
            throw new RuntimeException("The ringbuffer is empty.");
        }
        else if (first == this.capacity()) {
            return rb[0];
        }
        return rb[first];
    }

    // tests this class by directly calling all instance method
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        RingBuffer buffer = new RingBuffer(n);
        for (int i = 1; i <= n; i++) {
            buffer.enqueue(i);
        }
        double t = buffer.dequeue();
        buffer.enqueue(t);
        StdOut.println("Size after wrap-around is " + buffer.size());
        while (buffer.size() >= 2) {
            double x = buffer.dequeue();
            double y = buffer.dequeue();
            buffer.enqueue(x + y);
        }
        StdOut.println(buffer.peek());
    }
}
