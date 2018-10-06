package parser.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

public class FileWriter {

    private String filePath = "D:\\PG\\INZYNIERKA\\Polygon-parser pliki";

    private String filenameStart = "budynki-polygony";
    private String fileExtension = ".txt";

    public void saveFile(String json) {
        try {
            Files.write(Paths.get(filePath, prepareFilename()), json.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String prepareFilename() {
        StringBuilder filename = new StringBuilder();
        filename.append(filenameStart);
        filename.append("_");
        filename.append(new Date().toString());
        filename.append(fileExtension);

        return filename.toString();
    }
}
