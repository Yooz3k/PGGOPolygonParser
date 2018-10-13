package parser.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileReader {

    private final static String FILE_PATH = "G:\\PG\\INZYNIERKA\\Polygon-parser-pliki";
    private final static String FILENAME = "budynki-wspolrzedne.txt";

    public void readFile() {
        Path path = Paths.get(FILE_PATH, FILENAME);

        try {
            List<String> coords = Files.readAllLines(path);

            new ContentReader().readFileContent(coords);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}