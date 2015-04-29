import unittest
from battleship import *


class goodBoard(unittest.TestCase):

    def setUp(self):
        self.p1 = Player('p1', True)
        self.p2 = Player('p2', False)
        player_list = [self.p1, self.p2]
        self.p1board = Board(self.p1, 5)

    def testPlayer(self):
        self.assertEqual(self.p1board.player, self.p1, "player is not p1")


if __name__ == '__main__':
    unittest.main()
