# Training project

[![Build Status](https://travis-ci.org/kondratyev-nv/training.svg?branch=master)](https://travis-ci.org/kondratyev-nv/training)
[![codecov](https://codecov.io/gh/kondratyev-nv/training/branch/master/graph/badge.svg)](https://codecov.io/gh/kondratyev-nv/training)

Solutions to various programming challenges in Java, C++ and Python.

## Currently solved challenges

### Trie implementation - [Java](java/src/main/java/ru/nk/training/Trie.java)

Implementation of Trie data structure designed to add words, count number of words by specified prefix, and to obtaining words that start with the specified prefix.

### Count stars - [Java](java/src/main/java/ru/nk/training/StarCounter.java)

For this problem, you should write a program which counts the number of stars visible in a bitmap image. An image consists of pixels, and each pixel is either black or white (represented by the characters `#` and `-`, respectively). All black pixels are considered to be part of the sky, and each white pixel is considered to be part of a star. White pixels that are adjacent vertically or horizontally are part of the same star.

### Checking intersection of rectangles - [Java](java/src/main/java/ru/nk/training/RectangleIntersectionChecker.java), [C++](cpp/src/rectangles_intersect.cpp)

You are given two rectangles, each defined by an upper-left (UL) corner and a lower-right (LR) corner. Both rectangles’ edges will always be parallel to the x or y axis. Write a function that determines whether the two rectangles overlap.

### Least Recently Used (LRU) cache - [Java](java/src/main/java/ru/nk/training/LruCache.java)

Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: `get` and `put`.
 - `get(key)` - Get the value of the key if the key exists in the cache, otherwise return `null`. 
 - `put(key, value)` - Set or insert the value if the key is not already present. 

When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

### Left rotation of an array - [Java](java/src/main/java/ru/nk/training/ArrayRotator.java)

A left rotation operation on an array of size N shifts each of the array's elements 1 unit to the left. Given an array of integers and a number, k, perform k left rotations on the array.

### Remove chars from the string - [Java](java/src/main/java/ru/nk/training/CharRemover.java), [C++](cpp/src/remove_chars.cpp)

Write an efficient function that deletes characters from an ASCII string. Use the prototype `string removeChars(string str, string remove);` where any character existing in `remove` must be deleted from `str`. For example, given a `str` of "Battle of the Vowels: Hawaii vs. Grozny" and a remove of "aeiou", the function should transform `str` to "Bttl f th Vwls: Hw vs. Grzny".

### Count number of road forks - [Python](python/src/count_number_of_road_forks.py)

Consider the forest as an N x M grid. Each cell is either empty (represented by .) or blocked by a tree (represented by X). You can move LEFT, RIGHT, UP, and DOWN through empty cells, but you cannot travel through a tree cell. Starting cell is marked with the character M, and the target cell is marked with a *. The upper-left corner is indexed as (0, 0). Find a number of times you are able to move in more than one direction.

### Find first non repeated char - [Java](java/src/main/java/ru/nk/training/FirstNonRepeatedCharFinder.java)

Write an efficient function to find the first nonrepeated character in a string. For instance, the first nonrepeated character in "total" is 'o' and the first nonrepeated character in "teeter" is 'r'.

### Reverse sentence - [Java](java/src/main/java/ru/nk/training/SentenceReverser.java)

Write a function that reverses the order of the words in a string. For example, your function should transform the string "Do or do not, there is no try." to "try. no is there not, do or Do". Assume that all words are space delimited and treat punctuation the same as letters.

### Binary search - [Java](java/src/main/java/ru/nk/training/BinarySearcher.java)

Implement a function to perform a binary search on a sorted array of integers to find the index of a given integer.

### Generate permutation of a string - [Java](java/src/main/java/ru/nk/training/StringPermutationGenerator.java)

Implement a routine that prints all possible orderings of the characters in a string. In other words, print all permutations that use all the characters from the original string. For example, given the string “hat”, your function should print the strings “tha”, “aht”, “tah”, “ath”, “hta”, and “hat”. Treat each character in the input string as a distinct character, even if it is repeated. Given the string “aaa”, your routine should print “aaa” six times. You may print the permutations in any order you choose.

### Get number of combination to obtain specified sum - [Java](java/src/main/java/ru/nk/training/SumCombinationsFinder.java)

Davis has s staircases in his house, and he likes to climb each staircase 1, 2, or 3 steps at a time. Being a very precocious child, he wonders how many ways there are to reach the top of the staircase. Given the respective heights for each of the s staircases in his house, find and print the number of ways he can climb each staircase on a new line.

### Height of a tree - [Java](java/src/main/java/ru/nk/training/BinaryTreeHeightFinder.java)

The height of a tree (binary or not) is defined to be the maximum distance from the root node to any leaf node. Write a function to calculate the height of an arbitrary binary tree.

### Even tree - [Java](java/src/main/java/ru/nk/training/EvenTreeCounter.java)

You are given a tree (a simple connected graph with no cycles). The tree has N nodes numbered from 1 to N and is rooted at node 1. Find the maximum number of edges you can remove from the tree to get a forest such that each connected component of the forest contains an even number of vertices.

### Find common ancestor - [Java](java/src/main/java/ru/nk/training/BinaryTreeCommonAncestorFinder.java)

Given the value of two nodes in a binary search tree, find the lowest (nearest) common ancestor. You may assume that both values already exist in the tree.

### Rotate binary tree - [Java](java/src/main/java/ru/nk/training/BinaryTreeRotator.java)

Given an unbalanced binary search tree with more nodes in the left subtree than 
the right, reorganise the tree to improve its balance while maintaining the properties of a binary search tree

### Maximize profit on stock - [Java](java/src/main/java/ru/nk/training/StocksProfitCalculator.java)

Your algorithms have become so good at predicting the market that you now know what the share price of Wooden Orange Toothpicks Inc. (WOT) will be for the next N days. Each day, you can either buy one share of WOT, sell any number of shares of WOT that you own, or not make any transaction at all. What is the maximum profit you can obtain with an optimum trading strategy?

### Rotate matrix - [Java](java/src/main/java/ru/nk/training/MatrixRotator.java)

Given an image represented by a NxN matrix, where each pixel in the image is 4 bytes, write a method to rotate the image by 90 degrees. Can you do this in place?

### Check if linked list has cycle - [Java](java/src/main/java/ru/nk/training/LinkedListCycleChecker.java)

You are given a linked list with at least one node that is either null-terminated (acyclic) or ends in a cycle (cyclic). Write a function that takes a pointer to the head of a list and determines whether the list is cyclic or acyclic. Your function should return false if the list is acyclic and true if it is cyclic. You may not modify the list in any way.

### Integer/String Conversions - [Java](java/src/main/java/ru/nk/training/IntegerStringConverter.java)

Write two conversion routines. The first routine converts a string to a signed integer. You may assume that the string contains only digits and the minus character ('-'), that it is a properly formatted integer number, and that the number is within the range of an int type. The second routine converts a signed integer stored as an int back to a string.

### Add one to big integer - [Python](python/src/add_one_to_big_integer.py)

Given a non-negative integer represented as a non-empty array of digits, plus one to the integer. You may assume the integer do not contain any leading zero, except the number 0 itself. The digits are stored such that the most significant digit is at the head of the list.

### Interval map - [Java](java/src/main/java/ru/nk/training/IntervalMap.java)

Implementation of interval map - data structure that maps half-open intervals of comparable keys to values. Value can be queried for a specific key, result will be the value mapped to the interval containing the key.

### Evaluate Reverse Polish Notation - [Python](python/src/evaluate_reverse_polish_notation.py)

Evaluate the value of an arithmetic expression in Reverse Polish Notation. Valid operators are `+`, `-`, `*`, `/`. Each operand may be an integer or another expression. Result of division operation is rounded to nearest integer.

### Count ways of making change - [C++](cpp/src/count_ways_to_make_change.cpp)

You have `m` types of coins available in infinite quantities where the value of each coin is given in the array `C`. Can you determine the number of ways of making change for `n` units using the given types of coins? For example, if `m = 4`, and `C = [8, 3, 1, 2]`, we can make change for `n = 3` units in three ways: `{1, 1, 1}`, `{1, 2}`, and `{3}`.

### Count number of pairs by difference - [Java](java/src/main/java/ru/nk/training/PairsByDifferenceCounter.java)

Given `N` integers, count the number of pairs of integers whose difference is `K`.

### Find minimum sum with different indices - [Java](java/src/main/java/ru/nk/training/MinimumSumWithDifferentIndicesFinder.java)

You are given two arrays `A` and `B` each containing `n` integers. You need to choose exactly one number from `A` and exactly one number from `B` such that the index of the two chosen numbers is not same and the sum of the two chosen values is minimum.

### Count patterns - [Python](python/src/count_patterns.py)

A string `s` contains many patterns of the form `1(0+)1` where `(0+)` represents any non-empty consecutive sequence of zeros. The patterns are allowed to overlap. For example, consider string `1101001`, we can see there are two consecutive sequences `1(0)1` and `1(00)1` which are of the form `1(0+)1`. Find the total number of patterns of the form `1(0+)1` that occur in `s`.

### Find longest palindromic subsequence - [Java](java/src/main/java/ru/nk/training/LongestPalindromicSubsequenceFinder.java)

Given a string, find a longest palindromic subsequence in it. A longest palindromic subsequence is a sequence that appears in the same relative order, but not necessarily contiguous (not substring) and palindrome in nature (means the subsequence will read same from the front and back).

### Given a string find out if symbols can be rearranged to a palindrom - [Python](python/src/can_rearrange_to_palindrom.py)

Given a string find out if symbols can be rearranged to a palindrom.

### Find longest palindromic subsequence that can be obtained with specific transformations - [Java](java/src/main/java/ru/nk/training/LongestPalindromicSubsequenceWithTransformationFinder.java)

The alphabet system consists of `n` letters, denoted by the integers from `1` to `n`. Some letters can be transformed to other letters. A transformation is denoted by a pair of two letters, `x -> y`. Using this transformation, you can replace letter `x` with letter `y`. Transformations also have additional properties:
 - If letter `x` can be transformed to letter `y` using a transformation, then letter `y` can be transformed to letter `x` as well.
 - If letter `x` can be transformed to letter `y` and letter `y` can be transformed to letter `z`, then letter `x` can be transformed to letter `z` as well.
 
You are given a sequence `s` comprising of `m` letters. You are given `k` transformations that can be applied to `s`. You may apply transformations to zero or more letters in the sequence. When a transformation is applied to a letter, the other letters of the string remain unaffected. You can also apply a single transformation multiple times on the same sequence. Find the length of the longest possible palindromic subsequence after applying zero or more transformations on the letters of the given sequence.

### Word Break - [Java](java/src/main/java/ru/nk/training/StringByWordsSplitter.java)

Given a non-empty string `s` and a dictionary of words containing non-empty words, determine if `s` can be segmented into a space-separated sequence of one or more dictionary words. You may assume the dictionary does not contain duplicate words. For example, given `s = "leetcode"`, `dict = ["leet", "code"]` return space separated sentence `"leet code"`.

### Find number of available positions in matrix - [Java](java/src/main/java/ru/nk/training/MatrixAvailablePositionsCounter.java)

You are given `n x m` matrix and a list of tracks that always run in straight horizontal lines along a row. In other words, the start and end points of a track are `(r, c1)` and `(r, c2)`, where `r` represents the row number, `c1` represents the starting column, and `c2` represents the ending column of the track. Determine the number of cell that is not occupied by a track.

### Recursive Digit Sum - [Python](python/src/get_recursive_digit_sum.py)

We define super digit of an integer `x` using the following rules:
- If `x` has only 1 digit, then its super digit is `x`.
- Otherwise, the super digit of `x` is equal to the super digit of the digit-sum of `x`. Here, digit-sum of a number is defined as the sum of its digits.

You are given two numbers `n` and `k`. You have to calculate the super digit of `P`. `P` is created when number `n` is concatenated `k` times.

### Find Minimum Spanning Tree (MST) - [Python](python/src/minimum_spanning_tree.py)

Given a graph which consists of several edges connecting the N nodes in it. It is required to find a subgraph of the given graph with the following properties:
- The subgraph contains all the nodes present in the original graph.
- The subgraph is of minimum overall weight (sum of all edges) among all such subgraphs.
- It is also required that there is exactly one, exclusive path between any two nodes of the subgraph.

### Min Stack - [Java](java/src/main/java/ru/nk/training/MinStack.java)

Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
- `push(x)` - Push element x onto stack.
- `pop()` - Removes the element on top of the stack.
- `peek()` - Get the top element.
- `getMin()` - Retrieve the minimum element in the stack.

### Balanced Brackets - [Java](java/src/main/java/ru/nk/training/BalancedBracketsChecker.java)

A bracket is considered to be any one of the following characters: `(`, `)`, `{`, `}`, `[`, or `]`. Two brackets are considered to be a matched pair if the an opening bracket (i.e., `(`, `[`, or `{`) occurs to the left of a closing bracket (i.e., `)`, `]`, or `}`) of the exact same type. There are three types of matched pairs of brackets: `[]`, `{}`, and `()`. A matching pair of brackets is not balanced if the set of brackets it encloses are not matched. For example, `{[(])}` is not balanced because the contents in between `{` and `}` are not balanced. The pair of square brackets encloses a single, unbalanced opening bracket, `(`, and the pair of parentheses encloses a single, unbalanced closing square bracket, `]`. By this logic, we say a sequence of brackets is considered to be balanced if the following conditions are met:
- It contains no unmatched brackets.
- The subset of brackets enclosed within the confines of a matched pair of brackets is also a matched pair of brackets.

Given  strings of brackets, determine whether each sequence of brackets is balanced.

### Is This a Binary Search Tree? - [Java](java/src/main/java/ru/nk/training/BinarySearchTreeChecker.java)

Given the root node of a binary tree, can you determine if it's also a binary search tree. A binary tree is a binary search tree when the following ordering requirements are satisfied:
- The value of every node in a node's left subtree is less than the data value of that node.
- The value of every node in a node's right subtree is greater than the data value of that node.

### Average of Fibonacci numbers - [Java](java/src/main/java/ru/nk/training/AverageOfFibonacciNumbersFinder.java)

Find the average of first `n` numbers from Fibonacci sequence using Java streams

### Reverse each word in a string - [C++](cpp/src/reverse_words.cpp)

Write a program to reverse each word in a sentence. For example, if input string is "Hello World" then the output should be "olleH dlroW".

### Find the point where maximum intervals overlap - [Python](python/src/max_segment_intersections.py)

Consider a big party where a log register for guest’s entry and exit times is maintained. Find the time at which there are maximum guests in the party. Note that entries in register are not in any order.

### Highway Construction - [C++](cpp/include/highway_cost_calculator.hpp)

You are planning the next FIFA World Cup and you are counting the number of highways that need to be built to connect the cities with the venue. Your country has n cities and all cities lie on a single straight road called “Highway Road”. If you want to go from City `x` to City `y` (where `x` ≤ `y`), you need to go through city `x`, `x + 1`, `x + 2`, .., `y - 1`, `y`. The requirements for the highways are as follows:
 - All games will be held in the `n`-th city.
 - New bidirectional roads, called "Super Highways", need to be built such that it is possible to visit the `n`-th city from any other city directly. 

You also have the cost to fulfil the second condition. The engineering team knows that if the length of a Super Highway is `l`, then it will cost `l`<sup>`k`</sup>, where k is an integer constant. The length of Super Highway between city `x` and `y` is `|x - y|`. For this problem, you need to find only a rough estimation of the cost, hence, find Total Cost Modulo 1000000009.

### 3D Surface Area - [C++](cpp/src/surface_area.cpp)

Madison, is a little girl who is fond of toys. Her friend Mason works in a toy manufacturing factory. Mason has a 2D board A of size `H` x `W` with `H` rows and `W` columns. The board is divided into cells of size 1 x 1 with each cell indicated by it's coordinate `(i, j)`. The cell `(i, j)` has an integer `A`<sub>`ij`</sub> written on it. To create the toy Mason stacks `A`<sub>`ij`</sub> number of cubes of size 1 x 1 x 1 on the cell `(i, j)`. Given the description of the board showing the values of `A`<sub>`ij`</sub> and that the price of the toy is equal to the 3D surface area find the price of the toy.
