package pages;

import management.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Store extends JFrame implements ActionListener {
    JButtonPlus marioButton;
    JButtonPlus marioGreenButton;
    JButtonPlus ghostButton;
    JButtonPlus snakeButton;
    JButtonPlus rabbitButton;
    JButtonPlus back;

    public Store() {
        /////////////////background
        ImageIcon background = new ImageIcon("src/logoAndCloudsImages/background.PNG");
        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setIcon(background);
        backgroundLabel.setBounds(70,40,1420,915);
        ////////////////
        JButtonPlus label = new JButtonPlus("Choose to buy");

        ImageIcon marioImage = null;
        ImageIcon marioGreenImage = null;
        ImageIcon ghostImage = null;
        ImageIcon snakeImage = null;
        ImageIcon rabbitImage = null;
        try {
            marioImage = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/marioImages/bigMario.png")));
            marioGreenImage = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/marioImages/bigMarioGreen.png")));
            ghostImage = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/marioImages/bigGhost.png")));
            snakeImage = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/marioImages/bigSnake.png")));
            rabbitImage = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/marioImages/bigRabbit.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JLabelPlus marioLabel = new JLabelPlus(marioImage,50);
        JLabelPlus marioGreenLabel = new JLabelPlus(marioGreenImage,200);
        JLabelPlus ghostLabel = new JLabelPlus(ghostImage,350);
        JLabelPlus snakeLabel = new JLabelPlus(snakeImage,500);
        JLabelPlus rabbitLabel = new JLabelPlus(rabbitImage,650);

        marioButton = new JButtonPlus("0",50,this);
        marioGreenButton = new JButtonPlus("5",200,this);
        ghostButton = new JButtonPlus("10",350,this);
        snakeButton = new JButtonPlus("15",500,this);
        rabbitButton = new JButtonPlus("20",650,this);

        int[] userCharacters = UserManager.getUsersCharacters(GameManager.username);

        for(int i = 0; i < userCharacters.length ; i++) {
            if(userCharacters[i] == 1) {
                marioButton.setBorder(BorderFactory.createLineBorder(Color.GREEN,3));;
            } else if(userCharacters[i] == 2) {
                marioGreenButton.setBorder(BorderFactory.createLineBorder(Color.GREEN,3));;
            } else if(userCharacters[i] == 3) {
                ghostButton.setBorder(BorderFactory.createLineBorder(Color.GREEN,3));;
            } else if(userCharacters[i] == 4) {
                snakeButton.setBorder(BorderFactory.createLineBorder(Color.GREEN,3));;
            } else if(userCharacters[i] == 5) {
                rabbitButton.setBorder(BorderFactory.createLineBorder(Color.GREEN,3));;
            }
        }

        back = new JButtonPlus(this);

        JPanel panel = new JPanel();
        panel.setBounds(0,0,Data.screenWidth,Data.screenHeight);
        panel.setBackground(new Color(0x1298D6));
        panel.setOpaque(true);
        panel.setLayout(null);
        panel.add(label);
        panel.add(marioLabel);
        panel.add(marioGreenLabel);
        panel.add(ghostLabel);
        panel.add(snakeLabel);
        panel.add(rabbitLabel);
        panel.add(marioButton);
        panel.add(marioGreenButton);
        panel.add(ghostButton);
        panel.add(snakeButton);
        panel.add(rabbitButton);
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

        String[] usersInfo = UserManager.getUsersInfo(GameManager.username);

        if(e.getSource() == marioGreenButton) {
            if(UserManager.doesUserHaveThisCharacter(2)) {
                JOptionPane.showMessageDialog(null,"You already have this character.","Invalid choice",JOptionPane.WARNING_MESSAGE);
            } else {
                if(Integer.parseInt(usersInfo[3]) >= 5) {
                    marioGreenButton.setBorder(BorderFactory.createLineBorder(Color.GREEN,3));
                    UserManager.changeUserCoins(usersInfo[0],-5);
                    UserManager.addUserCharacter(GameManager.username,2);
                } else {
                    JOptionPane.showMessageDialog(null,"You do not have enough coins to buy this.","Invalid choice",JOptionPane.WARNING_MESSAGE);
                }
            }
        }

        if(e.getSource() == ghostButton) {
            if(UserManager.doesUserHaveThisCharacter(3)) {
                JOptionPane.showMessageDialog(null,"You already have this character.","Invalid choice",JOptionPane.WARNING_MESSAGE);
            } else {
                if(Integer.parseInt(usersInfo[3]) >= 10) {
                    ghostButton.setBorder(BorderFactory.createLineBorder(Color.GREEN,3));
                    UserManager.changeUserCoins(usersInfo[0],-10);
                    UserManager.addUserCharacter(GameManager.username,3);
                } else {
                    JOptionPane.showMessageDialog(null,"You do not have enough coins to buy this.","Invalid choice",JOptionPane.WARNING_MESSAGE);
                }
            }
        }

        if(e.getSource() == snakeButton) {
            if(UserManager.doesUserHaveThisCharacter(4)) {
                JOptionPane.showMessageDialog(null,"You already have this character.","Invalid choice",JOptionPane.WARNING_MESSAGE);
            } else {
                if(Integer.parseInt(usersInfo[3]) >= 15) {
                    snakeButton.setBorder(BorderFactory.createLineBorder(Color.GREEN,3));
                    UserManager.changeUserCoins(usersInfo[0],-15);
                    UserManager.addUserCharacter(GameManager.username,4);
                } else {
                    JOptionPane.showMessageDialog(null,"You do not have enough coins to buy this.","Invalid choice",JOptionPane.WARNING_MESSAGE);
                }
            }
        }


        if(e.getSource() == rabbitButton) {
            if(UserManager.doesUserHaveThisCharacter(4)) {
                JOptionPane.showMessageDialog(null,"You already have this character.","Invalid choice",JOptionPane.WARNING_MESSAGE);
            } else {
                if(Integer.parseInt(usersInfo[3]) >= 20) {
                    rabbitButton.setBorder(BorderFactory.createLineBorder(Color.GREEN,3));
                    UserManager.changeUserCoins(usersInfo[0],-20);
                    UserManager.addUserCharacter(GameManager.username,5);
                } else {
                    JOptionPane.showMessageDialog(null,"You do not have enough coins to buy this.","Invalid choice",JOptionPane.WARNING_MESSAGE);
                }
            }
        }


        if(e.getSource() == back) {
            new MainPage();
            this.dispose();
        }
    }
}
