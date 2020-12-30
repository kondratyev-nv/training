"""
Number coloring

There is a sequence of integer numbers a1,a2,...,an. You need to color the elements of the sequence so that for 
an arbitrary color, all elements of this color should be divisible by the minimal element of this color.
You need to minimize the number of colors used.

For example, you can color elements [40,10,60] in one color, because all of them are divisable by 10. Every color
can be used any number of times (including using any color exactly once). Elements of the same color are not obliged to 
be adjacent.

For example, if a=[6,2,3,4,12], then you need to use two colors: 6,3,12 can be colored in the first color (6,3,12 are 
divisable by 3), and 2 and 4 in the second color (2 and 4 are divisable by 2). For example, if a=[10,7,15], then you
need 3 colors (each element should be colored in different color).

Input:
First row contains one integer n (1<=n<=100), where n is the lenght of the sequence.
Second row contains n integers a1,a2,...,an (1<=ai<=100). Numbers in the sequence can be repeated.

Output:
Print the minimal number of colors needed to color the elements correctly.
"""

def number_coloring(numbers):
    if len(numbers) == 0:
        return 0

    numbers.sort()
    if len(numbers) == 1 or numbers[0] == 1:
        return 1

    cnt = 0
    next_start = 0
    color = [0] * len(numbers)
    while next_start != -1:
        cnt += 1
        cur_start = next_start
        next_start = -1
        cur = numbers[cur_start]
        for i in range(cur_start, len(numbers)):
            if color[i] != 0:
                continue
            if numbers[i] % cur == 0:
                color[i] = cnt
            elif next_start == -1:
                next_start = i
    
    return cnt