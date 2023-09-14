import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.random.RandomGenerator;

/**
 * Number guessing game for humans.
 * <p>
 * Enter a seed number, and the computer will think of a number between 0 and
 * 99 that you have to guess in at most seven tries. Afterwards, you see your
 * guessing history so you can learn to better play the game.
 *
 * @author Bartosz Sójka
 * @author TODO
 * @id 1960903
 * @id TODO
 * @data 2023-09-14
 */
public class HumanGame {
    private final Scanner scanner = new Scanner(System.in);

    void run() {
        int code = initialize();
        List<Integer> history = play(code);
        printHistory(code, history);
    }

    public static void main(String[] args) {
        new HumanGame().run();
    }

    private List<Integer> play(int code) {
        List<Integer> history = new LinkedList<>();
        for (int i = 0; i < 7; i++) {
            int guess = scanner.nextInt();
            history.add(guess);
            if (guess == code) {
                System.out.println("Good guess! You won.");
                return history;
            } else if (guess > code) {
                System.out.println("lower");
            } else {
                System.out.println("higher");
            }
        }
        System.out.println("No more guesses, you lost.");
        return history;
    }

    private int initialize() {
        System.out.println("Type an arbitrary number");
        long seed = scanner.nextLong();
        RandomGenerator randomGenerator = new Random(seed);
        int code = randomGenerator.nextInt(100);
        System.out.println("Start guessing!");
        return code;
    }

    private void printHistory(int code, List<Integer> history) {
        System.out.println("guesses:");
        for (int guess : history) {
            String guessesString = "";
            guessesString += ".".repeat(Math.min(guess, code));
            if (guess < code) {
                guessesString += "X";
                guessesString += ".".repeat(code - guess - 1);
                guessesString += "|";
                guessesString += ".".repeat(99 - code);
            } else if (guess > code) {
                guessesString += "|";
                guessesString += ".".repeat(guess - code - 1);
                guessesString += "X";
                guessesString += ".".repeat(99 - guess);
            } else {
                guessesString += "X";
                guessesString += ".".repeat(99 - code);
            }
            System.out.println(guessesString);
        }
    }
}
