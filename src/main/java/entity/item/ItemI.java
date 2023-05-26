package entity.item;

import java.awt.*;

public interface ItemI {
    void getItemImage(String path);
//    void setDefaultValues();
//    void update();
//    void isEaten();
//    void fallUpdate();
//    void deadUpdate();
//    void plantCollisionUpdate();
    void gravityUpdate();
    void draw(Graphics2D g2);
    void moveForward();
    void moveBackward();
}
