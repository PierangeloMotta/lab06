package it.unibo.collections;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Example performance measuring. Use this class as working example of how to
 * measure the time necessary to perform operations on data structures.
 */
public final class Performance {
    public static final int ELEMS = 100_000;
    private final List<Integer> list;
    private long firstStep;
    private long secondStep;

    public Performance(List<Integer> list) {
        this.list = list;
    }
    
    /*
     * Prepare a variable for measuring time
     */
    public void getFirstTime() {
        this.firstStep = System.nanoTime();
    }

    /*
     * Run the benchmark
     */
    public void run() {
        for (int i = 1; i <= ELEMS; i++) {
            list.add(0, i);
        }
    }
    /*
     * read n "times" a value
     */
    public void read(final int times) {
        for (int i = 0; i < times; i++) {
            this.list.get(list.size() / 2);
        }
    }
    /*
     * Compute the time and print result
     */
    public void getSecondTime() {
        this.secondStep = System.nanoTime() - this.firstStep;
    }

    public void printResult(final String kindOfAction) {
        final var millis = TimeUnit.NANOSECONDS.toMillis(this.secondStep);
        System.out.println(kindOfAction + " " + list.size() + " ints in a Set took " + this.secondStep
                + "ns (" + millis + "ms)");
    }
}