import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Detects if a point hits any of two circles.
 * Circles are defined by their centers (x, y) and radius. A point is considered
 * to hit a circle if it falls inside or on the circle's boundary.
 *
 * <p>
 * Usage:
 * The program reads eight floating-point numbers from the standard input.
 * The first two numbers are the coordinates of the center of the first circle.
 * The third number is the radius of the first circle.
 * The next three numbers are the same as the first three, but for the second circle.
 * The last two numbers are the coordinates of the point.
 *
 * <p>
 * The program prints one of the following messages:
 * <ul>
 *     <li>"input error" if the radius of any circle is negative</li>
 *     <li>"The point does not hit either circle" if the point does not hit any of the circles</li>
 *     <li>"The point hits the first circle" if the point hits the first circle but not the second</li>
 *     <li>"The point hits the second circle" if the point hits the second circle but not the first</li>
 *     <li>"The point hits both circles" if the point hits both circles</li>
 * </ul>
 * <p>
 * END
 *
 * @author Bartosz Sójka
 * @author Nikolaos Boutsioukis
 * @ID 1960903
 * @ID 1971514
 */
class HitDetection {

    /**
     * The threshold for comparing floating-point values.
     */
    private static final double THRESHOLD = 0.00001;

    /**
     * Main method to run the program.
     */
    public void run() {
        List<Double> input = readInput();
        HitStatus hitStatus = getHitStatus(input);
        printHitStatus(hitStatus);
    }

    /**
     * Entry point of the program.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new HitDetection().run();
    }

    /**
     * Print the appropriate hit status message.
     *
     * @param hitStatus The status of the point's hit.
     */
    private void printHitStatus(HitStatus hitStatus) {
        switch (hitStatus) {
            case ERROR:
                System.out.println("input error");
                break;
            case NEITHER:
                System.out.println("The point does not hit either circle");
                break;
            case FIRST:
                System.out.println("The point hits the first circle");
                break;
            case SECOND:
                System.out.println("The point hits the second circle");
                break;
            case BOTH:
                System.out.println("The point hits both circles");
                break;
        }
    }

    /**
     * Read input values from the standard input.
     *
     * @return A list containing the eight input values.
     */
    private List<Double> readInput() {
        Scanner scanner = new Scanner(System.in);
        List<Double> inputList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            inputList.add(scanner.nextDouble());
        }
        return inputList;
    }

    /**
     * Determine if the point hits any circle and return the corresponding status.
     *
     * @param input List of input values containing both circles and point coordinates.
     *              The list should contain eight values in the following order:
     *              x1, y1, r1, x2, y2, r2, x, y
     *              where (x1, y1) and (x2, y2) are the centers of the first and second circle,
     *              r1 and r2 are their radius, and (x, y) are the coordinates of the point.
     * @return The hit status of the point.
     */
    private HitStatus getHitStatus(List<Double> input) {
        if (input.get(2) < 0 || input.get(5) < 0) {
            return HitStatus.ERROR;
        }

        double distance1 = getDistance(input.get(0), input.get(1), input.get(6), input.get(7));
        boolean hit1 = distance1 - input.get(2) < THRESHOLD;
        double distance2 = getDistance(input.get(3), input.get(4), input.get(6), input.get(7));
        boolean hit2 = distance2 - input.get(5) < THRESHOLD;

        if (hit1 && hit2) {
            return HitStatus.BOTH;
        } else if (hit1) {
            return HitStatus.FIRST;
        } else if (hit2) {
            return HitStatus.SECOND;
        } else {
            return HitStatus.NEITHER;
        }
    }

    /**
     * Calculate the distance between two points.
     *
     * @param x1 X-coordinate of the first point.
     * @param y1 Y-coordinate of the first point.
     * @param x2 X-coordinate of the second point.
     * @param y2 Y-coordinate of the second point.
     * @return The distance between the two points.
     */
    private double getDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    /**
     * Enumeration representing the possible hit statuses.
     */
    private enum HitStatus {
        NEITHER, FIRST, SECOND, BOTH, ERROR
    }
}
