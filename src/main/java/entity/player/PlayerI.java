package entity.player;

import management.Data;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public interface PlayerI {
    void getPlayerImage(int id);
    void setDefaultValues();
    void update();
    void fallUpdate();
    void deadUpdate();
    void plantCollisionUpdate();
    void gravityUpdate();
    void draw(Graphics2D g2);
}
