package com.benhadfield.tokenizer;

import com.benhadfield.file.File;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Tokenizer class, responsible for converting text files to tokens for indexing.
 */

public class Tokenizer {
    // fields

    private ArrayList<String> tokens = new ArrayList<String>();

    // constructors

    public Tokenizer(String path) throws IOException {
        String data = new File(path).readFile();
        this.tokens = generateTokens(data);
    }

    // public methods

    public ArrayList<String> getTokens() {
        return this.tokens;
    }

    // private methods

    private ArrayList<String> generateTokens(String data) {
        // Pure function takes a string and returns an array list of tokens.

        String[] words = data.split("\\W");
        ArrayList<String> tokens = new ArrayList<>();
        for (String word : words) {
            // check word exists, then normaliseit and add it to token array
            if (word.length() > 0) {
                String token = normalizeToken(word);
                tokens.add(token);
            }
        }
        return tokens;
    }

    private String normalizeToken(String word) {
        /*
        * Normalization consists of
        * 1) converting to lowercase
        * 2) depluralizing
        * 3) converting to root word
        * */
        return word.toLowerCase();
    }
}
