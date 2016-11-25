package com.benhadfield.indexer;

import com.benhadfield.posting.Posting;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class ReducerTest {
    private String testTerm = "fish";
    private Posting[] testPostings = {
            new Posting(30, 1),
            new Posting(10, 1),
            new Posting(20, 2)
    };

    private Reducer reducer;

    @BeforeEach
    void setUp() {
        reducer = new Reducer(testTerm, testPostings);
    }

    @Test
    void getTerm() {
        // tests getTerm returns unmodified term
        assertEquals(testTerm, reducer.getTerm());
    }

    @Test
    void getPostings() {
        // tests method returns postings sorted by fileID
        Posting[] postings = reducer.getPostings();
        assertAll(
                () -> assertEquals(postings[0].getFileId(), 10),
                () -> assertEquals(postings[1].getFileId(), 20),
                () -> assertEquals(postings[2].getFileId(), 30)
        );
    }
}