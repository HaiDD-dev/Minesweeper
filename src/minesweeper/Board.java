package minesweeper;

import java.util.Random;

public class Board {

    private final Cell[][] board; // 2D array representing the game board
    private final int rows; // Number of rows on the board
    private final int cols; // Number of columns on the board
    private final int mines; // Number of mines on the board

    // Constructor to initialize the board
    public Board(int rows, int cols, int mines) {
        this.rows = rows;
        this.cols = cols;
        this.mines = mines;
        board = new Cell[rows][cols]; // Initialize the board with the given size
        initializeBoard(); // Initialize cells
        placeMines(); // Place mines randomly
        calculateNearbyMines(); // Calculate nearby mines for each cell
    }

    // Initialize the board with empty cells
    private void initializeBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = new Cell(); // Create a new cell for each position
            }
        }
    }

    // Randomly place mines on the board
    private void placeMines() {
        Random rand = new Random(); // Use Random to generate positions
        int placedMines = 0; // Counter to track how many mines are placed
        while (placedMines < mines) {
            int row = rand.nextInt(rows); // Generate random row
            int col = rand.nextInt(cols); // Generate random column
            if (!board[row][col].isMine()) { // Check if the cell already has a mine
                board[row][col].setMine(true); // Place the mine
                placedMines++; // Increment the mine counter
            }
        }
    }

    // Calculate the number of nearby mines for each cell
    void calculateNearbyMines() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!board[i][j].isMine()) { // Skip mine cells
                    int nearbyMines = countMinesAround(i, j); // Count nearby mines
                    board[i][j].setNearbyMines(nearbyMines); // Set the count in the cell
                }
            }
        }
    }

    // Count the number of mines around a specific cell
    private int countMinesAround(int row, int col) {
        int count = 0;
        for (int i = -1; i <= 1; i++) { // Loop through the adjacent cells
            for (int j = -1; j <= 1; j++) {
                int r = row + i;
                int c = col + j;
                // Check if the adjacent cell is valid and contains a mine
                if (r >= 0 && r < rows && c >= 0 && c < cols && board[r][c].isMine()) {
                    count++;
                }
            }
        }
        return count; // Return the number of nearby mines
    }

    // Get the cell at a specific row and column
    public Cell getCell(int row, int col) {
        return board[row][col];
    }
}
