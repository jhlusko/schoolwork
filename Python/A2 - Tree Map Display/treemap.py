import pygame
import os
import os.path
import random
from operator import attrgetter


class Path(object):

    def __init__(self, d):
        '''(string)-> Path
        Create a path object.
        '''
        self.path = d
        self.size = getsize(d)


class File(Path):

    def __init__(self, d):
        '''(string)-> File
        Create a file object.
        '''
        Path.__init__(self, d)
        self.colour = new_colour()
        #These are the horizontal and vertical extremes of the rectangle,
        #initialized to none, because the rectangle hasn't been drawn yet.
        self.x1 = None
        self.x2 = None
        self.y1 = None
        self.y2 = None


class Directory(Path):

    def __init__(self, d):
        '''(string)-> Directory
        Create a directory object.
        '''
        Path.__init__(self, d)
        self.subdirectories = bysize(list_subdirectories(d))
        self.files = bysize(list_files(d))


class FileList(object):

    def __init__(self, directory_object):
        ''' (Directory) -> FileList
        Create a FileList object containing two lists containing all files
        in the directory, sorted by their x1 and y1 values.
        Precondition: tree_map(directory_object) must have been called
        '''
        self.by_x = sorted(all_files(directory_object), key=attrgetter('x1'))
        self.by_y = sorted(all_files(directory_object), key=attrgetter('y1'))


def getsize(d):
    '''(string) -> int
    Return the size of a file/directory from its path.
    '''
    size = os.path.getsize(d)
    if os.path.isdir(d):
        for filename in os.listdir(d):
            subitem = os.path.join(d, filename)
            if os.path.isdir(subitem):
                size += getsize(subitem)
            else:
                size += os.path.getsize(subitem)
    if size == 0:  # this avoids a ZeroDivisionError later on
        size = 1
    return size


def list_subdirectories(d):
    '''(string) -> list
    Return a list of subdirectories (directory objects) for a
    directory (path, not Directory object).
    '''
    list_sub = []
    for filename in os.listdir(d):
        subitem = os.path.join(d, filename)
        if os.path.isdir(subitem):
            list_sub.append(Directory(subitem))
    return list_sub


def list_files(d):
    '''(string) -> list
    Return a list of files (file objects) for a directory (path).
    '''
    list_files = []
    for filename in os.listdir(d):
        subitem = os.path.join(d, filename)
        if not os.path.isdir(subitem):
            list_files.append(File(subitem))
    return list_files


def bysize(l1):
    '''(list) -> list
    Return the input list, sorted by the size of each file/subdirectory.
    '''
    l1 = sorted(l1, key=attrgetter('size'))
    l1.reverse()
    return l1


def new_colour():
    ''' (NoneType) -> (int, int, int)
    Return a random colour.
    '''
    return (random.randint(0, 255), random.randint(0, 255), \
            random.randint(0, 255))


def tree_map(directory_object, (x, y, width, height), screen):
    '''(Directory, (int, int, int, int), pygame display) -> NoneType
    Modify the Directory and the pygame display, so the pygame display
    shows a tree map of the sizes of the files in the directory.
    '''
    borderwidth = 4
    #draws the white border around directories
    pygame.draw.rect(screen, white, (x, y, width, height), borderwidth)
    x += borderwidth
    y += borderwidth
    width -= 2 * borderwidth
    height -= 2 * borderwidth
    area = width * height
    for subdirectory in directory_object.subdirectories:
        scale = float(subdirectory.size) / float(directory_object.size)
        if width >= height:  # then tile left to right
            tree_map(subdirectory, (x, y, scale * area / height, height),\
                     screen)
            #move pointers to upper-left open space
            x += scale * area / height
            width -= scale * area / height
        else:  # tile top to bottom
            tree_map(subdirectory, (x, y, width, scale * area / width), screen)
            #move pointers to upper-left open space
            y += scale * area / width
            height -= scale * area / width
    for file1 in directory_object.files:
        scale = float(file1.size) / float(directory_object.size)
        if width >= height:  # then tile left to right
            #draw rectangle
            pygame.draw.rect(screen, new_colour(), (x, y, \
                                                scale * area / height, height))
            #update file
            file1.x1 = x
            file1.x2 = x + scale * area / height - 1
            file1.y1 = y
            file1.y2 = y + height - 1
            #move pointers to upper-left open space
            x += scale * area / height
            width -= scale * area / height
        else:  # tile top to bottom
            #draw rectangle
            pygame.draw.rect(screen, new_colour(), (x, y, \
                                                width, scale * area / width))
            #update file
            file1.x1 = x
            file1.x2 = x + width - 1
            file1.y1 = y
            file1.y2 = y + scale * area / width - 1
            #move pointers to upper-left open space
            y += scale * area / width
            height -= scale * area / width


def all_files(directory_object):
    ''' (Directory) -> list
    Return a list containing all the files in a directory and its
    subdirectories.
    '''
    files = []
    for item in directory_object.files:
        files.append(item)
    for item in directory_object.subdirectories:
        files.extend(all_files(item))
    return files


def getpath():
    '''(None) -> str
    Return the path from user input.
    '''
    unanswered = True
    while unanswered:
        path = raw_input("Please enter the path: ")
        try:
            os.path.getsize(path)
            unanswered = False
        except:
            pass
    return path


def getwindow():
    ''' (None) -> (int, int)
    Return the dimensions of the display window, from user input.
    '''
    unanswered = True
    while unanswered:
        width_pixels = raw_input("Please enter a number of pixels for \
the width of the treemap window: ")
        try:
            width_pixels = int(width_pixels)
            unanswered = False
        except:
            pass
    unanswered = True
    while unanswered:
        height_pixels = raw_input("Please enter a number of pixels for \
the height of the treemap window: ")
        try:
            height_pixels = int(height_pixels)
            unanswered = False
        except:
            pass
    return (width_pixels, height_pixels)


if __name__ == '__main__':
    #get the path from the user
    path = getpath()
    #get the display window dimensions from the user
    (width_pixels, height_pixels) = getwindow()
    #create the display
    pygame.init()
    screen = pygame.display.set_mode((width_pixels, height_pixels))
    white = (255, 255, 255)
    black = (0, 0, 0)
    screen.fill(white)
    directory_object = Directory(path)
    #draw the treemap leaving 20 pixels at the bottom for text
    tree_map(directory_object, (0, 0, width_pixels, height_pixels - 20),\
             screen)
    all_files = FileList(directory_object)
    #get a font to display the path
    font = pygame.font.Font(None, 16)
    text_surface = font.render(path, 1, black)
    text_pos = (0, height_pixels - font.get_linesize())
    screen.blit(text_surface, text_pos)
    #display the path (and also the treemap)
    pygame.display.flip()
    #instead of the directory path, display the either the file path,
    #or file size of the rectangle the mouse is hovering over
    text_box = pygame.Surface((width_pixels, 20))
    running = True
    text_path = True
    while running:
        event = pygame.event.poll()
        if event.type == pygame.QUIT:
            running = False
        elif event.type == pygame.MOUSEMOTION:
            text_box.fill(black)
            screen.blit(text_box, (0, height_pixels - 20))
            text = ' '
            (x, y) = event.pos
            for file1 in all_files.by_x:
                if file1.x1 > x:
                    break
                elif file1.x1 <= x <= file1.x2 and file1.y1 <= y <= file1.y2:
                    if text_path == True:
                        text = file1.path
                    else:
                        text = str(file1.size)
            text_surface1 = font.render(text, 1, white)
            screen.blit(text_surface1, text_pos)
            pygame.display.flip()
        #clicking switches between file path and file size display modes
        elif event.type == pygame.MOUSEBUTTONDOWN:
            if text_path == True:
                text_path = False
            else:
                text_path = True
