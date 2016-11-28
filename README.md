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
**`Grouper`**  
The `Grouper` class takes a list of `Mapper` objects, and outputs an inverted index, which is a map of terms to an array of file ID - term frequency pairs.

**`Reducer`**  
The `Reducer` class takes the inverted index output by the `Grouper` and encodes then writes the postings to disk.

**`Mapper`**  
The `Mapper` class takes a file path and uses a `Tokenizer` instance to generate a Hash Map, with a `Token : Posting` structure.

```java
class Main {
    public static void main(String[] args) {
        Mapper mapper = new Mapper("./data/example_1.txt");
        mapper._printMap();
    }
}
```
```
Term    Attributes

'one'   (fileId: 0, frequency: 1)
'fish'  (fileId: 0, frequency: 2)
'two'   (fileId: 0, frequency: 1)
```

Resources
---------
- [University of Birkbeck: Inverted Indexing for Text
 Retrieval](http://www.dcs.bbk.ac.uk/~dell/teaching/cc/book/ditp/ditp_ch4.pdf)  
- [Victor Lavrenko's video series](https://www.youtube.com/watch?v=Mlp8hlKwETs)  
- [Dropbox Blog: Improving the performance of full-text search](https://blogs.dropbox.com/tech/2016/09/improving-the-performance-of-full-text-search/)  
- [Felipe Hummel's Tiny Search Engine](https://github.com/felipehummel/TinySearchEngine/blob/master/scala/tinySearch.scala)
