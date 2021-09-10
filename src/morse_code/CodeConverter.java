package morse_code;

import java.util.HashMap;
import java.util.Map;

public class CodeConverter {
    Map<String, Character> converter = new HashMap<>();
    public CodeConverter() {
        converter.put(".-", 'a');
        converter.put("-...", 'b');
        converter.put("-.-.", 'c');
        converter.put("-..", 'd');
        converter.put(".", 'e');
        converter.put("..-.", 'f');
        converter.put("--.", 'g');
        converter.put("....", 'h');
        converter.put("..", 'i');
        converter.put(".---", 'j');
        converter.put("-.-", 'k');
        converter.put(".-..", 'l');
        converter.put("--", 'm');
        converter.put("-.", 'n');
        converter.put("---", 'o');
        converter.put(".--.", 'p');
        converter.put("--.-", 'q');
        converter.put(".-.", 'r');
        converter.put("...", 's');
        converter.put("-", 't');
        converter.put("..-", 'u');
        converter.put("...-", 'v');
        converter.put(".--", 'w');
        converter.put("-..-", 'x');
        converter.put("-.--", 'y');
        converter.put("--..", 'z');
    }
}
