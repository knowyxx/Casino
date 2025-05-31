# Casino Project Documentation

## Table of Contents
- [Goal](#goal)
- [Description](#description)
- [System Requirements](#system-requirements)
- [Main Structure](#main-structure)
- [Testing Data](#testing-data)
- [User Guide](#user-guide)
- [Conclusion](#conclusion)

---

## Goal

- My project is about simulating a casino with a bar. My main goal is for people to try how a casino works and not spend any real money, so they can get a view on how lucky you must be to make a living out of it. In my project you will find a bar where the user can buy drinks which increase his luck depending on the drink, there are four games available (blackjack, poker, roulette, slots) where you can also play with bots.

## Description

- The program can simulate games of blackjack, poker, roulette, and slots, and can give the user a drink. Now I will explain it further on how it works:

### Blackjack

- We ask the user how many bots he wants to play with and the maximum is 4, then we ask the user how much he would like to bet and then we start the game. We will firstly reset some variables such as `endOfGame`, then we will give 2 cards to the user, bots, and dealer (last one is hidden). The bots play first and then comes the user's turn; they will see their cards and choose if they want to hit or stay. After everyone busted or stayed, the loop will end and will proceed to the possibilities on how the game will end. If the user won, they will win money; otherwise, they will lose money.

### Poker

- We will firstly reset some variables, then two cards will be given to the user and bots. They will be put into a loop now until everybody has the same bet or everybody folded. After that, there will be three cards added into the community, and this will repeat until there are 5 cards in the community and everybody has the same bet. Then it will show who won by calculating who has the most powerful combination or the highest card.

### Roulette

- We will firstly ask the user how much he would like to bet, then we will start the game and reset some variables and ask the user what he wants to bet on (number, color, parity, range). Depending on what they want to bet on, they will be further asked on what exactly they want to bet on (12, red, odd, low). Then they will be rewarded if they picked the right choice.

### Slots

- We will firstly load the symbols and then ask the user how much he wants to spend on one spin. Then the user can spin until they run out of money or they decide to end the game. For each spin, they will be shown the combination that they got and will only win money if the 3 symbols are the same.

### Bar

- The user must have made the command `go to the bar` in order to go to the bar. Firstly, the bar will load all the drinks and will show all of the drinks. Then we will loop until the user answers with a drink they can afford, they exit, or a number. If the user has chosen a drink, they will drink the drink and luck will be added.

## System Requirements

- Operating System: Windows 10 / macOS / Linux 
- Java Version: Java SE Development Kit (JDK) 17 or higher

## Main Structure

- I will show important classes and their structure

### User

- Has all the information about the user; without it we wouldn't be able to start the game. We store information such as the user's age, cards, and balance.

- `public void winnings(double bet)` : Add or subtract money from their balance.

- `public void loadUser()` : Load user's deck, ask how old they are (this will loop until the user has correctly written their age).

### Play

- This class is important because it starts the games the user wants to play. It will ask and then start the game the user chose.

- `execute()` : This is the main method that starts `choosingGame()`, the answer will be put into a switch which after the right input, depending on what answer, will ask the user some more questions and then start the game the user chose.

- `choosingGame()` : This is where the user chooses which game he would like to play and it loops until the user inputs a correct option.

### Console

- This class is the most important one because all of the other classes are dependent on this class. In here we initialize the user and commands, and execute commands.

- `initialization()` : To initialize all the commands by putting them into a HashMap and load the user.

- `doCommand(boolean underage)` : Executes commands if requirements are met (user is not underage, user has money).

- `start()` : This is where we use `initialization()` and `doCommand(boolean underage)`. `doCommand(boolean underage)` will be put into a loop until the user has exited or the command `exit()` is true.

## Testing Data

- You can test my project by trying to break it when you must input something in a scanner by writing a word instead of a number. I personally mostly tested this because this is where I could break it. For example, when you have the choice to either hit or stay in blackjack and you input "hit" instead of the number, it now doesn't break but says that you should input a valid number and loops.

## User Guide

- You have some commands that you can execute.

### Help

- This will show you all the commands that you can execute.

### Play

- You will choose a game which you want to play and it will start the game.

### Exit

- You will leave the casino, ending the program.

### Go To The Bar

- You will go to the bar where you will be shown all available drinks and will be able to get a drink.

### Balance

- Will show your balance.

## Conclusion

- I didn't have many problems in this project. The only one I can remember that really prolonged my time was making the poker game function like a normal poker game. I think it was a huge mistake for me to implement poker because it took a very long time to make. Other than that, I didn't really have any more problems with the project. I must say that this was very annoying to make because I started earlier than I initially wanted. I wanted to have it done early, so I started a week or two earlier and ended up working on it for a month straight instead of the two weeks I thought it would take. So the conclusion is to really think about what you want to implement in the game and to start even earlier.

- Lines in total: 2077

- Lines of code: 1376

