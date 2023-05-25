import game.GameFrame;
import game.GamePanel;
import management.Data;
import management.GameManager;
import management.UserManager;
import pages.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("it is not ok");
        Data.gameFrame = new GameFrame(new GamePanel(5));
//        new EnterPage();
    }
}
