#for randomly placing ships later on
import random

#so that the boards can know who their opponent is, and generate appropraite
#sunk ship messages
player_list = []
#used to print both boards at the end of the game
board_list = []


def ship_length(ship_name):
    ''' (str) -> int
    Takes in ship name outputs ship length'''

    if ship_name == "Aircraft Carrier":
        return 5
    elif ship_name == "Battleship":
        return 4
    elif ship_name == "Submarine":
        return 3
    elif ship_name == "Cruiser":
        return 3
    elif ship_name == "Destroyer":
        return 2
    else:
        raise Exception("Bad ship name")


def addPlayer(player):
    '''Takes in player object, adds it to player_list'''
    if player.__class__.__name__ == Player.__name__:
        player_list.append(player)
    else:
        print "%s is not a player object" % player


def addBoard(board):
    '''Takes in board object, adds it to board_list'''

    if board.__class__.__name__ == Board.__name__:
        board_list.append(board)
    else:
        print "%s is not a board object" % board


class Player(object):
    '''A player of Battleship.'''

    def __init__(self, name, is_human):
        '''(Player, str, bool) -> Player
        Creates a player object which knows its name and whether it's a\
        human/computer.'''
        self.name = name
        self.is_human = is_human
        addPlayer(self)


class Ship(object):

    def __init__(self, name, locant, direction):
        ''' (Ship, str, tuple, str) -> Ship
        Creates a ship object, which is most importantly a list of the \
        positions the ship occupies.'''

        if direction != "right" and direction != "down" and direction != \
           'left' and direction != 'up':
            raise Exception('Direction must be either "right", "left", "up" or\
             "down"')
        self.direction = direction
        self.name = name
        self.hitcount = 0
        self.size = ship_length(name)

        #this builds a list of tuples which are the spaces
        #(pairs of coordinates) which the ship occupies on the board
        (x, y) = locant
        self.spaces = [locant]
        for space in range(self.size - 1):
            if direction == "right":
                x += 1
                self.spaces.append((x, y))
            elif direction == "left":
                x -= 1
                self.spaces.append((x, y))
            elif direction == "down":
                y += 1
                self.spaces.append((x, y))
            elif direction == "up":
                y -= 1
                self.spaces.append((x, y))
            else:
                raise Exception('Direction must be either "left", "up", \
                "right" or "down"')


class Board(object):
    '''A battleship board.'''

    def __init__(self, player, board_size):
        '''(Board, Player, int) -> Board
        Creates a board of size board_size, which knowns who it belongs to,\
        who its opponent is, how many ships are on it, what those ships are,\
        how many times they've been hit, how many have been sunk and \
        represents itself with a list of lists.'''

        self.player = player
        if len(player_list) < 2:
            raise Exception('player_list contains <2 players')
        else:
            player_list
            if player_list[0] == player:
                self.opponent = player_list[1]
            elif player_list[1] == player:
                self.opponent = player_list[0]

        #bad_move is used to determine if the player succeeded in manually
        #adding a ship on their turn, if they failed then it prevents the
        #other player from placing a ship (giving them an extra ship) so that
        #it goes back to the original player's turn and they can place
        #another ship
        self.bad_move = False
        self.size = board_size
        self.board = []
        self.ships = []
        self.hitcount = 0
        self.sunkcount = 0

        #this builds the all-important list-of-lists representation of the
        #board
        for y in range(board_size):
            rows = []
            for x in range(board_size):
                rows.append('0')
            self.board.append(rows)
        addBoard(self)

    def displayBoard(self):
        '''(Board) -> None
        Prints a list of lists containing each position and whether it's been\
        shot at, hit, or has a ship on it.'''
        count = 0
        #this deals with the problem of different sized (number of digits)
        #numbers shifting the board by varying amounts up to 4 digits
        for x in self.board:
            if len(str(count)) == 1:
                print str(count) + '   ' + str(x)
                count += 1
            elif len(str(count)) == 2:
                print str(count) + '  ' + str(x)
                count += 1
            elif len(str(count)) == 3:
                print str(count) + ' ' + str(x)
                count += 1
            else:
                print str(count) + ' ' + str(x)
                count += 1
        newlist = []
        count = 0
        #to avoid the numbers getting progressively shifted away from the
        #board i only show their last digit
        for x in range(self.size):
            if count == 10:
                count = 0
            newlist.append(str(count))
            count += 1
        print '    ' + str(newlist)

    def removedS(self):
        '''(Board)-> None
        Prints the board (list of lists) with the 'S's replaced by '0's'''

        newboard = []
        for x in self.board:
            sublist = []
            for y in x:
                if y == 'S':
                    sublist.append('0')
                else:
                    sublist.append(y)
            newboard.append(sublist)
        count = 0
        #this deals with the problem of different sized (number of digits)
        #numbers shifting the board by varying amounts up to 4 digits
        for x in newboard:
            if len(str(count)) == 1:
                print str(count) + '   ' + str(x)
                count += 1
            elif len(str(count)) == 2:
                print str(count) + '  ' + str(x)
                count += 1
            elif len(str(count)) == 3:
                print str(count) + ' ' + str(x)
                count += 1
            else:
                print str(count) + ' ' + str(x)
                count += 1

        newlist = []
        count = 0
        #to avoid the numbers getting progressively shifted away from the
        #board i only show their last digit
        for x in range(self.size):
            if count == 10:
                count = 0
            newlist.append(str(count))
            count += 1
        print '    ' + str(newlist)

    def returnValue(self, x_value, y_value):
        '''(Board, int, int) -> str
        Returns the value of a coordinate'''

        if x_value >= self.size:
            raise Exception("x value was larger than the board")
        if y_value >= self.size:
            raise Exception("y value was larger than the board")
        row = self.board[y_value]
        value = row[x_value]
        return value

    def enoughRoom(self, name, locant, direction):
        '''(board, str, tuple, str) -> bool
        Determines if there is room to place the ship'''
        shipsize = ship_length(name)
        (x, y) = locant

        if direction == 'right':
            if x + shipsize - 1 >= self.size:
                return False
            else:
                for space in range(shipsize):
                    if self.returnValue(x, y) != '0':
                        return False
                    else:
                        x += 1
            return True

        elif direction == 'left':
            if x - shipsize + 1 < 0:
                return False
            else:
                for space in range(shipsize):
                    if self.returnValue(x, y) != '0':
                        return False
                    else:
                        x -= 1
            return True

        elif direction == 'down':
            if y + shipsize - 1 >= self.size:
                return False
            else:
                for space in range(shipsize):
                    if self.returnValue(x, y) != '0':
                        return False
                    else:
                        y += 1
            return True

        elif direction == 'up':
            if y - shipsize + 1 < 0:
                return False
            else:
                for space in range(shipsize):
                    if self.returnValue(x, y) != '0':
                        return False
                    else:
                        y -= 1
            return True

        else:
            raise Exception("shouldn't get here")

    def addShip(self, name, locant, direction):
        '''(Board, str, tuple, str) ->  None
        Updates the board to reflect a new ship.'''
        shipsize = ship_length(name)
        (x, y) = locant

        if self.enoughRoom(name, locant, direction) == False:
            print "There wasn't room for the ship at that location."
            self.bad_move = True

        elif self.enoughRoom(name, locant, direction) == True:
            self.bad_move = False
            #place ship
            #add to ship to board's list of ships
            self.ships.append(Ship(name, locant, direction))
            #change the board to reflect the new ship
            for space in range(shipsize):
                if direction == "right":
                    self.changeValue('S', x, y)
                    x += 1

                elif direction == "left":
                    self.changeValue('S', x, y)
                    x -= 1

                elif direction == "down":
                    self.changeValue('S', x, y)
                    y += 1

                elif direction == "up":
                    self.changeValue('S', x, y)
                    y -= 1

                else:
                    raise Exception("shouldn't get here")

        else:
            raise Exception("shouldn't get here")

    def randomlyAddShips(self, name, num_ships):
        '''(Board, str, int) -> None
        Randomly places num_ships amount of name type ships, and updates the \
        board to reflect these changes.'''

        shipsize = ship_length(name)
        #sees if there is room to place a ship at random locations until
        #either there is room for the ship, and it places it there, or it
        #has tried and failed the same number of times as there are squares
        #on the board, in which case it tells the user that it couldn't
        #place the ship
        for each_ship in range(num_ships):
            for count in range(self.size * self.size):
                (x, y) = (random.randint(0, \
                            self.size - 1), random.randint(0, self.size - 1))

                if self.enoughRoom(name, (x, y), "right") == True:
                    self.addShip(name, (x, y), "right")
                    break
                elif self.enoughRoom(name, (x, y), "down") == True:
                    self.addShip(name, (x, y), "down")
                    break
                if count == self.size * self.size - 1:
                    if self.player.is_human:
                        print "The %s couldn't be added to your board %s."\
                              % (name, self.player.name)

    def move(self, x, y):
        '''(Board, int, int) -> None
        Updates the board to reflect a new move, and prints the outcome.'''

        if x >= self.size:
            raise Exception("x value was larger than the board")
        if y >= self.size:
            raise Exception("y value was larger than the board")

        if self.returnValue(x, y) == 'M':
            if self.opponent.is_human == True:
                print "You already fired there and missed! Please choose your \
                firing co-ordinates more carefully next turn."
            else:
                print "The computer fired somewhere it had already tried - \
                lucky you!"

        if self.returnValue(x, y) == 'H':
            if self.opponent.is_human == True:
                print "You already fired there and hit! Please choose your \
                firing co-ordinates more carefully next turn."
            else:
                print "The computer fired somewhere it had already tried - \
                lucky you!"

        if self.returnValue(x, y) == '0':
            print "Miss!"
            self.changeValue('M', x, y)
        if self.returnValue(x, y) == 'S':
            print "Hit!"
            self.changeValue('H', x, y)

    def changeValue(self, new_value, x_value, y_value):
        '''(Board, str, int, int) -> None/str
        Updates the board with a new value, if a ship is sunk or the game ends\
        it prints a message'''

        if x_value >= self.size:
            raise Exception("x value was larger than the board")
        if y_value >= self.size:
            raise Exception("y value was larger than the board")
        row = self.board[y_value]
        row[x_value] = new_value
        #if its a hit
        if new_value == "H":
            #for every ship on the board
            for ship in self.ships:
                #for each space the ship occupies
                for space in ship.spaces:
                    #if the space is the space that was fired at
                    if (x_value, y_value) == space:
                        ship.hitcount += 1
                        self.hitcount += 1
                        #if that hit finished the ship
                        if ship.hitcount == ship.size:
                            print "%s sunk a %s!" % (self.opponent.name, \
                                                    ship.name)
                            self.sunkcount += 1
                            #if that was the last unsunk ship on the board
                            if self.sunkcount == len(self.ships):
                                print "%s wins!" % self.opponent.name
                                print "%s this is your final board:" \
                                      % board_list[0].player.name
                                board_list[0].displayBoard()
                                print "%s this is your final board:" \
                                      % board_list[1].player.name
                                board_list[1].displayBoard()
                                while True:
                                    print "Would you like to play again? \
Please enter 'yes' or 'no'"
                                    answer = raw_input()
                                    play_again = answer
                                    if play_again == 'yes':
                                        newGame()
                                    elif play_again == 'no':
                                        print "Thanks for playing!"
                                        #this prevents the game from continuing
                                        #since the player thinks the game is
                                        #over, they won't give any input
                                        pause = raw_input()
                                        break


def badCoordinates(board):
    '''(Board)-> tuple
    Asks for coordinate input, and then formats them into an appropriate \
    tuple.'''
    print "Bad coordinates. Please enter your co-ordinates in the form \
(int, int)."
    coord = raw_input()
    coordlist = coord.strip('(').strip(')').split(',')
    if not len(coordlist) == 2:
        coordlist = badCoordinates(board)
    x = coordlist[0].strip()
    if str.isdigit(x):
        x = int(x)
    else:
        coordlist = badCoordinates(board)
        x = int(coordlist[0].strip())
    y = coordlist[1].strip()
    if str.isdigit(y):
        y = int(y)
    else:
        coordlist = badCoordinates(board)
        y = int(coordlist[1].strip())

    if x > board.size - 1 or y > board.size - 1:
        print "Values must be smaller than the board size."
        badCoordinates(board)
    return coordlist


def newGame():
    '''The code of the game.'''
    print "Player 1 please enter your name:"
    p1name = raw_input()
    p1 = Player(p1name, True)
    temp = 0
    while (temp != 'yes' and temp != 'no'):
        print "Are you playing with a friend? Please respond 'yes' or 'no'"
        temp = raw_input()
        if temp == 'yes':
            print "Player 2 please enter your name:"
            p2name = raw_input()
            p2 = Player(p2name, True)
        elif temp == 'no':
            p2 = Player('p2', False)
            temp2 = 0
    print "What size board would you like to play on?"
    size = raw_input()
    correctsize = False
    while correctsize != True:
        try:
            size = int(size)
            if size < 2:
                print "Board size must be larger than 1. Please enter an\
 appropriate board size."
                size = raw_input()
                correctsize = False
            else:
                correctsize = True
        except:
            print "Board size must be an int, please enter a board size."
            size = raw_input()
    p1board = Board(p1, size)
    p2board = Board(p2, size)
    if p2.is_human == False:
        print "Would you like to place your ships manually? Please enter\
 'yes' or 'no'."
    elif p2.is_human == True:
        print "If both players wish to manually place their ships enter 'yes',\
 otherwise enter 'no'."
    temp = raw_input()
    if temp == 'yes':
        temp2 = 0
        while (temp2 != 'yes' and temp2 != 'no'):
            print "%s, would you like to place a ship?" % p1name
            temp2 = raw_input()
            if temp2 == 'yes':
                shiptype = 0
                while shiptype != '1' and shiptype != '2' and shiptype != '3'\
                      and shiptype != '4' and shiptype != '5':
                    print "To add an Aircraft Carrier, type '1'"
                    print "To add a Battleship, type '2'"
                    print "To add a Submarine, type '3'"
                    print "To add a Cruiser, type '4'"
                    print "To add a Destroyer, type '5'"
                    shiptype = raw_input()
                    if shiptype == '1':
                        ship = "Aircraft Carrier"
                    elif shiptype == '2':
                        ship = "Battleship"
                    elif shiptype == '3':
                        ship = "Submarine"
                    elif shiptype == '4':
                        ship = "Cruiser"
                    elif shiptype == "5":
                        ship = "Destroyer"
                print "Please enter the coordinates you would like to place \
the end of the ship upon in form (horizontal coordinate, vertical coordinate)."
                coord = raw_input()
                coordlist = coord.strip('(').strip(')').split(',')
                if not len(coordlist) == 2:
                    coordlist = badCoordinates(p2board)
                x = coordlist[0].strip()
                if str.isdigit(x):
                    x = int(x)
                else:
                    coordlist = badCoordinates(p2board)
                    x = int(coordlist[0].strip())
                y = coordlist[1].strip()
                if str.isdigit(y):
                    y = int(y)
                else:
                    coordlist = badCoordinates(p2board)
                    y = int(coordlist[1].strip())

                if x > p2board.size - 1 or y > p2board.size - 1:
                    print "Values must be smaller than the board size."
                    badCoordinates(p2board)
                locant = (x, y)
                direction = 0
                while (direction != 'up' and direction != 'down' and direction\
                       != 'right' and direction != 'left'):
                    print "Please enter the direction you would like to place\
 the rest of the ship away from the end coordinate you seleceted (either \
'up', 'down', 'right' or 'left')"
                    direction = raw_input()
                p1board.addShip(ship, locant, direction)
                print "This is where you placed the ship:"
                p2board.displayBoard()
                if p2.is_human == False and p1board.bad_move == False:
                    p2board.randomlyAddShips(ship, 1)
                    temp2 = 0
                elif p2.is_human == True and p1board.bad_move == False:
                    print "%s, where would you like to place your %s? \
Please enter the coordinates in the form (horizontal coordinate, vertical \
coordinate)" % (p2name, ship)
                    coord = raw_input()
                    coordlist = coord.strip('(').strip(')').split(',')
                    if not len(coordlist) == 2:
                        coordlist = badCoordinates(p2board)
                    x = coordlist[0].strip()
                    if str.isdigit(x):
                        x = int(x)
                    else:
                        coordlist = badCoordinates(p2board)
                        x = int(coordlist[0].strip())
                    y = coordlist[1].strip()
                    if str.isdigit(y):
                        y = int(y)
                    else:
                        coordlist = badCoordinates(p2board)
                        y = int(coordlist[1].strip())

                    if x > p2board.size - 1 or y > p2board.size - 1:
                        print "Values must be smaller than the board size."
                        badCoordinates(p2board)
                    locant = (x, y)
                    direction = 0
                    while (direction != 'up' and direction != 'down' and \
                           direction != 'right' and direction != 'left'):
                        print "Please enter the direction you would like to\
 place the rest of the ship away from the end coordinate you seleceted \
(either 'up', 'down', 'right' or 'left')"
                        direction = raw_input()
                    p2board.addShip(ship, locant, direction)
                    print "This is where you placed the ship:"
                    p2board.displayBoard()
                    temp2 = 0
    if temp == 'no':
        list_shiptypes = ['Aircraft Carrier', 'Battleship', 'Submarine', \
'Cruiser', 'Destroyer']
        for ship in list_shiptypes:
            print "How many %s" % ship + "s would you like to play with?"
            num = int(raw_input())
            p1board.randomlyAddShips(ship, num)
            p2board.randomlyAddShips(ship, num)
    while True:
        if p1board.ships == []:
            print "No ships were added to %s's board. The game is being \
reset. Please enter appropriate values to ensure at least one \
ship is placed." % p1name
            newGame()
        elif p2board.ships == []:
            print "No ships were added to %s's board. The game is being \
reset. Please enter appropriate values to ensure at least one \
ship is placed." % p2name
            newGame()
        if p2.is_human == True:
            print "%s look away. Welcome back %s! Press enter to see your \
board." % (p2.name, p1.name)
            useless = raw_input()
            print "This is your board:"
            p1board.displayBoard()
            print "This is your opponent's board:"
            p2board.removedS()
        else:
            print "This is your board:"
            p1board.displayBoard()
            print "This is your opponent's board:"
            p2board.removedS()
        print "Please enter the coordinates you would like \
to fire at in the form (horizontal coordinate, vertical coordinate):"
        coord = raw_input()
        coordlist = coord.strip('(').strip(')').split(',')
        if not len(coordlist) == 2:
            coordlist = badCoordinates(p2board)
        x = coordlist[0].strip()
        if str.isdigit(x):
            x = int(x)
        else:
            coordlist = badCoordinates(p2board)
            x = int(coordlist[0].strip())
        y = coordlist[1].strip()
        if str.isdigit(y):
            y = int(y)
        else:
            coordlist = badCoordinates(p2board)
            y = int(coordlist[1].strip())

        if x > p2board.size - 1 or y > p2board.size - 1:
            print "Values must be smaller than the board size."
            badCoordinates(p2board)

        p2board.move(x, y)
        print "This is the effect of your move:"
        p2board.removedS()
        if p2.is_human == True:
            print "%s look away. Welcome back %s! Press enter to see your \
board" % (p1.name, p2.name)
            useless = raw_input()
            print "This is your board:"
            p2board.displayBoard()
            print "This is your opponent's board:"
            p1board.removedS()
            print "Please enter the x-coordinate and y-coordinate you would \
like to fire at in the form (x, y):"
            coord = raw_input()
            coordlist = coord.strip('(').strip(')').split(',')
            if not len(coordlist) == 2:
                coordlist = badCoordinates(p1board)
            x = coordlist[0].strip()
            if str.isdigit(x):
                x = int(x)
            else:
                coordlist = badCoordinates(p1board)
                x = int(coordlist[0].strip())
            y = coordlist[1].strip()
            if str.isdigit(y):
                y = int(y)
            else:
                coordlist = badCoordinates(p1board)
                y = int(coordlist[1].strip())

            if x > p1board.size - 1 or y > p1board.size - 1:
                print "Values must be smaller than the board size."
                badCoordinates(p1board)
            p1board.move(x, y)
            print "This is the effect of your move:"
            p1board.removedS()
        else:
            p1board.move(random.randint(0, p1board.size - 1), \
                         random.randint(0, p1board.size - 1))


if __name__ == '__main__':
    try:
        newGame()
    except:
        #this shouldn't ever be executed, but just in case
        print "That caused a fatal error!"
        while True:
            print "Would you like to play again? Please enter 'yes' or 'no'"
            answer = raw_input()
            play_again = answer
            if play_again == 'yes':
                newGame()
            elif play_again == 'no':
                print "Thanks for playing, sorry about the error!"
