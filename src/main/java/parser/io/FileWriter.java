package parser.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

public class FileWriter {

    private static final Logger LOGGER = Logger.getLogger(FileWriter.class.getName());

    private static final String FILE_PATH = "G:\\PG\\INZYNIERKA\\Polygon-parser-pliki";
    private static final String DIRECTORY_PREFIX = "JSON_";
    private static final String FILENAME = "building_displays";
//    private String fileExtension = ".txt";

    public void saveFile(String json, String directoryTag) {
        Path directory = prepareDirectory(directoryTag);

        System.out.println("DIRECTORY TO_STRING: " + directory.toString());

        try {
            Files.write(Paths.get(directory.toString(), FILENAME), json.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Path prepareDirectory(String directoryTag) {
        StringBuilder dir = new StringBuilder();
        dir.append(FILE_PATH);
        dir.append("\\");
        dir.append(DIRECTORY_PREFIX);
        dir.append(directoryTag);

        String dirName = dir.toString().toUpperCase();

        Path directory = Paths.get(dirName);
        createDirectory(directory);

        return directory;
    }

    private void createDirectory(Path directory) {
        if (Files.exists(directory)) {
            LOGGER.info("Directory already exists!");
        } else {
            try {
                Files.createDirectory(directory);
            } catch (IOException e) {
                LOGGER.warning("Failed to create directory!");
                e.printStackTrace();
            }
        }
    }
/*
    private String prepareFilename() {
        StringBuilder filename = new StringBuilder();
        filename.append(filenamePrefix);
        filename.append("_");
        filename.append(new Date().toString());
//        filename.append(fileExtension);

        return filename.toString();
    }*/
}
