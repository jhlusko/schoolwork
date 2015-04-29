import pygame
import os
import os.path
import random
from operator import attrgetter

class File(object):
    '''
    '''
    def __init__(self, d):
        self.path = d
        self.size = os.path.getsize(d)
        self.rectangle = None


class Directory(File):
    '''
    '''
    def __init__(self, d):
        File.__init__(self, d)
        self.size = getsize(d)
        self.subdirectories = bysize(list_subdirectories(d))
        self.files = bysize(list_files(d))
        self.all_files = all_files(d)


def getsize(d):
    size = os.path.getsize(d)
    for filename in os.listdir(d):
        subitem = os.path.join(d, filename)
        if os.path.isdir(subitem):
            size += getsize(subitem)
        else:
            size += os.path.getsize(subitem)
    return size

def list_subdirectories(d):
    list_sub = []
    for filename in os.listdir(d):
        subitem = os.path.join(d, filename)
        if os.path.isdir(subitem):
            list_sub.append(Directory(subitem))
    return list_sub


def list_files(d):
    list_files = []
    for filename in os.listdir(d):
        subitem = os.path.join(d, filename)
        if not os.path.isdir(subitem):
            list_files.append(File(subitem))
    return list_files


def bysize(l1):
    '''(list) -> list
    '''
    l1 = sorted(l1, key=attrgetter('size'))
    l1.reverse()
    return l1


def all_files(directory_object):
    files = []
    for subdirectory in directory_object.subdirectories:
        all_subfiles = all_files(subdirectory)
        for subfile in all_subfiles:
            files.append(subfile)
    for file1 in directory_object.files:
        files.append(subfile)
    return files



if __name__ == '__main__':
    path = "C:\Users\Jamie\Documents\University"
    direc = Directory(path)

