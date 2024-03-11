package PPEVM.lab4.starSystem;

import java.util.ArrayList;

public class Planet {
    private String name;
    private final ArrayList<Moon> moons;
    public Planet(String name) {
        this.name = name;
        moons = new ArrayList<>();
    }

    public void addMoon(String moon){
        moons.add(new Moon(moon));
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}