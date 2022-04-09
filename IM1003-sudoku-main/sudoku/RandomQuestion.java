package sudoku;


import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public class RandomQuestion {

    public static int[][] randomQuestion(Boolean showAnswer) {
        int[] solutionArray = { 1, 2, 3, 4, 5, 6, 7, 8, 9};

        boolean displayAnswer=showAnswer;
        shuffleArray(solutionArray);
        int[][] random = new int[9][9];
        for(int i=0;i<9;i++)
        {
            random[i][i]=solutionArray[i];
        }
        
        Sudoku s = new Sudoku(random); // you can also pass incomplete sudoku as a parameter
        if(s.solveSudoku()&&displayAnswer)
        {
            s.displaySudoku();
        }
        else
        {
            System.out.println("change variable 'displayAnswer' to true to display answer");
        }

        int[][] randomSudoku= new int[9][9];
        randomSudoku= s.returnArray();

        return randomSudoku;
    }

    public static void shuffleArray(int[] ar) {
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }

}


class Sudoku
{
    private int[][] sudoku;
    private static final int UNASSIGNED = 0;

    public Sudoku()
    {
        sudoku = new int[9][9];
    }

    public Sudoku(int sudoku[][])
    {
        this.sudoku= sudoku;
    }

    public boolean solveSudoku()
    {
        for(int row=0;row<9;row++)
        {
            for(int col=0;col<9;col++)
            {
                if(sudoku[row][col]==UNASSIGNED)
                {
                    for(int number=1;number<=9;number++)
                    {
                        if(isAllowed(row, col, number))
                        {
                            sudoku[row][col] = number;
                            if(solveSudoku())
                            {
                                return true;
                            }
                            else
                            {
                                sudoku[row][col] = UNASSIGNED;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean containsInRow(int row,int number)
    {
        for(int i=0;i<9;i++)
        {
            if(sudoku[row][i]==number)
            {
                return true;
            }
        }
        return false;
    }

    private boolean containsInCol(int col,int number)
    {
        for(int i=0;i<9;i++)
        {
            if(sudoku[i][col]==number)
            {
                return true;
            }
        }
        return false;
    }

    private boolean containsInBox(int row, int col,int number)
    {
        int r = row - row%3;
        int c = col - col%3;
        for(int i=r;i<r+3;i++)
        {
            for(int j=c;j<c+3;j++)
            {
                if(sudoku[i][j]==number)
                {
                    return true;
                }
            }

        }
        return false;
    }

    private boolean isAllowed(int row, int col,int number)
    {
        return !(containsInRow(row, number) || containsInCol(col, number) || containsInBox(row, col, number));
    }

    public void displaySudoku()
    {
        for(int i=0;i<9;i++)
        {
            if(i%3==0 && i!=0)
            {
                System.out.println("----------------------------------\n");
            }
            for(int j=0;j<9;j++)
            {
                if(j%3==0 && j!=0)
                {
                    System.out.print(" | ");
                }
                System.out.print(" " + sudoku[i][j] + " ");

            }

            System.out.println();
        }
        System.out.println("\n\n______________ANSWER_____________\n\n");
    }
    public int[][] returnArray()
    {
        int[][] array=new int[9][9];
        for (int row=0;row<9;row++)
        {
            for (int col=0;col<9;col++)
            {
                array[row][col]=sudoku[row][col];
            }
        }

        return array;
    }

}