package org.example.advancedAssignment4;

public class Home {
    private int id;
    private int amount;
    private int capacity;
    private boolean isCage;

    public Home(int id, int amount, int capacity, boolean isCage) {
        this.id = id;
        this.amount = amount;
        this.capacity = capacity;
        this.isCage = isCage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isCage() {
        return isCage;
    }

    public void setCage(boolean cage) {
        isCage = cage;
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