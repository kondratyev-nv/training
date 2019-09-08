'''
The population of HackerWorld is 10^9. Initially, none of the people 
are friends with each other. In order to start a friendship, two persons 
a and b have to shake hands, where 1 <= a, b <= 10^9. The friendship 
relation is transitive, that is if a and b shake hands with each other, 
a and friends of q become friends with b and friends of b.

You will be given q queries. After each query, you need to report 
the size of the largest friend circle (the largest group of friends) 
formed after considering that query.

For example, your list of queries is:

    1 2
    3 4
    2 3

First, 1 and 2 shake hands, forming a circle of 2. Next, 3 and 4 do 
the same. Now there are two groups of 2 friends. When 2 and 3 become 
friends in the next query, both groups of friends are added together 
to make a circle of 4 friends. We would print

    2
    2
    4

Solution:
The solution is to use disjoint set union data structure -  
https://en.wikipedia.org/wiki/Disjoint-set_data_structure, with additional
traking of size of components.
'''


class DisjointSet:
    '''
    Implementation of disjoint set union data structure.
    '''
    
    def __init__(self):
        self.roots_by_vertex = {}
        self.sized_by_root = {}

    def __get_root(self, x):
        '''
        Returns root of vertex x
        '''
        while self.roots_by_vertex[x] != x:
            self.roots_by_vertex[x] = self.roots_by_vertex[self.roots_by_vertex[x]]
            x = self.roots_by_vertex[x]
        return x

    def union(self, x, y):
        '''
        Connects two elements x and y. Returns size of connected component.
        '''
        if x not in self.roots_by_vertex:
            self.roots_by_vertex[x] = x
            self.sized_by_root[x] = 1
        if y not in self.roots_by_vertex:
            self.roots_by_vertex[y] = y
            self.sized_by_root[y] = 1
        
        rx = self.__get_root(x)
        ry = self.__get_root(y)
        if rx == ry: # do not allow cycles
            return self.sized_by_root[rx]
        
        (minr, maxr) = (rx, ry) if self.sized_by_root[rx] < self.sized_by_root[ry] else (ry, rx)
        self.roots_by_vertex[minr] = maxr
        self.sized_by_root[maxr] += self.sized_by_root[minr]
        del self.sized_by_root[minr]
        
        return self.sized_by_root[maxr]

    def is_connected(self, x, y):
        '''
        Checks if vertices x and y are connected by some path.
        '''
        return self.__get_root(x) == self.__get_root(y)
