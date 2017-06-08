# Training project

Solutions to various programming challenges in Java, C++ and Python.

## Currently solved challenges

### Trie implementation - [Java](java/src/main/java/ru/nk/training/Trie.java)

Implemetation of Trie data structure designed to add words, count number of words by specified prefix, and to obtaining words that starts with the specified prefix.

### Count stars - [Java](java/src/main/java/ru/nk/training/StarCounter.java)

For this problem, you should write a program which counts the number of stars visible in an bitmap image. An image consists of pixels, and each pixel is either black or white (represented by the characters `#` and `-`, respectively). All black pixels are considered to be part of the sky, and each white pixel is considered to be part of a star. White pixels that are adjacent vertically or horizontally are part of the same star.

### Checking intersection of rectangles - [Java](java/src/main/java/ru/nk/training/RectangleIntersectionChecker.java), [C++](cpp/src/rectangles_intersect.cpp)

You are given two rectangles, each defined by an upper-left (UL) corner and a lower-right (LR) corner. Both rectangles’ edges will always be parallel to the x or y axis. Write a function that determines whether the two rectangles overlap.

### Least Recently Used (LRU) cache - [Java](java/src/main/java/ru/nk/training/LruCache.java)

### Left rotation of an array - [Java](java/src/main/java/ru/nk/training/LeftRotation.java)

A left rotation operation on an array of size N shifts each of the array's elements 1 unit to the left. Given an array of integers and a number, k, perform k left rotations on the array.

### Remove chars from the string - [Java](java/src/main/java/ru/nk/training/Strings/CharRemover.java), [C++](cpp/src/remove_chars.cpp)

Write an efficient function that deletes characters from an ASCII string. Use the prototype `string removeChars(string str, string remove);` where any character existing in remove must be deleted from `str`. For example, given a str of "Battle of the Vowels: Hawaii vs. Grozny" and a remove of "aeiou", the function should transform `str` to "Bttl f th Vwls: Hw vs. Grzny".

### Count number of road forks - [Python](python/src/count_number_of_road_forks.py)

Consider the forest as an N x M grid. Each cell is either empty (represented by .) or blocked by a tree (represented by X). You can move LEFT, RIGHT, UP, and DOWN through empty cells, but you cannot travel through a tree cell. Starting cell is marked with the character M, and the target cell is marked with a *. The upper-left corner is indexed as (0, 0). Find a number of times you are able to move in more than one direction.

### Find first non repeated char - [Java](java/src/main/java/ru/nk/training/Strings/FirstNonRepeatedCharFinder.java)

Write an efficient function to find the first nonrepeated character in a string. For instance, the first nonrepeated character in "total" is 'o' and the first nonrepeated character in "teeter" is 'r'.

### Reverse sentence - [Java](java/src/main/java/ru/nk/training/Strings/SentenceReverser.java)

Write a function that reverses the order of the words in a string. For example, your function should transform the string "Do or do not, there is no try." to "try. no is there not, do or Do". Assume that all words are space delimited and treat punctuation the same as letters.

### Binary search - [Java](java/src/main/java/ru/nk/training/Recursion/BinarySearcher.java)

Implement a function to perform a binary search on a sorted array of integers to find the index of a given integer.

### Generate permutation of a string - [Java](java/src/main/java/ru/nk/training/Recursion/PermutationGenerator.java)

Implement a routine that prints all possible orderings of the characters in a string. In other words, print all permutations that use all the characters from the original string. For example, given the string “hat”, your function should print the strings “tha”, “aht”, “tah”, “ath”, “hta”, and “hat”. Treat each character in the input string as a distinct character, even if it is repeated. Given the string “aaa”, your routine should print “aaa” six times. You may print the permutations in any order you choose.

### Get number of combination to obtain specified sum - [Java](java/src/main/java/ru/nk/training/Recursion/SumCombinationsFinder.java)

Davis has s staircases in his house and he likes to climb each staircase 1, 2, or 3 steps at a time. Being a very precocious child, he wonders how many ways there are to reach the top of the staircase. Given the respective heights for each of the s staircases in his house, find and print the number of ways he can climb each staircase on a new line.

### Height of a tree - [Java](java/src/main/java/ru/nk/training/Trees/HeightFinder.java)

The height of a tree (binary or not) is defined to be the maximum distance from the root node to any leaf node. Write a function to calculate the height of an arbitrary binary tree.

### Even tree - [Java](java/src/main/java/ru/nk/training/Trees/EvenTreeCounter.java)

You are given a tree (a simple connected graph with no cycles). The tree has N nodes numbered from 1 to N and is rooted at node 1. Find the maximum number of edges you can remove from the tree to get a forest such that each connected component of the forest contains an even number of vertices.

### Find common ancestor - [Java](java/src/main/java/ru/nk/training/Trees/CommonAncestorFinder.java)

Given the value of two nodes in a binary search tree, find the lowest (nearest) common ancestor. You may assume that both values already exist in the tree.

### Rotate binary tree - [Java](java/src/main/java/ru/nk/training/Trees/BinaryTreeRotator.java)

Given an unbalanced binary search tree with more nodes in the left subtree than 
the right, reorganize the tree to improve its balance while maintaining the properties of a binary search tree
