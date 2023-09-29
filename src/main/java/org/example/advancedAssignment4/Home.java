package org.example.advancedAssignment4;

public class Home {
    private final int id;
    private int amount;
    private final int capacity;
    private final boolean isCage;

    public Home(int id, int capacity, boolean isCage) {
        this.id = id;
        this.capacity = capacity;
        this.isCage = isCage;
    }

    public int getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }


    public void increaseAmount() {
        this.amount++;
    }

    public void decreaseAmount() {
        this.amount--;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isCage() {
        return isCage;
    }

    @Override
    public String toString() {
        return "Home{" +
                "id=" + id +
                ", amount=" + amount +
                ", capacity=" + capacity +
                ", isCage=" + isCage +
                '}';
    }
}