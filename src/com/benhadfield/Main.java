package com.benhadfield;

import com.benhadfield.tokenizer.Tokenizer;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Tokenizer t = new Tokenizer("./data/an example.txt");
        t.createTokens();

        for (String token : t.getTokens()) {
            System.out.println(token);
        }
    }
}
