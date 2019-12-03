package de.lingen.modsim.tictactoe;

public class TicTacToe {

    private int [][] board = null;
    private int boardSize = 3;

    public TicTacToe(){
        board = new int [boardSize][boardSize];

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = 0;
            }
        }
    }

    public boolean move(int x, int y, int stone){
        if(board[x][y] == 0) {
            board[x][y] = stone;
            return true;
        }
        else{
            System.out.println("Das Feld ist schon belegt.");
            return false;
        }
    }



    private int evaluateBoard(int [][] board){
        // check waagerecht
        for (int i = 0; i < boardSize ; i++) {
            // check waagerecht
            if( board[0][i] == board[1][i]
                && board[1][i] == board[2][i])
                    return board[0][i];

            //check senkrecht
            else if( board[i][0] == board[i][1]
                && board[i][1] == board[i][2])
                    return board[i][0];
        }

        // check diagonal
        if((board[0][0] == board[1][1] && board[1][1] == board[2][2]) ||
            (board[2][0] == board[1][1] == board[0][2]))
                return board[1][1];


        return 0;
    }

    private int countOccupiedFields(int[][] board){
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if(board[i][j] != 0)
                    count++;
            }
        }
        return count;
    }

    private int[] computeNonOccupiedFields(int[][] board){
        int[] nonOccupiedFields = new int[];
        int fugly = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if(board[i][j] == 0) {
                    nonOccupiedFields[fugly] = (i + 1) * 10 + (j + 1);
                    fugly++;
                }
            }
        }

        return nonOccupiedFields;
    }

    private boolean checkIfGameEnded(){
        if(countOccupiedFields() == 9) {
            System.out.println("End!");
            switch (evaluateBoard()) {
                case 1:
                    System.out.println("Player 1 wins");
                    break;
                case -1:
                    System.out.println("player 2 wins");
                    break;
                default:
                    System.out.println("draw");
            }
            return true;
        }
        else
            return false;
    }

    public void showBoard(){
        String header = "**";
        for (int i = 0; i < boardSize; i++) {
            header += i+1 + "*";
        }
        header += "*";

        System.out.println(header);

        for (int i = 0; i < boardSize; i++) {
            String zeile = i+1 + " ";
            for (int j = 0; j < boardSize; j++) {
                if(board[j][i] == 0)
                    zeile += "- ";
                else if(board[j][i] == 1)
                    zeile += "X ";
                else
                    zeile += "O ";
            }
            zeile += i+1;
            System.out.println(zeile);
        }
        System.out.println(header);
    }
}
