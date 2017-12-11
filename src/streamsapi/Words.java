package streamsapi;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

/**
 * Created by evami on 11.12.17.
 */
public class Words {

    public static void main(String[] args) {
        File text = new File ("/home/evami/wp.txt");
        try {
            Map<Integer, List<String>> result = Files.lines(text.toPath())
                    .parallel()
                    .map(line -> line.toLowerCase().replaceAll("\\pP", " "))
                    .flatMap(line -> Arrays.stream(line.split(" ")))
                    .map(String::trim)
                    .filter(word -> !"".equals(word))
                    .distinct()
                    .collect(groupingBy(String::length));
            for (Map.Entry<Integer, List<String>> entry : result.entrySet()) {
                System.out.println(entry);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
