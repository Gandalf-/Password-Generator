import sys, getopt, string, random

def random_pass(length, generations):
    '''
    int, int -> list of strings
    '''
    output = []
    chars = string.ascii_uppercase + string.ascii_lowercase + string.digits

    while generations > 0:
        new_pass = ''.join(random.SystemRandom().choice(chars) for _ in range(length))
        output.append(new_pass)
        generations -= 1

    return output 

def derive_pass(string, generations):
    '''
    string, int -> list of strings
    '''
    output = []

    while generations > 0:
        output.append('hello')
        generations -= 1

    return output 

def print_syntax():
    '''
    none -> none
    '''
    print 'pgen.py -g <number of generations> -r <password length>'
    print '        -g <number of generations> -d <string to garble>'
    sys.exit()

def main(argv):
    '''
    list of strings -> none
    '''
    generations = 5
    action = 'x'
    output = False

    try:
        opts, args = getopt.getopt(argv,'h:r:d:g:')

    except getopt.GetoptError:
        print_syntax()

    for opt, arg in opts:
        if opt == '-h':
            print_syntax()

        elif opt == '-g':
            generations = arg

        elif opt == '-r':
            output = random_pass(int(arg), generations)

        elif opt == '-d':
            output =  derive_pass(arg, generations)

        else:
            print_syntax()

        if output:
            for elem in output:
                print elem
    return

if __name__ == '__main__':
    main(sys.argv[1:])
