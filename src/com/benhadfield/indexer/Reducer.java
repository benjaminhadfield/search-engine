package com.benhadfield.indexer;

import com.benhadfield.posting.Posting;

import java.util.*;

/*
 * The Reducer class is responsible for writing postings lists to disk.
 */

public class Reducer {
    private String term;
    private Posting[] postings;

    /**
     * Constructs a reducer with a term and sorted list of postings.
     */

    public Reducer(String term, Posting[] postings) {
        this.term = term;
        this.postings = sortPostings(postings);
    }

    public String getTerm() {
        return term;
    }

    public Posting[] getPostings() {
        return postings;
    }

    private Posting[] sortPostings(Posting[] postings) {
        // copy array to prevent mutation of original array
        Posting[] postingsCopy = postings.clone();

        // sort array and return
        Arrays.sort(postingsCopy);
        return postingsCopy;
    }

}
