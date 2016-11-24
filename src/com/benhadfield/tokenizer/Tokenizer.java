package com.benhadfield.tokenizer;

import com.benhadfield.file.File;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Tokenizer class, responsible for converting text files to tokens for indexing.
 */

public class Tokenizer {

    private String fileData;
    private ArrayList<String> tokens = new ArrayList<String>();

    // constructors

    public Tokenizer(String path) throws IOException {
        this.fileData = new File(path).readFile();
    }

    // public methods

    public ArrayList<String> getTokens() {
        return this.tokens;
    }

    public void createTokens() {
        // create words from fileData
        String[] words = fileData.split("\\W");
        for (String word : words) {
            // check word exists, then normalise it and add it to token array
            if (word.length() > 0) {
                String token = normalizeToken(word);
                tokens.add(token);
            }
        }
    }

    // private methods

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
