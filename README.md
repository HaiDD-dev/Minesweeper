# Minesweeper

## Description

This is a classic implementation of the Minesweeper game created in Java using the Swing library for the graphical user interface. The game allows players to customize the difficulty by setting the number of rows, columns, and mines on the board.

This project was built using Apache NetBeans.

## Features

* **Customizable Game Board**: Players can define the size of the board (rows and columns) and the number of mines before starting a game.
* **Classic Gameplay**:
    * **Left-click** on a cell to reveal it.
    * **Right-click** on a cell to flag it as a potential mine.
* **First-Click Safety**: The first cell you click is guaranteed not to be a mine. If you happen to click on a mine on your first move, the mine will be relocated to a different cell.
* **Win/Loss Detection**: The game ends when you either click on a mine (loss) or reveal all the safe cells (win).
* **Game Over Options**: After a game ends, you can choose to:
    * Play Again with the same settings.
    * Return to the Main Menu to change settings.
    * Exit the game.
* **Input Validation**: The start menu includes validation to ensure the number of mines is valid for the given board size.

## How to Run the Game

The project is ready to run from the distributable JAR file.

1.  Navigate to the `dist/` directory.
2.  Execute the following command in your terminal:
    ```sh
    java -jar Minesweeper.jar
    ```
    This will launch the start menu, where you can configure the game board and start playing.

## How to Build from Source

You can build the project from source using Apache Ant and the provided `build.xml` script.

1.  Make sure you have the Java Development Kit (JDK) and Apache Ant installed on your system.
2.  Open your terminal and navigate to the root directory of the project.
3.  Run the following command:
    ```sh
    ant jar
    ```
4.  This will compile the source code and create the `Minesweeper.jar` file in the `dist/` directory.

Alternatively, you can open the project in the Apache NetBeans IDE and use the "Build" or "Clean and Build" options.

## Project Structure
