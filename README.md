### Chess Challenge

## Problem

The problem is to find all unique configurations of a set of normal chess pieces on a chess board with
dimensions M×N where none of the pieces is in a position to take any of the others. Providing the number of
results is useful, but not enough to complete the assignment. Assume the colour of the piece does not
matter, and that there are no pawns among the pieces.
Write a program which takes as input:
- The dimensions of the board: M, N
- The number of pieces of each type (King, Queen, Bishop, Rook and Knight) to try and place on the
board.

As output, the program should list all the unique configurations to the console for which all of the pieces can
be placed on the board without threatening each other.
When returning your solution, please provide with your answer the total number of unique configurations for
a 7×7 board with 2 Kings, 2 Queens, 2 Bishops and 1 Knight. Also provide the time it took to get
the final score. Needless to say, the lower the time, the better.


### How to run

You can clone repository, import it to Your favourite IDE and run it from there or using SBT: enter root directory of application and type:
~~~~
sbt test // to run tests 
sbt run // to run example configuration 
~~~~
