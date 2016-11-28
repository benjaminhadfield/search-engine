package com.benhadfield.indexer;

import com.benhadfield.posting.Posting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.TreeMap;

/**
 * The Grouper class is responsible for grouping postings across multiple files by term.
 */

public class Grouper {
    private TreeMap<String, ArrayList<Posting>> invertedIndex = new TreeMap<>();
    private Mapper[] mappers;

    public Grouper(Mapper... mappers) {
        this.mappers = sortMappers(mappers);
        group();
    }

    public TreeMap<String, ArrayList<Posting>> getInvertedIndex() {
        return invertedIndex;
    }

    public void _printInvertedIndex() {
        System.out.println("Term\tPostings");
        invertedIndex.forEach((term, postings) -> {
            System.out.print(term);
            System.out.print(" \t");
            for (Posting posting : postings) {
                System.out.print(posting.toString());
                System.out.print(", ");
            }
            System.out.println();
        });
    }

    private void group() {
        // iterates through all Mapper objects and assigns them to the invertedIndex
        for (Mapper mapper : mappers) {
            TreeMap<String, Posting> map = mapper.getMap();
            map.forEach(this::addToGroup);
        }
    }

    private Mapper[] sortMappers(Mapper[] mappers) {
        // sort mapper objects according to fileID
        Arrays.sort(mappers);
        return mappers;
    }

    private void addToGroup(String term, Posting posting) {
        if (invertedIndex.containsKey(term)) {
            invertedIndex.get(term).add(posting);
        } else {
            invertedIndex.put(term, new ArrayList<>(Collections.singletonList(posting)));
        }
    }
}
