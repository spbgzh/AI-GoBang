package chessGoBang;

import org.junit.Test;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class ChessPanelTest {
    JFrame frame = new JFrame();
    ChessPanel chessPanel = new ChessPanel();

    @Test
    public void TestChessPanel() {
        frame.add(chessPanel);
        frame.setSize(476, 532);
        frame.setVisible(true);
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
