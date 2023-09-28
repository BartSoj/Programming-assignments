package org.example.advancedAssignment4;

import java.util.ArrayList;
import java.util.List;

public class MyZoo {

    private final List<FoodStorage> foodStorages = new ArrayList<>();
    private final List<Animal> animals = new ArrayList<>();
    private final List<Home> homes = new ArrayList<>();

    public MyZoo() {

    }

    private FoodStorage findStorage(FoodType foodType) {
        for (FoodStorage foodStorage : foodStorages) {
            if (foodStorage.getType() == foodType) {
                return foodStorage;
            }
        }
        return null;
    }

    private FoodStorage findStorageOrThrow(FoodType foodType) {
        FoodStorage foodStorage = findStorage(foodType);
        if (foodStorage == null) {
            throw new RuntimeException("Food storage not found");
        }
        return foodStorage;
    }

    private Animal findAnimal(String name) {
        for (Animal animal : animals) {
            if (animal.getName().equals(name)) {
                return animal;
            }
        }
        return null;
    }

    private Animal findAnimalOrThrow(String name) {
        Animal animal = findAnimal(name);
        if (animal == null) {
            throw new RuntimeException("Animal not found");
        }
        return animal;
    }

    private Home findHome(int homeId) {
        for (Home home : homes) {
            if (home.getId() == homeId) {
                return home;
            }
        }
        return null;
    }

    private Home findHomeOrThrow(int homeId) {
        Home home = findHome(homeId);
        if (home == null) {
            throw new RuntimeException("Home not found");
        }
        return home;
    }

    public void addFoodStorage(FoodStorage foodStorage) {
        if (findStorage(foodStorage.getType()) != null) {
            throw new RuntimeException("Food storage already exists");
        }
        foodStorages.add(foodStorage);
    }

    public void addAnimal(Animal animal) {
        if (findAnimal(animal.getName()) != null) {
            throw new RuntimeException("Animal already exists");
        }

        Home home = findHomeOrThrow(animal.getHomeId());
        checkHomeConditionsForNewAnimal(animal, home);
        home.increaseAmount();
        animals.add(animal);
    }

    public void addHome(Home home) {
        if (findHome(home.getId()) != null) {
            throw new RuntimeException("Home already exists");
        }
        homes.add(home);
    }

    public void moveAnimal(String name, int homeId) {
        Animal animal = findAnimalOrThrow(name);
        Home home = findHomeOrThrow(homeId);
        checkHomeConditionsForNewAnimal(animal, home);
        home.increaseAmount();
        animal.setHomeId(homeId);
        Home prevHome = findHomeOrThrow(animal.getHomeId());
        prevHome.decreaseAmount();
    }

    private void checkHomeConditionsForNewAnimal(Animal animal, Home home) {
        if (home.getAmount() >= home.getCapacity()) {
            throw new RuntimeException("Home is full");
        }
        if (home.isCage() && !animal.getType().liveInCage()) {
            throw new RuntimeException("Animal can't live in cage");
        }
        for (Animal animalInHome : animals) {
            if (animalInHome.getHomeId() == animal.getHomeId() && !animal.canLiveWith(animalInHome)) {
                throw new RuntimeException("Animal can't live with other animal");
            }
        }
    }

    public void removeAnimal(String name) {
        Animal animal = findAnimalOrThrow(name);
        Home home = findHomeOrThrow(animal.getHomeId());
        home.decreaseAmount();
        animals.remove(animal);
    }

    public void addFood(FoodType foodType, int amount) {
        FoodStorage foodStorage = findStorageOrThrow(foodType);
        if (foodStorage.getAmount() + amount > foodStorage.getCapacity()) {
            throw new RuntimeException("Not enough capacity in food storage");
        }
        foodStorage.addAmount(amount);
    }

    public void feedOfFood(FoodType foodType, int amount, int homeId) {
        findHomeOrThrow(homeId);
        for (Animal animal : animals) {
            if (animal.getHomeId() == homeId && !animal.eats(foodType)) {
                throw new RuntimeException("Animal can't eat this food");
            }
        }

        FoodStorage foodStorage = findStorageOrThrow(foodType);
        if (foodStorage.getAmount() < amount) {
            throw new RuntimeException("Not enough food");
        }
        foodStorage.removeAmount(amount);
    }
}
