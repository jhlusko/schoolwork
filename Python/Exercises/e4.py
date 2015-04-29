class Node(object):
    '''A node in a linked list.'''

    def __init__(self, data):
        '''(Node, object) -> NoneType
        Create a Node containing data with None as next.'''

        self.data = data
        self.next = None


def to_string(front):
    '''(Node) -> str
    Return a list representation of the linked list front in the Python list
    style. If the list is circular, include ',...' at the end.'''

    res = '['

    if front:
        t = front

        # Treat the first node separately because we want one fewer commas than
        # there are nodes.
        res += str(t.data)
        t = t.next

        # Move along until we fall off or wrap back around.
        while t and t != front:
            res += ', %s' % t.data
            t = t.next

        # If we wrapped around, mark it as circular.
        if t == front:
            res += ', ...'

    res += ']'

    return res


def append(list1, list2):
    '''(Node, Node) -> Node
    Return the head of the linked list made by appending list2 to list1.
    Precondition: neither are circular.'''
    head = list1
    if head != None:
        while list1.next != None:
            list1 = list1.next
        list1.next = list2
        return head
    else:
        return list2


def toggle_circular(front):
    '''(Node) -> NoneType
    If linked list front is circular, make it non-circular. If linked list
    front is non-circular, make it circular. If front is None, do nothing.'''

    head = front
    while front:
        if front.next == head:
            front.next = None
            return head
        elif front.next == None:
            front.next = head
            return head
        else:
            front = front.next
    return head


def remove_values(front, v):
    '''(Node, object) -> Node
    Remove all Nodes from linked list front whose data are equal to v and
    return the front of the resulting linked list.
    Precondition: front is not circular.'''

    head = front
    if head != None:
        while head.data == v:
            head = head.next
            if not head:
                break
        parent = head
        while parent != None:
            child = parent.next
            if child:
                while child.data == v:
                    child = child.next
                    if child == None:
                        break
                parent.next = child
            parent = child
        return head
    else:
        return head

if __name__ == '__main__':
    lst = Node(1)
    lst.next = Node(2)  # Make it non-circular and append a second Node.
    lst.next.next = Node(11)
    lst.next.next.next = Node(12)
    lst.next.next.next.next = Node(11)
    lst.next.next.next.next.next = Node(1)
    print to_string(lst)
    lst = remove_values(lst, 1)
    print to_string(lst)
