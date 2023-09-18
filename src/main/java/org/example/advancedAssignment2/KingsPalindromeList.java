import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Reads a list of numbers, and can reconstruct the corresponding list of
 * Palindromes,
 * produce the size of the largest magic set, and the content of that magic set.
 * <p>
 * Usage:
 * Provide in the first line of input number of a task to be executed on
 * palindrome list. Available options are;
 * 1 - you will get corrected list of palindromes in an order as provided in
 * input
 * 2 - you will get number of elements of the largest set that can be obtained
 * from palindrome list after corrections
 * 3 - you will get printed largest magic set of palindromes in ascending order
 * <p>
 * In second line of input please provide number of palindromes you will provie
 * in third line.
 * <p>
 * The third line is expected to have exact number of palindromes as provided in
 * second line of input. Each palindrome should be a natural number with odd
 * number of digits not more than 17 digits. Palindromes should be separated by
 * space.
 * <p>
 * After submitting third line you will get result printed out in a single line.
 *
 * @author Anna Smolko
 * @author Bartosz Sojka
 * @ID 19
 * @ID 19
 */
public class KingsPalindromeList {
    private static int MAX_PALINDROME_LENGTH = 17;
    /**
     * Array with palindromes.
     */
    private Palindrome[] palindromes;
    /**
     * Current number of palindomes in array.
     */
    private int size = 0;
    private int maxSetNumber = 0;
    private int maxSet = 0;

    /**
     * Array with palindromes id's for largest palindrome in magic set.
     */
    private int[] set;

    /**
     * Creates new palindrome list.
     *
     * @param len expected number of palindomes to be added to list.
     */
    public KingsPalindromeList(int len) {
        palindromes = new Palindrome[len];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int task = sc.nextInt();
        int n = sc.nextInt();
        KingsPalindromeList list = new KingsPalindromeList(n);
        for (int i = 0; i < n; i++) {
            list.addPalindrome(sc.nextLong());
        }
        sc.close();

        if (task == 1) {
            list.printPalindromes(System.out);
        } else if (task == 2) {
            list.findMaxSet();
            System.out.println(list.maxSet);
        } else if (task == 3) {
            list.printMaxSetNumbers(System.out);
        }
    }

    /**
     * Adds palindrome to palindrome list.
     */
    public void addPalindrome(long p) {
        palindromes[size] = new Palindrome(p);
        size++;
    }

    /**
     * Prints palindromes from list.
     */
    public void printPalindromes(PrintStream ps) {
        for (int i = 0; i < size - 1; i++) {
            ps.print(palindromes[i].getNumber() + " ");
        }
        ps.println(palindromes[size - 1].getNumber());
    }

    /**
     * Calculates the longest magic set size in palindromes list.
     *
     * @return longest magic set size
     */
    private int findMaxSet() {
        Arrays.sort(palindromes);
        int setIndex;
        set = new int[palindromes.length];
        for (int i = palindromes.length - 1; i >= 0; i--) {
            setIndex = 1;
            if (set[i] == 0) {
                set[i] = i;
                for (int j = i - 1; j >= 0; j--) {
                    if (isBInA(i, j) == 0) {
                        setIndex++;
                        set[j] = i;
                    }
                }
            }
            if (setIndex > maxSet) {
                maxSet = setIndex;
                maxSetNumber = i;
            }
        }
        return maxSet;
    }

    /**
     * Prints on given print stream the longest magic set.
     */
    private void printMaxSetNumbers(PrintStream ps) {
        if (maxSet == 0) {
            findMaxSet();
        }
        int setIndex = maxSet;
        long[] result = new long[maxSet];
        for (int i = maxSetNumber; i >= 0; i--) {
            if (set[i] == maxSetNumber || isBInA(maxSetNumber, i) == 0) {
                setIndex--;
                set[i] = maxSetNumber;
                result[setIndex] = palindromes[i].getNumber();
            }
        }
        for (int i = 0; i < maxSet - 1; i++) {
            ps.print(result[i] + " ");
        }
        ps.println(result[maxSet - 1]);
    }

    private int isBInA(int a, int b) {
        int check = 0;
        for (int m = 0; m < palindromes[b].magic.length; m++) {
            if (palindromes[a].magic[m] != palindromes[b].magic[m]) {
                check = 1;
                break;
            }
        }
        return check;
    }

    private class Palindrome implements Comparable<Palindrome> {
        private int[] magic;
        private long number;

        public Palindrome(long num) {
            int pos = MAX_PALINDROME_LENGTH;
            int[] numbers = new int[MAX_PALINDROME_LENGTH];
            long value = num;
            while (value > 0) {
                pos--;
                numbers[pos] = (int) (value % 10);
                value = value / 10;
            }
            makeCorrections(pos, numbers);
            initiateMagic(pos, numbers);
            extractNumber();
        }

        private void makeCorrections(int startingPosition, int[] numbers) {
            int x = startingPosition;
            for (int i = MAX_PALINDROME_LENGTH - 1; i >= x; i--) {
                if (numbers[x] < numbers[i]) {
                    numbers[i - 1]++;
                }
                numbers[i] = numbers[x];
                x++;
            }
            x--;
            int j = x;
            while (numbers[x] == 10) {
                numbers[x] = 0;
                numbers[j] = 0;
                x--;
                j++;
                numbers[j]++;
                numbers[x]++;
            }
        }

        private void initiateMagic(int startingPosition, int[] numbers) {
            int k = 0;
            int mpoint = (MAX_PALINDROME_LENGTH - startingPosition + 1) / 2;
            magic = new int[mpoint];
            for (int i = MAX_PALINDROME_LENGTH - mpoint; i < MAX_PALINDROME_LENGTH; i++) {
                magic[k++] = numbers[i];
            }
        }

        private void extractNumber() {
            for (int i = magic.length - 1; i >= 0; i--) {
                number = number * 10 + magic[i];
            }
            for (int i = 1; i < magic.length; i++) {
                number = number * 10 + magic[i];
            }
        }

        public long getNumber() {
            return number;
        }

        @Override
        public int compareTo(KingsPalindromeList.Palindrome o) {
            if (this.getNumber() > o.getNumber()) {
                return 1;
            } else if (this.getNumber() == o.getNumber()) {
                return 0;
            } else {
                return -1;
            }
        }
    }
}