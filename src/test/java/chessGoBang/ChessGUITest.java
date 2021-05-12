package chessGoBang;

import org.junit.Test;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class ChessGUITest {
    ChessGUI test= new ChessGUI();
    @Test
    public void TestChessGUI() {
        test.create();
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
