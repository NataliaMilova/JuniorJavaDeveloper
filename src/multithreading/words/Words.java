package multithreading.words;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.function.BinaryOperator;

/**
 * Created by evami on 03.12.17.
 */
public class Words {

    private final BlockingDeque<String> strings = new LinkedBlockingDeque<>();
    private final BlockingDeque<HashMap<String, Integer>> hashMaps = new LinkedBlockingDeque<>();
    private final List<Counter> listofCounters = new ArrayList<>();
    private final HashMap<String, Integer> globalMap = new HashMap<>();
    private final String STOP = new String();

    private void start(){
        int processors = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i < processors; ++i)
            listofCounters.add(new Counter());
        for (Counter counter: listofCounters){
            counter.start();
        }
        long start = System.currentTimeMillis();
        try {
            File text = new File("/home/evami/wp.txt");
            List<String> lines = Files.readAllLines(text.toPath());
            for (String line: lines){
                strings.addLast(line);
            }
            strings.addLast(STOP);
            int cnt = 0;
            while (!Thread.currentThread().isInterrupted()){
                try {
                    HashMap<String, Integer> task = hashMaps.takeFirst();
                    if (task == null){
                        System.err.println("Error in some counter");
                        return;
                    }
                    else{
                        task.forEach((k, v) -> globalMap.merge(k, v, (BinaryOperator<Integer>) (integer, integer2) -> integer + integer2));
                        cnt++;
                    }
                    if (cnt == processors)
                        break;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
            }
            List<Map.Entry<String, Integer>> result = new ArrayList<>(globalMap.entrySet());
            Collections.sort(result, (o1, o2) -> o2.getValue() - o1.getValue());
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
        new Words().start();
    }

    private class Counter extends Thread{

        private HashMap<String, Integer> words = new HashMap<>();

        @Override
        public void run() {
            while (!isInterrupted()){
                try {
                    String task = strings.takeFirst();
                    if (task != Words.this.STOP) {
                        String[] wordSplit =
                                task.toLowerCase()
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
                    else {
                        Words.this.strings.addLast(task);
                        Words.this.hashMaps.addLast(words);
                        return;
                    }
                } catch (InterruptedException e) {
                    Words.this.hashMaps.addLast(null);
                    interrupt();
                    e.printStackTrace();
                }
            }
        }
    }
}
