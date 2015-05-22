import sys
import readline
from CLI import CLI
import Logging


def main():

    # resolving the '-' issue
    readline.set_completer_delims(readline.get_completer_delims().replace('-', ''))

    cli = CLI()

    if len(sys.argv) > 1:
        cli.onecmd(' '.join(sys.argv[1:]))
    else:
        cli.cmdloop()

if __name__ == '__main__':
    main()
