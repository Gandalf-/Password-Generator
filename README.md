Password-Generator
==================

This program generates passwords. It has two main modes, generating completely random passwords with given specifications and generating passwords that are derived from some phrase that you give it.

GENERAL: this program can take multiple inputs from the user. To destinguish between inputs, use the ';' character. Invalid inputs won't do anything and you'll be prompted for valid input.

RANDOM PASSWORDS: the default values are length of 10 characters. By using 'r; length' where length is a number, you can specify how long you want the random password to be.

DERIVED PASSWORDS: this mode requires a phrase to be converted into something usable as a password. You don't need to put the phrase in quotes; everything after the seperating character ';' will be included.

NUMBER OF GENERATIONS: the default number of generations is 5, meaning that the program gives you 5 options every time you tell it to generate a password. This number can be changed by 's; number', where 'number' is the number of generations you want to be displayed.

REGENERATE: this command tells the program to run again using whatever input you gave it the last time it ran. This command has no effect if you call it without previously running either a 'r' or 'd' command
