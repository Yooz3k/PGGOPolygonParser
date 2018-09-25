package parser.io;

import parser.data.Building;

import java.util.ArrayList;
import java.util.List;

public class ContentWriter {

    private List<Building> buildings = new ArrayList<>();

    public void addBuilding(Building building) {
        buildings.add(building);
    }

    public void saveFile() {
        new FileWriter().saveFile(buildings);
    }
}
