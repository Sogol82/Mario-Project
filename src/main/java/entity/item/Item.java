package entity.item;

import entity.Entity;
import game.CollisionChecker;
import management.Data;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Item extends Entity implements ItemI{
    public int IHeight = Data.tileSize;
    game.GamePanel gp;
    @Override
    public void getItemImage(String path) {
        try {
            upR = ImageIO.read(getClass().getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void gravityUpdate() {
        if(gp.collisionChecker.gravity(this)) {
            y += jumpSpeed;
        } else if(y % Data.tileSize != 0) {
                y = ((y/ Data.tileSize)+1) * Data.tileSize;
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        BufferedImage image = upR;
        g2.drawImage(image, x, y, IHeight, IHeight, null);
    }

    @Override
    public void moveForward() {
        if(direction.equals("right")) {
            x += speed;
        } else {
            x -= speed;
        }
    }

    @Override
    public void moveBackward() {
        if(direction.equals("right")) {
            x -= speed;
        } else {
            x += speed;
        }
    }
}
