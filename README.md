Search Engine
=============
After reading [Improving the performance of full-text search](https://blogs.dropbox.com/tech/2016/09/improving-the-performance-of-full-text-search/) on the [Dropbox Blog](https://blogs.dropbox.com/dropbox/) (which is an excellent insight into the company) I was inspired to try building my own full-text search engine.

To build on what I learnt in last year's object oriented module, I'm going to implement this in Java 8.

Aims
----
- Implement an Inverted Index algorithm
- Measure performance

Current Functionality
---------------------
**[`Mapper`](https://github.com/benjaminhadfield/search-engine/blob/master/src/com/benhadfield/indexer/Mapper.java)**  
The `Mapper` class takes a path to a file and uses a `Tokenizer` instance to generate a map, with a `K<Token>, V<Posting>` structure.
This map contains a mapping of tokens to a file ID - token frequency pair for that file.

```java
class Main {
    public static void main(String[] args) {
        Mapper mapper = new Mapper("./data/example_1.txt");
        mapper._printMap();
    }
}
```

The code would output the structure of the generated map.

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
The `Reducer` class takes the inverted index output by the `Grouper` and encodes then writes the postings to disk.

```java
class Main {
    public static void main(String[] args) {
        Mapper m1 = new Mapper("./data/example_1.txt");
        Mapper m2 = new Mapper("./data/example_2.txt");
        Grouper grouper = new Grouper(m1, m2);
        
        Reducer reducer = new Reducer();
        reducer.commitIndex();
    }
}
```

The `commitIndex()` method generates an `_index.txt` file.

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


Resources
---------
- [University of Birkbeck: Inverted Indexing for Text
 Retrieval](http://www.dcs.bbk.ac.uk/~dell/teaching/cc/book/ditp/ditp_ch4.pdf)  
- [Victor Lavrenko's video series](https://www.youtube.com/watch?v=Mlp8hlKwETs)  
- [Dropbox Blog: Improving the performance of full-text search](https://blogs.dropbox.com/tech/2016/09/improving-the-performance-of-full-text-search/)  
- [Felipe Hummel's Tiny Search Engine](https://github.com/felipehummel/TinySearchEngine/blob/master/scala/tinySearch.scala)
