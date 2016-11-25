package com.benhadfield.posting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class PostingTest {
    private Posting posting;

    @BeforeEach
    void setUp() {
        this.posting = new Posting(0, 1);
    }

    @Test
    void getFileId() {
        assertEquals(0, posting.getFileId());
    }

    @Test
    void getFrequency() {
        assertEquals(1, posting.getFrequency());
    }

    @Test
    void incrementFrequency() {
        posting.incrementFrequency();
        assertEquals(2, posting.getFrequency());
    }

}