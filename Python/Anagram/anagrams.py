from textlist import TextList
from anagram import make_reader

def permutate(word):
    """permutate a sequence and return a list of the permutations"""
    if not word:
        return [word]  # is an empty sequence
    else:
        temp = []
        for letter in range(len(word)):
            word_without_letter = word[:letter] + word[letter+1:]
            for m in permutate(word_without_letter):
                temp.append(word[letter:letter+1] + m)
    return temp

anagram_reader = make_reader(permutate('catharsis'))
dict1 = TextList(open('dictionary.txt', 'r'))
print dict1
#anagram_reader.uniquify(dict1)
