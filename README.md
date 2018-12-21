# Training project

[![Build Status](https://travis-ci.org/kondratyev-nv/training.svg?branch=master)](https://travis-ci.org/kondratyev-nv/training)
[![Coverage](https://codecov.io/gh/kondratyev-nv/training/branch/master/graph/badge.svg)](https://codecov.io/gh/kondratyev-nv/training)
[![Unlicense](https://img.shields.io/badge/license-Unlicense-blue.svg)](UNLICENSE)

Solutions to various programming challenges in Java, C++ and Python.

## How to build

### Java

Java 8 and Maven are required. To build and run tests

    cd java
    mvn clean test

### Python

Python 3.5 is required. To run tests

    cd python
    python -m unittest discover -v --pattern *_tests.py

To run tests with coverage 

    coverage run --source=src -m unittest discover -v --pattern *_tests.py

To run tests in VSCode [Python extension](https://marketplace.visualstudio.com/items?itemName=ms-python.python) can be used.

### C++

To build from VSCode open cpp directory (`code cpp`) and run build task. To run tests after build execute test task.

#### Linux

On Linux gcc-5 and g++-5 and CMake 3.2 are required. To build and run tests from the console go to the cpp directory (`cd ./cpp/`) and run `./build.sh` or 

    mkdir -p build
    cd build
    cmake -DCMAKE_BUILD_TYPE=Debug -G "Unix Makefiles" ..
    cmake --build .
    ctest -VV

To build and run tests with coverage use `./build_with_coverage.sh`. You will need lcov installed for this.

#### Windows

MinGW 6.3.0 (with packages mingw-developer-toolkit, mingw32-base, mingw32-gcc-g++, msys-base and mingw32-pthreads-w32) and CMake 3.2 are required. Pathes to the CMake and MinGW binaries should be in your PATH variable. Also for VSCode IntelliSense to work environment variable MINGW_PATH need to be specified and should point to MinGW installation directory (C:\\MinGW by default). To build and run tests from the command line go to the cpp directory (`cd .\cpp`) and run `.\build.bat` or

    if not exist build mkdir build
    cd build
    cmake -DCMAKE_BUILD_TYPE=Debug -G "Unix Makefiles" ..
    cmake --build .
    ctest -VV

## Currently solved challenges

### Trie implementation - [Java](java/src/main/java/ru/nk/training/Trie.java)

[HackerRank - Tries: Contacts](https://www.hackerrank.com/challenges/ctci-contacts)

Implementation of Trie data structure designed to add words, count number of words by specified prefix, and to obtaining words that start with the specified prefix.

### Count stars - [Java](java/src/main/java/ru/nk/training/StarCounter.java)

[LeetCode - Number of Islands](https://leetcode.com/problems/number-of-islands)

For this problem, you should write a program which counts the number of stars visible in a bitmap image. An image consists of pixels, and each pixel is either black or white (represented by the characters `#` and `-`, respectively). All black pixels are considered to be part of the sky, and each white pixel is considered to be part of a star. White pixels that are adjacent vertically or horizontally are part of the same star.

### Checking intersection of rectangles - [Java](java/src/main/java/ru/nk/training/RectangleIntersectionChecker.java), [C++](cpp/src/rectangles_intersect.cpp)

You are given two rectangles, each defined by an upper-left (UL) corner and a lower-right (LR) corner. Both rectangles’ edges will always be parallel to the x or y axis. Write a function that determines whether the two rectangles overlap.

### Least Recently Used (LRU) cache - [Java](java/src/main/java/ru/nk/training/LruCache.java)

[LeetCode - LRU Cache](https://leetcode.com/problems/lru-cache)

Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: `get` and `put`.
 - `get(key)` - Get the value of the key if the key exists in the cache, otherwise return `null`. 
 - `put(key, value)` - Set or insert the value if the key is not already present. 

When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

### Left rotation of an array - [Java](java/src/main/java/ru/nk/training/ArrayRotator.java)

[HackerRank - Arrays: Left Rotation](https://www.hackerrank.com/challenges/ctci-array-left-rotation),
[HackerRank - Left Rotation](https://www.hackerrank.com/challenges/array-left-rotation),
[GeeksforGeeks - Program for array rotation](https://www.geeksforgeeks.org/array-rotation)

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

[HackerRank - Recursion: Davis' Staircase](https://www.hackerrank.com/challenges/ctci-recursive-staircase),
[GeeksforGeeks - Count ways to reach the n’th stair](https://www.geeksforgeeks.org/count-ways-reach-nth-stair)

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

[HackerRank - Stock Maximize](https://www.hackerrank.com/challenges/stockmax)

Your algorithms have become so good at predicting the market that you now know what the share price of Wooden Orange Toothpicks Inc. (WOT) will be for the next N days. Each day, you can either buy one share of WOT, sell any number of shares of WOT that you own, or not make any transaction at all. What is the maximum profit you can obtain with an optimum trading strategy?

### Rotate matrix - [Java](java/src/main/java/ru/nk/training/MatrixRotator.java)

[LeetCode - Rotate Image](https://leetcode.com/problems/rotate-image)

Given an image represented by a NxN matrix, where each pixel in the image is 4 bytes, write a method to rotate the image by 90 degrees. Can you do this in place?

### Check if linked list has cycle - [Java](java/src/main/java/ru/nk/training/LinkedListCycleChecker.java)

[LeetCode - Linked List Cycle](https://leetcode.com/problems/linked-list-cycle)

You are given a linked list with at least one node that is either null-terminated (acyclic) or ends in a cycle (cyclic). Write a function that takes a pointer to the head of a list and determines whether the list is cyclic or acyclic. Your function should return false if the list is acyclic and true if it is cyclic. You may not modify the list in any way.

### Integer/String Conversions - [Java](java/src/main/java/ru/nk/training/IntegerStringConverter.java)

Write two conversion routines. The first routine converts a string to a signed integer. You may assume that the string contains only digits and the minus character ('-'), that it is a properly formatted integer number, and that the number is within the range of an int type. The second routine converts a signed integer stored as an int back to a string.

### Add one to big integer - [Python](python/src/add_one_to_big_integer.py)

[LeetCode - Plus One](https://leetcode.com/problems/plus-one)

Given a non-negative integer represented as a non-empty array of digits, plus one to the integer. You may assume the integer do not contain any leading zero, except the number 0 itself. The digits are stored such that the most significant digit is at the head of the list.

### Interval map - [Java](java/src/main/java/ru/nk/training/IntervalMap.java)

Implementation of interval map - data structure that maps half-open intervals of comparable keys to values. Value can be queried for a specific key, result will be the value mapped to the interval containing the key.

### Evaluate Reverse Polish Notation - [Python](python/src/evaluate_reverse_polish_notation.py)

[LeetCode - Evaluate Reverse Polish Notation](https://leetcode.com/problems/evaluate-reverse-polish-notation)

Evaluate the value of an arithmetic expression in Reverse Polish Notation. Valid operators are `+`, `-`, `*`, `/`. Each operand may be an integer or another expression. Result of division operation is rounded to nearest integer.

### Count ways of making change - [C++](cpp/src/count_ways_to_make_change.cpp)

[HackerRank - The Coin Change Problem](https://www.hackerrank.com/challenges/coin-change)

You have `m` types of coins available in infinite quantities where the value of each coin is given in the array `C`. Can you determine the number of ways of making change for `n` units using the given types of coins? For example, if `m = 4`, and `C = [8, 3, 1, 2]`, we can make change for `n = 3` units in three ways: `{1, 1, 1}`, `{1, 2}`, and `{3}`.

### Count number of pairs by difference - [Java](java/src/main/java/ru/nk/training/PairsByDifferenceCounter.java)

[HackerRank - Pairs](https://www.hackerrank.com/challenges/pairs),
[GeeksforGeeks - Count all distinct pairs with difference equal to k](https://www.geeksforgeeks.org/count-pairs-difference-equal-k)

Given `N` integers, count the number of pairs of integers whose difference is `K`.

### Find minimum sum with different indices - [Java](java/src/main/java/ru/nk/training/MinimumSumWithDifferentIndicesFinder.java)

[HackerRank - Week of Code 33 - Twin Arrays](https://www.hackerrank.com/contests/w33/challenges/twin-arrays)

You are given two arrays `A` and `B` each containing `n` integers. You need to choose exactly one number from `A` and exactly one number from `B` such that the index of the two chosen numbers is not same and the sum of the two chosen values is minimum.

### Count patterns - [Python](python/src/count_patterns.py)

[HackerRank - Week of Code 33 - Pattern Count](https://www.hackerrank.com/contests/w33/challenges/pattern-count)

A string `s` contains many patterns of the form `1(0+)1` where `(0+)` represents any non-empty consecutive sequence of zeros. The patterns are allowed to overlap. For example, consider string `1101001`, we can see there are two consecutive sequences `1(0)1` and `1(00)1` which are of the form `1(0+)1`. Find the total number of patterns of the form `1(0+)1` that occur in `s`.

### Find longest palindromic subsequence - [Java](java/src/main/java/ru/nk/training/LongestPalindromicSubsequenceFinder.java)

[LeetCode -  Longest Palindromic Substring](https://leetcode.com/problems/longest-palindromic-substring)

Given a string, find a longest palindromic subsequence in it. A longest palindromic subsequence is a sequence that appears in the same relative order, but not necessarily contiguous (not substring) and palindrome in nature (means the subsequence will read same from the front and back).

### Given a string find out if symbols can be rearranged to a palindrom - [Python](python/src/can_rearrange_to_palindrom.py)

[GeeksforGeeks - Check if characters of a given string can be rearranged to form a palindrome](https://www.geeksforgeeks.org/check-characters-given-string-can-rearranged-form-palindrome),
[LeetCode - Palindrome Permutation](https://leetcode.com/articles/palindrome-permutation)

Given a string find out if symbols can be rearranged to a palindrom.

### Find longest palindromic subsequence that can be obtained with specific transformations - [Java](java/src/main/java/ru/nk/training/LongestPalindromicSubsequenceWithTransformationFinder.java)

[HackerRank - Week of Code 33 - Transform to Palindrome](https://www.hackerrank.com/contests/w33/challenges/transform-to-palindrome)

The alphabet system consists of `n` letters, denoted by the integers from `1` to `n`. Some letters can be transformed to other letters. A transformation is denoted by a pair of two letters, `x -> y`. Using this transformation, you can replace letter `x` with letter `y`. Transformations also have additional properties:
 - If letter `x` can be transformed to letter `y` using a transformation, then letter `y` can be transformed to letter `x` as well.
 - If letter `x` can be transformed to letter `y` and letter `y` can be transformed to letter `z`, then letter `x` can be transformed to letter `z` as well.
 
You are given a sequence `s` comprising of `m` letters. You are given `k` transformations that can be applied to `s`. You may apply transformations to zero or more letters in the sequence. When a transformation is applied to a letter, the other letters of the string remain unaffected. You can also apply a single transformation multiple times on the same sequence. Find the length of the longest possible palindromic subsequence after applying zero or more transformations on the letters of the given sequence.

### Word Break - [Java](java/src/main/java/ru/nk/training/StringByWordsSplitter.java)

[LeetCode - Word Break](https://leetcode.com/problems/word-break)

Given a non-empty string `s` and a dictionary of words containing non-empty words, determine if `s` can be segmented into a space-separated sequence of one or more dictionary words. You may assume the dictionary does not contain duplicate words. For example, given `s = "leetcode"`, `dict = ["leet", "code"]` return space separated sentence `"leet code"`.

### Find number of available positions in matrix - [Java](java/src/main/java/ru/nk/training/MatrixAvailablePositionsCounter.java)

[HackerRank - Gridland Metro](https://www.hackerrank.com/challenges/gridland-metro)

You are given `n x m` matrix and a list of tracks that always run in straight horizontal lines along a row. In other words, the start and end points of a track are `(r, c1)` and `(r, c2)`, where `r` represents the row number, `c1` represents the starting column, and `c2` represents the ending column of the track. Determine the number of cell that is not occupied by a track.

### Recursive Digit Sum - [Python](python/src/get_recursive_digit_sum.py)

[HackerRank - Super Digit](https://www.hackerrank.com/challenges/super-digit)

We define super digit of an integer `x` using the following rules:
- If `x` has only 1 digit, then its super digit is `x`.
- Otherwise, the super digit of `x` is equal to the super digit of the digit-sum of `x`. Here, digit-sum of a number is defined as the sum of its digits.

You are given two numbers `n` and `k`. You have to calculate the super digit of `P`. `P` is created when number `n` is concatenated `k` times.

### Find Minimum Spanning Tree (MST) - [Python](python/src/minimum_spanning_tree.py)

[HackerRank - Prim's (MST): Special Subtree](https://www.hackerrank.com/challenges/primsmstsub)

Given a graph which consists of several edges connecting the N nodes in it. It is required to find a subgraph of the given graph with the following properties:
- The subgraph contains all the nodes present in the original graph.
- The subgraph is of minimum overall weight (sum of all edges) among all such subgraphs.
- It is also required that there is exactly one, exclusive path between any two nodes of the subgraph.

### Min Stack - [Java](java/src/main/java/ru/nk/training/MinStack.java)

[LeetCode - Min Stack](https://leetcode.com/problems/min-stack)

Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
- `push(x)` - Push element x onto stack.
- `pop()` - Removes the element on top of the stack.
- `peek()` - Get the top element.
- `getMin()` - Retrieve the minimum element in the stack.

### Balanced Brackets - [Java](java/src/main/java/ru/nk/training/BalancedBracketsChecker.java)

[HackerRank - Balanced Brackets](https://www.hackerrank.com/challenges/balanced-brackets),
[LeetCode - Valid Parentheses](https://leetcode.com/problems/valid-parentheses)

A bracket is considered to be any one of the following characters: `(`, `)`, `{`, `}`, `[`, or `]`. Two brackets are considered to be a matched pair if the an opening bracket (i.e., `(`, `[`, or `{`) occurs to the left of a closing bracket (i.e., `)`, `]`, or `}`) of the exact same type. There are three types of matched pairs of brackets: `[]`, `{}`, and `()`. A matching pair of brackets is not balanced if the set of brackets it encloses are not matched. For example, `{[(])}` is not balanced because the contents in between `{` and `}` are not balanced. The pair of square brackets encloses a single, unbalanced opening bracket, `(`, and the pair of parentheses encloses a single, unbalanced closing square bracket, `]`. By this logic, we say a sequence of brackets is considered to be balanced if the following conditions are met:
- It contains no unmatched brackets.
- The subset of brackets enclosed within the confines of a matched pair of brackets is also a matched pair of brackets.

Given  strings of brackets, determine whether each sequence of brackets is balanced.

### Is This a Binary Search Tree? - [Java](java/src/main/java/ru/nk/training/BinarySearchTreeChecker.java)

[HackerRank - Trees: Is This a Binary Search Tree?](https://www.hackerrank.com/challenges/ctci-is-binary-search-tree)

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

[HackerRank - Week of Code 35 - Highway Construction](https://www.hackerrank.com/contests/w35/challenges/highway-construction)

You are planning the next FIFA World Cup and you are counting the number of highways that need to be built to connect the cities with the venue. Your country has n cities and all cities lie on a single straight road called “Highway Road”. If you want to go from City `x` to City `y` (where `x` ≤ `y`), you need to go through city `x`, `x + 1`, `x + 2`, .., `y - 1`, `y`. The requirements for the highways are as follows:
 - All games will be held in the `n`-th city.
 - New bidirectional roads, called "Super Highways", need to be built such that it is possible to visit the `n`-th city from any other city directly. 

You also have the cost to fulfil the second condition. The engineering team knows that if the length of a Super Highway is `l`, then it will cost `l`<sup>`k`</sup>, where k is an integer constant. The length of Super Highway between city `x` and `y` is `|x - y|`. For this problem, you need to find only a rough estimation of the cost, hence, find Total Cost Modulo 1000000009.

### 3D Surface Area - [C++](cpp/src/surface_area.cpp)

[HackerRank - Week of Code 35 - 3D Surface Area](https://www.hackerrank.com/contests/w35/challenges/3d-surface-area)

Madison, is a little girl who is fond of toys. Her friend Mason works in a toy manufacturing factory. Mason has a 2D board A of size `H` x `W` with `H` rows and `W` columns. The board is divided into cells of size 1 x 1 with each cell indicated by it's coordinate `(i, j)`. The cell `(i, j)` has an integer `A`<sub>`ij`</sub> written on it. To create the toy Mason stacks `A`<sub>`ij`</sub> number of cubes of size 1 x 1 x 1 on the cell `(i, j)`. Given the description of the board showing the values of `A`<sub>`ij`</sub> and that the price of the toy is equal to the 3D surface area find the price of the toy.

### Map Binary Tree by Levels - [Java](java/src/main/java/ru/nk/training/BinaryTreeLevelMapper.java)

Given a binary tree, map it to lists of values by level, breadth-first. If any node is absent in the tree there should be an empty element in the level list. For example the binary tree
 
        1
     /    \
    2      3
     \
      4
 
should be mapped to lists
 
    [1]
    [2, 3]
    [X, 4, X, X]

### Race Against Time - [C++](cpp/src/race_min_time.cpp)

[HackerRank - Week of Code 36 - A Race Against Time](https://www.hackerrank.com/contests/w36/challenges/a-race-against-time)

A relay race is being organised in a school for middle school students by two high school students, Mason and Madison. Mason starts with the baton and Madison receives the baton at the final destination. There are middle school students in between Mason and Madison, and each of their heights is given. Mason's height, too, is given. Initially, the baton is with Mason and it is passed to the destination in a manner similar to a relay race.
 
1. At any moment,the current baton carrier has an option to hand over the baton to the student at the current position or to continue to the next position. However, if the student at the given position is taller than the current baton carrier, it is mandatory to hand over the baton because it is a safer option according to Mason.
1. It takes one second to move between consecutive positions.
1. Whenever the baton is handed over , there is a time and price associated with it.
1. The time taken, in seconds, is the absolute difference between the heights of the current baton carrier and the student to whom the baton is handed.
1. The student to whom the baton is passed charges a given price.
 
Note: Price charged can be negative too.
 
The baton must be sent to Madison in the minimum possible sum of time and price. Complete the function Solve which takes the number of middle school students, Mason's height, and heights and prices charged by middle school students as input, and return the minimum possible sum of time and price required for the baton to reach Madison.

### Flatten Binary Tree to Linked List - [Java](java/src/main/java/ru/nk/training/BinaryTreeFlattener.java)

[LeetCode - Flatten Binary Tree to Linked List](https://leetcode.com/problems/flatten-binary-tree-to-linked-list)

Given a binary tree, flatten it to a linked list in-place. For example, given

        1
       / \
      2   5
     / \   \
    3   4   6

The flattened tree should look like:

    1
     \
      2
       \
        3
         \
          4
           \
            5
             \
              6

### Trapping Rain Water - [Java](java/src/main/java/ru/nk/training/TrappedWaterFinder.java)

[LeetCode - Trapping Rain Water](https://leetcode.com/problems/trapping-rain-water)

Given `n` non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.

For example, given `[0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1]`, return `6`.
                                
                                       |###|
                   |###|~~~~~~~~~~~~~~~|###||###|~~~~~|###|
         |###|~~~~~|###||###|~~~~~|###||###||###||###||###||###|
    ============================================================
      0    1    0    2    1    0    1    3    2    1    2    1  

### Word Pattern - [Python](python/src/is_word_pattern.py)

[LeetCode - Word Pattern](https://leetcode.com/problems/word-pattern)

Given a pattern and a string `str`, find if `str` follows the same pattern. Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in `str`. Examples:

 - pattern = `"abba"`, str = `"dog cat cat dog"` should return true.
 - pattern = `"abba"`, str = `"dog cat cat fish"` should return false.
 - pattern = `"aaaa"`, str = `"dog cat cat dog"` should return false.
 - pattern = `"abba"`, str = `"dog dog dog dog"` should return false.

Notes: You may assume pattern contains only lowercase letters, and `str` contains lowercase letters separated by a single space. 

### Permuting Two Arrays - [C++](cpp/src/can_permute_for_sum.cpp)

[HackerRank - Permuting Two Arrays](https://www.hackerrank.com/challenges/two-arrays)

Consider two `n`-element arrays of integers, `A` and `B`. You want to permute them into some `A'` and `B'` such that the relation `a_i' + b_i' >= k` holds for all `i` where `0 <= i < n`. For example, if `A = [0, 1]`, `B = [0, 2]`, and `k = 1`, a valid `A'`, `B'` satisfying our relation would be `A' = [1 , 0]` and `B' = [0, 2]`.

Print `YES` if some permutations `A'` and `B'`, exist satisfying the relation above. If no valid permutations exist, print `NO` instead.

### Unbounded knapsack - [Python](python/src/unbounded_knapsack.py)

[HackerRank - Knapsack](https://www.hackerrank.com/challenges/unbounded-knapsack),
[GeeksforGeeks - Unbounded Knapsack (Repetition of items allowed)](https://www.geeksforgeeks.org/unbounded-knapsack-repetition-items-allowed)

Given a knapsack weight `W` and a set of `n` items with certain value `val`<sub>`i`</sub> and weight `wt`<sub>`i`</sub>, we need to calculate minimum amount that could make up this quantity exactly. This is different from classical Knapsack problem, here we are allowed to use unlimited number of instances of an item.

### Journey to the Moon - [Python](python/src/count_possible_astronaut_pairs.py)

[HackerRank - Journey to the Moon](https://www.hackerrank.com/challenges/journey-to-the-moon)

The member states of the UN are planning to send two people to the Moon. But there is a problem. In line with their principles of global unity, they want to pair astronauts of two different countries. There are `N` trained astronauts numbered from `0` to `N - 1`. But those in charge of the mission did not receive information about the citizenship of each astronaut. The only information they have is that some particular pairs of astronauts belong to the same country. Your task is to compute in how many ways they can pick a pair of astronauts belonging to different countries. Assume that you are provided enough pairs to let you identify the groups of astronauts even though you might not know their country directly. For instance, if `1`, `2`, `3` are astronauts from the same country; it is sufficient to mention that `(1, 2)` and `(2, 3)` are pairs of astronauts from the same country without providing information about a third pair `(1, 3)`.

### Sum of subnumbers - [Python](python/src/subnumbers_sum.py)

[HackerRank - Sam and sub-strings](https://www.hackerrank.com/challenges/sam-and-substrings)

Samantha and Sam are playing a game. They have `N` balls in front of them, each ball numbered from `0` to `9`, except the first ball which is numbered from `1` to `9`. Samantha calculates all the sub-strings of the number thus formed, one by one. If the sub-string is `S`, Sam has to throw S candies into an initially empty box. At the end of the game, Sam has to find out the total number of candies in the box, `T`. As `T` can be large, Samantha asks Sam to tell `T % (10^9+7)` instead. If Sam answers correctly, he can keep all the candies. Sam can't take all this Maths and asks for your help.

### Ice Cream Parlor - [C++](cpp/src/find_pair_by_sum.cpp)

[HackerRank - Hash Tables: Ice Cream Parlor](https://www.hackerrank.com/challenges/ctci-ice-cream-parlor)

Each time Sunny and Johnny take a trip to the Ice Cream Parlor, they pool together `m` dollars for ice cream. On any given day, the parlor offers a line of `n` flavors. Each flavor, `i`, is numbered sequentially with a unique ID number from `1` to `n` and has a cost, `cost`<sub>`i`</sub>, associated with it.

Given the value of `m` and the cost of each flavor for `t` trips to the Ice Cream Parlor, help Sunny and Johnny choose two distinct flavors such that they spend their entire pool of money during each visit. For each trip to the parlor, print the ID numbers for the two types of ice cream that Sunny and Johnny purchase as two space-separated integers on a new line. You must print the smaller ID first and the larger ID second.

Note: Two ice creams having unique IDs `i` and `j` may have the same cost (i.e., `cost`<sub>`i`</sub> == `cost`<sub>`j`</sub>).

### Sort huge numbers - [C++](cpp/src/sort_huge_numbers.cpp)

[HackerRank - Big Sorting](https://www.hackerrank.com/challenges/big-sorting)

Consider an array of numeric strings, `unsorted`, where each string is a positive number with anywhere from 1 to 10<sup>6</sup> digits. Sort the array's elements in non-decreasing (i.e., ascending) order of their real-world integer values and print each element of the sorted array on a new line.

### Is abbreviation - [C++](cpp/src/is_abbreviation.cpp)

[HackerRank - Abbreviation](https://www.hackerrank.com/challenges/abbr)

You can perform the following operation on some string, `a`:
- Capitalize zero or more of `a`'s lowercase letters at some index `i` (i.e., make them uppercase).
- Delete all of the remaining lowercase letters in `a`.

Given `q` queries in the form of two strings, `a` and `b`, determine if it's possible to make `a` equal to `b` by performing the above operation on `a`. If `a` can be transformed into `b`, print `YES` on a new line; otherwise, print `NO`.

### Maximize loot - [C++](cpp/src/maximize_loot.cpp)

A thief finds much more loot than his bag can fit. Help him to find the most valuable combination of items assuming that any fraction of a loot item can be put into his bag. The goal of this code problem is to implement an algorithm for the fractional knapsack problem. The first line of the input contains the number `n` of items and the capacity `W` of a knapsack. The next `n` lines define the values and weights of the items. The `i`-th line contains integers `v`<sub>`i`</sub> and `w`<sub>`i`</sub> — the value and the weight of `i`-th item, respectively.

### Maximum advertisement revenue - [C++](cpp/src/maximum_advertisement_revenue.cpp)

Given two sequences `a`<sub>`1`</sub>, `a`<sub>`2`</sub>, ..., `a`<sub>`n`</sub> (`a`<sub>`i`</sub> is the profit per click of the `i`-th ad) and `b`<sub>`1`</sub>, `b`<sub>`2`</sub>, ..., `b`<sub>`n`</sub> (`b`<sub>`i`</sub> is the average number of clicks per day of the `i`-th slot), we need to partition them into `n` pairs (`a`<sub>`i`</sub>, `b`<sub>`j`</sub>) such that the sum of their products is maximized.

### Maximum number of prizes - [C++](cpp/src/maximum_number_of_prizes.cpp)

The goal of this problem is to represent a given positive integer `n` as a sum of as many pairwise distinct positive integers as possible. That is, to find the maximum `k` such that `n` can be written as `a`<sub>`1`</sub> + `a`<sub>`2`</sub> + ..., + `a`<sub>`k`</sub> where `a`<sub>`1`</sub>, ..., `a`<sub>`k`</sub> are positive integers and `a`<sub>`i`</sub> != `a`<sub>`j`</sub> for all 1 ≤ `i` < `j` ≤ `k`.

### Money change - [C++](cpp/src/money_change.cpp)

The goal in this problem is to find the minimum number of coins needed to change the input value (an integer) into coins with denominations 1, 5, and 10.

### Maximum salary - [C++](cpp/src/maximum_salary.cpp)

Compose the largest number out of a set of integers. The first line of the input contains an integer `n`. The second line contains integers `a`<sub>`1`</sub>, `a`<sub>`2`</sub>, ..., `a`<sub>`n`</sub>. Output the largest number that can be composed out of `a`<sub>`1`</sub>, `a`<sub>`2`</sub>, ..., `a`<sub>`n`</sub>.

### Has negative cycle - [Python](python/src/has_negative_cycle.py)

Given an directed graph with possibly negative edge weights and with `n` vertices and `m` edges, check whether it contains a cycle of negative weight.

### Is bipartite - [Python](python/src/is_bipartite.py)

Given an undirected graph with `n` vertices and `m` edges, check whether it is bipartite. An undirected graph is called bipartite if its vertices can be split into two parts such that each edge of the graph joins to vertices from different parts. Bipartite graphs arise naturally in applications where a graph is used to model connections between objects of two different types (say, boys and girls; or students and dormitories).

### Connected components - [Python](python/src/vertices_to_components.py)

Given an undirected graph with `n` vertices and `m` edges, compute the number of connected components in it.

### Topological sort - [Python](python/src/topological_sort.py)

Compute a topological ordering of a given directed acyclic graph (DAG) with `n` vertices and `m` edges.

### Convert array into heap - [C++](cpp/include/heapify.hpp)

The first step of the HeapSort algorithm is to create a heap from the array you want to sort. Your task is to implement this first step and convert a given array of integers into a heap. You will do that by applying a certain number of swaps to the array. Swap is an operation which exchanges elements a `i` and a `j` of the array a for some `i` and `j`. You will need to convert the array into a heap using only O(n) swaps, as was described in the lectures. Note that you will need to use a min-heap instead of a max-heap in this problem.

### Parallel processing - [Python](python/src/parallel_processing.py)

You have a program which is parallelized and uses `n` independent threads to process the given list of `m` jobs. Threads take jobs in the order they are given in the input. If there is a free thread, it immediately takes the next job from the list. If a thread has started processing a job, it doesn’t interrupt or stop until it finishes processing the job. If several threads try to take jobs from the list simultaneously, the thread with smaller index takes the job. For each job you know exactly how long will it take any thread to process this job, and this time is the same for all the threads. You need to determine for each job which thread will process it and when will it start processing.

### Counting Inversions - [C++](cpp/src/sort_with_invertions_counting.cpp)

[HackerRank - Merge Sort: Counting Inversions](https://www.hackerrank.com/challenges/ctci-merge-sort),
[GeeksforGeeks - Count Inversions in an array](https://www.geeksforgeeks.org/counting-inversions)

In an array, `arr`, the elements at indices `i` and `j` (where `i` < `j`) form an inversion if `arr`<sub>`i`</sub> > `arr`<sub>`j`</sub>. In other words, inverted elements `arr`<sub>`i`</sub> and `arr`<sub>`j`</sub> are considered to be "out of order". To correct an inversion, we can swap adjacent elements.

For example, consider `arr = [2, 4, 1]`. It has two inversions: (2, 1) and (4, 1). To sort the array, we must perform the following two swaps to correct the inversions: swap(`arr`<sub>`1`</sub>, `arr`<sub>`2`</sub>) and swap(`arr`<sub>`0`</sub>, `arr`<sub>`1`</sub>)

Print the number of inversions that must be swapped to sort an array on a new line.

### Maximum pairwise product - [C++](cpp/src/maximum_pairwise_product.cpp)

[GeeksforGeeks - Find a pair with maximum product in array of Integers](https://www.geeksforgeeks.org/return-a-pair-with-maximum-product-in-array-of-integers)

Find the maximum product of two distinct numbers in a sequence of non-negative integers.

### Fibonacci numbers - [C++](cpp/src/fibonacci_numbers.cpp)

1. Given an integer `n`, find the `n`-th Fibonacci number `F`<sub>`n`</sub>.
1. Given two integers `n` and `m`, output `F`<sub>`n`</sub> `mod` `m` (that is, the remainder of `F`<sub>`n`</sub> when divided by `m`).
1. Given an integer `n`, find the last digit of the sum `F`<sub>`0`</sub> + `F`<sub>`1`</sub> + ... + `F`<sub>`n`</sub>.
1. Given two non-negative integers `m` and `n`, where `m` ≤ `n`, find the last digit of the sum `F`<sub>`m`</sub> + `F`<sub>`m+1`</sub> + ... + `F`<sub>`n`</sub>.

### GCD and LCM - [C++](cpp/src/gcd.cpp)

1. Given two integers `a` and `b`, find their greatest common divisor.
1. Given two integers `a` and `b`, find their least common multiple.

### Majority Element - [Python](python/src/find_majority_element.py)

Given a sequence of elements `a`<sub>`1`</sub>, `a`<sub>`2`</sub>, ... , `a`<sub>`n`</sub>, you would like to check whether it contains an element (majority element) that appears more than `n`/2 times. 

### Organizing a Lottery - [Python](python/src/count_segments_for_points.py)

You are organizing an online lottery. To participate, a person bets on a single integer. You then draw several ranges of consecutive integers at random. A participant’s payoff then is proportional to the number of ranges that contain the participant’s number minus the number of ranges that does not contain it. You need an efficient algorithm for computing the payoffs for all participants.

You are given a set of points on a line and a set of segments on a line. The goal is to compute, for each point, the number of segments that contain this point.
