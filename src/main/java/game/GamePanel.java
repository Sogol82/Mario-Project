package game;

import entity.enemy.Plant;
import entity.item.Coin;
import entity.player.FireMario;
import entity.player.Player;
import entity.player.Shot;
import management.*;
import pages.LoadGames;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class GamePanel extends JPanel implements Runnable{
    public int seconds = Data.levelTime;
    String second_String = String.format("%03d",seconds);
    public int playerID;
    int coins;
    public int score;
    int FPS = 70;
    TileManager tileManager;
    Thread gameThread;
    KeyHandler keyHandler;
    public CollisionChecker collisionChecker;
    public Player player;
    public List<Plant> plants;
    public List<Coin> coinsList;
    public Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            seconds--;
            if(seconds < 0) seconds = 0;
            second_String = String.format("%03d",seconds);
        }
    });

    public GamePanel(int id) {
        playerID = id;
        plants = new ArrayList<>();
        coinsList = new ArrayList<>();
        tileManager = new TileManager(this);
        keyHandler = new KeyHandler();
        collisionChecker = new CollisionChecker(this);
        //////////////////////////////////////////////////////////////////
        player = new FireMario(this,keyHandler);
        coins = 0;
        score = 0;

        this.setPreferredSize(new Dimension(Data.screenWidth,Data.screenHeight));
        this.setBackground(new Color(0x1298D6));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);

        timer.start();

        gameThread = new Thread(this);
        gameThread.start();
    }

    public GamePanel(int x , int y, int id) {
        plants = new ArrayList<>();
        tileManager = new TileManager(this);
        keyHandler = new KeyHandler();
        collisionChecker = new CollisionChecker(this);
        coins = 0;
        score = 0;

        playerID = id;
        player = new Player(this,keyHandler);
        player.x = x;
        player.y = y;

        this.setPreferredSize(new Dimension(Data.screenWidth,Data.screenHeight));
        this.setBackground(new Color(0x1298D6));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);

        timer.start();

        gameThread = new Thread(this);
        gameThread.start();
    }

    /////////////////delta method
    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread != null) {
            if(seconds == 0) {
                player.isDead = true;
                player.deadUp = true;
                player.yDead = player.y;
                player.hearts--;
                seconds = Data.levelTime;
                timer.stop();
            }
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if(delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }
    public void update() {
        player.update();
        for(Plant plant : plants) {
            plant.update();
        }

        coinUpdate();

        if(keyHandler.exitGame || player.hearts == 0) {
            endGame();
        }
    }

    public void coinUpdate() {
        //////////////////////////////check shots
        ArrayList<Coin> newCoins = new ArrayList<>();
        for(Coin coin : coinsList) {
            if(!coin.isEaten) {
                newCoins.add(coin);
            }
        }
        coinsList = newCoins;
    }
    public void endGame() {
        score += seconds;
        score += 20 * player.hearts;
        UserManager.changeUserCoins(GameManager.username,coins);
        GameManager.saveNewGame(this.player.x,this.player.y,this.playerID,this.score);
        if(Data.isLevelPassed) {
            GameManager.deleteGame();
        }
        new LoadGames();
        Data.gameFrame.remove(this);
        Data.gameFrame.dispose();
        timer.stop();
        gameThread = null;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.white);
        g2.setFont(new Font("MV Boli",Font.BOLD,40));
        g2.drawString("SCORE",0 + 80, 50);
        g2.drawString("COINS",(Data.screenWidth/5) + 80, 50);
        g2.drawString("LEVEL 1",(Data.screenWidth/5) * 2 + 80, 50);
        g2.drawString("TIME",(Data.screenWidth/5) * 3 + 80, 50);
        g2.drawString("LIVES",(Data.screenWidth/5) * 4 + 80, 50);

        g2.drawString(String.valueOf(score),0 + 80, 100);
        g2.drawString(String.valueOf(coins),(Data.screenWidth/5) + 80, 100);
        g2.drawString(second_String,(Data.screenWidth/5) * 3 + 80, 100);
        g2.drawString(String.valueOf(this.player.hearts),(Data.screenWidth/5) * 4 + 80, 100);

        if(keyHandler.goDownPipe) {
            player.draw(g2);

            tileManager.draw(g2);
        } else {
            tileManager.draw(g2);

            player.draw(g2);
        }

        g2.dispose();
    }
}
