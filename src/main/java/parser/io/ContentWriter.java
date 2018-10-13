package parser.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import parser.data.Building;

import java.util.List;
import java.util.Map;

public class ContentWriter {

    public void formatFile(Map<String, List<Building>> buildings) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter fw = new FileWriter();

        buildings.keySet()
                .stream()
                .forEach(t -> {
                    String json = gson.toJson(buildings.get(t));
                    fw.saveFile(json, t);
                });
    }
}
