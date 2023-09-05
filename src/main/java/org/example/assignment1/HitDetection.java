package org.example.assignment1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Detects if a point hits any of two circles.
 * <p>
 * Usage:
 * TODO: Documentation
 * <p>
 * END
 *
 * @author Bartosz Sójka
 * @author
 * @ID 1960903
 * @ID
 */
class HitDetection {

    public void run() {
        List<Double> input = readInput();
        HitStatus hitStatus = getHitStatus(input);
        printHitStatus(hitStatus);
    }

    public static void main(String[] args) {
        new HitDetection().run();
    }

    private void printHitStatus(HitStatus hitStatus) {
        switch (hitStatus) {
            case ERROR -> System.out.println("input error");
            case NEITHER -> System.out.println("The point does not hit either circle");
            case FIRST -> System.out.println("The point hits the first circle");
            case SECOND -> System.out.println("The point hits the second circle");
            case BOTH -> System.out.println("The point hits both circles");
        }
    }

    private List<Double> readInput() {
        Scanner scanner = new Scanner(System.in);
        List<Double> inputList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            inputList.add(scanner.nextDouble());
        }
        return inputList;
    }

    private HitStatus getHitStatus(List<Double> input) {
        if (input.get(2) < 0 || input.get(5) < 0) {
            return HitStatus.ERROR;
        }

        double distance1 = getDistance(input.get(0), input.get(1), input.get(6), input.get(7));
        boolean hit1 = distance1 < input.get(2);
        double distance2 = getDistance(input.get(3), input.get(4), input.get(6), input.get(7));
        boolean hit2 = distance2 < input.get(5);

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

    private double getDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }
}
