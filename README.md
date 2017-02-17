Search Engine
=============
After reading [Improving the performance of full-text search](https://blogs.dropbox.com/tech/2016/09/improving-the-performance-of-full-text-search/) on the [Dropbox Blog](https://blogs.dropbox.com/dropbox/) (which is an excellent insight into the company) I was inspired to try building my own full-text search engine.

To build on what I learnt in last year's object oriented module, I'm going to implement this in Java 8.


Aims
----
- [x] Implement an inverted index algorithm  
- [ ] Measure performance  
- [ ] Reiterate on original algorithm  


Current Functionality
---------------------
**[`Mapper`](https://github.com/benjaminhadfield/search-engine/blob/master/src/com/benhadfield/indexer/Mapper.java)**  
The `Mapper` class takes a path to a file and uses a `Tokenizer` instance to generate a map, with a `K<Token>, V<Posting>` structure.

```java
class Main {
    public static void main(String[] args) {
        Mapper mapper = new Mapper("./data/example/example_1.txt");
        mapper._printMap();
    }
}
```

This code would output the structure of the generated map.

```text
Term    Attributes
'fish'  (fileId: 0, frequency: 2)
'one'   (fileId: 0, frequency: 1)
'two'   (fileId: 0, frequency: 1)
```


**[`Grouper`](https://github.com/benjaminhadfield/search-engine/blob/master/src/com/benhadfield/indexer/Grouper.java)**  
The `Grouper` class takes a list of `Mapper` objects, and generates an inverted index, which is a map of terms to an array of file ID - token frequency pairs.
Note that terms in the map are sorted alphabetically.

```java
class Main {
    public static void main(String[] args) {
        Mapper m1 = new Mapper("./data/example_1.txt");
        Mapper m2 = new Mapper("./data/example_2.txt");

        Grouper grouper = new Grouper(m1, m2);
        grouper._printInvertedIndex();
    }
}
```

This code would produce an inverted index with the following structure.

```text
Term    Postings
blue    (fileId: 0, frequency: 1), 
fish    (fileId: 0, frequency: 2), (fileId: 1, frequency: 2), 
one     (fileId: 1, frequency: 1), 
red     (fileId: 0, frequency: 1), 
two     (fileId: 1, frequency: 1),
```


**[`Reducer`](https://github.com/benjaminhadfield/search-engine/blob/master/src/com/benhadfield/indexer/Reducer.java)**  
The `Reducer` class takes the inverted index output by the `Grouper` and writes an encoded version of the postings lists to disk.

```java
class Main {
    public static void main(String[] args) {
        Mapper m1 = new Mapper("./data/example_1.txt");
        Mapper m2 = new Mapper("./data/example_2.txt");
        Grouper grouper = new Grouper(m1, m2);
        
        Reducer reducer = new Reducer(grouper.getInvertedIndex());
    }
}
```

The Reducer constructor generates an `_index0.txt` file.

```text
0:1,
0:2,1:2,
1:1,
0:1,
1:1, 
```

The encoding of this file is simple at the moment.

Lines are ordered alphabetically according to their corresponding token.
Each line contains information for files containing that token only.
The number before the `:` corresponds to the file ID (which is assigned by the `Mapper` class).
The second number corresponds to the number of times the token appears in that file.

The reducer is capable of distributing the index across multiple files to prevent the creation of a single monolithic file.
In this simple example, that limit is set arbitrarily at 100, meaning if the search space contains greater than 100 tokens then the index will be split across more than one `_index<i>.txt` file.


**[`Retriever`](https://github.com/benjaminhadfield/search-engine/blob/master/src/com/benhadfield/retriever/Retriever.java)**  
The `Retriever` class builds a map of term to term location whilst terms are indexed.
This map can then be queried using the `get()` method to return the encoded locations of the relevant files.

```java
Retriever.get("fish");
```

This code returns

```text
0:2,1:2
```

which indicates that the term "fish" occurs in the file with an ID of 1 twice and in the file with an ID of 2 twice.
This result can then be interpreted by the `Query` class.


Resources
---------
- [University of Birkbeck: Inverted Indexing for Text
 Retrieval](http://www.dcs.bbk.ac.uk/~dell/teaching/cc/book/ditp/ditp_ch4.pdf)  
- [Victor Lavrenko's video series](https://www.youtube.com/watch?v=Mlp8hlKwETs)  
- [Dropbox Blog: Improving the performance of full-text search](https://blogs.dropbox.com/tech/2016/09/improving-the-performance-of-full-text-search/)  
- [Felipe Hummel's Tiny Search Engine](https://github.com/felipehummel/TinySearchEngine/blob/master/scala/tinySearch.scala)  
- [Google Developer: Base 128 Varints](https://developers.google.com/protocol-buffers/docs/encoding#varints)  
