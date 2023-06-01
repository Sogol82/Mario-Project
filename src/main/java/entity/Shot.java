package entity;

import game.CollisionChecker;
import game.GamePanel;
import management.Data;
import management.GameManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Shot extends Entity {
    GamePanel gp;
    CollisionChecker collisionChecker;
    public int SHeight;
    public boolean stop;
    public int numberOfFPS;
    public int counter = 0;
    public int shotsTileColumn;
    public int distanceFromFirstColumn = 0;
    public int drawCounter = 1;
    public int firstXDrawn = 0;
    ///////////////lastXDrawn initialization is for fun
    public int lastXDrawn = 1;
    public Shot(int x, int y, int speed,String direction, GamePanel gp) {

        shotsTileColumn = x/Data.tileSize;

        this. x = x;
        this.y = y;
        this.speed = speed;
        this.gp = gp;
        collisionChecker = new CollisionChecker(gp);


        if(direction.equals("right") || direction.equals("upRight")) {
            this.direction = "right";
//            distanceFromFirstColumn += Data.tileSize;
            shotsTileColumn += 1;

        } else {
            this.direction = "left";

        }

//        if(this.direction.equals("right")) {
//            this.x+=Data.tileSize;
//        } else {
//            this.x-=Data.tileSize;
//        }

        SHeight = Data.tileSize/2;
        solidArea = new Rectangle(0,2 * Data.scale,SHeight,(SHeight) - (5 * Data.scale));

        numberOfFPS = ((Data.tileSize*8 - (SHeight - solidArea.x - solidArea.width))/speed)*2;

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
            if(!collisionChecker.checkRight(this)) {
                x -= speed;
                stop = true;
            }
        } else {
            x -= speed;
            if(!collisionChecker.checkLeft(this)) {
                x += speed;
                stop = true;
            }
        }
        check8Block();

//        if(direction.equals("right")) {
//            x += speed;
//        } else {
//            x -= speed;
//        }
//        check8Block();

    }

    public void moveBackward() {
//        if(direction.equals("right")) {
//            x -= speed;
//            if(!collisionChecker.checkRight(this)) {
//                x += speed;
//                stop = true;
//            }
//        } else {
//            x += speed;
//            if(!collisionChecker.checkLeft(this)) {
//                x -= speed;
//                stop = true;
//            }
//        }
//        check8Block();

        if(direction.equals("right")) {
            x -= speed;
        } else {
            x += speed;
        }
        check8Block();
    }


    int shotCounter = 0;
    public void draw(int x, Graphics2D g2) {
//        this.x = x + gp.player.x - gp.player.screenX;
        check8Block();

        counter++;
        BufferedImage image = null;
        switch(direction) {
            case "right":
                image = upR;
                if(shotCounter == 0) {
                    shotCounter++;
                } else {
                    shotCounter = 0;
                    distanceFromFirstColumn += speed;
                }
                break;
            case "left":
                image = upL;
                if(shotCounter == 0) {
                    shotCounter++;
                } else {
                    shotCounter = 0;
                    distanceFromFirstColumn -= speed;
                }
        }

        g2.drawImage(image, x, y, SHeight, SHeight, null);


//        solidArea = new Rectangle(0,2 * Data.scale,SHeight,(SHeight) - (5 * Data.scale));
        g2.setColor(Color.green);
        g2.drawRect(x+solidArea.x,y+solidArea.y,solidArea.width,solidArea.height);


        //collisionChecker.drawTiles(g2,this);

//        collisionChecker.drawTiles(g2,this);

//        if(gp.player.x < screenX) {
//            g2.drawImage(image, gp.player.x, y, Data.tileSize, PHeight, null);
//        } else if(gp.player.x > Data.maxLevelWidth - Data.screenWidth + screenX) {
//            g2.drawImage(image, gp.player.x - Data.maxLevelWidth + Data.screenWidth, y, Data.tileSize, PHeight, null);
//        } else {
//            g2.drawImage(image, screenX, y, Data.tileSize, PHeight, null);
//        }
    }

    public void check8Block() {

        if(direction.equals("right")) {
            if(lastXDrawn - firstXDrawn >= Data.tileSize*8) {
                stop = true;
                return;
            }
        } else {
            if(firstXDrawn - lastXDrawn >= Data.tileSize*8) {
                System.out.println("hehe");
                System.out.println("first x : " + firstXDrawn + "   last x : " + lastXDrawn + "  difference : " + (firstXDrawn-lastXDrawn));
                stop = true;
                return;
            }
        }

        if((x < 0) || (!collisionChecker.checkRight(this) || !collisionChecker.checkLeft(this)) ) {
            stop = true;
        }

//
////        if(!collisionChecker.checkRight(this) || !collisionChecker.checkLeft(this)) {
////            stop = true;
////            return false;
////        }
//
//
////        if(direction.equals("right")) {
//////            if ()
////        } else {
////            x += speed;
////        }
//
//        if(counter >= numberOfFPS) {
//            stop = true;
//            return false;
//        }
//        stop = false;
//        return true;
    }











    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////

}
