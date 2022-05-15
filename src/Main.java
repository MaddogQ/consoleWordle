import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        System.out.println("Welcome to Wordle!");
        WordleDicLoader wdl = new WordleDicLoader();

        File f = new File("words.txt");
        if (!f.exists()) {
            System.out.println("NO DICTIONARY FOUND!");
            wdl.init();
        }

        Scanner in = new Scanner(System.in);
        System.out.println("Do you want to view stats about your words? [Y|N]");
        if (in.nextLine().toUpperCase().equals("Y")) {
            wdl.getStats();
        }

        // Main loop
        gameLoop();
    }

    public static void gameLoop() {
        while (true) {
            System.out.println("Ready to play Wordle! You have 6 guesses.");
            Scanner in = new Scanner(System.in);
            for (int i = 1; i < 7; i++) {
                System.out.print("Guess " + i + ": ");
                String response = in.nextLine();
                //

            }

            System.out.print("Would you like to play again? [Y/N]: ");
            if (in.nextLine().toUpperCase().equals("Y")) {
                System.out.println("wan ni ma de bi");
                continue;
            } else {
                System.out.println("break");
                break;
            }
        }
    }
}