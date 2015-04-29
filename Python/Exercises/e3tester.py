import unittest
from e3a import *


class tests(unittest.TestCase):


    def setUp(self):
        self.preorder = [10, 6, 8, 12, 3, 4, 11, 15]
        self.inorder = [8, 6, 3, 12, 4, 10, 11, 15]
        self.root = make_tree(self.preorder, self.inorder)

    def test1(self):
        self.assertEqual(self.root.key, 10, '10')
        self.assertEqual(self.root.left.key, 6, '6')
        self.assertEqual(self.root.left.left.key, 8, '8')
        self.assertEqual(self.root.left.right.key, 12, '12')
        self.assertEqual(self.root.left.left.left.key, 3, '3')
        self.assertEqual(self.root.left.left.right.key, 4, '4')
        self.assertEqual(self.root.right.key, 11, '11')
        self.assertEqual(self.root.right.right.key, 15, '15')


if __name__ == '__main__':
    unittest.main()

    #preorder = [10, 6, 8, 12, 3, 4, 11, 15]
    #inorder = [8, 6, 3, 12, 4, 10, 11, 15]
    #root = make_tree(preorder, inorder)
    #print root.key
    #print root.left.key
    #print root.left.left.key
    #print root.left.right.key
    #print root.left.right.left.key
    #print root.left.right.right.key
    #print root.right.key
    #print root.right.right.key