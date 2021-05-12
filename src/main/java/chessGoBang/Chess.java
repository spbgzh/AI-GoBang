package chessGoBang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The Chess class is mainly responsible for various logical events,
 * such as logical placement, AI situation analysis, victory judgment, etc.
 *
 * @author Guo ZiHan
 * @version 1.0
 */
public class Chess {

    // map
    private int[][] map;

    // AI
    public static final int AI = 1;

    // player
    public static final int HUMAN = 2;

    // Who will play chess first
    public static int firstPlay = HUMAN;

    // Constructor
    public Chess() {
        this.map = new int[15][15];
        mapInit();
    }

    /**
     * initialize map
     */
    public void mapInit() {
        for (int[] i : map) Arrays.fill(i, 0);
    }


    /**
     * When AI play first get the location
     */
    public Location start() {
        int x = 5 + (int) (Math.random() * (9 - 5 + 1));
        int y = 5 + (int) (Math.random() * (9 - 5 + 1));
        map[x][y] = firstPlay;
        return new Location(x, y);
    }


    /**
     * place chess
     *
     * @param x      Abscissa 0-14
     * @param y      Ordinate 0-14
     * @param player AI(1) or HUMAN(2)
     * @return Judge whether the placement is successful
     */
    public boolean play(int x, int y, int player) {
        // Judge boundary
        if (x < 0 || x >= 15)
            return false;
        if (y < 0 || y >= 15)
            return false;
        //Judge whether it is empty (0 is empty)
        if (map[x][y] != 0)
            return false;
        //Successfully passed judgment
        map[x][y] = player;
        return true;
    }


    /**
     * Get all possible locations
     * Use set to git rid of same location
     *
     * @return set of locations
     */
    private List<Location> getAllPossibleLocation(int[][] Map) {
        List<Location> allPossibleLocation = new ArrayList<>();
        for (int i = 0; i < Map.length; i++)
            for (int j = 0; j < Map[i].length; j++) {
                if (Map[i][j] != 0) {
                    if (j != 0 && Map[i][j - 1] == 0)
                        addPossibleLocationToList(allPossibleLocation, i, j - 1);
                    if (j != (Map[i].length - 1) && Map[i][j + 1] == 0)
                        addPossibleLocationToList(allPossibleLocation, i, j + 1);
                    if (i != 0 && j != 0 && Map[i - 1][j - 1] == 0)
                        addPossibleLocationToList(allPossibleLocation, i - 1, j - 1);
                    if (i != 0 && Map[i - 1][j] == 0)
                        addPossibleLocationToList(allPossibleLocation, i - 1, j);
                    if (i != 0 && j != (Map[i].length - 1) && Map[i - 1][j + 1] == 0)
                        addPossibleLocationToList(allPossibleLocation, i - 1, j + 1);
                    if (i != (Map.length - 1) && j != 0 && Map[i + 1][j - 1] == 0)
                        addPossibleLocationToList(allPossibleLocation, i + 1, j - 1);
                    if (i != (Map.length - 1) && Map[i + 1][j] == 0)
                        addPossibleLocationToList(allPossibleLocation, i + 1, j);
                    if (i != (Map.length - 1) && j != (Map[i].length - 1) && Map[i + 1][j + 1] == 0)
                        addPossibleLocationToList(allPossibleLocation, i + 1, j + 1);
                }
            }
        return allPossibleLocation;
    }
    /* examples:
        for location [x], all location [0] is a PossibleLocation
        [ ][ ][ ][ ][ ]
        [ ][0][0][0][ ]
        [ ][0][x][0][ ]
        [ ][0][0][0][ ]
        [ ][ ][ ][ ][ ]
    */


    /**
     * Add locations to the collection and filter duplicate elements
     *
     * @param allPossibleLocation Collection of locations
     * @param x                   Abscissa
     * @param y                   Ordinate
     */
    private void addPossibleLocationToList(List<Location> allPossibleLocation, int x, int y) {
        boolean flag = true;
        for (Location location : allPossibleLocation)
            if (location.getX() == x && location.getY() == y) {
                flag = false;
                break;
            }
        if (flag) allPossibleLocation.add(new Location(x, y));
    }


    /**
     * AI analyzes the game and scores all possible locations
     * Randomly select the location with the highest score
     *
     * @return Return the location of AI will play
     */
    public Location analysis() {

        // get all possible location
        List<Location> allPossibleLocation = getAllPossibleLocation(this.map);

        // get all locations with same highest score but different location
        List<Location> allMaxLocation = new ArrayList<>();

        int max = 0;// Highest score

        // Traverse all possible locations and find location with highest score
        for (Location location : allPossibleLocation) {

            // Calculate the score by Minimax Search using deep =3
            int score = getScore(location.getX(), location.getY());
            location.setScore(score);

            // Judge whether it is highest score
            if (score > max) max = score;

            // If the current score is the highest score and not 0
            // If the value of the first element in the allMaxLocation collection is less than max
            // clear it first and add the element
            // otherwise just add element
            if (max != 0 && score == max) {
                if (allMaxLocation.size() > 0)
                    if (allMaxLocation.get(0).getScore() < max)
                        allMaxLocation.clear();
                allMaxLocation.add(location);
            }
            // print locations of highest score
            System.out.println("x=" + location.getX() + " y=" + location.getY() + " score=" + score);
        }

        // Randomly select a location from the highest score collection
        Location loc = allMaxLocation.get((int) (Math.random() * allMaxLocation.size()));
        System.out.println("The highest score is:" + loc.getScore());
        System.out.println("AI play:(" + (loc.getX() + 1) + "," + (loc.getY() + 1) + ");");

        // Return the location of the machine played
        return new Location(loc.getX(), loc.getY());
    }


    /**
     * set map
     * for test
     *
     * @param map input int[][] map
     */
    public void setMap(int[][] map) {
        this.map = map;
    }


    /**
     * get map
     * for test
     *
     * @return get map to test
     */
    public int[][] getMap() {
        return map;
    }


    /**
     * getScore from current position
     *
     * @param x Abscissa
     * @param y Ordinate
     */
    public int getScore(int x, int y) {
        // Think from the other person's perspective
        // Choose the location's highest score point for defense
        int xScore = getXScore(x, y, HUMAN) + getXScore(x, y, AI);
        int yScore = getYScore(x, y, HUMAN) + getYScore(x, y, AI);
        int skewScore1 = getSkewScore1(x, y, HUMAN) + getSkewScore1(x, y, AI);
        int skewScore2 = getSkewScore2(x, y, HUMAN) + getSkewScore2(x, y, AI);
        return xScore + yScore + skewScore1 + skewScore2;
    }


    /**
     * Calculate score based on situation
     *
     * @param count   Number of chess pieces connected together
     * @param status1 When one end is blocked (Empty: false, blocked: ture)
     * @param status2 When the other end is blocked (Empty: false, blocked: ture)
     * @return score of situation
     */
    private int getScoreBySituation(int count, boolean status1, boolean status2) {
        int score = 0;

        // 5 chess pieces connected
        if (count >= 5)
            score += 200000;// isWin

        // 4 chess pieces connected
        if (count == 4) {
            if (!status1 && !status2)
                score += 50000;
            if ((status1 && !status2) || (!status1 && status2))
                score += 3000;
            if (status1 && status2)
                score += 1000;
        }

        // 3 chess pieces connected
        if (count == 3) {
            if (!status1 && !status2)
                score += 3000;
            if ((status1 && !status2) || (!status1 && status2))
                score += 1000;
            if (status1 && status2)
                score += 500;
        }

        // 2 chess pieces connected
        if (count == 2) {
            if (!status1 && !status2)
                score += 500;
            if ((status1 && !status2) || (!status1 && status2))
                score += 200;
            if (status1 && status2)
                score += 100;
        }

        // Only one chess piece
        if (count == 1) {
            if (!status1 && !status2)
                score += 100;
            if ((status1 && !status2) || (!status1 && status2))
                score += 50;
            if (status1 && status2)
                score += 30;
        }

        return score;
    }


    /**
     * Horizontal score
     *
     * @param x   Abscissa
     * @param y   Ordinate
     * @param cur Player
     * @return score
     */
    public int getXScore(int x, int y, int cur) {
        int other;
        if (cur == AI) other = HUMAN;
        else other = AI;

        // Simulated move
        map[x][y] = cur;

        // States on both end
        boolean leftStatus = false;
        boolean rightStatus = false;
        int j, count = 0;//count is the number of chess pieces connected together

        // Traverse
        for (j = y; j < map.length; j++) {
            if (map[x][j] == cur)
                count++;
            else {
                if (map[x][j] == other)
                    rightStatus = true;// Right is blocked
                break;
            }
        }
        for (j = y - 1; j >= 0; j--) {
            if (map[x][j] == cur)
                count++;
            else {
                if (map[x][j] == other)
                    leftStatus = true;// Left is blocked
                break;
            }
        }

        // restore move
        map[x][y] = 0;

        // Calculate the score
        return getScoreBySituation(count, leftStatus, rightStatus);
    }


    /**
     * Vertical score
     *
     * @param x   Abscissa
     * @param y   Ordinate
     * @param cur Player
     * @return score
     */
    public int getYScore(int x, int y, int cur) {
        int other;
        if (cur == AI) other = HUMAN;
        else other = AI;

        // Simulated move
        map[x][y] = cur;

        // States on both end
        boolean topStatus = false;
        boolean bottomStatus = false;
        int i, count = 0;

        // Traverse
        for (i = x; i < map.length; i++) {
            if (map[i][y] == cur)
                count++;
            else {
                if (map[i][y] == other)
                    bottomStatus = true;// Bottom is blocked
                break;
            }
        }
        for (i = x - 1; i >= 0; i--) {
            if (map[i][y] == cur)
                count++;
            else {
                if (map[i][y] == other)
                    topStatus = true;// Top is blocked
                break;
            }
        }
        // restore
        map[x][y] = 0;

        return getScoreBySituation(count, topStatus, bottomStatus);
    }


    /**
     * Positive oblique score
     *
     * @param x   Abscissa
     * @param y   Ordinate
     * @param cur Player
     * @return score
     */
    public int getSkewScore1(int x, int y, int cur) {
        int other;
        if (cur == AI) other = HUMAN;
        else other = AI;

        // Simulated move
        map[x][y] = cur;

        boolean topStatus = false;
        boolean bottomStatus = false;
        int i, j;
        int count = 0;
        // From top left to bottom right
        for (i = x, j = y; i < map.length && j < map.length; i++, j++) {
            if (map[i][j] == cur)
                count++;
            else {
                if (map[i][j] == other)
                    bottomStatus = true;// Bottom is blocked
                break;
            }
        }

        for (i = x - 1, j = y - 1; i >= 0 && j >= 0; i--, j--) {
            if (map[i][j] == cur)
                count++;
            else {
                if (map[i][j] == other)
                    topStatus = true;// Top is blocked
                break;
            }
        }
        // restore
        map[x][y] = 0;
        return getScoreBySituation(count, topStatus, bottomStatus);
    }


    /**
     * Negative oblique score
     *
     * @param x   Abscissa
     * @param y   Ordinate
     * @param cur Player
     * @return score
     */
    public int getSkewScore2(int x, int y, int cur) {
        int other;
        if (cur == AI) other = HUMAN;
        else other = AI;

        // Simulated move
        map[x][y] = cur;

        boolean topStatus = false;
        boolean bottomStatus = false;
        int i, j;
        // From top right to bottom left
        int count = 0;
        for (i = x, j = y; i < map.length && j >= 0; i++, j--) {
            if (map[i][j] == cur)
                count++;
            else {
                if (map[i][j] == other)
                    bottomStatus = true;// bottom is block
                break;
            }
        }

        for (i = x - 1, j = y + 1; i >= 0 && j < map.length; i--, j++) {
            if (map[i][j] == cur)
                count++;
            else {
                if (map[i][j] == other)
                    topStatus = true;// top is blocked
                break;
            }
        }

        // restore
        map[x][y] = 0;
        return getScoreBySituation(count, topStatus, bottomStatus);
    }


    /**
     * Judge whether the game is draw
     * By judging whether there are any empty locations on the
     *
     * @return draw or not
     */
    public boolean isDraw() {
        for (int[] i : map) {
            for (int j : i) {
                if (j == 0) return false;
            }
        }
        return true;
    }


    /**
     * Judge whether this player win
     *
     * @param x   Abscissa
     * @param y   Ordinate
     * @param cur Player
     * @return win or not
     */
    public boolean isWin(int x, int y, int cur) {

        // In four directions, the numbers of chess pieces connected together
        int count = 0, count2 = 0, count3 = 0, count4 = 0;
        int i, j;

        // Horizontal scan
        for (j = y; j < map.length; j++) {
            if (map[x][j] == cur)
                count++;
            else
                break;
        }
        for (j = y - 1; j >= 0; j--) {
            if (map[x][j] == cur)
                count++;
            else
                break;
        }
        if (count >= 5)
            return true;

        // Vertical scan
        for (i = x; i < map.length; i++) {
            if (map[i][y] == cur)
                count2++;
            else
                break;
        }
        for (i = x - 1; i >= 0; i--) {
            if (map[i][y] == cur)
                count2++;
            else
                break;
        }
        if (count2 >= 5)
            return true;

        // Positive oblique scan
        for (i = x, j = y; i < map.length && j < map.length; i++, j++) {
            if (map[i][j] == cur)
                count3++;
            else
                break;
        }
        for (i = x - 1, j = y - 1; i >= 0 && j >= 0; i--, j--) {
            if (map[i][j] == cur)
                count3++;
            else
                break;
        }
        if (count3 >= 5)
            return true;

        // Negative oblique scan
        for (i = x, j = y; i < map.length && j >= 0; i++, j--) {
            if (map[i][j] == cur)
                count4++;
            else
                break;
        }
        for (i = x - 1, j = y + 1; i >= 0 && j < map.length; i--, j++) {
            if (map[i][j] == cur)
                count4++;
            else
                break;
        }
        return count4 >= 5;
    }


    /**
     * main Function entry
     */
    public static void main(String[] args) {
        ChessGUI.create();
    }


}

