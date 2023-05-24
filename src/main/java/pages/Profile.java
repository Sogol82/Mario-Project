package pages;

import management.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Profile extends JFrame implements ActionListener {
    JButtonPlus back;

    public Profile() {
        /////////////////background
        ImageIcon background = new ImageIcon("src/logoAndCloudsImages/background.PNG");
        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setIcon(background);
        backgroundLabel.setBounds(70,40,1420,915);
        ////////////////
        String[] userInfo = UserManager.getUsersInfo(GameManager.username);

        JButtonPlus label = new JButtonPlus(userInfo[0] + "                     Highest Score :" + userInfo[2]);

        JButtonPlus label2 = new JButtonPlus("Characters you own.");
        label2.setBounds(0,400-label.getHeight() - 20,Data.screenWidth,label.getHeight());

        back = new JButtonPlus(this);


        //////////////////////////////////////////////////////////



        JPanel panel = new JPanel();
        panel.setBounds(0,0, Data.screenWidth,Data.screenHeight);
        panel.setBackground(new Color(0x1298D6));
        panel.setOpaque(true);
        panel.setLayout(null);
        panel.add(label);
        panel.add(label2);
        panel.add(back);

        int[] userCharacters = UserManager.getUsersCharacters(GameManager.username);
        int j = 50;

        for(int i = 0; i < userCharacters.length ; i++) {
            if(userCharacters[i] == 1) {
                ImageIcon marioImage = null;
                try {
                    marioImage = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/marioImages/bigMario.png")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                JLabelPlus marioLabel = new JLabelPlus(marioImage,j);
                j+=150;
                panel.add(marioLabel);
            } else if(userCharacters[i] == 2) {
                ImageIcon marioGreenImage = null;
                try {
                    marioGreenImage = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/marioImages/bigMarioGreen.png")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                JLabelPlus marioGreenLabel = new JLabelPlus(marioGreenImage,j);
                j+=150;
                panel.add(marioGreenLabel);
            } else if(userCharacters[i] == 3) {
                ImageIcon ghostImage = null;
                try {
                    ghostImage = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/marioImages/bigGhost.png")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                JLabelPlus ghostLabel = new JLabelPlus(ghostImage,j);
                j+=150;
                panel.add(ghostLabel);
            } else if(userCharacters[i] == 4) {
                ImageIcon snakeImage = null;
                try {
                    snakeImage = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/marioImages/bigSnake.png")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                JLabelPlus snakeLabel = new JLabelPlus(snakeImage,j);
                j+=150;
                panel.add(snakeLabel);
            } else if(userCharacters[i] == 5) {
                ImageIcon rabbitImage = null;
                try {
                    rabbitImage = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/marioImages/bigRabbit.png")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                JLabelPlus rabbitLabel = new JLabelPlus(rabbitImage,650);
                j+=150;
                panel.add(rabbitLabel);
            }
        }


        panel.add(backgroundLabel);


        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setSize(Data.screenWidth,Data.screenHeight);
        this.setTitle("Profile");
        this.add(panel);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == back) {
            new MainPage();
            this.dispose();
        }
    }
}
