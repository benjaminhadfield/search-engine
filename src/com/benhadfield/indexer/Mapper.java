package com.benhadfield.indexer;

import com.benhadfield.tokenizer.Tokenizer;
import com.benhadfield.posting.Posting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/*
 * The Mapper class is responsible for creating postings, keyed by term.
 */

public class Mapper {

    // fields

    private static int _fileId = 0;
    private int fileId;
    private HashMap<String, Posting> map = new HashMap<String, Posting>();
    private Tokenizer tokenizer;

    // constructors

    public Mapper(String file_path) throws IOException {
        // set a unique file ID on construction, and then increment
        this.fileId = _fileId++;

        // generate tokens for the passed in file
        this.tokenizer = new Tokenizer(file_path);
        tokenizer.generateTokens();

        // generate map
        generateMap();
    }

    // public methods

    public HashMap getMap() {
        return map;
    }

    public int getFileId() {
        return fileId;
    }

    public void _printMap() {
        System.out.println("Term\tAttributes");
        map.forEach((term, posting) -> System.out.println(
                "'" + term + "'\t" + posting.toString()));
        System.out.println();
    }

    // private methods

    public void generateMap() {
        ArrayList<String> tokens = tokenizer.getTokens();
        for (String token : tokens) {
            if (map.containsKey(token)) {
                map.get(token).incrementFrequency();
            } else {
                map.put(token, new Posting(fileId, 1));
            }
        }
    }
}
