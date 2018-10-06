package parser.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileReader {

    private String directory = "D:\\PG\\INZYNIERKA\\Polygon-parser pliki";

    private String fileName = "budynki-wspolrzedne.txt";

    public void readFile() {
        Path path = Paths.get(directory, fileName);

        try {
            List<String> coords = Files.readAllLines(path);

            new ContentReader().readFileContent(coords);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}