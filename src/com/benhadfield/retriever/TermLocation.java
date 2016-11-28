package com.benhadfield.retriever;

/**
 * The TermLocation class is responsible for holding the location of a term's postings list as output by a reducer.
 */

class TermLocation {
    private String path;
    private long offset;

    TermLocation(String path, long offset) {
        this.path = path;
        this.offset = offset;
    }

    @Override
    public String toString() {
        return "(" + path + " : " + offset + ")";
    }
}
