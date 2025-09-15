package org.example;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ShufflersTest {
    @Test
    void fisherYatesKeepsElements() {
        List<Integer> a = Shufflers.range(1000);
        List<Integer> b = new ArrayList<>(a);
        Shufflers.fisherYates(b, new Random(1));
        Collections.sort(b);
        assertEquals(a, b);
    }

    @Test
    void removeKeepsElements() {
        List<Integer> a = Shufflers.range(200);
        List<Integer> b = new ArrayList<>(a);
        Shufflers.shuffleByRemoving(b, new Random(2));
        Collections.sort(b);
        assertEquals(a, b);
    }

    @Test
    void randomKeySortKeepsElements() {
        List<Integer> a = Shufflers.range(200);
        List<Integer> b = new ArrayList<>(a);
        Shufflers.shuffleByRandomKey(b, new Random(3));
        Collections.sort(b);
        assertEquals(a, b);
    }
}
