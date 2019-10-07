"""
Samantha and Sam are playing a game. They have N balls in front of them, 
each ball numbered from 0 to 9, except the first ball which is numbered from 1 to 9. 
Samantha calculates all the sub-strings of the number thus formed, one by one. 
If the sub-string is S, Sam has to throw S candies into an initially empty box. 
At the end of the game, Sam has to find out the total number of candies in the box, T. 
As T can be large, Samantha asks Sam to tell T % (10^9+7) instead. 
If Sam answers correctly, he can keep all the candies. 
Sam can't take all this Maths and asks for your help.
"""


def subnumbers_sum(n):
    MOD = 1000000007

    def __madd(x, y):
        return (x % MOD + y % MOD) % MOD

    s = 0
    m = 1
    for i in range(len(n) - 1, -1, -1):
        s = __madd(s, int(n[i]) * m * (i + 1))
        m = __madd(m * 10, 1)
    return s
