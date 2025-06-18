package minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class StartMenu extends JFrame {

    private final JTextField rowsField;
    private final JTextField colsField;
    private final JTextField minesField;

    // Constructor for the StartMenu
    public StartMenu() {
        setTitle("Minesweeper"); // Set window title
        setSize(400, 300); // Increase window size to match larger font
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close operation
        setLayout(new GridLayout(4, 2)); // Set layout to GridLayout with 4 rows and 2 columns

        // Create a new font with a larger size
        Font font = new Font("Arial", Font.PLAIN, 30);

        // Label and text field for rows input
        JLabel rowsLabel = new JLabel("Rows: ");
        rowsLabel.setFont(font); // Set font for the label
        add(rowsLabel);
        rowsField = new JTextField("10"); // Default value for rows
        rowsField.setFont(font); // Set font for the text field
        add(rowsField);

        // Label and text field for columns input
        JLabel colsLabel = new JLabel("Cols: ");
        colsLabel.setFont(font); // Set font for the label
        add(colsLabel);
        colsField = new JTextField("10"); // Default value for columns
        colsField.setFont(font); // Set font for the text field
        add(colsField);

        // Label and text field for mines input
        JLabel minesLabel = new JLabel("Mines: ");
        minesLabel.setFont(font); // Set font for the label
        add(minesLabel);
        minesField = new JTextField("10"); // Default value for mines
        minesField.setFont(font); // Set font for the text field
        add(minesField);

        // Start button
        JButton startButton = new JButton("Start");
        startButton.setFont(font); // Set font for the button
        add(startButton);

        // Action listener for the start button
        startButton.addActionListener((ActionEvent _) -> {
            try {
                int rows = Integer.parseInt(rowsField.getText());
                int cols = Integer.parseInt(colsField.getText());
                int mines = Integer.parseInt(minesField.getText());

                // Calculate total number of cells
                int totalCells = rows * cols;

                // Validate the number of mines
                if (mines >= totalCells) {
                    JOptionPane.showMessageDialog(StartMenu.this,
                            "The number of mines must be less than the total number of cells!",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } else if (mines <= 0) {
                    JOptionPane.showMessageDialog(StartMenu.this,
                            "The number of mines must be greater than 0!",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Start the Minesweeper game with the entered parameters
                    new Minesweeper(rows, cols, mines);
                    dispose(); // Close start menu after starting the game
                }
            } catch (NumberFormatException ex) {
                // Show error message if input is not a valid number
                JOptionPane.showMessageDialog(StartMenu.this,
                        "Please enter valid numbers!",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Center the window on the screen
        setLocationRelativeTo(null);

        // Make the window visible
        setVisible(true);
    }

    // Main method to start the program
    public static void main(String[] args) {
        new StartMenu();
    }
}

// them bang xep hang
// them tinh thoi gian
// about us
// tutorial