package com.benhadfield.posting;

/*
 * Represents a single posting instance, ie. a fileID-frequency pair.
 */

public class Posting implements Comparable<Posting> {
    // fields

    private final int fileId;
    private int frequency;

    // constructors

    public Posting(int fileId, int frequency) {
        this.fileId = fileId;
        this.frequency = frequency;
    }

    // public methods

    public int getFileId() {
        return fileId;
    }

    public int getFrequency() {
        return frequency;
    }

    public void incrementFrequency() {
        frequency++;
    }

    @Override
    public int compareTo(Posting posting) {
        return (int)Math.signum(fileId - posting.getFileId());
    }

    @Override
    public String toString() {
        return "(fileId: " + getFileId()
                + ", frequency: " + getFrequency() + ")";
    }
}
