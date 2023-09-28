package org.example.advancedAssignment4;

import java.util.Scanner;

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
            if (command == 0) {
                stringBuilder.append(addAnimal(myZoo) ? "0" : "0!");
            } else if (command == 1) {
                stringBuilder.append(moveAnimal(myZoo) ? "1" : "1!");
            } else if (command == 2) {
                stringBuilder.append(removeAnimal(myZoo) ? "2" : "2!");
            } else if (command == 3) {
                stringBuilder.append(buyOfFood(myZoo) ? "3" : "3!");
            } else if (command == 4) {
                stringBuilder.append(feedOfFood(myZoo) ? "4" : "4!");
            }
            stringBuilder.append(" ");
            command = scanner.nextInt();
        }
        System.out.println(stringBuilder.toString().trim());
    }

    private MyZoo createMyZoo() {
        MyZoo myZoo = new MyZoo();
        for (FoodType foodType : FoodType.values()) {
            myZoo.addFoodStorage(new FoodStorage(foodType, 0, 100));
        }
        for (int i = 0; i < 10; i++) {
            myZoo.addHome(new Home(i, 0, 2, true));
        }
        for (int i = 10; i < 15; i++) {
            myZoo.addHome(new Home(i, 0, 6, false));
        }
        return myZoo;
    }

    private boolean addAnimal(MyZoo myZoo) {
        AnimalType animalType = AnimalType.getById(scanner.nextInt());
        String name = scanner.next();
        int homeId = scanner.nextInt();
        Animal animal = new Animal(name, animalType, homeId);
        try {
            myZoo.addAnimal(animal);
        } catch (RuntimeException e) {
            return false;
        }
        return true;
    }

    private boolean moveAnimal(MyZoo myZoo) {
        String name = scanner.next();
        int homeId = scanner.nextInt();
        try {
            myZoo.moveAnimal(name, homeId);
        } catch (RuntimeException e) {
            return false;
        }
        return true;
    }

    private boolean removeAnimal(MyZoo myZoo) {
        String name = scanner.next();
        try {
            myZoo.removeAnimal(name);
        } catch (RuntimeException e) {
            return false;
        }
        return true;
    }

    private boolean buyOfFood(MyZoo myZoo) {
        FoodType foodType = FoodType.getById(scanner.nextInt());
        int amount = scanner.nextInt();
        try {
            myZoo.addFood(foodType, amount);
        } catch (RuntimeException e) {
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
        } catch (RuntimeException e) {
            return false;
        }
        return true;
    }
}
