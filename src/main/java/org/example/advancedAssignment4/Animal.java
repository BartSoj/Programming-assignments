package org.example.advancedAssignment4;

public class Animal {
    private final String name;
    private final AnimalType type;
    private int homeId;

    public Animal(String name, AnimalType type, int homeId) {
        this.name = name;
        this.type = type;
        this.homeId = homeId;
    }

    public boolean canLiveWith(Animal animal) {
        return (!type.isHerbivore() || animal.getType().isHerbivore())
                && (!animal.getType().isHerbivore() || type.isHerbivore())
                && (type.isHerbivore() || animal.getType() == type)
                && !type.isSolitary()
                && !animal.getType().isSolitary();
    }

    public boolean eats(FoodType foodType) {
        if (type.isCarnivore()) {
            return foodType.isMeat();
        } else if (type.isHerbivore()) {
            return !foodType.isMeat() && !(type == AnimalType.ANTELOPE && foodType == FoodType.CARROTS);
        }
        return foodType.isMeat() || foodType == FoodType.CARROTS;
    }

    public String getName() {
        return name;
    }

    public AnimalType getType() {
        return type;
    }

    public int getHomeId() {
        return homeId;
    }

    public void setHomeId(int homeId) {
        this.homeId = homeId;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", homeId=" + homeId +
                '}';
    }
}
