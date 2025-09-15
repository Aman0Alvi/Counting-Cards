package org.example;

import java.util.*;

public final class Shufflers {

    private Shufflers() {}

    public static <T> void shuffleByRandomKey(List<T> list, Random rng) {
        int n = list.size();
        List<Map.Entry<Double,T>> keyed = new ArrayList<>(n);
        for (T t : list) keyed.add(new AbstractMap.SimpleEntry<>(rng.nextDouble(), t));
        keyed.sort(Comparator.comparingDouble(Map.Entry::getKey));
        for (int i = 0; i < n; i++) list.set(i, keyed.get(i).getValue());
    }

    public static <T> void shuffleByRemoving(List<T> list, Random rng) {
        List<T> current = new ArrayList<>(list);
        List<T> evaluted = new ArrayList<>(current.size());
        while (!current.isEmpty()) {
            int j = rng.nextInt(current.size());
            evaluted.add(current.remove(j));
        }
        for (int i = 0; i < evaluted.size(); i++) list.set(i, evaluted.get(i));
    }

    public static <T> void fisherYates(List<T> list, Random rng) {
        for (int i = list.size() - 1; i > 0; i--) {
            int j = rng.nextInt(i + 1);      
            Collections.swap(list, i, j);
        }
    }

    public static List<Integer> range(int n) {
        List<Integer> a = new ArrayList<>(n);
        for (int i = 0; i < n; i++) a.add(i);
        return a;
    }
}
