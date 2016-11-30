package com.benhadfield.retriever;

/**
 * The TermLocation class is responsible for holding the location of a term's postings list as output by a reducer.
 *
 * Since this class is only used by the Retriever, lets constrain access to this package only.
 */

class TermLocation {
    private String path;
    private long offset;

    TermLocation(String path, long offset) {
        this.path = path;
        this.offset = offset;
    }

    String getPath() {
        return path;
    }

    long getOffset() {
        return offset;
    }

    @Override
    public String toString() {
        return "(" + path + " : " + offset + ")";
    }
}
