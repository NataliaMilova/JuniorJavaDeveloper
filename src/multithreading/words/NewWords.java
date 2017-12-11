package multithreading.words;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by evami on 05.12.17.
 */
public class NewWords {
    private int processors = 4;
    private ExecutorService poolStrings = Executors.newFixedThreadPool(processors);
    private List<Future<HashMap<String, Integer>>> hashMaps = new ArrayList<>();
    private HashMap<String, Integer> globalMap = new HashMap<>();
    private List<List<String>> subLines = new ArrayList<>();

    private void start() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        try {
            File text = new File("/home/evami/wp.txt");
            List<String> lines = Files.readAllLines(text.toPath());
            int len = lines.size() / processors;
            int pos = 0;
            for (int i = 0; i < processors; i++) {
                if (i == processors - 1)
                    subLines.add(lines.subList(pos, lines.size() - 1));
                else {
                    subLines.add(lines.subList(pos, len - 1));
                    pos = len - 1;
                    len += lines.size() / processors;
                }
            }
            for (List<String> subLine : subLines) {
                Future<HashMap<String, Integer>> result = poolStrings.submit(new Callable<HashMap<String, Integer>>() {
                    private HashMap<String, Integer> words = new HashMap<>();

                    @Override
                    public HashMap<String, Integer> call() throws Exception {
                        for (String line : subLine) {
                            String[] wordSplit =
                                    line.toLowerCase()
                                            .replaceAll("\\p{Punct}", " ")
                                            .trim()
                                            .split("\\s");
                            for (String s : wordSplit) {
                                if (s.length() > 0)
                                    if (words.get(s) != null)
                                        words.put(s, words.get(s) + 1);
                                    else
                                        words.put(s, 1);
                            }
                        }
                        return words;
                    }
                });
                hashMaps.add(result);
            }
            for (int i = 0; i < processors; i++)
                hashMaps.get(i).get().forEach((k, v) -> globalMap.merge(k, v, (BinaryOperator<Integer>) (integer, integer2) -> integer + integer2));
            List<Map.Entry<String, Integer>> result = new ArrayList<>(globalMap.entrySet());
            Collections.sort(result, (o1, o2) -> o2.getValue() - o1.getValue());
            int tmp = 0;
            for (Map.Entry<String, Integer> word : result) {
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
        poolStrings.shutdown();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
       new NewWords().start();
    }
}
