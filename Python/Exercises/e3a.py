from node import Node


def make_tree(preorder, inorder):
    '''(list, list) -> Node
    Return the root of the tree described by the lists.'''

    #if its an empty tree return None
    if preorder == [] or inorder == []:
        return None

    root = preorder[0]
    index = inorder.index(root)
    root_node = Node(preorder[0])
    #if both subtrees are empty
    if len(preorder) == 1:
        return root_node
    #if the left subtree is not empty
    if inorder.index(preorder[1]) < inorder.index(preorder[0]):
        root_node.left = make_tree(preorder[1: index + 1], inorder[:index])
        inorder_right_subtree = []
        for item in inorder:
            inorder_right_subtree.append(item)
        for count in range(len(preorder)):
            #if the item is not the root or on the left subtree
            #then count is the index of the root of the right subtree
            if inorder.index(preorder[count]) > index:
                root_node.right = make_tree(preorder[count:], \
                                            inorder_right_subtree)
                break
            else:
                #removes the root and left subtree, so that
                #inorder_right_subtree is an inorder list for the right subtree
                inorder_right_subtree.remove(preorder[count])
    #if the left subtree is empty
    elif inorder.index(preorder[1]) > inorder.index(preorder[0]):
        if len(preorder) == 2:
            root_node.right = Node(preorder[1])
            return root_node
        root_node.right = make_tree(preorder[index + 1:], inorder[index + 1:])
        inorder_left_subtree = []
        for item in inorder:
            inorder_left_subtree.append(item)
        for count in range(len(preorder)):
            #if the item is not the root or on the right subtree
            #then count is the index of the root of the left subtree
            if inorder.index(preorder[count]) < index:
                root_node.left = make_tree(preorder[count:], \
                                           inorder_left_subtree)
                break
            #removes the root and right subtree, so that inorder_left_subtree
            #is an inorder list for the left subtree
            else:
                inorder_left_subtree.remove(preorder[count])

    return root_node
