package com.benhadfield.indexer;

import com.benhadfield.tokenizer.Tokenizer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The Mapper class is responsible for creating postings, keyed by term.
 */

public class Mapper {
    // fields

    private HashMap<String, Integer> map = new HashMap<String, Integer>();
    private Tokenizer tokenizer;

    // constructors

    public Mapper(String file_path) throws IOException {
        this.tokenizer = new Tokenizer(file_path);
        tokenizer.generateTokens();
    }

    // public methods

    public HashMap getMap() {
        return this.map;
    }

    public void generateMap() {
        ArrayList<String> tokens = tokenizer.getTokens();
        for (String token : tokens) {
            if (map.containsKey(token)) {
                map.replace(token, map.get(token) + 1);
            } else {
                map.put(token, 1);
            }
        }
    }

    public void _printMap() {
        map.forEach((term, freq) -> System.out.println(
                "term: '" + term + "'\nfreq: " + freq + "\n"));
    }

    // private methods
}
