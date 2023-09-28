package org.example.advancedAssignment4;

import java.util.ArrayList;
import java.util.List;

public class MyZoo {

    private final List<FoodStorage> foodStorages = new ArrayList<>();
    private final List<Animal> animals = new ArrayList<>();
    private final List<Home> homes = new ArrayList<>();

    public MyZoo() {

    }

    public void addFoodStorage(FoodStorage foodStorage) {
        for (FoodStorage foodStorage1 : foodStorages) {
            if (foodStorage1.getType() == foodStorage.getType()) {
                throw new RuntimeException("Food storage already exists");
            }
        }
        foodStorages.add(foodStorage);
    }

    public void addHome(Home home) {
        for (Home home1 : homes) {
            if (home1.getId() == home.getId()) {
                throw new RuntimeException("Home already exists");
            }
        }
        homes.add(home);
    }

    public void addAnimal(Animal animal) {
        for (Animal animal1 : animals) {
            if (animal1.getName().equals(animal.getName())) {
                throw new RuntimeException("Animal already exists");
            }
        }

        for (Home home : homes) {
            if (home.getId() == animal.getHomeId()) {
                checkHomeConditionsForNewAnimal(animal, home);
                home.setAmount(home.getAmount() + 1);
                animals.add(animal);
                return;
            }
        }
        throw new RuntimeException("Home not found");
    }

    public void moveAnimal(String name, int homeId) {
        for (Animal animal : animals) {
            if (animal.getName().equals(name)) {
                for (Home home : homes) {
                    if (home.getId() == homeId) {
                        checkHomeConditionsForNewAnimal(animal, home);
                        home.setAmount(home.getAmount() + 1);
                        animal.setHomeId(homeId);
                        for (Home prevHome : homes) {
                            if (prevHome.getId() == animal.getHomeId()) {
                                prevHome.setAmount(home.getAmount() - 1);
                            }
                        }
                        return;
                    }
                }
                throw new RuntimeException("Home not found");
            }
        }
        throw new RuntimeException("Animal not found");
    }

    private void checkHomeConditionsForNewAnimal(Animal animal, Home home) {
        if (home.getAmount() >= home.getCapacity()) {
            throw new RuntimeException("Home is full");
        }
        if (home.isCage() && !animal.getType().liveInCage()) {
            throw new RuntimeException("Animal can't live in cage");
        }
        for (Animal animalInHome : animals) {
            if (animalInHome.getHomeId() == animal.getHomeId()) {
                if (animalInHome.getType().isHerbivore() && !animal.getType().isHerbivore()) {
                    throw new RuntimeException("Animal can't live with herbivore");
                }
                if ((animalInHome.getType().isCarnivore() || animalInHome.getType().isOmnivore()) && animal.getType() != animalInHome.getType()) {
                    throw new RuntimeException("Animal can't live with other carnivore or omnivore");
                }
                if (animalInHome.getType().isSolitary()) {
                    throw new RuntimeException("Animal can't live with solitary");
                }
            }
        }
    }

    public void removeAnimal(String name) {
        for (Animal animal : animals) {
            if (animal.getName().equals(name)) {
                for (Home home : homes) {
                    if (home.getId() == animal.getHomeId()) {
                        home.setAmount(home.getAmount() - 1);
                        animals.remove(animal);
                        return;
                    }
                }
                throw new RuntimeException("Home not found");
            }
        }
        throw new RuntimeException("Animal not found");
    }

    public void addFood(FoodType foodType, int amount) {
        for (FoodStorage foodStorage : foodStorages) {
            if (foodStorage.getType() == foodType) {
                if (foodStorage.getAmount() + amount > foodStorage.getCapacity()) {
                    throw new RuntimeException("Food storage is full");
                }
                foodStorage.setAmount(foodStorage.getAmount() + amount);
                return;
            }
        }
        throw new RuntimeException("Food storage not found");
    }

    public void feedOfFood(FoodType foodType, int amount, int homeId) {
        boolean homeExisits = false;
        for (Home home : homes) {
            if (home.getId() == homeId) {
                homeExisits = true;
                for (Animal animal : animals) {
                    if (animal.getHomeId() == homeId && !animal.getType().eats(foodType)) {
                        throw new RuntimeException("Animal can't eat this food");
                    }
                }
                break;
            }
        }
        if (!homeExisits) {
            throw new RuntimeException("Home not found");
        }
        for (FoodStorage foodStorage : foodStorages) {
            if (foodStorage.getType() == foodType) {
                if (foodStorage.getAmount() >= amount) {
                    foodStorage.setAmount(foodStorage.getAmount() - amount);
                }
                throw new RuntimeException("Not enough food");
            }
        }
        throw new RuntimeException("Food storage not found");
    }
}
