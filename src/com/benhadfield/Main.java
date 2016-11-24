package com.benhadfield;

import com.benhadfield.indexer.Mapper;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Mapper mapper = new Mapper("./data/numbers.txt");
        mapper.generateMap();

        mapper._printMap();
    }
}
