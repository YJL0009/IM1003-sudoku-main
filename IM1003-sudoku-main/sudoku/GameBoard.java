package sudoku;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameBoard extends JPanel {
   // Name-constants for the game board properties
   public static final int GRID_SIZE = 9;    // Size of the board
   public static final int SUBGRID_SIZE = 3; // Size of the sub-grid

   // Name-constants for UI sizes
   public static final int CELL_SIZE = 60;   // Cell width/height in pixels
   public static final int BOARD_WIDTH  = CELL_SIZE * GRID_SIZE;
   public static final int BOARD_HEIGHT = CELL_SIZE * GRID_SIZE;
                                             // Board width/height in pixels

   // The game board composes of 9x9 "Customized" JTextFields,
   private Cell[][] cells = new Cell[GRID_SIZE][GRID_SIZE];
   // It also contains a Puzzle
   private Puzzle puzzle = new Puzzle();

   // Constructor
   public GameBoard() {
      super.setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));  // JPanel

      // Allocate the 2D array of Cell, and added into JPanel.
      for (int row = 0; row < GRID_SIZE; ++row) {
         for (int col = 0; col < GRID_SIZE; ++col) {
            cells[row][col] = new Cell(row, col);
            super.add(cells[row][col]);   // JPanel
         }
      }

      // [done 3] Allocate a common listener as the ActionEvent listener for all the
      //  Cells (JTextFields)
      CellInputListener listener = new CellInputListener();
      // [done 4] Every editable cell adds this common listener
      for (Cell[] row : cells) {
        for (Cell cell : row) {
            if (cell.status != CellStatus.SHOWN){
                cell.addActionListener(listener);   // For all editable rows and cols   
            }
        }
     }

      super.setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
   }

   /**
    * Initialize the puzzle number, status, background/foreground color,
    *   of all the cells from puzzle[][] and isRevealed[][].
    * Call to start a new game.
    */
   public void init() {
      // Get a new puzzle
      puzzle.newPuzzle(2);

      // Based on the puzzle, initialize all the cells.
      for (int row = 0; row < GRID_SIZE; ++row) {
         for (int col = 0; col < GRID_SIZE; ++col) {
            cells[row][col].init(puzzle.numbers[row][col], puzzle.isShown[row][col]);
         }
      }
   }

   /**
    * Return true if the puzzle is solved
    * i.e., none of the cell have status of NO_GUESS or WRONG_GUESS
    */
   public boolean isSolved() {
      for (int row = 0; row < GRID_SIZE; ++row) {
         for (int col = 0; col < GRID_SIZE; ++col) {
            if (cells[row][col].status == CellStatus.NO_GUESS || cells[row][col].status == CellStatus.WRONG_GUESS) {
               return false;
            }
         }
      }
      return true;
   }

   // [done 2] Define a Listener Inner Class
   private class CellInputListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
       // Get the JTextField that triggers this event
       Cell sourceCell = (Cell)e.getSource();
       // For debugging
       System.out.println("You entered " + sourceCell.getText());

       /*
        * [done 5]
        * 1. Get the input String via sourceCell.getText()
       */ 
        String cellText = sourceCell.getText();
       /*
        * 2. Convert the String to int via Integer.parseInt().
        */
        int cellValue;
        try {
            cellValue = Integer.parseInt(cellText);        
        } catch (NumberFormatException ex) {
            //done: handle exception
            //todo: make error pane
            System.out.println("not a valid number");
            return;
        }
        /*
        * 3. Assume that the solution is unique. Compare the input number with
        *    the number in sourceCell.number. Set its status and paint().
        */
        CellStatus newStatus = (cellValue == sourceCell.number)
            ?CellStatus.CORRECT_GUESS:CellStatus.WRONG_GUESS;
        sourceCell.setStatus(newStatus);
        sourceCell.paint();
       /*
        * [done 6][Later] Check if the player has solved the puzzle after this move,
        *   by call isSolved(). Put up a congratulation JOptionPane, if so.
        */
        GameBoard soureBoard = (GameBoard)sourceCell.getParent();
        if( soureBoard.isSolved() ){
            JOptionPane.showMessageDialog(null, "Congratulation!");
        }
    }
 }
}