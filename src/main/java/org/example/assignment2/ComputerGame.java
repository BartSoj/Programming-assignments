import java.util.Scanner;

/**
 * Simple number guessing game for computers.
 * <p>
 * Think of a number and type "go". Your computer starts guessing
 * your number. Reply "lower" when the guess is too high, "higher"
 * when it is too low, and "guessed" when the computer guessed
 * your number correctly.
 *
 * @author Bartosz Sójka
 * @author TODO
 * @id 1960903
 * @id TODO
 * @data 2023-09-14
 */
public class ComputerGame {
    private final Scanner scanner = new Scanner(System.in);

    private void run() {
        initialize();
        play();
    }

    public static void main(String[] args) {
        new ComputerGame().run();
    }

    private void play() {
        int min = 0;
        int max = 1000;
        for (int i = 0; i < 10; i++) {
            int guess = (min + max) / 2;
            System.out.println(guess);
            String input = scanner.nextLine();
            if (input.equals("lower")) {
                max = guess;
            } else if (input.equals("higher")) {
                min = guess;
            } else if (input.equals("guessed")) {
                return;
            } else {
                throw new RuntimeException("Invalid input");
            }
        }
        System.out.println("I give up");
    }

    private void initialize() {
        System.out.println("Think of a secret number not smaller than 0 and not greater than 999. Type ’go’ when you have one.");
        String input = scanner.nextLine();
        if (!"go".equals(input)) {
            throw new RuntimeException("Invalid input");
        }
    }
}
