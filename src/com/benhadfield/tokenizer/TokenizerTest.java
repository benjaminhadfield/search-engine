package com.benhadfield.tokenizer;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/*
 * Created by Moose on 25/11/2016.
 */

class TokenizerTest {
    private Tokenizer tokenizer;

    @BeforeEach
    void setUp() throws IOException {
        this.tokenizer = new Tokenizer("./data/example_1.txt");
    }

    @Test
    void testGetTokens() {
        // we expect the tokenizer to return an ArrayList if called after generateTokens()
        assertEquals(
                new ArrayList<>(Arrays.asList("one", "fish", "two", "fish")),
                tokenizer.getTokens()
        );
    }

    @Test
    void testGenerateTokens() {
        assertEquals(2, 1 + 1);
    }
}