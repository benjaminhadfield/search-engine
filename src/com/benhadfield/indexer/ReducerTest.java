package com.benhadfield.indexer;

import com.benhadfield.posting.Posting;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;


class ReducerTest {
    private Reducer reducer;
    private static TreeMap<String, ArrayList<Posting>> invertedIndex = new TreeMap<>();

    private static void setUpInvertedIndex() {
        invertedIndex.clear();
        invertedIndex.put(
                "token_1",
                new ArrayList<>(Arrays.asList(
                        new Posting(0, 3),
                        new Posting(1, 1))));
        invertedIndex.put(
                "token_2",
                new ArrayList<>(Arrays.asList(
                        new Posting(1, 1),
                        new Posting(2, 2),
                        new Posting(3, 1))));
        invertedIndex.put(
                "token_3",
                new ArrayList<>(Arrays.asList(
                        new Posting(1, 1),
                        new Posting(2, 2))));
    }

    @BeforeEach
    void setUp() {
        setUpInvertedIndex();
        this.reducer = new Reducer(invertedIndex);
    }

    @Test
    void testOrderPreserved() {
        List<List<Posting>> values = reducer.getValues();
        // file ID of element [0][0] should be 0.
        assertEquals(values.get(0).get(0).getFileId(), 0);
        // file ID of element [1][2] should be 3.
        assertEquals(values.get(1).get(2).getFileId(), 3);
        // file ID of element [2][1] should be 2.
        assertEquals(values.get(2).get(1).getFileId(), 2);
    }
}