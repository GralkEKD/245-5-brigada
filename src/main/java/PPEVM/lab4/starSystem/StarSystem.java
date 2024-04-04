package PPEVM.lab4.starSystem;

import java.util.ArrayList;

public class StarSystem {
    private final Star starName;
    private final ArrayList<Planet> planets;
    public StarSystem(Star starName) {
        this.starName = starName;
        planets = new ArrayList<>();
    }

    public void addPlanet(String planet) {
        planets.add(new Planet(planet));
    }

    public int printNumberOfPlanets() { return planets.size(); }

    public String getStarName() {
        return starName.getName();
    }
}