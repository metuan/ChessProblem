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


### Last note

Please follow best practices whilst writing your code. Tests, proper commits, proper configuration, good
code structure, clean code practices are among others, good signs of your professional approach. Your
code will be used as a proof of your capabilities.


### HOW TO RUN

You can clone repository, import it to Your favourite IDE and run it from there or using SBT: enter root directory of application and type:
~~~~
sbt test // to run tests 
sbt run // to run example configuration 
~~~~


### Answer

Input:

    - ChessBoard(7,7)
    - List(King, King, Queen, Queen, Bishop, Bishop, Knight)

Output: 
- Number of solutions: 3063828
- Elapsed time:
    
    - IntelliJ : 
    Number of solutions: 3063828
    Elapsed time: 20.371 ms
![INTELLIJ IDEA CAPTURE](images/intelliJ.PNG)
    
    - SBT console : 
    [info] Number of solutions: 3063828
    [info] Elapsed time: 20.902 ms
    [success] Total time: 25 s, completed Dec 22, 2017 1:24:47 AM
![SBT CONSOLE CAPTURE](images/sbtConsole.PNG)

### How we can improve?

In my solution I wanted to use recursive backtracking algorithm (its use case is well known in [8 queens problem](https://en.wikipedia.org/wiki/Eight_queens_puzzle)) 

- We can have a look at our result layouts. We can find among them similar ones. It is possible to rely on that fact. We can try to find 'base' set of 'winning' layouts and generate other layouts which based on them. I could implement function to find first winning set and then by combinations (Newton Symbol) try to generate other winning chess boards. 

- It is possible to use pure functional tail-recurisve functions to generate those winning layouts since we are using recursive backtracking. It would be much more time effective than my solution but it would take much more time for me to write it. 
