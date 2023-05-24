package pages;

import game.GameFrame;
import game.GamePanel;
import management.Data;
import management.GameManager;
import management.JButtonPlus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadGames extends JFrame implements ActionListener {
    JButtonPlus game1;
    JButtonPlus game2;
    JButtonPlus game3;
    JButtonPlus back;

    public LoadGames() {
        /////////////////background
        ImageIcon background = new ImageIcon("src/logoAndCloudsImages/background.PNG");
        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setIcon(background);
        backgroundLabel.setBounds(70,40,1420,915);
        ////////////////
        JButtonPlus label = new JButtonPlus("Choose one game");


        game1 = new JButtonPlus("Game 1",250,Data.red,this);

        game2 = new JButtonPlus("Game 2",360,Data.yellow,this);

        game3 = new JButtonPlus("Game 3",470,Data.green,this);

        back = new JButtonPlus(this);

        JPanel panel = new JPanel();
        panel.setBounds(0,0,Data.screenWidth,Data.screenHeight);
        panel.setBackground(new Color(0x1298D6));
        panel.setOpaque(true);
        panel.setLayout(null);
        panel.add(label);
        panel.add(game1);
        panel.add(game2);
        panel.add(game3);
        panel.add(back);
        panel.add(backgroundLabel);

        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setSize(Data.screenWidth,Data.screenHeight);
        this.setTitle("Super Mario");
        this.add(panel);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==game1) {
            GameManager.gameNumber = 1;
            if(Data.isNewGame) {
                new ChooseCharacterAndDifficulty();
                this.dispose();
            } else {
                int[] data = GameManager.returnGame();
                if(data[0] == -1) {
                    JOptionPane.showMessageDialog(null,"This game is empty","Invalid Game",JOptionPane.WARNING_MESSAGE);
                } else {
                    Data.gameFrame = new GameFrame(new GamePanel(data[0],data[1],data[2]));
                    this.dispose();
                }
            }
        }
        if(e.getSource()==game2) {
            GameManager.gameNumber = 2;
            if(Data.isNewGame) {
                new ChooseCharacterAndDifficulty();
                this.dispose();
            } else {
                int[] data = GameManager.returnGame();
                if(data[0] == -1) {
                    JOptionPane.showMessageDialog(null,"This game is empty","Invalid Game",JOptionPane.WARNING_MESSAGE);
                } else {
                    Data.gameFrame = new GameFrame(new GamePanel(data[0],data[1],data[2]));
                    this.dispose();
                }
            }
        }
        if(e.getSource()==game3) {
            GameManager.gameNumber = 3;
            if(Data.isNewGame) {
                new ChooseCharacterAndDifficulty();
                this.dispose();
            } else {
                int[] data = GameManager.returnGame();
                if(data[0] == -1) {
                    JOptionPane.showMessageDialog(null,"This game is empty","Invalid Game",JOptionPane.WARNING_MESSAGE);
                } else {
                    Data.gameFrame = new GameFrame(new GamePanel(data[0],data[1],data[2]));
                    this.dispose();
                }
            }
        }

        if(e.getSource() == back) {
            new MainPage();
            this.dispose();
        }
    }
}
