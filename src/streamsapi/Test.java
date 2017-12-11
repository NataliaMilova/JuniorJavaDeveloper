package streamsapi;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

/**
 * Created by evami on 11.12.17.
 */
public class Test {

    public static void main(String[] args) {
        int len;
        Map<Integer, Set<String>> result = new HashMap<>();
        try {
            File text = new File("/home/evami/wp.txt");
            List<String> lines = Files.readAllLines(text.toPath());
            for (String line: lines){
                String[] wordSplit =
                        line.toLowerCase()
                                .replaceAll("\\p{Punct}", " ")
                                .trim()
                                .split("\\s");

                for (String s : wordSplit) {
                    if (s.length() > 0) {
                        len = s.length();
                        result.computeIfAbsent(len, k -> new HashSet<>());
                        result.get(len).add(s);
                    }
                }
            }
            for (Map.Entry<Integer, Set<String>> entry: result.entrySet()) {
                System.out.println(entry);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
