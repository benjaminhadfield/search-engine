package com.benhadfield;

import com.benhadfield.indexer.Grouper;
import com.benhadfield.indexer.Mapper;
import com.benhadfield.indexer.Reducer;
import com.benhadfield.retriever.Retriever;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Mapper m1 = new Mapper("./data/example/example_1.txt");
        Mapper m2 = new Mapper("./data/example/example_2.txt");

        Grouper grouper = new Grouper(m1, m2);
        Reducer reducer = new Reducer(grouper.getInvertedIndex());

        Scanner scanner = new Scanner(System.in);
        while(true) {
            String searchTerm = scanner.nextLine();
            if ("exit()".equals(searchTerm)) {
                break;
            }
            System.out.println(Retriever.get(searchTerm));
        }
        scanner.close();
    }
}
