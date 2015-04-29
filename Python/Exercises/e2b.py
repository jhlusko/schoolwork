class Toy(object):
    def play(self):
        return 'Squeak!'


class Dog(object):
    def __init__(self, name):
        self.name = name
        self.yip_list = []

    def call(self, shout):
        if shout == "Here, %s!" % self.name:
            return True
        else:
            return False

    def play(self, toy, n):
        self.yip_list = []
        for n in range(n):
            self.yip_list.append('Yip! %s' % toy.play())
        return self.yip_list
