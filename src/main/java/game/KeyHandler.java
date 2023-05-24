package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean upPressed,leftPressed, rightPressed, downPressed, goDownPipe, jump, getDown, checkFirstTimeForYJump, gravity, exitGame;
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == 37) leftPressed = true;
        if(code == 39) rightPressed = true;
        if(code == 38) {
            if(!upPressed && !goDownPipe) {
                upPressed = true;
                if(!jump && !getDown && !gravity) {
                    jump = true;
                    checkFirstTimeForYJump = true;
                }
            }
        }
        if(code == 40) {
            if(!downPressed) {
                downPressed = true;
                if(!jump && !getDown && !gravity) {
                    goDownPipe = true;
                }
            }
        }
        if(code == 8) exitGame = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == 37) leftPressed = false;
        if(code == 39) rightPressed = false;
        if(code == 38) upPressed = false;
        if(code == 40) downPressed = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
