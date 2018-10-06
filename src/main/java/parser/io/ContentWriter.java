package parser.io;

import com.google.gson.Gson;
import parser.data.Building;

import java.util.List;

public class ContentWriter {

    public void formatFile(List<Building> buildings) {
        Gson gson = new Gson();
        String json = gson.toJson(buildings);

        new FileWriter().saveFile(json);
    }
}
