package org.example.jmh;

import org.example.Shufflers;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.*;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
@Warmup(iterations = 1, time = 1, timeUnit = TimeUnit.SECONDS)  
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS) 
@Fork(1)
public class ShuffleBenchmarks {
    @Param({"52", "1000", "10000", "100000"})
    public int n;

    private List<Integer> baseline;
    private Random rng;

    @Setup(Level.Iteration)
    public void setup() {
        baseline = Shufflers.range(n);
        rng = new Random(42); 
    }

    private List<Integer> fresh() {
        return new ArrayList<>(baseline);
    }

    @Benchmark
    public void randomKeySort(Blackhole bh) {
        List<Integer> a = fresh();
        Shufflers.shuffleByRandomKey(a, rng);
        bh.consume(a);
    }

    @Benchmark
    public void removeN2(Blackhole bh) {
        List<Integer> a = fresh();
        Shufflers.shuffleByRemoving(a, rng);
        bh.consume(a);
    }

    @Benchmark
    public void fisherYates(Blackhole bh) {
        List<Integer> a = fresh();
        Shufflers.fisherYates(a, rng);
        bh.consume(a);
    }
}
