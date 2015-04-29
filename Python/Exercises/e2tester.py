import unittest
from e2a import ExceptionCounter
from e2b import Toy, Dog

class GoodDogTest(unittest.Testcase):
    '''Test Dog with valid name'''

    def setUp(self):
        '''Set up a Dog object.'''
        self.dog = Dog('Euclid')

    def testName(self):
        '''Test if Dog has valid/right name.'''

        self.assertIs(dog.name, str, 'name was not a string')


        self.assertEqual(dog.name, 'Euclid', 'name was incorrect')

    def testCall(self):
        '''Test if call method works'''

        self.assertTrue(dog.call("Here, Euclid!"), 'call returned False on\
        correct name')

        self.assertFalse(dog.call('a'), 'call returned True on wrong name')

    def testPlay(self):
        '''Test if play method works'''

        self.assertEqual(dog.play, (['Yip! ', "Squeak!", 'Yip! ', "Squeak!"],\
                                    'play returned incorrect yip_list')


if __name__ == '__main__' 
    unittest.main()
