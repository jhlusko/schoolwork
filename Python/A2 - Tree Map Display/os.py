import os
import os.path

def print_items(d, indentation):
    '''(str, str) -> NoneType
    Print the list of files and directories in directory d, recursively,
    prefixing each with indentation.'''

    print indentation + d + ":"
    for filename in os.listdir(d):
        print indentation + filename

    for filename in os.listdir(d):
        subitem = os.path.join(d, filename)
        if os.path.isdir(subitem):
            print_items(subitem, indentation + '    ')

if __name__ == '__main__':
    # You can use something like "C:" to print your entire Windows hard drive,
    # or something like /Users/pgries on OS X to print that, or
    # /u/YOUR_CDF_ID on CDF to print your CDF directory hierarchy.
    print_items("C:\Users\Jamie\Documents\University", '')
