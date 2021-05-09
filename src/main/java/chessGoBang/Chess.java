package chessGoBang;

public class Chess {

    // board
    private int[][] board;

    // AI
    public static final int AI = 1;

    // player
    public static final int HUMAN = 2;

    // Constructor
    public Chess() {
        this.board = new int[15][15];

    }
    // initialize board
    public void boardInit() {
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[i].length; j++)
                board[i][j] = 0;
    }


}
