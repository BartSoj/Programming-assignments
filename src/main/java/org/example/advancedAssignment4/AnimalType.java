package org.example.advancedAssignment4;

public enum AnimalType {
    LION(1),
    TIGER(2),
    LEOPARD(3),
    ZEBRA(4),
    ANTELOPE(5),
    GIRAFFE(6),
    BEAR(7);

    private final int id;

    AnimalType(int id) {
        this.id = id;
    }

    public static AnimalType getById(int id) {
        for (AnimalType animalType : AnimalType.values()) {
            if (animalType.id == id) {
                return animalType;
            }
        }
        return null;
    }

    public boolean isCarnivore() {
        return this == LION || this == TIGER || this == LEOPARD;
    }

    public boolean isHerbivore() {
        return this == ZEBRA || this == ANTELOPE || this == GIRAFFE;
    }

    public boolean isOmnivore() {
        return this == BEAR;
    }

    public boolean isSolitary() {
        return this == TIGER || this == LEOPARD || this == BEAR;
    }

    public boolean liveInCage() {
        return this == LION || this == TIGER || this == LEOPARD || this == BEAR;
    }

    public boolean eats(FoodType foodType) {
        if (isCarnivore()) {
            return foodType.isMeat();
        } else if (isHerbivore()) {
            return !foodType.isMeat() && !(this == ANTELOPE && foodType == FoodType.CARROTS);
        } else if (isOmnivore()) {
            return foodType.isMeat() || foodType == FoodType.CARROTS;
        }
        return false;

    }
}
