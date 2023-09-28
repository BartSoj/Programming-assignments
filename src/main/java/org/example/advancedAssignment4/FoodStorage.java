package org.example.advancedAssignment4;

public class FoodStorage {
    private FoodType type;
    private int amount;
    private int capacity;

    public FoodStorage(FoodType type, int amount, int capacity) {
        this.type = type;
        this.amount = amount;
        this.capacity = capacity;
    }

    public FoodType getType() {
        return type;
    }

    public void setType(FoodType type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        if (amount > capacity) {
            throw new RuntimeException("Not enough capacity in food storage");
        }
        this.amount = amount;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
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
