package org.example.advancedAssignment4;

public class FoodStorage {
    private final FoodType type;
    private int amount;
    private final int capacity;

    public FoodStorage(FoodType type, int amount, int capacity) {
        this.type = type;
        this.amount = amount;
        this.capacity = capacity;
    }

    public FoodType getType() {
        return type;
    }

    public int getAmount() {
        return amount;
    }

    public void addAmount(int amount) {
        this.amount += amount;
    }

    public void removeAmount(int amount) {
        this.amount -= amount;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return "FoodStorage{" +
                "type=" + type +
                ", amount=" + amount +
                ", capacity=" + capacity +
                '}';
    }
}
