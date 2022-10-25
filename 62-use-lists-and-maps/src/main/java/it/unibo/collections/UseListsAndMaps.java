package it.unibo.collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Example class using {@link List} and {@link Map}.
 *
 */
public final class UseListsAndMaps {
    private static int MIN = 1000;
    private static int MAX = 2000;
    private static int ELEMS = Performance.ELEMS;

    private UseListsAndMaps() {
    }

    /**
     * @param s
     *          unused
     */
    public static void main(final String... s) {
        /*
         * 1) Create a new ArrayList<Integer>, and populate it with the numbers
         * from 1000 (included) to 2000 (excluded).
         */
        final List<Integer> listInt = new ArrayList<>();
        for (int i = MIN; i < MAX; i++) {
            listInt.add(i);
        }

        /*
         * 2) Create a new LinkedList<Integer> and, in a single line of code
         * without using any looping construct (for, while), populate it with
         * the same contents of the list of point 1.
         */
        final List<Integer> linkedInt = new LinkedList<>(listInt);

        /*
         * 3) Using "set" and "get" and "size" methods, swap the first and last
         * element of the first list. You can not use any "magic number".
         * (Suggestion: use a temporary variable)
         */
        int temp = listInt.get(listInt.size() - 1);
        listInt.set(listInt.size() - 1, listInt.get(0));
        listInt.set(0, temp);

        /*
         * 4) Using a single for-each, print the contents of the arraylist.
         */
        for (final int integer : listInt) {
            System.out.println("Int: " + integer);
        }
        /*
         * 5) Measure the performance of inserting new elements in the head of
         * the collection: measure the time required to add 100.000 elements as
         * first element of the collection for both ArrayList and LinkedList,
         * using the previous lists. In order to measure times, use as example
         * TestPerformance.java.
         */

        long time = System.nanoTime();
        for (int i = 0; i < ELEMS; i++) {
            listInt.add(0, i);
        }
        time = System.nanoTime() - time;
        var millis = TimeUnit.NANOSECONDS.toMillis(time);
        System.out.println(// NOPMD
                "Inserting "
                        + listInt.size()
                        + " ints in arrayInt took "
                        + time
                        + "ns ("
                        + millis
                        + "ms)");

        time = System.nanoTime();
        for (int i = 0; i < ELEMS; i++) {
            linkedInt.add(0, i);
        }

        time = System.nanoTime() - time;
        millis = TimeUnit.NANOSECONDS.toMillis(time);
        System.out.println(// NOPMD
                "Inserting "
                        + linkedInt.size()
                        + " ints in linkedInt took "
                        + time
                        + "ns ("
                        + millis
                        + "ms)");

        // New samples
        System.out.print("####### ");
        ArrayList<Integer> a = new ArrayList<>();
        Performance pa = new Performance(a);
        pa.getFirstTime();
        pa.run();
        pa.getSecondTime();
        pa.printResult("Adding");

        System.out.print("@@@@@@@ ");
        LinkedList<Integer> l = new LinkedList<>();
        Performance pl = new Performance(l);
        pl.getFirstTime();
        pl.run();
        pl.getSecondTime();
        pl.printResult("Adding");

        /*
         * 6) Measure the performance of reading 1000 times an element whose
         * position is in the middle of the collection for both ArrayList and
         * LinkedList, using the collections of point 5. In order to measure
         * times, use as example PerfTest.java.
         */
        System.out.print("####### ");
        pa.getFirstTime();
        pa.read(1000);
        pa.getSecondTime();
        pa.printResult("Reading");

        System.out.print("@@@@@@@ ");
        pl.getFirstTime();
        pl.read(1000);
        pl.getSecondTime();
        pl.printResult("Reading");

        /*
         * 7) Build a new Map that associates to each continent's name its
         * population:
         *
         * Africa -> 1,110,635,000
         *
         * Americas -> 972,005,000
         *
         * Antarctica -> 0
         *
         * Asia -> 4,298,723,000
         *
         * Europe -> 742,452,000
         *
         * Oceania -> 38,304,000
         */
        final Map<String, Long> world = new HashMap<>();

        world.put("Africa", 1_110_635_000L);
        world.put("Americas", 972_005_000L);
        world.put("Antarctica", 0L);
        world.put("Asia", 4_298_723_000L);
        world.put("Europe", 742_452_000L);
        world.put("Oceania", 38_304_000L);
        /*
         * 8) Compute the population of the world
         */
        long total = 0L;
        for (final long val : world.values()) {
            total += val;
        }
        System.out.println("World Population: " + total);
    }
}
