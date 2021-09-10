package morse_code;

public class MorseCode {
    /**
     * # Input: array of a reasonable length of integers, consisting entirely of 1's and 0's
     * # Output: Arbitrary-length string made up of the characters ".", "-", and " "
     * # A "." is one or two 1's in a row (1 or 1,1)
     * # A "-" is three, four or five 1's in a row (1,1,1 or 1,1,1,1 or 1,1,1,1,1)
     * # A short pause (represented as the empty string "" in the output) is one or two 0's in a row (0 or 0,0)
     * # A long pause (represented as a " " in the output) is three, four, or five 0's in a row
     * # Goal: Transform raw signal (runs of 1's and 0's) into "intermediate" representation of morse code, e.g.
     * #
     * # signal = [1, 0, 1, 1, 0, 0, 1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1]
     * # assert "... --.-" == parse_morse(signal), "\"" + parse_morse(signal) + "\""
     *
     */

    public static String encode(int[] input) {
        // assume input is not null
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < input.length) {
            int j = i + 1;
            while (j < input.length && input[j] == input[i]) {
                j++;
            }
            int diff = j - i;
            if (diff <= 2) {
                if (input[i] == 1) {
                    sb.append('.');
                }
            } else {
                if (input[i] == 1) {
                    sb.append('-');
                } else {
                    sb.append(' ');
                }
            }
            i = j;
        }
        return sb.toString();
    }

}
class Test {
    public static void main(String[] args) {
        int[] input = {1, 0, 1, 1, 0, 0, 1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1};
        System.out.println(MorseCode.encode(input));
    }
}
