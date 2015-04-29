import unittest
from battleship import *


class goodPlayer(unittest.TestCase):
    '''Test the behaviour of a player.'''

    def setUp(self):
        '''Set up a player object.'''
        self.p1 = Player('p1', True)
        self.p2 = Player('p2', False)

    def testPlayer_list(self):
            self.assertEqual(player_list, [self.p1, self.p2], \
"bad player_list")

if __name__ == '__main__':
    unittest.main()
