package com.benhadfield.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/*
 * File responsible for reading file data.
 */

public class File {
    private String path;

    public File(String path) {
        this.path = path;
    }

    public String readFile() throws IOException {
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
        return convertStringArrayToString(data);
    }

    public void writeFile(String... data) {
        // write incoming string data to file. If no data is passed in then this
        // function does nothing.
        if (data.length == 0) {
            return;
        }

        Charset utf8 = StandardCharsets.UTF_8;
        List<String> lines = Arrays.asList(data);
        try {
            Files.write(Paths.get(path), lines, utf8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

    private String convertStringArrayToString(String[] array) {
        /*
        * Method flattens String[] to String.
        * */
        return String.join(" ", array);
    }
}
