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
**24 Nov 2016**  
The Mapper class is now functional, and given a text file will return a list of terms and an associated payload, in this simple case that is simply the term frequency.

For example, given the `numbers.txt` file as input:

```java
public class Main {
    public static void main(String[] args) throws IOException {
        Mapper mapper = new Mapper("./data/numbers.txt");
        mapper.generateMap();
        mapper._printMap();
    }
}
```

We get the output

```
term: 'four'
freq: 4

term: 'one'
freq: 1

term: 'two'
freq: 2

term: 'three'
freq: 3

term: 'five'
freq: 5
```

Resources
---------
- [University of Birkbeck: Inverted Indexing for Text
 Retrieval](http://www.dcs.bbk.ac.uk/~dell/teaching/cc/book/ditp/ditp_ch4.pdf)  
- [Victor Lavrenko's video series](https://www.youtube.com/watch?v=Mlp8hlKwETs)  
- [Dropbox Blog: Improving the performance of full-text search](https://blogs.dropbox.com/tech/2016/09/improving-the-performance-of-full-text-search/)  
