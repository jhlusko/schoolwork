class TextList(object):
    '''A list of words and their frequencies.'''
    
    def __init__(self, filename):
        '''(TextList, str) -> NoneType
        Build a TextList from the contents of a file named filename.'''
        
        self.words = {}
        FILE = open(filename)
        for line in FILE:
            for word in line.split():
                self.words[word] = self.words.setdefault(word, 0) + 1
        FILE.close()

    def uniqify(self, tl):
        '''(TextList, TextList) -> NoneType
        Remove words (found in both this TextList and TextList tl) from both
        TextLists.'''
        
        for w in self.words.keys():
            if w in tl.words:
                del self.words[w]
                del tl.words[w]
