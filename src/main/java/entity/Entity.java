package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public int x, y;
    public int speed;
    public int jumpSpeed;
    public BufferedImage upR, upL, right1, right2, left1, left2, dead;
    public String direction = "right";
    public int spriteNum = 1;
    public int spriteCounter = 0;
    public Rectangle solidArea;
    public String type;
}
