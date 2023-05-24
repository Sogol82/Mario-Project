package pages;

import management.Data;
import management.GameManager;
import management.JButtonPlus;
import management.UserManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPage extends JFrame implements ActionListener {

    JButtonPlus startNewGame;
    JButtonPlus continuePreviousGame;
    JButtonPlus highestScore;
    JButtonPlus store;
    JButtonPlus coins;
    JButtonPlus profile;
    JButtonPlus back;

    public MainPage() {
        /////////////////background
        ImageIcon background = new ImageIcon("src/logoAndCloudsImages/background.PNG");
        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setIcon(background);
        backgroundLabel.setBounds(70,40,1420,915);
        ////////////////

        startNewGame = new JButtonPlus("Start New Game",100,Data.red,this);

        continuePreviousGame = new JButtonPlus("Continue Last Game",210,Data.yellow,this);

        highestScore = new JButtonPlus("Highest Scores",320,Data.green,this);

        store = new JButtonPlus("Store",430,Data.red,this);

        String[] usersInfo = UserManager.getUsersInfo(GameManager.username);
        int coin = Integer.parseInt(usersInfo[3]);
        coins = new JButtonPlus("Coins:" + coin ,540,Data.yellow,this);
        coins.removeActionListener(this);
        coins.setContentAreaFilled(false);

        JLabel coinsBackground = new JLabel();
        coinsBackground.setBounds(coins.getX(),coins.getY(),coins.getWidth(),coins.getHeight());
        coinsBackground.setBackground(Data.yellow);
        coinsBackground.setOpaque(true);

        profile = new JButtonPlus("Profile",650,Data.green,this);

        back = new JButtonPlus(this);

        JPanel panel = new JPanel();
        panel.setBounds(0,0,Data.screenWidth,Data.screenHeight);
        panel.setBackground(new Color(0x1298D6));
        panel.setOpaque(true);
        panel.setLayout(null);
        panel.add(startNewGame);
        panel.add(continuePreviousGame);
        panel.add(highestScore);
        panel.add(store);
        panel.add(coins);
        panel.add(coinsBackground);
        panel.add(profile);
        panel.add(back);
        panel.add(backgroundLabel);

        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setSize(Data.screenWidth,Data.screenHeight);
        this.setTitle("Main Page");
        this.add(panel);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==startNewGame) {
            Data.isNewGame = true;
            new LoadGames();
            this.dispose();
        }
        if(e.getSource()==continuePreviousGame) {
            Data.isNewGame = false;
            new LoadGames();
            this.dispose();
        }
        if(e.getSource()==highestScore) {
            UserManager.leaderBoard();
        }
        if(e.getSource()==store) {
            new Store();
            this.dispose();
        }
        if(e.getSource()==profile) {
            new Profile();
            this.dispose();
        }

        if(e.getSource() == back) {
            new EnterPage();
            this.dispose();
        }
    }

}
