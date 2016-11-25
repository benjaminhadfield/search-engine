package com.benhadfield.tokenizer;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


class TokenizerTest {
    private Tokenizer tokenizer;

    @BeforeEach
    void setUp() throws IOException {
        this.tokenizer = new Tokenizer("./data/example_1.txt");
    }

    @Test
    void getTokens() {
        // we expect the tokenizer to return an ArrayList<String>.
        assertEquals(
                new ArrayList<>(Arrays.asList("one", "fish", "two", "fish")),
                tokenizer.getTokens()
        );
    }

    @Test
    void generateTokens() {
        // we expect generate tokens to produce a normalized list of tokens given a String.
        assertEquals(
                new ArrayList<>(Arrays.asList("one", "two", "three")),
                tokenizer.generateTokens("One, tWo thRee.")
        );
        assertEquals(
                new ArrayList<>(Arrays.asList("1", "2", "3")),
                tokenizer.generateTokens("1 -- 2: (3);")
        );
    }
}