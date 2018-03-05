def is_word_pattern(pattern, sentence):
    """
    Given a pattern and a string str, find if str follows the same pattern.
    Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.
    Examples:
     - pattern = "abba", str = "dog cat cat dog" should return true.
     - pattern = "abba", str = "dog cat cat fish" should return false.
     - pattern = "aaaa", str = "dog cat cat dog" should return false.
     - pattern = "abba", str = "dog dog dog dog" should return false.
    Notes: You may assume pattern contains only lowercase letters, and str contains lowercase letters separated by a single space. 
    """
    words = sentence.split(' ')
    if len(words) != len(pattern):
        return False
    pattern_match = {}
    word_match = {}
    for index in range(0, len(words)):
        s = pattern[index]
        w = words[index]
        if s in pattern_match and w in word_match:
            if pattern_match[s] != w:
                return False
        elif s not in pattern_match and w not in word_match:
            pattern_match[s] = w
            word_match[w] = s
        else:
            return False
    return True
