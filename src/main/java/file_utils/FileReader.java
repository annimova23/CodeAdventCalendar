package file_utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileReader {
    private static final String FILE_NAME = "src\\main\\resources\\calories.txt";

    public static List<String> readFile() {
        try (Stream<String> stream = Files.lines(Paths.get(FILE_NAME))) {

            return stream.collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
