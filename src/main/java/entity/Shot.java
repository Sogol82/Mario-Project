package entity;

import game.GamePanel;
import management.Data;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Shot extends Entity{
    public int SHeight;
    public boolean stop;
    public int numberOfFPS;
    public int counter = 0;
    public Shot(int x, int y, int speed,String direction) {
        this. x = x;
        this.y = y;
        this.speed = speed;
        numberOfFPS = (Data.tileSize*8)/speed;

        if(direction.equals("right") || direction.equals("upRight")) {
            this.direction = "right";
        } else {
            this.direction = "left";
        }


        if(this.direction.equals("right")) {
            this.x+=Data.tileSize;
        } else {
            this.x-=Data.tileSize;
        }

        SHeight = Data.tileSize/2;
        solidArea = new Rectangle(0,2 * Data.scale,SHeight,(SHeight) - (5 * Data.scale));
        getShotImage();
    }

    void getShotImage() {
        try {
            upR = ImageIO.read(getClass().getResourceAsStream("/marioImages/Shot.png"));
            upL = ImageIO.read(getClass().getResourceAsStream("/marioImages/ShotLeft.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void moveForward() {
        if(direction.equals("right")) {
            x += speed;
        } else {
            x -= speed;
        }
        check8Block();

//        if(direction.equals("right")) {
//            if(check8Block(x+speed)) {
//                x += speed;
//            }
//        } else {
//            if(check8Block(x-speed)) {
//                x -= speed;
//            }
//        }
    }

    public void moveBackward() {
        if(direction.equals("right")) {
            x -= speed;
        } else {
            x += speed;
        }
        check8Block();

//        if(direction.equals("right")) {
//            if(check8Block(x-speed)) {
//                x -= speed;
//            }
//        } else {
//            if(check8Block(x+speed)) {
//                x += speed;
//            }
//        }
    }

    public void draw(Graphics2D g2) {
        counter++;
        BufferedImage image = null;
        switch(direction) {
            case "right":
                image = upR;
                break;
            case "left":
                image = upL;
        }
        g2.drawImage(image, x, y, SHeight, SHeight, null);
    }

    public boolean check8Block() {
        if(counter >= numberOfFPS) {
            stop = true;
            return false;
        }
        stop = false;
        return true;
    }
}
