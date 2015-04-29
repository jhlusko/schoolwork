import unittest
from battleship import *


class nonemptyBoard(unittest.TestCase):
    '''Test the behaviour of a board object'''

    def setUp(self):
        '''Set up an empty board.'''
        self.p1 = Player('p1', True)
        self.p2 = Player('p2', False)
        player_list = [self.p1, self.p2]
        self.p1board = Board(self.p1, 4)
        self.p1board.addShip("Destroyer", (0, 0), "right")
        self.p1board.addShip("Destroyer", (1, 1), "right")

    def testMoveSunk(self):
        '''Test that move() can sink a ship'''
        self.p1board.move(0, 0)
        self.p1board.move(1, 0)
        self.assertEqual(self.p1board.sunkcount, 1, "sunkcount should be 1")

if __name__ == '__main__':
    unittest.main()
