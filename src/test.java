import java.util.HashMap;
import java.util.Map;

public class test {
    public static void main(String[] args) {
        String line = "BUT her sister sat still just as she had left her, leaning her head,";

        line = line.replaceAll("[0-9]+|\\p{Punct}", "");

        System.out.println(line);

        String[] words = line.split(" ");

        Map<String, Integer> tokens = new HashMap<String, Integer>();

        for (String word : words) {
            if (tokens.get(word) == null) {
                tokens.put(word, 1);
            } else {
                tokens.put(word, tokens.get(word) + 1);
            }
        }

        for (Map.Entry<String, Integer> entry : tokens.entrySet()) {
            String key = entry.getKey();
            Object val = entry.getValue();
            System.out.println(key);
            System.out.println(val);
        }
    }
}