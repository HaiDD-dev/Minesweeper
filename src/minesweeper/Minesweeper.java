package minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Minesweeper extends JFrame {

    private final Board board;
    private final JButton[][] buttons;

    private final int rows;
    private final int cols;
    private final int mines;

    private boolean gameOver = false; // Variable to track game status
    private boolean firstClick = true; // Variable to track if it's the first click

    // Constructor for MinesweeperGUI
    public Minesweeper(int rows, int cols, int mines) {
        this.rows = rows;
        this.cols = cols;
        this.mines = mines;
        board = new Board(rows, cols, mines);
        buttons = new JButton[rows][cols];

        setLayout(new GridLayout(rows, cols)); // Set layout as grid
        initializeButtons(); // Initialize buttons

        setTitle("Minesweeper");
        setSize(600, 600); // Set window size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close operation
        setLocationRelativeTo(null); // Center the window on the screen
        setVisible(true); // Make the window visible
    }

    // Initialize buttons for the board
    private void initializeButtons() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                JButton button = new JButton(); // Create button for each cell
                buttons[i][j] = button;
                add(button); // Add button to the frame

                int row = i;
                int col = j;

                // Add a MouseListener to handle left and right clicks
                button.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        // If the game is over, ignore further clicks
                        if (gameOver) {
                            return;
                        }

                        // Left click to reveal the cell
                        if (e.getButton() == MouseEvent.BUTTON1) {
                            handleClick(row, col);
                        }

                        // Right click to place or remove a flag
                        if (e.getButton() == MouseEvent.BUTTON3) {
                            handleRightClick(row, col);
                        }
                    }
                });
            }
        }
    }

    // Handle button click
    private void handleClick(int row, int col) {
        // If it's the first click, check the cell
        if (firstClick) {
            if (board.getCell(row, col).isMine()) {
                // If the first cell is a mine, move the mine
                moveMines(row, col);
            }
            firstClick = false; // Mark that the first click has happened
        }

        Cell cell = board.getCell(row, col);
        if (cell.isRevealed()) {
            return; // Do nothing if the cell is already revealed
        }

        cell.reveal(); // Reveal the cell

        // Set the color for revealed cells
        buttons[row][col].setBackground(Color.LIGHT_GRAY);

        if (cell.isMine()) {
            buttons[row][col].setText("*"); // Display mine symbol
            showLoseDialog(); // End game on mine click
        } else {
            buttons[row][col].setText(String.valueOf(cell.getNearbyMines())); // Display nearby mines count
            if (cell.getNearbyMines() == 0) {
                revealEmptyCells(row, col); // Reveal adjacent cells if no nearby mines
            }
            // Check for win condition if the game is not over
            if (!gameOver && checkWinCondition()) {
                showWinDialog(); // Show win dialog if the player wins
            }
        }
    }

    // Handle right click to place or remove a flag
    private void handleRightClick(int row, int col) {
        Cell cell = board.getCell(row, col);

        // If the cell is already revealed, we cannot flag it
        if (cell.isRevealed()) {
            return;
        }

        // Toggle flag status
        if (cell.isFlagged()) {
            cell.setFlagged(false);
            buttons[row][col].setText(""); // Remove flag symbol
        } else {
            cell.setFlagged(true);
            buttons[row][col].setText("F"); // Display flag symbol
        }
    }

    // Move mines if the first clicked cell is a mine
    private void moveMines(int firstRow, int firstCol) {
        // Find a new position for the mine
        int newRow, newCol;
        do {
            newRow = (int) (Math.random() * rows); // Generate random row
            newCol = (int) (Math.random() * cols); // Generate random column
        } while ((newRow == firstRow && newCol == firstCol)
                || (board.getCell(newRow, newCol).isMine())); // Ensure the mine is not placed in the first cell

        // Move the mine from the first cell to the new position
        board.getCell(firstRow, firstCol).setMine(false); // Set first cell to not a mine
        board.getCell(newRow, newCol).setMine(true); // Set new position to a mine
        board.calculateNearbyMines(); // Update nearby mines count
    }

    // Reveal adjacent empty cells
    private void revealEmptyCells(int row, int col) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int r = row + i;
                int c = col + j;
                if (r >= 0 && r < rows && c >= 0 && c < cols && !board.getCell(r, c).isRevealed()) {
                    handleClick(r, c); // Recursively reveal adjacent cells
                }
            }
        }
    }

    // Check if the player has won the game
    private boolean checkWinCondition() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Cell cell = board.getCell(i, j);
                // If there's a non-mine cell that hasn't been revealed, the game is not won yet
                if (!cell.isMine() && !cell.isRevealed()) {
                    return false;
                }
            }
        }
        return true; // All non-mine cells are revealed, the player wins
    }

    // Show dialog when the player wins
    private void showWinDialog() {
        gameOver = true; // Mark the game as over
        int option = JOptionPane.showOptionDialog(this,
                "Congratulations! You won! Do you want to play again?",
                "Victory!",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                new String[]{"Play Again", "Back to Menu", "Exit"},
                "Play Again");

        switch (option) {
            case JOptionPane.YES_OPTION:
                new Minesweeper(rows, cols, mines);// Start a new game
                dispose(); // Close the current window
                break;
            case JOptionPane.NO_OPTION:
                new StartMenu();// Go back to start menu
                dispose(); // Close the current window
                break;
            case JOptionPane.CANCEL_OPTION:
                System.exit(0); // Exit the program
            default:
                break;
        }
    }

    // Show dialog when the player loses
    private void showLoseDialog() {
        gameOver = true; // Mark the game as over
        int option = JOptionPane.showOptionDialog(this,
                "Game Over! Do you want to play again?",
                "Game Over!",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.ERROR_MESSAGE,
                null,
                new String[]{"Play Again", "Back to Menu", "Exit"},
                "Play Again");

        switch (option) {
            case JOptionPane.YES_OPTION:
                new Minesweeper(rows, cols, mines);// Start a new game
                dispose(); // Close the current window
                break;
            case JOptionPane.NO_OPTION:
                new StartMenu();// Go back to start menu
                dispose(); // Close the current window
                break;
            case JOptionPane.CANCEL_OPTION:
                System.exit(0); // Exit the program
            default:
                break;
        }
    }
}
