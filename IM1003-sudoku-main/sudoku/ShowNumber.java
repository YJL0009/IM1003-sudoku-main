package sudoku;
public class ShowNumber {
    public static boolean[][] shownNumber(String level) {
        int[] rowArray = {1, 2, 3, 4, 5, 6, 7, 8, 0};
        int[] colArray = {1, 2, 3, 4, 5, 6, 7, 8, 0};
        int noOfSetGenerated=0;
        if (level=="Hard")
        {
            noOfSetGenerated=2;
        }
        else if (level=="Medium")
        {
            noOfSetGenerated=3;
        }
        else if (level=="Easy")
        {
            noOfSetGenerated=4;
        }
        else if (level=="Noob")
        {
            noOfSetGenerated=6;
        }

        boolean[][] showArray = new boolean[9][9];
        int count = 0;


        for (int j = 0; j < noOfSetGenerated; j++) {

            RandomQuestion.shuffleArray(rowArray);
            RandomQuestion.shuffleArray(colArray);
            for (int i = 0; i < 9; ++i) {

                if (showArray[rowArray[i]][colArray[i]] == false) {
                    showArray[rowArray[i]][colArray[i]] = true;
                    count++;
                }
            }
        }

        System.out.println("number of shown number:"+count);
        System.out.println("Level"+level);

//        for (int i = 0; i < 9; i++) {
//            if (i % 3 == 0 && i != 0) {
//                System.out.println("----------------------------------\n");
//            }
//            for (int j = 0; j < 9; j++) {
//                if (j % 3 == 0 && j != 0) {
//                    System.out.print(" | ");
//                }
//                System.out.print(" " + showArray[i][j] + " ");
//
//            }
//
//            System.out.println();
//        }
//        System.out.println("\n\n______________ANSWER_____________\n\n");
        return showArray;
    }
}

