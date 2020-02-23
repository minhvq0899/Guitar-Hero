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

public class GuitarHero {
    public static void main(String[] args) {
        double CONCERT = 440.0;
        int nextnote; // the index of the char typed
        char key; // the char typed
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' "; // our keyboard
        // an array of 37 GuitarStrings
        GuitarString[] guitarStrings = new GuitarString[37];
        // fill the array with corresponding frequency
        for (int i = 0; i < guitarStrings.length; i++) {
            double fre = CONCERT * Math.pow(2, ((i - 24.0) / 12.0));
            guitarStrings[i] = new GuitarString(fre);
        }
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                // store the char typed in key
                key = StdDraw.nextKeyTyped();
                // store its index into nextnote
                nextnote = keyboard.indexOf(key);
                if (0 <= nextnote && nextnote <= keyboard.length()) {
                    // pluck its corresponding guitarStrings
                    guitarStrings[nextnote].pluck();
                }
                double sample = 0.0;
                for (int i = 0; i < guitarStrings.length; i++) {
                    // the sum of all samples
                    sample += guitarStrings[i].sample();
                }
                // play that sample
                StdAudio.play(sample);
                for (int i = 0; i < guitarStrings.length; i++) {
                    // tic all the guitarStrings
                    guitarStrings[i].tic();
                }
            }
        }
    }
}
