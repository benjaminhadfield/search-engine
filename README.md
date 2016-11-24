Search Engine
=============
After reading [Improving the performance of full-text search](https://blogs.dropbox.com/tech/2016/09/improving-the-performance-of-full-text-search/) on the [Dropbox Blog](https://blogs.dropbox.com/dropbox/) (which is an excellent insight into the company) I was inspired to try building my own full-text search engine.

To build on what I learnt in last year's object oriented module, I'm going to implement this in Java 8.
Where possible, methods are pure.

Aims
----
- Implement an Inverted Index algorithm
- Measure performance

Current Functionality
---------------------
Implemented a Postings class, to relate a document ID with a term frequency.

For example, given the `data/numbers.txt` file as input we get the output

```
Term    Attributes

'four'  (fileId: 0, frequency: 4)
'one'   (fileId: 0, frequency: 1)
'two'   (fileId: 0, frequency: 2)
'three'	(fileId: 0, frequency: 3)
'five'  (fileId: 0, frequency: 5)
```

Resources
---------
- [University of Birkbeck: Inverted Indexing for Text
 Retrieval](http://www.dcs.bbk.ac.uk/~dell/teaching/cc/book/ditp/ditp_ch4.pdf)  
- [Victor Lavrenko's video series](https://www.youtube.com/watch?v=Mlp8hlKwETs)  
- [Dropbox Blog: Improving the performance of full-text search](https://blogs.dropbox.com/tech/2016/09/improving-the-performance-of-full-text-search/)  
- [Felipe Hummel's Tiny Search Engine](https://github.com/felipehummel/TinySearchEngine/blob/master/scala/tinySearch.scala)
