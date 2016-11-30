package com.benhadfield.retriever;

import com.benhadfield.file.File;

import java.io.IOException;
import java.util.HashMap;

/**
 * The Retriever class is responsible for mapping terms to file - line offset pairs, so search terms can be quickly
 * found. The map of terms to term locations could be stored in memory.
 */

public class Retriever {
    // choosing HashMap because chance of collisions is low, therefore we should get close to O(1) time
    private static HashMap<String, TermLocation> locations = new HashMap<>();

    public static void addLocation(String term, String path, long offset) {
        TermLocation location = new TermLocation(path, offset);
        locations.put(term, location);
    }

    public static TermLocation getLocation(String term) {
        return locations.get(term);
    }

    public static String get(String term) {
        if(getLocation(term) != null) {
            TermLocation location = getLocation(term);
            try {
                return readAtLocation(location.getPath(), location.getOffset());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void _printLocations() {
        System.out.println("String\tTerm Location");
        locations.forEach((term, location) -> {
            System.out.print(term);
            System.out.print("\t");
            System.out.println(location.toString());
        });
    }

    private static String readAtLocation(String path, long offset) throws IOException, IndexOutOfBoundsException {
        File file = new File(path);
        return file.readLine(offset);
    }
}
