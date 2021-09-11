package morse_code;

import java.util.HashMap;
import java.util.Map;

public class MorseCodeII {
    /**
     * # Input: array of integers, consisting entirely of 1's and 0's
     * # Output: Alphabetic string of letters and spaces
     * # A "." is one or two 1's in a row (1 or 1,1)
     * # A "-" is three, four, or five 1's in a row (1,1,1 or 1,1,1,1 or 1,1,1,1,1)
     * # A short pause that separates individual tokens is one or two 0's in a row (0 or 0,0)
     * # A medium pause that separates groups of tokens that correspond to letters is 3, 4, or 5 0's in a row
     * # A space between words (" " in the final output) is 6 or more 0's in a row
     * # Goal: Transform raw signal (runs of 1's and 0's) into text, e.g.
     * # Example:
     * #
     * # signal = [0, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1,
     * 1, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 1,
     * 1, 1, 1, 0, 0, 1, 0, 1, 1, 1, 0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 1, 1,
     * 1, 0, 1, 0, 0, 0, 1, 0, 0]
     * # morse = ".--- --- .. -.[some representation of a long pause]... --.- ..- .- .-. ."
     * # assert "join square" == decode_morse(signal)
     * # Morse alphabet:
     * # a .-
     * # b -...
     * # c -.-.
     * # d -..
     * # e .
     * # f ..-.
     * # g --.
     * # h ....
     * # i ..
     * # j .---
     * # k -.-
     * # l .-..
     * # m --
     * # n -.
     * # o ---
     * # p .--.
     * # q --.-
     * # r .-.
     * # s ...
     * # t -
     * # u ..-
     * # v ...-
     * # w .--
     * # x -..-
     * # y -.--
     * # z --..
     */

    static Map<String, Character> DICT = new HashMap<>() {{
        put(".-", 'a');
        put("-...", 'b');
        put("-.-.", 'c');
        put("-..", 'd');
        put(".", 'e');
        put("..-.", 'f');
        put("--.", 'g');
        put("....", 'h');
        put("..", 'i');
        put(".---", 'j');
        put("-.-", 'k');
        put(".-..", 'l');
        put("--", 'm');
        put("-.", 'n');
        put("---", 'o');
        put(".--.", 'p');
        put("--.-", 'q');
        put(".-.", 'r');
        put("...", 's');
        put("-", 't');
        put("..-", 'u');
        put("...-", 'v');
        put(".--", 'w');
        put("-..-", 'x');
        put("-.--", 'y');
        put("--..", 'z');
    }};

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
                } else if (diff >= 6) {
                    sb.append('$');
                } else {
                    sb.append(' ');
                }
            }
            i = j;
        }
        return toWords(sb.toString().toCharArray());
    }

    private static String toWords(char[] input) {
        StringBuilder sb;
        StringBuilder result = new StringBuilder();
        int i = 0;
        while (i < input.length) {
            sb = new StringBuilder();
            while (i < input.length && input[i] != ' ' && input[i] != '$') {
                sb.append(input[i++]);
            }
            result.append(DICT.get(sb.toString()));
            if (i < input.length && input[i] == '$') {
                result.append(' ');
            }
            i++;
        }
        return result.toString();
    }

    public static void main(String[] args) {
        int[] signal = {0, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0,
                1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 1, 1, 0,
                1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1,
                0, 0, 1, 0, 1, 1, 1, 0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 0, 0, 0, 1, 0, 1, 1, 1,
                0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 0, 0};
        System.out.println(MorseCodeII.encode(signal));
    }
}


