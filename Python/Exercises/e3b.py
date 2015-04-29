from node import Node
from e3a import make_tree


def make_list(root):
    sums = []
    if root == None:
        return sums
    if root.key == None:
        sums.append(0)

    else:
        sums.append(root.key)
        if root.left == None:
            sums.append(0)
        else:
            sums.append(make_list(root.left))
        if root.right == None:
            sums.append(0)
        else:
            sums.append(make_list(root.right))
    return sums


def find_leaf(the_list):
    for item in the_list:
        if type(item) == list:
            return find_leaf(item)
        if the_list[the_list.index(item) + 1] == 0 and \
           the_list[the_list.index(item) + 2] == 0:
            return item


def flatten(the_list):
    new_list = []
    for item in the_list:
        if type(item) == list:
            sub_list = flatten(item)
            for item in sub_list:
                new_list.append(item)
        else:
            new_list.append(item)
    return new_list


def find_depth(number, preorder, inorder):
    root = make_tree(preorder, inorder)
    count = 1
    if root.key == number:
        return count
    else:
        while get_direction(number, root, inorder) != 'found':
            if get_direction(number, root, inorder) == 'left':
                root = root.left
                count += 1
            elif get_direction(number, root, inorder) == 'right':
                root = root.right
                count += 1
        return count


def get_direction(number, root, inorder):
    if inorder.index(number) < inorder.index(root.key):
        return 'left'
    elif inorder.index(number) > inorder.index(root.key):
        return 'right'
    else:
        return 'found'


def make_preorder(root):
    nodes = []
    if root != None:
        nodes.append(root.key)
        nodes.extend(make_preorder(root.left))
        nodes.extend(make_preorder(root.right))
    return nodes


def make_inorder(root):
    nodes = []
    if root != None:
        nodes.extend(make_inorder(root.left))
        nodes.append(root.key)
        nodes.extend(make_inorder(root.right))
    return nodes


def sum_to_deepest(root):
    preorder = make_preorder(root)
    inorder = make_inorder(root)
    root = make_tree(preorder, inorder)
    l1 = make_list(root)
    flat_l1 = flatten(l1)
    max_sum = 0
    max_depth = 0
    try:
        leaf = find_leaf(l1[1:])
    except:
        return flat_l1[0]
    if len(flat_l1) == 0:
        return max_sum
    if len(flat_l1) == 3:
        max_sum = root.key
    while len(flat_l1) != 3:
        index = flat_l1.index(leaf)
        a_sum = 0
        for item in flat_l1[:index + 1]:
            a_sum += item
        flat_l1.pop(index)
        flat_l1.pop(index)
        if find_depth(leaf, preorder, inorder) > max_depth:
            max_depth = find_depth(leaf, preorder, inorder)
            max_sum = a_sum
        if find_depth(leaf, preorder, inorder) >= max_depth:
            if a_sum > max_sum:
                max_sum = a_sum
        leaf = find_leaf(flat_l1)
        a_sum = 0
    return max_sum
