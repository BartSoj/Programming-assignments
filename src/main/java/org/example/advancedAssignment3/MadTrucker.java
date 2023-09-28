import java.util.*;

/**
 * Solves the problem of planning the order in which a truck driver should refuel
 * their truck to avoid stopping at prohibited locations.
 *
 * @author Anna Smolko
 * @ID 1967037
 * @author Bartosz Sójka
 * @ID 1960903
 */
public class MadTrucker {

    private final List<Integer> cans = new ArrayList<>();
    private final List<Integer> noStop = new ArrayList<>();

    public static void main(String[] args) {
        new MadTrucker().run();
    }

    /**
     * Runs the program.
     */
    public void run() {
        initialize();
        List<Integer> canIndexes = getCanIndexes();
        String chansInOrder = getCansInOrder(canIndexes, 0, "");
        System.out.println(chansInOrder);
    }

    /**
     * Reads data from input.
     */
    private void initialize() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            cans.add(scanner.nextInt());
        }
        for (int i = 0; i < n - 1; i++) {
            noStop.add(scanner.nextInt());
        }
    }

    /**
     * Returns list of cans indexes sorted by can value.
     */
    private List<Integer> getCanIndexes() {
        Map<Integer, Integer> cansWithIdx = new HashMap<>();
        for (int i = 0; i < cans.size(); i++) {
            cansWithIdx.put(i, cans.get(i));
        }
        return cansWithIdx.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .toList();
    }

    /**
     * Recursively finds the optimal order of fuel cans to use
     */
    private String getCansInOrder(List<Integer> cansIdx, int total, String output) {
        if (cansIdx.isEmpty()) {
            return output;
        }
        for (int i : cansIdx) {
            int can = cans.get(i);
            if (!noStop.contains(total + can)) {
                List<Integer> nextCansIdx = new ArrayList<>(cansIdx);
                nextCansIdx.remove(Integer.valueOf(i));
                String cansInOrder = getCansInOrder(nextCansIdx, total + can, output + " " + i);
                if (cansInOrder != null) {
                    return cansInOrder;
                }
            }
        }
        return null;
    }

}
