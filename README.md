# Programming-Assignment

This repository contains implementations for questions 2-6 of Part A.  
Each problem is located in a separate folder with its own Java file, example `input.txt`, and `output.txt`.  
Bonus questions 1 and 3 are in the bonus folder and bonus question 2 is in the "Heap Bottom Up" folder.

---

## Table of Contents

- [Q2: Merge and Quick Sort Algorithms](#Merge-and-Quick-Sort)
- [Q3: HeapBottomUp Algorithm](#Heap-Bottom-Up)
- [Q4: Horspool's String Matching](#Horspool's-String-Matching)
- [Q5: Floyd's Algorithm](#Floyd's) 
- [Q6: Maximum Bipartite Matching](#Maximum-Bipartite-Matching)

---

## Merge and Quick Sort
**Folder:** `Merge-and-Quick-Sort/`
**Main Code:** `Sort.java`

### How to Compile & Run:
```bash 
javac Sort.java  
java Sort 
``` 

### Input File Format:
 Space-separated list of integers (or doubles) to sort.

## Heap Bottom Up
**Folder:** `Heap-Bottom-Up/`
**Main Code:** `MaxHeapBottomUp.java` `MinHeapBottomUp.java` `Bonus_HeapSort.java`

### How to Compile & Run (same for max, min, and bonus):
```bash
javac MaxHeapBottomUp.java  
java MaxHeapBottomUp.java
```  

### Input File Format:
First line: Number of keys (integer). 
Second line: Space-separated integer keys. 

## Horspool's String Matching
**Folder:** `Horspool's-String-Matching/`
**Main Code:** `HorspoolMatching.java`

### How to Compile & Run:
```bash
javac HorspoolMatching.java  
java HorspoolMatching.java
```  

### Input File Format:
First line: The pattern to be matched. 
Second line: The text where you are searching for pattern.
*Only lowercase alphabet letters and spaces allowed.

## Floyd's Algorithm
**Folder:** `Floyd's/`
**Main Code:** `Floyd.java`

### How to Compile & Run:
```bash
javac Floyd.java  
java Floyd.java
```  

### Input File Format:
Each line represents a row of the distance Matrix. Distances are floating-point numbers separated by spaces. Diagonal entries should be 0.
*Use a large number (e.g., 9999) to represent infinity.

## Maximum Bipartite Matching
**Folder:** `Maximum-Bipartite-Matching/`
**Main Code:** `MaximumBipartiteMatching.java`

### How to Compile & Run:
```bash
javac MaximumBipartiteMatching.java  
java MaximumBipartiteMatching.java
``` 

### Input File Format:
First line: Space-separated vertices from set V. 
Second line: Space-separated vertices from set U.
The rest: Adjacency matrix (0 or 1), V rows x U Columns.