package com.benhadfield;

import com.benhadfield.indexer.Grouper;
import com.benhadfield.indexer.Mapper;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Mapper m1 = new Mapper("./data/example_2.txt");
        Mapper m2 = new Mapper("./data/example_1.txt");
        m1._printMap();
        m2._printMap();

        Grouper grouper = new Grouper(m1, m2);
        grouper.group();

    }
}
