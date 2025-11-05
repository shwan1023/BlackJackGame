# BlackJackGame

A game for BlackJack.

> You must create a Java program that simulates one or more games of the *BlackJack* game. The rules of the *BlackJack* game that you must implement are as follows:
>
> There must be at least two participants. At the beginning of the game, each participant receives an identical number of chips (usually 2 or 3 chips). The game consists of a series of rounds. A round is carried out as follows: at the beginning of the round, there is an empty pot. Each player plays in turn, by rolling three dice, the goal being to total the sum of 21 by adding the values of all the dice rolled. The player rolls the three dice a first time, and is entitled to a second roll of zero, one, two or three dice (as desired). The sum of all the dice rolled (3, 4, 5 or 6 dice) is calculated. If this sum is equal to 21, the player is the winner of this round and the round is over. If this sum is greater than 21, the player gives a chip to the pot. At the end of the round, if none of the players has achieved 21, the first one who has totaled the sum closest to 21 is the winner. At the end of the round, if there is a winner, the contents of the pot go to the winner and all other players must give a chip to the winner. At the end of the round, if there is no winner, the pot is lost to the bank. As soon as a player has no more chips, he is eliminated from the game. The winner of the *BlackJack* game is the only player remaining after all the others have been eliminated. After each round, the remaining players are re-arranged randomly around the table.
>
> In your *BlackJack* game, it must be possible to have **two types of players**: human player and virtual player. A **human player** chooses the number of dice to roll on the second roll (0, 1, 2 or 3). In other words, the execution of the game must be interrupted to allow the player to enter (in the console) the number of dice he wishes to roll for his second roll. A **virtual player** chooses the number of dice to roll on the second roll by applying a formula. For example, a possible strategy is summarized in the following table:
>
> | First roll total | Second roll number of dices |
> | ---------------- | --------------------------- |
> | < 9              | 3                           |
> | < 15             | 2                           |
> | > 15             | 1                           |
>
> In addition to the *BlackJack* game, you must create a program that simulates a large number of *BlackJack* games played only by virtual players, and that simply displays the number of games won by each player and the number of draws. This program must allow you to experimentally get some statistics on the game (i.e. the percentage of games won by each player).
>
> Your code must reflect as best as possible the different elements of the game described above. To do this, you must implement (a lot of) different classes, to model the dice, the players (both human and virtual), the BlackJack game, the BlackJack statistics. A demo will be given in class to show you the expected behavior of both the BlackJack game and the BlackJack statistics.



## How to start

Run `BlackJackGame.java` for play and `BlackJackStatistics.java` for simulation.

