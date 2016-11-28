package com.benhadfield.indexer;

import com.benhadfield.posting.Posting;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The Grouper class is responsible for grouping postings across multiple files by term.
 */

public class Grouper {
    private HashMap<String, ArrayList<Posting>> group;
    private Mapper[] mappers;

    public Grouper(Mapper... mappers) {
        this.mappers = mappers;
    }

    public void group() {

    }
}
