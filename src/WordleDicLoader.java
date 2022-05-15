
import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class WordleDicLoader {

    public WordleDicLoader() throws IOException {
        WebCrawler wc = new WebCrawler();
        Map<String, Integer> words = wc.getWords();

        File f = new File("words.txt");
        Scanner in = new Scanner(f);

        PrintWriter writer = new PrintWriter("words.txt", "UTF-8");
        words.forEach((k, v) -> writer.println(k + " : " + v));
        // writer.println("The first line");
        // writer.println("The second line");
        writer.close();
    }

    public static void main(String[] args) throws IOException {
        WordleDicLoader wdl = new WordleDicLoader();

    }

}