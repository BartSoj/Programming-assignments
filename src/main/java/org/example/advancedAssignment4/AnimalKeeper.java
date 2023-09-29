package org.example.advancedAssignment4;

import java.util.Scanner;

/**
 * @author Anna Smolko
 * @author Bartosz Sójka
 * @ID 1967037
 * @ID 1960903
 */
public class AnimalKeeper {
    private final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        new AnimalKeeper().run();
    }

    private void run() {
        StringBuilder stringBuilder = new StringBuilder();
        MyZoo myZoo = createMyZoo();
        int command = scanner.nextInt();
        while (command < 5) {
            boolean commandResult = executeCommand(command, myZoo);
            stringBuilder.append(command);
            if (!commandResult) {
                stringBuilder.append("!");
            }
            stringBuilder.append(" ");
            command = scanner.nextInt();
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        System.out.println(stringBuilder);
    }

    private MyZoo createMyZoo() {
        MyZoo myZoo = new MyZoo();
        for (FoodType foodType : FoodType.values()) {
            myZoo.addFoodStorage(new FoodStorage(foodType, 100));
        }
        for (int i = 0; i < 10; i++) {
            myZoo.addHome(new Home(i, 2, true));
        }
        for (int i = 10; i < 15; i++) {
            myZoo.addHome(new Home(i, 6, false));
        }
        return myZoo;
    }

    private boolean executeCommand(int command, MyZoo myZoo) {
        try {
            switch (command) {
                case 0:
                    return addAnimal(myZoo);
                case 1:
                    return moveAnimal(myZoo);
                case 2:
                    return removeAnimal(myZoo);
                case 3:
                    return buyOfFood(myZoo);
                case 4:
                    return feedOfFood(myZoo);
            }
        } catch (RuntimeException e) {
            // uncomment for testing
            e.printStackTrace();
            return false;
        }
        return false;
    }

    private boolean addAnimal(MyZoo myZoo) {
        AnimalType animalType = AnimalType.getById(scanner.nextInt());
        String name = scanner.next();
        int homeId = scanner.nextInt();
        Animal animal = new Animal(name, animalType, homeId);
        try {
            myZoo.addAnimal(animal);
        } catch (ZooRulesViolationException e) {
            return false;
        }
        return true;
    }

    private boolean moveAnimal(MyZoo myZoo) {
        String name = scanner.next();
        int homeId = scanner.nextInt();
        try {
            myZoo.moveAnimal(name, homeId);
        } catch (ZooRulesViolationException e) {
            return false;
        }
        return true;
    }

    private boolean removeAnimal(MyZoo myZoo) {
        String name = scanner.next();
        try {
            myZoo.removeAnimal(name);
        } catch (ZooRulesViolationException e) {
            return false;
        }
        return true;
    }

    private boolean buyOfFood(MyZoo myZoo) {
        FoodType foodType = FoodType.getById(scanner.nextInt());
        int amount = scanner.nextInt();
        try {
            myZoo.addFood(foodType, amount);
        } catch (ZooRulesViolationException e) {
            return false;
        }
        return true;
    }

    private boolean feedOfFood(MyZoo myZoo) {
        FoodType foodType = FoodType.getById(scanner.nextInt());
        int amount = scanner.nextInt();
        int homeId = scanner.nextInt();
        try {
            myZoo.feedOfFood(foodType, amount, homeId);
        } catch (ZooRulesViolationException e) {
            return false;
        }
        return true;
    }
}
