import StringIO
from textlist import TextList

def make_reader(L):
    '''(list of strs) -> reader
    Return an open reader containing each str in L on its own line.
    '''
    return StringIO.StringIO('\n'.join(L))




if __name__ == '__main__':
    anagram_reader=make_reader(anagrams('cat'))
