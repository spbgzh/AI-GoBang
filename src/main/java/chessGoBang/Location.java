package chessGoBang;

/**
 * The Location class encapsulates a location on the board.
 * AI will score the location when analyzing the situation.
 *
 * @author Guo ZiHan
 * @version 1.0
 */
public class Location {
    // Abscissa
    private final int x;

    // Ordinate
    private final int y;

    // location owner
    private int player;

    // score
    private int score;

    // those setter and getter methods
    /**
     * set Score by input
     * @param score input score
     */
    public void setScore(int score) {
        this.score = score;
    }


    /**
     * get Score
     * @return output score
     */
    public int getScore() {
        return score;
    }


    /**
     * get X
     * @return output val of X
     */
    public int getX() {
        return x;
    }


    /**
     * get Y
     * @return output val of X
     */
    public int getY() {
        return y;
    }


    /**
     * get Player
     * @return output player
     */
    public int getPlayer() {
        return player;
    }


    // Constructor
    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Location(int x, int y, int player) {
        this.x = x;
        this.y = y;
        this.player = player;
    }

    public Location(int x, int y, int player, int score) {
        this.x = x;
        this.y = y;
        this.player = player;
        this.score = score;
    }
}
