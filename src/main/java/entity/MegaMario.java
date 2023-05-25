package entity;

import game.GamePanel;
import game.KeyHandler;
import management.Data;

import javax.imageio.ImageIO;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class MegaMario extends Player {
    public MegaMario(GamePanel gp, KeyHandler keyHandler) {
        super(gp,keyHandler);
        this.gp = gp;
        this.keyHandler = keyHandler;
        hearts = 3;
        solidArea = new Rectangle(2 * Data.scale,2 * Data.scale,12 * Data.scale,PHeight - (2 * Data.scale));
        this.setDefaultValues();
        this.getPlayerImage(this.gp.playerID);
    }
    public MegaMario(game.GamePanel gp, game.KeyHandler keyHandler, int x, int y, int hearts, int PHeight) {
        this(gp,keyHandler);
        this.x = x;
        this.y = y;
        this.hearts = hearts;
        this.PHeight = PHeight;
        solidArea = new Rectangle(2 * Data.scale,2 * Data.scale,12 * Data.scale,PHeight - (2 * Data.scale));
        this.getPlayerImage(this.gp.playerID);
    }
    public void getPlayerImage(int id) {
        ///////1 = mario
        ///////2 = green mario
        ///////3 ghost
        ///////4 snake
        ///////5 rabbit
        try {
            if(id == 1) {
                upR = ImageIO.read(getClass().getResourceAsStream("/marioImages/JumpRight.png"));
                upL = ImageIO.read(getClass().getResourceAsStream("/marioImages/JumpLeft.png"));
                right1 = ImageIO.read(getClass().getResourceAsStream("/marioImages/Right1.png"));
                right2 = ImageIO.read(getClass().getResourceAsStream("/marioImages/Right2.png"));
                left1 = ImageIO.read(getClass().getResourceAsStream("/marioImages/Left1.png"));
                left2 = ImageIO.read(getClass().getResourceAsStream("/marioImages/Left2.png"));
                dead = ImageIO.read(getClass().getResourceAsStream("/marioImages/Dead.png"));
            } else if (id == 2) {
                upR = ImageIO.read(getClass().getResourceAsStream("/marioImages/JumpRightgreen.png"));
                upL = ImageIO.read(getClass().getResourceAsStream("/marioImages/JumpLeftgreen.png"));
                right1 = ImageIO.read(getClass().getResourceAsStream("/marioImages/Right1green.png"));
                right2 = ImageIO.read(getClass().getResourceAsStream("/marioImages/Rightgreen.png"));
                left1 = ImageIO.read(getClass().getResourceAsStream("/marioImages/Left1green.png"));
                left2 = ImageIO.read(getClass().getResourceAsStream("/marioImages/Leftgreen.png"));
                dead = ImageIO.read(getClass().getResourceAsStream("/marioImages/Deadgreen.png"));
            } else if (id == 3) {
                upR = ImageIO.read(getClass().getResourceAsStream("/marioImages/UpRightGhost.png"));
                upL = ImageIO.read(getClass().getResourceAsStream("/marioImages/UpLefttGhost.png"));
                right1 = ImageIO.read(getClass().getResourceAsStream("/marioImages/RightGhost.png"));
                right2 = ImageIO.read(getClass().getResourceAsStream("/marioImages/Right2Ghost.png"));
                left1 = ImageIO.read(getClass().getResourceAsStream("/marioImages/LefttGhost.png"));
                left2 = ImageIO.read(getClass().getResourceAsStream("/marioImages/Left2Ghost.png"));
                dead = ImageIO.read(getClass().getResourceAsStream("/marioImages/deadGhost.png"));
            } else if (id == 4) {
                upR = ImageIO.read(getClass().getResourceAsStream("/marioImages/RightSnake.png"));
                upL = ImageIO.read(getClass().getResourceAsStream("/marioImages/LeftSnake.png"));
                right1 = ImageIO.read(getClass().getResourceAsStream("/marioImages/RightSnake.png"));
                right2 = ImageIO.read(getClass().getResourceAsStream("/marioImages/Right2Snake.png"));
                left1 = ImageIO.read(getClass().getResourceAsStream("/marioImages/LeftSnake.png"));
                left2 = ImageIO.read(getClass().getResourceAsStream("/marioImages/Left2Snake.png"));
                dead = ImageIO.read(getClass().getResourceAsStream("/marioImages/deadSnake.png"));
            } else if (id == 5) {
                upR = ImageIO.read(getClass().getResourceAsStream("/marioImages/UpRightRabbit.png"));
                upL = ImageIO.read(getClass().getResourceAsStream("/marioImages/UpLeftRabbit.png"));
                right1 = ImageIO.read(getClass().getResourceAsStream("/marioImages/RightRabbit.png"));
                right2 = ImageIO.read(getClass().getResourceAsStream("/marioImages/Right2Rabbit.png"));
                left1 = ImageIO.read(getClass().getResourceAsStream("/marioImages/LeftRabbit.png"));
                left2 = ImageIO.read(getClass().getResourceAsStream("/marioImages/Left2Rabbit.png"));
                dead = ImageIO.read(getClass().getResourceAsStream("/marioImages/deadRabbit.png"));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setDefaultValues() {
        x = Data.tileSize * 4;
        y = Data.screenHeight - (3 * Data.tileSize);
        speed = 4;
        jumpSpeed = 6;
        PHeight = Data.tileSize * 2;
    }
    public void update() {
        if(!isDead) {
            if(keyHandler.goDownPipe) {
                if(direction.equals("right") || direction.equals("left")) {
                    ///////////////////////////////////////////////////////
                    if(gp.collisionChecker.checkSecretPipe(this)) {
                        if(secretPipeCounter > 0) {
                            ySecretPipe = y + 2 * Data.tileSize;
                            pipeDown = true;
                            secretPipeCounter = 0;
                        }
                        if(pipeDown) {
                            if(y < ySecretPipe) {
                                y += 2;
                            } else {
                                y = ySecretPipe;
                                pipeUp = true;
                                pipeDown = false;
                            }
                        }
                        if(pipeUp) {
                            if(y > ySecretPipe -  2 * Data.tileSize) {
                                y -= 2;
                            } else {
                                y = ySecretPipe -  2 * Data.tileSize;
                                pipeUp = false;
                                secretPipeCounter = 1;
                                keyHandler.goDownPipe = false;
                            }
                        }
                    }
                    else {
                        keyHandler.goDownPipe = false;
                    }
                }
            } else if(keyHandler.leftPressed || keyHandler.rightPressed || keyHandler.jump || keyHandler.getDown) {

                if(keyHandler.leftPressed) {
                    if(gp.collisionChecker.checkLeft(this)) {
                        x -= speed;
                        direction = "left";
                        if(x < 0) {
                            x = 0;
                        }
                    }
                }
                if(keyHandler.rightPressed) {
                    if(gp.collisionChecker.checkRight(this)) {
                        x += speed;
                        direction = "right";
                        if (x > Data.maxLevelWidth - Data.tileSize) {
                            x = Data.maxLevelWidth - Data.tileSize;
                        }
                    }
                }

                /////////////////////////////////////////////////
                if(keyHandler.jump) {
                    if(keyHandler.checkFirstTimeForYJump) {
                        yJump = y;
                        keyHandler.checkFirstTimeForYJump = false;
                    }

                    if(gp.collisionChecker.checkUp(this)) {
                        y -= jumpSpeed;
                        if(y <= yJump-jumpHeight) {
                            y = yJump-jumpHeight;
                            keyHandler.getDown = true;
                            keyHandler.jump = false;
                        }
                    } else {
                        keyHandler.getDown = true;
                        keyHandler.jump = false;
                    }
                } else if(keyHandler.getDown) {
                    if(gp.collisionChecker.checkDown(this)) {
                        y += jumpSpeed;
                    } else {
                        if(y % Data.tileSize != 0) {
                            y = ((y/ Data.tileSize)+1) * Data.tileSize;
                        }
                        keyHandler.getDown = false;
                        if(direction.equals("upLeft")) {
                            direction = "left";
                        } else {
                            direction = "right";
                        }
                    }////////////////////////////////////////////////////////////////////////////
                }

                /////////////////direction
                if(keyHandler.jump && direction.equals("left")) {
                    direction = "upLeft";
                } else if(keyHandler.jump && direction.equals("right")) {
                    direction = "upRight";
                }

                ////////////////////////////////////////////////////////////////////////////////////////////////
                gp.collisionChecker.checkTile(this);
                //////////////////////////////////////////////////here is ok
                spriteCounter++;
                if(spriteCounter > 10) {
                    if(spriteNum == 1) {
                        spriteNum = 2;
                    } else if(spriteNum == 2) {
                        spriteNum = 1;
                    }
                    spriteCounter = 0;
                }
            } else {
                spriteNum = 1;
            }

            if(!keyHandler.goDownPipe) {
                gp.collisionChecker.checkCoinCollision(this);
                ///////////////////////////////////////////////////////////////
                if( direction.equals("upRight") ||  direction.equals("upLeft")) {
                    gp.collisionChecker.checkBlockCollision(this);
                }
                fallUpdate();
                plantCollisionUpdate();
                gravityUpdate();
            }

            if(gp.collisionChecker.checkLevelPassed(this)) {
                Data.isLevelPassed = true;
                gp.endGame();
            }
        } else {///////////////////////if dead
            deadUpdate();
        }
    }
    public void fallUpdate() {
        if(gp.collisionChecker.checkFall(this)) {
            this.isDead = true;
            deadUp = true;
            yDead = y;
            this.hearts--;
        }
    }
    public void deadUpdate() {
        if(deadUp) {
            gp.player = new MiniMario(this.gp,this.keyHandler,this.x,this.y+Data.tileSize,this.hearts);
        }
    }
    public void plantCollisionUpdate() {
        for(Plant plant : gp.plants) {
            if(gp.collisionChecker.checkPlantCollision(this,plant)) {
                this.isDead = true;
                deadUp = true;
                yDead = y;
                /////////////////////////////////
//                this.hearts--;
            }
        }
    }

}
