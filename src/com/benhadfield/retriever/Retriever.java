package com.benhadfield.retriever;

import com.benhadfield.indexer.Reducer;

import java.util.HashMap;

/**
 * The Retriever class is responsible for mapping terms to file - line offset pairs, so search terms can be quickly
 * found. The map of terms to term locations could be stored in memory.
 */

public class Retriever {
    // choosing HashMap because chance of collisions is low, therefore will get close to O(1) time
    private static HashMap<String, TermLocation> locations = new HashMap<>();

    public static void addLocation(String term, String path, long offset) {
        TermLocation location = new TermLocation(path, offset);
        locations.put(term, location);
    }

    public static void _printLocations() {
        System.out.println("String\tTerm Location");
        locations.forEach((term, location) -> {
            System.out.print(term);
            System.out.print("\t");
            System.out.println(location.toString());
        });
    }

    public static TermLocation get(String term) {
        return locations.get(term);
    }
}
