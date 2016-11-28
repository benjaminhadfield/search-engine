package com.benhadfield.indexer;

import com.benhadfield.file.File;
import com.benhadfield.posting.Posting;
import com.benhadfield.retriever.Retriever;

import java.util.*;

/*
 * The Reducer class is responsible for writing postings lists to disk.
 */

public class Reducer {
    private final static int termLimit = 100;
    private static int identifier = 0;
    private final String path;
    private String[] keys;
    private List<List<Posting>> values = new ArrayList<>();
    private Reducer nextReducer = null;

    /**
     * Constructs a reducer with list of postings sorted according to their corresponding term.
     */

    public Reducer(TreeMap<String, ArrayList<Posting>> invertedIndex) {
        // generate path name
        this.path = generatePath();
        // get keys from inverted index
        this.keys = invertedIndex.keySet().toArray(new String[invertedIndex.size()]);
        // get values from inverted index
        this.values = getValues(invertedIndex);
        // encode to file
        commitIndex();
    }

    public static int getTermLimit() {
        return termLimit;
    }

    public String getPath() {
        return path;
    }

    public List<List<Posting>> getValues() {
        return values;
    }

    public Reducer getNextReducer() {
        return nextReducer;
    }

    private void commitIndex() {
        // commits the group to a file, automatically named based on the identifier
        File file = new File(path);
        String [] data = new String[values.size()];

        int i = 0;
        for (List<Posting> postings : values) {
            String line = "";
            // encode all postings for a given term, this is sorted by document ID already.
            for (Posting posting : postings) {
                line += encodePosting(posting);
                Retriever.addLocation(keys[i], path, i);
            }
            data[i++] = line;
        }
        file.writeFile(data);
    }

    private ArrayList<List<Posting>> getValues(TreeMap<String, ArrayList<Posting>> invertedIndex) {
        ArrayList<List<Posting>> values = new ArrayList<>();

        // here we loop through the inverted index, assigning the postings lists to the values field
        // if there are more values than the limit then a second reducer class is created to handle the
        // rest (creating a distributed system)
        boolean isWithinLimit = termLimit > invertedIndex.size();
        int limit = isWithinLimit ? invertedIndex.size() : termLimit;
        String term = null;

        for (int i = 0; i < limit; i++) {
            term = invertedIndex.keySet().toArray(new String[0])[i];
            ArrayList<Posting> postings = invertedIndex.get(term);
            values.add(postings);
        }

        // if there are still more terms then create a new reducer to handle those
        if (!isWithinLimit) {
            invertedIndex.headMap(term, true).clear();
            this.nextReducer = new Reducer(invertedIndex);
        }

        return values;
    }

    private String encodePosting(Posting posting) {
        // this is where we encode posting data
        return posting.getFileId() + ":" + posting.getFrequency() + ",";
    }

    private String generatePath() {
        return "./data/_index/_index" + identifier++ + ".txt";
    }
}
