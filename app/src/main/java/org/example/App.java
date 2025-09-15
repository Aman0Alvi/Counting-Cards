package org.example;

import java.util.*;
import java.util.function.BiConsumer;

public class App {
    private static final int[] SIZES = {52, 1_000, 10_000, 100_000};
    private static final int TRIALS = 4;

    public static void main(String[] args) {
        Map<String, BiConsumer<List<Integer>, Random>> algs = new LinkedHashMap<>();
        algs.put("RandomKeySort", Shufflers::shuffleByRandomKey);
        algs.put("Remove(O(n^2))", Shufflers::shuffleByRemoving);
        algs.put("FisherYates", Shufflers::fisherYates);

        System.out.println("=== doing quick benchmark (ns) ===");
        for (int n : SIZES) {
            System.out.printf("%nSize n=%d%n", n);
            for (Map.Entry<String, BiConsumer<List<Integer>, Random>> e : algs.entrySet()) {
                String name = e.getKey();
                BiConsumer<List<Integer>, Random> fn = e.getValue();
                System.out.printf("  %-16s : ", name);
                long sum = 0L;
                for (int t = 0; t < TRIALS; t++) {
                    List<Integer> list = Shufflers.range(n);
                    Random rng = new Random(1234L + t); 
                    long start = System.nanoTime();
                    fn.accept(list, rng);
                    long dur = System.nanoTime() - start;
                    sum += dur;
                    System.out.print(dur + (t == TRIALS - 1 ? "" : ", "));
                }
                double avg = sum/(double) TRIALS;
                System.out.printf("  | avg=%.0f%n", avg);
            }
        }
        System.out.println("\n Use the JMH benchmarks: `./gradlew jmh`");
    }
}
