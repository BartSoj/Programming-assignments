package org.example.advancedAssignment4;

public class Animal {
    private String name;
    private AnimalType type;
    private int homeId;

    public Animal(String name, AnimalType type, int homeId) {
        this.name = name;
        this.type = type;
        this.homeId = homeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AnimalType getType() {
        return type;
    }

    public void setType(AnimalType type) {
        this.type = type;
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
