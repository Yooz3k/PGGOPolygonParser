package parser.io;

import com.google.common.collect.Lists;
import parser.data.Building;
import parser.data.Coord;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ContentReader {

    private Map<String, List<Building>> buildingsForTag;

    private int generatedId = 10000;

    public void readFileContent(List<String> buildingInfo) {
        buildingsForTag = new HashMap<>();
        Building building = null;
        List<Coord> coords = null;

        for (String line : buildingInfo) {
            if (line.contains("[") || line.contains("]")) { //Linia zawierająca nazwę i numer budynku
                coords = new ArrayList<>();

                building = readBuilding(line);

            } else if (line.contains(",")) {
                coords.add(extractCoords(line));
                if (line.contains(";")) {
                    building.setCoords(coords);
                    addBuildingToMap(building);
                }
            }
        }

        new ContentWriter().formatFile(buildingsForTag);
    }

    private Building readBuilding(String line) {
        Building building = new Building();

        building.setName(extractBuildingName(line));
//        building.setNumbers(extractBuildingNumbers(line));
        building.setTag(extractBuildingTag(line));
        building.setId(generateBuildingId());

        System.out.println("BUILDING: " + building.getName());

        return building;
    }

    private String extractBuildingName(String line) {
        int spaceIndex = line.indexOf(' ');
        return line.substring(0, spaceIndex).replaceAll("_", " ");
    }

    private List<Integer> extractBuildingNumbers(String line) {
        int openingBracketIndex = line.indexOf('[');
        int pipeIndex = line.indexOf('|');

        if (!line.contains("-")) { //Budynek posiada 1 numer
            String number = line.substring(openingBracketIndex+1, pipeIndex);
            return Lists.newArrayList(Integer.parseInt(number));
        } else { //Budynek posiada więcej niż 1 numer
            int separatorIndex = line.indexOf('-');
            Integer firstNumber = Integer.parseInt(line.substring(openingBracketIndex+1, separatorIndex));
            Integer secondNumber = Integer.parseInt(line.substring(separatorIndex+1, pipeIndex));
            return IntStream.rangeClosed(firstNumber, secondNumber).boxed().collect(Collectors.toList());
        }
    }

    private Coord extractCoords(String line) {
        int commaIndex = line.indexOf(',');
        int semicolonIndex = line.indexOf(';');

        BigDecimal longitude = new BigDecimal(line.substring(0, commaIndex));
        BigDecimal latitude;

        if (semicolonIndex != -1)
            latitude = new BigDecimal(line.substring(commaIndex+2, semicolonIndex));
        else
            latitude = new BigDecimal(line.substring(commaIndex+2));

        return new Coord(longitude, latitude);
    }

    private String extractBuildingTag(String line) {
        int pipeIndex = line.indexOf('|');
        int closingBracketIndex = line.indexOf(']');

        return line.substring(pipeIndex+1, closingBracketIndex).toUpperCase();
    }

    private int generateBuildingId() {
        return generatedId++;
    }

    private void addBuildingToMap(Building building) {
        String buildingTag = building.getTag();

        if (buildingsForTag.containsKey(buildingTag)) {
            List<Building> buildings = buildingsForTag.get(buildingTag);
            buildings.add(building);
            buildingsForTag.put(buildingTag, buildings);
        } else {
            List<Building> buildings = Lists.newArrayList(building);
            buildingsForTag.put(buildingTag, buildings);
        }
    }
}
