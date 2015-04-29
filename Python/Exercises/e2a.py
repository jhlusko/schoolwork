class ExceptionCounter(object):
    def __init__(self):
        num_exceptions = 0

    def check_for_exception(self, fn()):
        if is_instance(fn(), Exception):
            num_exceptions += 1
