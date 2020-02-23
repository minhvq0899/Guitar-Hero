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

public class GuitarString {
    private RingBuffer ourString; // ringbuffer
    private double SAMPLING_RATE = 44100.0; // sampling rate
    private int n = 0;

    // creates a guitar string of the specified frequency
    // , using a sampling rate of 44,100
    public GuitarString(double frequency) {
        double f = frequency;
        n = (int) Math.ceil(SAMPLING_RATE / f);
        ourString = new RingBuffer(n);
        // enqueue our string with 0s
        for (int i = 0; i < n; i++) {
            ourString.enqueue(0.0);
        }
    }

    // creates a guitar string whose length
    // and initial values are given by the specified array
    public GuitarString(double[] init) {
        // n is the length of our argument array
        n = init.length;
        // creat our own rb
        ourString = new RingBuffer(n);
        for (int i = 0; i < n; i++) {
            ourString.enqueue(init[i]);
        }
    }

    // returns the number of samples in the ring buffer
    public int length() {
        return ourString.size();
    }

    // plucks this guitar string
    // (by replacing the ring buffer with white noise)
    public void pluck() {
        for (int i = 0; i < ourString.capacity(); i++) {
            ourString.dequeue();
            ourString.enqueue(StdRandom.uniform(-0.5, 0.5));
        }
    }

    //  advances the Karplus-Strong simulation one time step
    public void tic() {
        double del = ourString.dequeue();
        double firstSample = ourString.peek();
        ourString.enqueue(0.996 * ((del + firstSample) / 2));
    }

    // returns the current sample
    public double sample() {
        return ourString.peek();
    }

    public static void main(String[] args) {
        double[] samples = { 0.2, 0.4, 0.5, 0.3, -0.2, 0.4, 0.3, 0.0, -0.1, -0.3 };
        GuitarString testString = new GuitarString(samples);
        int m = 25; // 25 tics
        for (int i = 0; i < m; i++) {
            double sample = testString.sample();
            StdOut.printf("%6d %8.4f\n", i, sample);
            testString.tic();
        }
    }
}
