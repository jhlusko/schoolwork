from e1a import E1Error
from e1a import raiser


def handler(a, b):
    ''' Takes two parameters and checks if the first is a callable function\
    and if the function can use the second parameter without raising an\
    error'''
    if callable(a) == False:
        print 'not callable'
    else:
        if isinstance(a(b), TypeError):
            print 'type'
        elif isinstance(a(b), E1Error):
            if b.history.is_empty():
                print 'empty stack'
            else:
                print b.history.pop()
        elif isinstance(a(b), Exception):
            print 'generic'
        else:
            print 'hunky-dory'

if __name__ == '__main__':

    def a(b):
        return b

    b = E1Error()
    handler(a, b)
