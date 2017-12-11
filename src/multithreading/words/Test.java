package multithreading.words;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

/**
 * Created by evami on 05.12.17.
 */
public class Test {

    private HashMap<String, Integer> globalMap = new HashMap<>();

    private void start(){
        long start = System.currentTimeMillis();
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
                    if (s.length() > 0)
                        if (globalMap.get(s) != null)
                            globalMap.put(s, globalMap.get(s) + 1);
                        else
                            globalMap.put(s, 1);
                }
            }
            List<Map.Entry<String, Integer>> result = new ArrayList<>(globalMap.entrySet());
            Collections.sort(result, new Comparator<Map.Entry<String, Integer>>() {
                @Override
                public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                    return o2.getValue() - o1.getValue();
                }
            });
            int tmp = 0;
            for (Map.Entry<String, Integer> word : result){
                System.out.println(word);
                ++tmp;
                if (tmp == 10)
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public static void main(String[] args) {
        new Test().start();
    }
}
