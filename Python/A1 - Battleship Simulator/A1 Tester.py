import unittest
from battleship import *


class goodPlayer(unittest.TestCase):
    '''Test the behaviour of a player.'''

    def setUp(self):
        '''Set up a player object.'''
        self.p1 = Player('p1', True)
        self.p2 = Player('p2', False)

#1
    def testName(self):
        '''Test if player name is set correctly'''
        self.assertEqual(self.p1.name, 'p1', "incorrect name")
        self.assertEqual(self.p2.name, 'p2', "incorrect name")

#2
    def testIs_human(self):
        '''Test if is_human is set correctly'''
        self.assertEqual(self.p1.is_human, True, "is_human was not True")
        self.assertEqual(self.p2.is_human, False, "is_human was not False")


class goodShip(unittest.TestCase):
    '''Test the behaviour of a ship.'''

    def setUp(self):
        '''Set up a ship object'''
        self.AC = Ship("Aircraft Carrier", (0, 0), "right")
        self.BS = Ship("Battleship", (1, 1), "down")

#3
    def testName(self):
        '''Test if ship name is set correctly'''
        self.assertEqual(self.AC.name, "Aircraft Carrier", "ship isn't \
named 'Aircraft Carrier'")
        self.assertEqual(self.BS.name, "Battleship", "ship isn't named \
'Aircraft Carrier'")

#4
    def testDirection(self):
        '''Test if ship direction is set correctly'''
        self.assertEqual(self.AC.direction, "right", "direction isn't 'right'")
        self.assertEqual(self.BS.direction, "down", "direction isn't 'down'")

#5
    def testHitcount(self):
        '''Test if hitcount is set up correctly'''
        self.assertEqual(self.AC.hitcount, 0, "hitcount isnt 0")

#6
    def testSize(self):
        '''Test if size (and thus ship_length()) is working properly'''
        self.assertEqual(self.AC.size, 5, "size of AC isn't 5")
        self.assertEqual(self.BS.size, 4, "size of BS isn't 4")

#7
    def testSpaces(self):
        '''Test if spaces is working properly'''
        AC = [(0, 0), (1, 0), (2, 0), (3, 0), (4, 0)]
        BS = [(1, 1), (1, 2), (1, 3), (1, 4)]
        self.assertEqual(self.AC.spaces, AC, "wrong spaces")
        self.assertEqual(self.BS.spaces, BS, "wrong spaces")


class emptyBoard(unittest.TestCase):
    '''Test the behaviour of a board object'''

    def setUp(self):
        '''Set up an empty board of size 4'''
        self.p1 = Player('p1', True)
        self.p2 = Player('p2', False)
        self.p1board = Board(self.p1, 4)

#8
    def testSize(self):
        '''Test if board was made of correct size'''
        self.assertEqual(self.p1board.size, 4, "size wasn't 4")

#9
    def testShips(self):
        '''Test that board is empty'''
        self.assertEqual(self.p1board.ships, [], "ships wasn't []")

#10
    def testHitcount(self):
        '''Test that hitcount is set up properly'''
        self.assertEqual(self.p1board.hitcount, 0, "hitcount wasn't 0")

#11
    def testSunkcount(self):
        '''Test that sunkcount is set up properly'''
        self.assertEqual(self.p1board.sunkcount, 0, "sunkcount wasn't 0")

#12
    def testBoard(self):
        '''Test that board (list of lists) is set up properly.'''
        board = [['0', '0', '0', '0'], ['0', '0', '0', '0'], ['0', '0', \
'0', '0'], ['0', '0', '0', '0']]
        self.assertEqual(self.p1board.board, board, "board was wrong")

#13
    def testReturnValue(self):
        '''Test returnValue() on an empty space'''
        self.assertEqual(self.p1board.returnValue(0, 0), '0', "should have \
returned '0'")

#14
    def testChangeValue(self):
        '''Test if changeValue() works for misses'''
        self.p1board.changeValue('M', 0, 0)
        self.assertEqual(self.p1board.returnValue(0, 0), 'M', "should have \
returned 'M'")

#15
    def testRandomlyAddGoodShip(self):
        '''Test that randomlyAddShips() changes the board'''
        board = [['0', '0', '0', '0'], ['0', '0', '0', '0'], ['0', '0', \
'0', '0'], ['0', '0', '0', '0']]
        self.p1board.randomlyAddShips("Destroyer", 1)
        self.assertNotEqual(self.p1board.board, board, "shouldn't be empty \
board")
        self.assertNotEqual(self.p1board.ships, [], "ships shouldn't be empty \
list")

#16
    def testRandomlyAddBadShip(self):
        '''Test that randomlyAddShips() doesn't change the board when ship \
        doesn't fit'''
        board = [['0', '0', '0', '0'], ['0', '0', '0', '0'], ['0', '0', \
'0', '0'], ['0', '0', '0', '0']]
        self.p1board.randomlyAddShips("Aircraft Carrier", 1)
        self.assertEqual(self.p1board.board, board, "should be empty board")
        self.assertEqual(self.p1board.ships, [], "ships should be empty list")

#17
    def testManuallyAddGoodShip(self):
        '''Test if addShip() works'''
        self.p1board.addShip("Destroyer", (0, 0), "right")
        board = [['S', 'S', '0', '0'], ['0', '0', '0', '0'], ['0', '0', '0', \
'0'], ['0', '0', '0', '0']]
        self.assertEqual(self.p1board.board, board, "problem with addShip()")

#18
    def testManuallyAddBadShip(self):
        '''Test that addShip() doesn't change the board when ship doesn't fit\
        because its larger than the board'''
        self.p1board.addShip("Aircraft Carrier", (0, 0), "right")
        board = [['0', '0', '0', '0'], ['0', '0', '0', '0'], ['0', '0', \
'0', '0'], ['0', '0', '0', '0']]
        self.assertEqual(self.p1board.board, board, "board should have \
        remained unchanged")
        self.assertEqual(self.p1board.enoughRoom("Aircraft Carrier", (0, 0), \
"right"), False, "problem with enoughRoom()")


class emptyBoard2(unittest.TestCase):
    '''Test the behaviour of a board object'''

    def setUp(self):
        '''Set up an empty board.'''
        self.p1 = Player('p1', True)
        self.p2 = Player('p2', False)
        self.p1board = Board(self.p1, 4)

#19
    def testManuallyAddBadShip(self):
        '''Test that addShip() doesn't change the board when ship doesn't \
        fit because its being placed outside of legal board coordinates'''
        self.p1board.addShip("Destroyer", (111, 1), "right")
        board = [['0', '0', '0', '0'], ['0', '0', '0', '0'], ['0', '0', \
'0', '0'], ['0', '0', '0', '0']]
        self.assertEqual(self.p1board.board, board, "board should have \
remained unchanged")
        self.assertEqual(self.p1board.enoughRoom("Destroyer", (111, 1), \
"right"), False, "problem with enoughRoom()")


class emptyBoard3(unittest.TestCase):
    '''Test the behaviour of a board object'''

    def setUp(self):
        '''Set up an empty board.'''
        self.p1 = Player('p1', True)
        self.p2 = Player('p2', False)
        self.p1board = Board(self.p1, 4)

#20
    def testManuallyAddBadShip(self):
        '''Test that addShip() doesn't change the board when ship doesn't \
        fit because its tail doesn't fit on the board'''
        self.p1board.addShip("Destroyer", (0, 0), "left")
        board = [['0', '0', '0', '0'], ['0', '0', '0', '0'], ['0', '0', \
'0', '0'], ['0', '0', '0', '0']]
        self.assertEqual(self.p1board.board, board, "board should have \
remained unchanged")
        self.assertEqual(self.p1board.enoughRoom("Destroyer", (0, 0), \
"left"), False, "problem with enoughRoom()")


class emptyBoard4(unittest.TestCase):
    '''Test the behaviour of a board object'''

    def setUp(self):
        '''Set up an empty board.'''
        self.p1 = Player('p1', True)
        self.p2 = Player('p2', False)
        self.p1board = Board(self.p1, 4)

#20
    def testManuallyAddBadShip(self):
        '''Test that addShip() doesn't change the board when ship doesn't \
        fit because there's a ship in the way'''
        self.p1board.addShip("Destroyer", (0, 0), "right")
        self.p1board.addShip("Destroyer", (0, 0), "right")
        board = [['S', 'S', '0', '0'], ['0', '0', '0', '0'], ['0', '0', \
'0', '0'], ['0', '0', '0', '0']]
        self.assertEqual(self.p1board.board, board, "board should have \
remained unchanged")
        self.assertEqual(self.p1board.enoughRoom("Destroyer", (0, 0), \
"left"), False, "problem with enoughRoom()")


class nonemptyBoard(unittest.TestCase):
    '''Test the behaviour of a board object'''

    def setUp(self):
        '''Set up an empty board.'''
        self.p1 = Player('p1', True)
        self.p2 = Player('p2', False)
        self.p1board = Board(self.p1, 4)
        self.p1board.addShip("Destroyer", (0, 0), "right")

#22
    def testMoveMiss(self):
        '''test that move() misses properly'''
        self.p1board.move(1, 1)
        self.assertEqual(self.p1board.returnValue(1, 1), 'M', "should have \
been 'M'")

#23
    def testMoveHit(self):
        '''test that move() hits properly'''
        self.p1board.move(0, 0)
        self.assertEqual(self.p1board.returnValue(0, 0), 'H', "should have \
been 'H'")
        self.assertEqual(self.p1board.ships[0].hitcount, 1, "ship's hitcount \
should be 1")

if __name__ == '__main__':
    unittest.main()
