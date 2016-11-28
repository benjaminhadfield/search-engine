package com.benhadfield.indexer;

import com.benhadfield.file.File;
import com.benhadfield.posting.Posting;

import java.util.*;

/*
 * The Reducer class is responsible for writing postings lists to disk.
 */

public class Reducer {
    private final static String path = "./data/_index.txt";
    private List<List<Posting>> values = new ArrayList<>();

    /**
     * Constructs a reducer with a term and sorted list of postings.
     */

    public Reducer(TreeMap<String, ArrayList<Posting>> invertedIndex) {
        this.values = getValues(invertedIndex);
    }

    public List<List<Posting>> getValues() {
        return values;
    }

    public void commitIndex() {
        // commits the group to a file at the specified location
        File file = new File(path);
        String [] data = new String[values.size()];

        int i = 0;
        for (List<Posting> postings : values) {
            String line = "";
            // encode all postings for a given term
            for (Posting posting : postings) {
                line += encodePosting(posting);
            }
            data[i++] = line;
        }
        file.writeFile(data);
    }

    private String encodePosting(Posting posting) {
        return posting.getFileId() + ":" + posting.getFrequency() + ",";
    }

    private ArrayList<List<Posting>> getValues(TreeMap<String, ArrayList<Posting>> invertedIndex) {
        ArrayList<List<Posting>> values = new ArrayList<>();
        // since we're using a TreeMap we know values are ordered by their corresponding keys
        for (ArrayList<Posting> postings : invertedIndex.values()) {
            values.add(postings);
        }
        return values;
    }
}
