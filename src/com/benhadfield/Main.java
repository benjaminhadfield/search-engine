package com.benhadfield;

import com.benhadfield.tokenizer.Tokenizer;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Tokenizer t = new Tokenizer("./data/inverted_index.txt");
        t.createTokens();

        for (String token : t.getTokens()) {
            System.out.println(token);
        }
    }
}
