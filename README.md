# Housie Project

The code contained in this repository is a working game of Housie.

Housie is the Indian version of Bingo. The rules to housie as coded in this project are the following:
* Every player will have a ticket with numbers
* The numbers on a single ticket are unique.
* The Caller calls a randomly generated number one at a time for the players.
* A number can be called only once.
* If a number called by the Caller appears on your ticket it is marked.
* All players track and mark the numbers called by the caller.
* The winner being the first person to mark off all of the their numbers making a winning combination. The following are the Winning Combinations:
  - Top Line - all numbers on the top line of the ticket are matched.
  - First Five - the first five numbers called on a ticket.
  - Full House - All numbers on a ticket are matched.
* A player can have more than one winning combination.
* If a particular winning combination is claimed it cannot be claimed again.


To run the Game application in this project you can checkout the code and perform the following steps:
1. gradle clean build
2. java -cp /path/to/build/libs/housie-1.0.jar housie.game.Game

Here is a sample game run:

```
# java -cp build/libs/housie-1.0.jar housie.game.Game

***** Let's play housie *****

Note: Press 'Q' to quit any time

Enter the number range (1-n): 60
Enter number of players playing the game: 3
Enter ticket size (rows x cols): 5x6
Enter numbers per row: 5
*** Tickets created successfully ***
Press 'N' to generate next number. N
Next number is: 57
Press 'N' to generate next number. N
Next number is: 17
Press 'N' to generate next number. N
Next number is: 47
Press 'N' to generate next number. N
Next number is: 48
Press 'N' to generate next number. N
Next number is: 38
Press 'N' to generate next number. N
Next number is: 41
Press 'N' to generate next number. N
Next number is: 24

We have a winner: Player2 has won 'First Five' winning combination!

Press 'N' to generate next number. N
Next number is: 22
Press 'N' to generate next number. N
Next number is: 8
Press 'N' to generate next number. N
Next number is: 4
Press 'N' to generate next number. N
Next number is: 51

...

Press 'N' to generate next number. N
Next number is: 52
Press 'N' to generate next number. N
Next number is: 10
Press 'N' to generate next number. N
Next number is: 25
Press 'N' to generate next number. N
Next number is: 58

We have a winner: Player2 has won 'Top Line' winning combination!

Press 'N' to generate next number.N
Next number is: 59

We have a winner: Player1 has won 'Full House' winning combination!


=============================================================
Summary:
Player1 : Full House
Player2 : Top Line and First Five
Player3 : Nothing
=============================================================
```
