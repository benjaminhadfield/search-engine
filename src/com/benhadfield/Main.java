package com.benhadfield;

import com.benhadfield.indexer.Grouper;
import com.benhadfield.indexer.Mapper;
import com.benhadfield.indexer.Reducer;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Mapper m1 = new Mapper("./data/example_2.txt");
        Mapper m2 = new Mapper("./data/example_1.txt");

        Grouper grouper = new Grouper(m1, m2);
        grouper._printInvertedIndex();
    }
}
