package sudoku;
/**
 * The Sudoku number puzzle to be solved
 */
public class Puzzle {
   // All variables have package access
   int[][] numbers = new int[GameBoard.GRID_SIZE][GameBoard.GRID_SIZE];

   // For testing, only 2 cells of "8" is NOT shown
   boolean[][] isShown = new boolean[GameBoard.GRID_SIZE][GameBoard.GRID_SIZE];

   // Constructor
   public Puzzle() {
      super();  // JPanel
   }

   public void newPuzzle(int numToGuess) {

      numbers = RandomQuestion.randomQuestion(true);//change to false if dont want show ans
      isShown=ShowNumber.shownNumber("Noob");

   }




   //(For advanced students) use singleton design pattern for this class
}