package entity.enemy;

import entity.Entity;
import management.Data;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Plant extends EnemyObject {
    int upHeight = 3 * Data.tileSize;
    int firstY;
    public int[] plantsTile;
    public int realX;
    boolean goingUp, goingDown, waiting;
    public Plant(int x, int y) {
        firstY = y;
        this.x = x;
        this.y = y;
        solidArea = new Rectangle(0,0,2 * Data.tileSize - 2,2 * Data.tileSize);
        speed = 2;
        getPlantImage();
        goingUp = true;
        spriteCounter = 0;
    }
    public void getPlantImage() {
        try {
            upR = ImageIO.read(getClass().getResourceAsStream("/enemies/Plaant.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if(goingUp) {
            y -= speed;
            spriteCounter++;
            if (y < firstY - upHeight) {
                y = firstY - upHeight;
                goingUp = false;
                goingDown = true;
            }
        } else if(goingDown) {
            y += speed;
            spriteCounter++;
            if (y > firstY) {
                y = firstY;
                goingDown = false;
                waiting = true;
            }
        } else if(waiting) {
            spriteCounter--;
            if (spriteCounter == 0) {
                waiting = false;
                goingUp = true;
            }
        }
    }

    public void draw(Graphics2D g2 ,int xTile) {
        BufferedImage image = upR;
        if(!waiting) g2.drawImage(image, xTile, y, Data.tileSize * 2, Data.tileSize * 2, null);
    }
}








