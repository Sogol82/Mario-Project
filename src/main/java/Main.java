import game.GameFrame;
import game.GamePanel;
import management.Data;
import management.GameManager;
import management.UserManager;
import pages.*;

public class Main {
    public static void main(String[] args) {
        Data.gameFrame = new GameFrame(new GamePanel(3));
//        new EnterPage();
    }
}
