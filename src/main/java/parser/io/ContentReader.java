package parser.io;

import com.google.common.collect.Lists;
import parser.data.Building;
import parser.data.Coord;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ContentReader {

    private Map<String, List<Building>> buildingsForTag;

    public void readFileContent(List<String> buildingInfo) {
        buildingsForTag = new HashMap<>();
        Building building = null;
        List<Coord> coords = null;

        for (String line : buildingInfo) {
            if (line.contains("[") || line.contains("]")) { //Linia zawierająca nazwę i numer budynku
                building = new Building();
                coords = new ArrayList<>();

                building.setName(extractBuildingName(line));
                building.setNumbers(extractBuildingNumbers(line));
            } else if (line.contains(",")) {
                coords.add(extractCoords(line));
                if (line.contains(";")) {
                    addBuildingToMap(building);
                }
            }
        }

        new ContentWriter().formatFile(buildingsForTag);
    }

    private String extractBuildingName(String line) {
        int spaceIndex = line.indexOf(" ");
        return line.substring(0, spaceIndex);
    }

    private List<Integer> extractBuildingNumbers(String line) {
        int openingBracketIndex = line.indexOf("[");
        int closingBracketIndex = line.indexOf("]");

        if (!line.contains("-")) { //Budynek posiada 1 numer
            String number = line.substring(openingBracketIndex, closingBracketIndex);
            return Lists.newArrayList(Integer.parseInt(number));
        } else { //Budynek posiada więcej niż 1 numer
            int separatorIndex = line.indexOf("-");
            Integer firstNumber = Integer.parseInt(line.substring(openingBracketIndex, separatorIndex));
            Integer secondNumber = Integer.parseInt(line.substring(separatorIndex+1, closingBracketIndex));
            return IntStream.rangeClosed(firstNumber, secondNumber).boxed().collect(Collectors.toList());
        }
    }

    private Coord extractCoords(String line) {
        int commaIndex = line.indexOf(",");
        int newLineIndex = line.indexOf("\n"); //FIXME czy \n w ogóle jest odczytywany
        String longitude = line.substring(0, commaIndex);
        String latitude = line.substring(commaIndex+2, newLineIndex);

        return new Coord(longitude, latitude);
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
