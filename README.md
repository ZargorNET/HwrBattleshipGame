# HWR OOP Battleship Game

This repository contains a student project created for an ongoing lecture on object-oriented programming with Java at
HWR Berlin (summer term 2022).

> :warning: This code is for educational purpose only. Do not rely on it!

## Abstract

A simple console based battleship game.
It supports 2 game modes:

- **Singeplayer** which is basically Mine Sweeper, but without the mines
- **Local Multiplayer** in which you play turn based

Also, the map size is selectable and does not have to be quadratic.
But there are limitations. The minimum size is 10x10 (so that all ships fit) and the maximum is 50x50 because this is
probably already too big.

An interesting problem which occurred during development:
Coming from a game dev like structure, we first had a rendering system which updates itself every second (1 FPS),
because this makes state changes much easier e.x. you do not have to think about updating the state prematurely but just
in time.
But while this system worked, it broke when typing an input because as it turns out, clearing a console is not as
straightforward as it seems due to the fact that it clears the input line as well.
So, we had to reside to updating the console every "enter press" which, as earlier said, came with some challenges.

## Feature List

[TODO]: # (For each feature implemented, add a row to the table!)

| Number | Feature                                   | Tests |
|--------|-------------------------------------------|-------|
| 1      | Dynamic Map Size                          | yes   |
| 2      | Singleplayer Mode                         | yes   |
| 3      | Multiplayer Mode                          | yes   |
| 4      | Set your own ships in Multiplayer Mode    | yes   |
| 5      | Automatic Ship Generation in Singleplayer | yes   |
| 6      | Restart the game after a game             | yes   |


## Additional Dependencies

[TODO]: # (For each additional dependency your project requires- Add an additional row to the table!)

| Number | Dependency Name | Dependency Description | Why is it necessary?                                |
|--------|-----------------|------------------------|-----------------------------------------------------|
| 1      | Jansi           | ANSI Console Library   | In order to clear the console (and possibly colors) |
| 2      | Lombok          | Easier Getters/Setters | Much more readable code                             |