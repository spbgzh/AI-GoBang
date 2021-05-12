package chessGoBang;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.swing.*;


/**
 * The View class is mainly responsible for the display of the game
 * window and calls to Chess and ChessPanel
 *
 * @author Guo ZiHan
 * @version 1.0
 */
public class ChessGUI {
    // JFame object
    static JFrame frame;

    // Chess object
    static Chess chess = new Chess();

    // chessPanel object
    static ChessPanel chessPanel;


    /**
     * create frame
     * Binding event listener
     */
    public static void create() {
        // Init frame
        frame = new JFrame("AI-GoLang");

        // Init chessPanel and add it to the frame
        chessPanel = new ChessPanel();
        frame.setResizable(false);
        frame.add(chessPanel);

        // add ico
        try {
            frame.setIconImage(ImageIO.read(Objects.requireNonNull(ChessGUI.class.getResource("/ico.png"))));//设置图标
        } catch (IOException e) {
            e.printStackTrace();
        }

        // toolbar
        JToolBar bar = new JToolBar("Bar");// create toolbar
        frame.add(bar, BorderLayout.NORTH);// add toolbar to the frame
        bar.setFloatable(false);// set toolbar can't move
        bar.setBorderPainted(false);// set toolbar don't draw the border

        // First tool: Restart
        JButton restartAction = new JButton("Restart");// create toolbar Restart
        restartAction.setBounds(20, 100, 100, 100);
        restartAction.setToolTipText("Restart");
        restartAction.setBorderPainted(false);//get rid of Border
        restartAction.setFocusPainted(false);//get rid of Focus
        restartAction.addActionListener(e -> {
            restartBoard(); //restart binding event listener
        });
        bar.add(restartAction);// Add tool

        // Second tool: firstAction
        final JButton firstAction = new JButton("Human");
        firstAction.setOpaque(true);
        firstAction.setBorderPainted(true);//get rid of Border
        firstAction.setFocusPainted(false);//get rid of Focus
        firstAction.addActionListener(e -> {
            if (firstAction.getText().equals("Human")) {
                firstAction.setText("AI");
                // set who play first to AI
                Chess.firstPlay = Chess.AI;
            } else {
                firstAction.setText("Human");
                // set who play first to HUMAN
                Chess.firstPlay = Chess.HUMAN;
            }
            restartBoard();

        });
        bar.add(firstAction);

        //Set mouse monitoring events for the chess panel
        chessPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showChess(chessPanel, e);
            }
        });

        // Set the relevant attributes of the frame
        frame.setSize(476, 532);
        frame.setLocationRelativeTo(null);// locate frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);// visible
    }


    /**
     * Chess reopening event processing function
     *
     */
    public static void restartBoard() {
        chess.mapInit(); //initialize map
        chessPanel.clearBoard();//redraw the board
        if (Chess.firstPlay == Chess.AI) {
            //if AI play first, AI need do play
            Location location = chess.start();
            chess.play(location.getX(), location.getY(), Chess.AI);
            //The chessboard panel controls the display of the moves
            chessPanel.doPlay(location.getX(), location.getY(), Chess.AI);
        }
    }

    /**
     * The mouse click event of the board panel
     *
     * @param chessPanel chessPanel
     * @param e          MouseEvent
     */
    public static void showChess(ChessPanel chessPanel, MouseEvent e) {
        // get click locations
        int x = e.getX();
        int y = e.getY();

        // transform click location to the location on the board
        int col = (x - 5) / 30;
        int row = (y - 5) / 30;

        // Judge whether click event is effective
        boolean isEnable = chess.play(row, col, Chess.HUMAN);
        if (isEnable) {
            // draw the pisses
            chessPanel.doPlay(row, col, Chess.HUMAN);
            System.out.println("Human play:(" + (row + 1) + "," + (col + 1) + ");");
            // Judge whether human is win
            if (chess.isWin(row, col, Chess.HUMAN)) {
                JOptionPane.showMessageDialog(frame, "Human Win!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                restartBoard();//restart the game
            }

            //Analysis the situation add get the location AI will play
            Location result = chess.analysis();

            // Do play AI's location
            chess.play(result.getX(), result.getY(), Chess.AI);

            // Draw the location
            chessPanel.doPlay(result.getX(), result.getY(), Chess.AI);

            // Judge whether AI is win
            if (chess.isWin(result.getX(), result.getY(), Chess.AI)) {
                JOptionPane.showMessageDialog(frame, "AI Win!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                restartBoard();
            }
            // Judge whether AI is draw
            if (chess.isDraw()) {
                JOptionPane.showMessageDialog(frame, "Draw", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                restartBoard();
            }
        } else System.out.println("illegal!");
    }
}
