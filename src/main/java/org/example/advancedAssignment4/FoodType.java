package org.example.advancedAssignment4;

public enum FoodType {
    HAY(1),
    CORN(2),
    GRAIN(3),
    CARROTS(4),
    CHICKEN(5),
    BEEF(6);

    private final int id;

    FoodType(int id) {
        this.id = id;
    }

    public static FoodType getById(int id) {
        for (FoodType foodType : FoodType.values()) {
            if (foodType.id == id) {
                return foodType;
            }
        }
        return null;
    }

    public boolean isMeat() {
        return this == CHICKEN || this == BEEF;
    }
}
