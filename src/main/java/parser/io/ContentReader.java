package parser.io;

import com.google.common.collect.Lists;
import parser.data.Building;
import parser.data.Coord;

import java.util.ArrayList;
import java.util.List;

public class ContentReader {

    public void readFileContent(List<String> buildingInfo) {
        List<Building> buildings = new ArrayList<>();
        Building building = null;
        List<Coord> coords = null;

        for (String line : buildingInfo) {
            if (line.contains("[") || line.contains("]")) { //Linia zawierająca nazwę i numer budynku
                building = new Building();
                coords = new ArrayList<>();

                building.setName(extractBuildingName(line));
                building.setNumber(extractBuildingNumber(line));
            } else if (line.contains(",")) {
                coords.add(extractCoords(line));
                if (line.contains(";")) {
                    buildings.add(building);
                }
            }
        }

        new ContentWriter().formatFile(buildings);
    }

    private String extractBuildingName(String line) {
        int spaceIndex = line.indexOf(" ");
        return line.substring(0, spaceIndex);
    }

    private List<Integer> extractBuildingNumber(String line) {
        int openingBracketIndex = line.indexOf("[");
        int closingBracketIndex = line.indexOf("]");

        if (!line.contains("-")) { //Budynek posiada 1 numer
            String number = line.substring(openingBracketIndex, closingBracketIndex);
            return Lists.newArrayList(Integer.parseInt(number));
        } else { //Budynek posiada 2 numery
            int separatorIndex = line.indexOf("-");
            String firstNumber = line.substring(openingBracketIndex, separatorIndex);
            String secondNumber = line.substring(separatorIndex+1, closingBracketIndex);
            return Lists.newArrayList(Integer.parseInt(firstNumber), Integer.parseInt(secondNumber));
        }
    }

    private Coord extractCoords(String line) {
        int commaIndex = line.indexOf(",");
        int newLineIndex = line.indexOf("\n"); //FIXME czy \n w ogóle jest odczytywany
        String longitude = line.substring(0, commaIndex);
        String latitude = line.substring(commaIndex+2, newLineIndex);

        return new Coord(longitude, latitude);
    }
}
