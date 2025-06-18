package minesweeper;

public class Cell {

    private boolean isMine; // Indicates if this cell contains a mine
    private boolean isRevealed; // Tracks whether the cell has been revealed
    private boolean isFlagged; // Tracks whether the cell is flagged
    private int nearbyMines; // Stores the number of nearby mines

    // Constructor to initialize a cell with default values
    public Cell() {
        this.isMine = false; // Initially, the cell does not contain a mine
        this.isRevealed = false; // The cell is not revealed by default
        this.isFlagged = false; // Initially, the cell is not flagged
        this.nearbyMines = 0; // No nearby mines at the beginning
    }

    // Check if the cell contains a mine
    public boolean isMine() {
        return isMine;
    }

    // Set whether the cell contains a mine
    public void setMine(boolean isMine) {
        this.isMine = isMine;
    }

    // Check if the cell has been revealed
    public boolean isRevealed() {
        return isRevealed;
    }

    // Reveal the cell
    public void reveal() {
        this.isRevealed = true;
    }

    // Check if the cell is flagged
    public boolean isFlagged() {
        return isFlagged;
    }

    // Set the flag status of the cell
    public void setFlagged(boolean isFlagged) {
        this.isFlagged = isFlagged;
    }

    // Get the number of nearby mines
    public int getNearbyMines() {
        return nearbyMines;
    }

    // Set the number of nearby mines for the cell
    public void setNearbyMines(int nearbyMines) {
        this.nearbyMines = nearbyMines;
    }
}
