package entity.item;

import entity.item.ItemObject;
import game.GamePanel;
import management.Data;

import java.awt.*;

public class Coin extends ItemObject {
    public int[] coinsTile;
    public Coin(int x, int y, GamePanel gp) {
        this.x = x;
        this.y = y;
        this.gp = gp;
        this.jumpSpeed = 1;
        speed = 2;
        solidArea = new Rectangle(3 * Data.scale,3 * Data.scale,9 * Data.scale,Data.tileSize - (3 * Data.scale));
        getItemImage("/tileImages/Coin5.png");
    }
}
