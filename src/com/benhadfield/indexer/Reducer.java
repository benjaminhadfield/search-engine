package com.benhadfield.indexer;

import com.benhadfield.file.File;
import com.benhadfield.posting.Posting;

import java.util.*;

/*
 * The Reducer class is responsible for writing postings lists to disk.
 */

public class Reducer {
    private static int identifier = 0;
    private static int termLimit = 10;
    private final String path;
    private List<List<Posting>> values = new ArrayList<>();

    /**
     * Constructs a reducer with a term and sorted list of postings.
     */

    public Reducer(TreeMap<String, ArrayList<Posting>> invertedIndex) {
        // generate path name
        this.path = "./data/_index" + identifier++ + ".txt";
        // get values from inverted index
        this.values = getValues(invertedIndex);
    }

    public List<List<Posting>> getValues() {
        return values;
    }

    public String getPath() {
        return path;
    }

    public void commitIndex() {
        // commits the group to a file at the specified location
        File file = new File(path);
        String [] data = new String[values.size()];

        int i = 0;
        for (List<Posting> postings : values) {
            String line = "";
            // encode all postings for a given term, this is sorted by document ID already.
            for (Posting posting : postings) {
                line += encodePosting(posting);
            }
            data[i++] = line;
        }
        file.writeFile(data);
    }

    private String encodePosting(Posting posting) {
        // this is where we encode posting data
        return posting.getFileId() + ":" + posting.getFrequency() + ",";
    }

    private ArrayList<List<Posting>> getValues(TreeMap<String, ArrayList<Posting>> invertedIndex) {
        ArrayList<List<Posting>> values = new ArrayList<>();
        // here we loop through the inverted index, assigning the postings lists to the values field
        // if there are more values than the limit then a second reducer class is created to handle the
        // rest (creating a distributed system).
        boolean isWithinLimit = termLimit > invertedIndex.size();
        int limit = isWithinLimit ? invertedIndex.size() : termLimit;

        for (int i = 0; i < limit; i++) {
            String term = invertedIndex.keySet().toArray(new String[0])[i];
            ArrayList<Posting> postings = invertedIndex.get(term);
            values.add(postings);
        }
//        for (ArrayList<Posting> postings : invertedIndex.values()) {
//            // since we're using a TreeMap we know values are ordered by their corresponding keys
//            values.add(postings);
//        }
        return values;
    }
}
