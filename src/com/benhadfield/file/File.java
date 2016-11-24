package com.benhadfield.file;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.nio.Buffer;

/**
 * File responsible for reading file data.
 */

public class File {

    // fields

    private String path;

    // constructors

    public File(String path) {
        this.path = path;
    }

    // public methods

    public String[] readFile() throws IOException {
        // set up readers
        FileReader fr = new FileReader(path);
        BufferedReader br = new BufferedReader(fr);

        // set up string array
        int numberOfLines = getFileLineCount();
        String[] data = new String[numberOfLines];

        // read file
        for (int i = 0; i < numberOfLines; i++) {
            data[i] = br.readLine();
        }

        // cleanup and return
        br.close();
        return data;
    }

    // private methods

    private int getFileLineCount() throws IOException {
        // setup readers
        FileReader fr = new FileReader(path);
        BufferedReader bf = new BufferedReader(fr);

        // setup count vars
        String line;
        int numberOfLines = 0;

        // count lines
        while ((line = bf.readLine()) != null) {
            numberOfLines++;
        }

        // cleanup and return
        bf.close();
        return numberOfLines;
    }
}
