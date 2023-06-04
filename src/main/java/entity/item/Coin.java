package entity.item;

import entity.item.ItemObject;
import game.GamePanel;
import management.Data;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Coin extends ItemObject {
    public int[] coinsTile;
    public boolean isEaten;
    public Coin(int x, int y, GamePanel gp) {
        this.x = x;
        this.y = y;
        this.gp = gp;
        this.jumpSpeed = 1;
        speed = 2;
        solidArea = new Rectangle(3 * Data.scale,3 * Data.scale,9 * Data.scale,Data.tileSize - (3 * Data.scale));
        getItemImage("/tileImages/Coin5.png");
    }

    @Override
    public void draw(Graphics2D g2, int x) {

        if(!isEaten) {
            BufferedImage image = upR;
            g2.drawImage(image, x, y, IHeight, IHeight, null);

            gp.collisionChecker.drawTiles(g2,this);
            gravityUpdate();

        }

//        System.out.println(y + "   " + x);
    }
}
