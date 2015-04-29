import stack


class E1Error(Exception):
    '''Constructs an empty stack called history'''
    history = stack.Stack()


def raiser(x, y):
    ''' Takes 2 parameters, if the first is an E1Error it pushes the second\
    parameter onto the stack and raises an E1 error. If the first parameter\
    is not an E1Error and cannot be raised to the second parameter then it\
    raises a TypeError'''
    if isinstance(x, E1Error):
        x.history.push(y)
        print x.history.pop()
        raise E1Error()
    else:
        pow(x, y)


if __name__ == '__main__':
    try:
        x = E1Error()
        y = 1
        raiser(x, y)
    except E1Error:
        print 'got e1error'
