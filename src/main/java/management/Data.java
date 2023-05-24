package management;

import game.GameFrame;

import java.awt.*;

public class Data {
    /////////////////colors
    public static Color green = new Color(0x33AF4A);
    public static Color red = new Color(0xED1C24);
    public static Color yellow = new Color(0xFAD008);
    public static Color blue = new Color(0x1298D6);

    ///////////////////buttons
    public static int buttonWidth =  350;
    public static int buttonHeight =  80;
    public static int backButtonWidth =  100;
    public static int backButtonHeight =  80;

    //////////////////////////screen
    public static final int originalTileSize = 16;
    public static final int scale = 5;
    public static final int tileSize = originalTileSize * scale;
    public static final int maxScreenColumn = 18;
    public static final int maxScreenRow = 12;
    public static final int screenWidth = maxScreenColumn * tileSize;
    public static final int screenHeight = maxScreenRow * tileSize;
    public static final int maxLevelSections = 5;
    public static final int maxLevelCol = maxLevelSections * maxScreenColumn;
    public static final int maxLevelWidth = maxLevelCol * Data.tileSize;

    ///////////////////frame
    public static GameFrame gameFrame;

    public static boolean isNewGame;

    public static int levelTime = 60;
    public static int coinX = 3 * scale;
    public static int coinY = 3 * scale;
    public static int coinWidth = 9 * scale;
    public static int coinHeight = 9 * scale;

    public static boolean isLevelPassed;
}
