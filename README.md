# Training project

Solutions to various programming challenges in Java and C++

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