package chessGoBang;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


/**
 * The ChessPanel class is responsible for board display, interaction, and placement on the view,
 * such as the drawing of the chessboard and chess pieces,
 * the preservation of the chessboard state, the placement,
 * and the clearing of events.
 *
 * @author Guo ZiHan
 * @version 1.0
 */
public class ChessPanel extends JPanel {

    private static final long serialVersionUID = 0;

    // The set of locations
    public List<Location> list = new ArrayList<>();

    // Size of cell
    int sizeCell = 30;

    // Length to the left border
    int margin=20;

    // Radius
    int radius=13;

    // The color of background
    Color bgColor=new Color(250, 220, 170);

    // The color of line
    Color lineColor=new Color(140, 100, 40);

    // The color of point
    Color pointColor=new Color(110, 70, 20);

    /**
     * Override paint
     * Change the components of the container itself
     */
    @Override
    public void paint(Graphics g1) {
        super.paint(g1);
        Graphics2D g=(Graphics2D) g1;
        // Set anti-aliasing
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING ,RenderingHints.VALUE_ANTIALIAS_ON);
        drawBoard(g);// draw board
        drawChessPieces(g);// draw pieces
    }


    /**
     * draw board
     * @param g Graphics2D
     */
    public void drawBoard(Graphics2D g){
        // set background color and draw background
        g.setColor(bgColor);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        // set line color
        g.setColor(lineColor);

        // draw board line
        for (int i = 0; i < 15; i++) {
            g.drawLine(margin, margin + sizeCell * i, this.getWidth() - margin, margin + sizeCell * i);
            g.drawLine(margin + sizeCell * i, margin, margin + sizeCell * i, this.getHeight() - margin);
        }

        // set point color and draw points
        // 10 is point's radius
        g.setColor(pointColor);
        g.fillOval(margin-5 + 3 * sizeCell, margin-5 + 3 * sizeCell, 10, 10);
        g.fillOval(margin-5 + 7 * sizeCell, margin-5 + 7 * sizeCell, 10, 10);
        g.fillOval(margin-5 + 3 * sizeCell, margin-5 + 11 * sizeCell, 10, 10);
        g.fillOval(margin-5 + 11 * sizeCell, margin-5 + 3 * sizeCell, 10, 10);
        g.fillOval(margin-5 + 11 * sizeCell, margin-5 + 11 * sizeCell, 10, 10);
    }


    /**
     * draw pieces
     * @param g Graphics2D
     */
    public void drawChessPieces(Graphics2D g){
        int i=1; //count the pieces

        // get FontMetrics object
        // In order to set the font to be centered
        FontMetrics metrics=g.getFontMetrics();
        int ascent = metrics.getAscent();
        int descent = metrics.getDescent();

        // Traverse the board an draw the pieces
        for (Location location : list) {
            if (location.getPlayer() == Chess.firstPlay)
                g.setColor(Color.black);// set first player is black
            else
                g.setColor(Color.white);// set first player is black

            // draw pieces
            g.fillOval(margin-13 + location.getY() * sizeCell,
                    margin-radius + location.getX() * sizeCell,
                    radius*2,
                    radius*2);

            // draw the number in the pieces
            if(location.getPlayer()==Chess.firstPlay) g.setColor(Color.white);
            else g.setColor(Color.black);
            String string=i+"";

            // Calculate coordinates
            g.drawString(string,
                    margin + location.getY() * sizeCell-metrics.stringWidth(string)/2,
                    margin + location.getX() * sizeCell-(ascent+descent)/2+ascent);
            i++;
        }
    }


    /**
     * Clear the board
     */
    public void clearBoard() {
        list.clear();
        repaint();
    }


    /**
     * do play do the board
     * @param row row
     * @param col col
     * @param player player
     */
    public void doPlay(int row, int col, int player) {
        list.add(new Location(row, col, player));
        repaint();
    }

}
