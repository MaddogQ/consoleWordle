import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebCrawler {

    // stats
    static int totalWords = 0;
    static int totalWrongLengthWords = 0;
    static int totalWordsKept = 0;
    static int uniqueWords = 0;

    // word list
    static Map<String, Integer> words = new HashMap<String, Integer>();

    public WebCrawler() throws IOException {
        URL urlAlice = new URL("https://www.gutenberg.org/cache/epub/28885/pg28885.txt");
        URL urlPaP = new URL("https://www.gutenberg.org/files/1342/1342-0.txt");
        URL urlRepublic = new URL("https://www.gutenberg.org/cache/epub/150/pg150.txt");
        URL urlGreatGatsby = new URL("https://www.gutenberg.org/ebooks/64317.txt.utf-8");

        ArrayList<URL> urlBooks = new ArrayList<URL>();
        urlBooks.add(urlAlice);
        urlBooks.add(urlPaP);
        urlBooks.add(urlRepublic);
        urlBooks.add(urlGreatGatsby);

        String fiveLetters = "\\b[a-z]{5}\\b";
        Pattern pattern = Pattern.compile(fiveLetters);

        for (URL url : urlBooks) {
            Scanner scan = new Scanner(url.openStream());
            while (scan.hasNext()) {
                String line = scan.nextLine();
                Matcher match = pattern.matcher(line);
                while (match.find()) {
                    String matched = match.group();
                    if (words.get(matched) == null) {
                        words.put(matched, 1);
                    } else {
                        words.put(matched, words.get(matched) + 1);
                    }
                }
                // tokenize(line);
            }
            scan.close();
        }
    }

    private void tokenize(String line) {
        String[] tokens = line.split(" ");
        // ArrayList<String> cleanTokens = new ArrayList<String>();
        // Map<String, Integer> cleanTokens = new HashMap<String, Integer>();

        for (String token : tokens) {
            // eliminate all non-letters
            String cleanToken = token.replaceAll("[0-9]+|\\p{Punct}+|\\s+|[^\\x00-\\x7F]+", "");
            cleanToken = cleanToken.toLowerCase();

            // System.out.println(cleanToken);

            if (cleanToken.length() > 0) {
                totalWords++;
            }

            if (cleanToken.length() == 5) {
                if (words.get(cleanToken) == null) {
                    words.put(cleanToken, 1);
                } else {
                    words.put(cleanToken, words.get(cleanToken) + 1);
                }
                totalWordsKept++;
            } else if (cleanToken.length() > 1) {
                totalWrongLengthWords++;
            }

        }
    }

    public void printStats() {
        // System.out.println("Word Freq:");
        // for (Map.Entry<String, Integer> entry : words.entrySet()) {
        // String key = entry.getKey();
        // Object val = entry.getValue();
        // System.out.print(key + " : ");
        // System.out.println(val);
        // }

        System.out.print("Total number of words processed: ");
        System.out.println(totalWords);

        System.out.print("Total good words by wrong length: ");
        System.out.println(totalWrongLengthWords);

        System.out.print("Total number of words kept: ");
        System.out.println(totalWordsKept);

        System.out.print("Number of unique words: ");
        uniqueWords = words.keySet().size();
        System.out.println(uniqueWords);

        ArrayList<String> keys = new ArrayList<>(words.keySet());
        ArrayList<Integer> values = new ArrayList<>(words.values());
        ArrayList<Integer> freqs = new ArrayList<>();
        Collections.sort(values);
        for (int i = values.size() - 1; i > values.size() - 21; i--) {
            freqs.add(values.get(i));
        }
        for (int i = 0; i < freqs.size(); i++) {
            Integer index;
            for (Integer value : values) {
                if (value == freqs.get(i)) { // wrong
                    index = values.indexOf(value);
                    System.out.print(keys.get(index) + " : ");
                    System.out.println(value);
                }
            }
        }
    }

    public Map<String, Integer> getWords() {
        return words;
    }

    public static void main(String[] args) throws IOException {
        WebCrawler wc = new WebCrawler();
        wc.printStats();

    }
}
