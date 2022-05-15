import java.io.PrintWriter;
import java.io.IOException;
import java.util.Map;

public class WordleDicLoader {

    private WebCrawler wc = new WebCrawler();

    public WordleDicLoader() throws IOException {
    }

    public void init() throws IOException {
        System.out.println("Initializing word dataset...");

        Map<String, Integer> words = wc.getWords();

        PrintWriter writer = new PrintWriter("words.txt", "UTF-8");
        words.forEach((k, v) -> writer.println(k));
        // writer.println("The first line");
        // writer.println("The second line");
        writer.close();
    }

    public void getStats() {
        wc.printStats();
    }
}