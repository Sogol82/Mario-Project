package pages;

        import game.GameFrame;
        import game.GamePanel;
        import management.Data;
        import management.JButtonPlus;
        import management.JLabelPlus;
        import management.UserManager;

        import javax.imageio.ImageIO;
        import javax.swing.*;
        import java.awt.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
        import java.io.IOException;
        import java.util.Objects;

public class ChooseCharacterAndDifficulty extends JFrame implements ActionListener {
    int id = 1;
    JLabelPlus marioLabel;
    JLabelPlus marioGreenLabel;
    JLabelPlus ghostLabel;
    JLabelPlus snakeLabel;
    JLabelPlus rabbitLabel;
    JButton marioButton;
    JButton marioGreenButton;
    JButton ghostButton;
    JButton snakeButton;
    JButton rabbitButton;
    JButtonPlus start;
    JButtonPlus back;

    public ChooseCharacterAndDifficulty() {
        /////////////////background
        ImageIcon background = new ImageIcon("src/main/java/logoAndCloudsImages/background.PNG");
        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setIcon(background);
        backgroundLabel.setBounds(70,40,1420,915);
        ////////////////
        JButtonPlus label = new JButtonPlus("Choose character and difficulty, then start");

        JLabelPlus submit1 = new JLabelPlus("Difficulty:",150, Data.red);

        JLabelPlus submit2 = new JLabelPlus("Character:",400,Data.yellow);


        ///////////////////////////easy
        JLabel easyBorderLabel = new JLabel();
        easyBorderLabel.setBounds(400,150,Data.buttonWidth-150,Data.buttonHeight);
        easyBorderLabel.setBackground(Color.BLACK);
        easyBorderLabel.setOpaque(true);

        JRadioButton easy = new JRadioButton("Easy");
        easy.setBounds(400+3,150+3,Data.buttonWidth-150-6,Data.buttonHeight-6);
        easy.setFont(new Font("MV Boli",Font.BOLD,30));
        easy.setForeground(Color.BLACK);
        easy.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
        easy.setBackground(Data.green);
        easy.addActionListener(this);
        easy.setFocusable(false);
        easy.setVisible(true);
        easy.setSelected(true);

        ///////////////////////////medium
        JLabel mediumBorderLabel = new JLabel();
        mediumBorderLabel.setBounds(400+250,150,Data.buttonWidth-150,Data.buttonHeight);
        mediumBorderLabel.setBackground(Color.BLACK);
        mediumBorderLabel.setOpaque(true);

        JRadioButton medium = new JRadioButton("Medium");
        medium.setBounds(400+250+3,150+3,Data.buttonWidth-150-6,Data.buttonHeight-6);
        medium.setFont(new Font("MV Boli",Font.BOLD,30));
        medium.setForeground(Color.BLACK);
        medium.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
        medium.setBackground(Data.yellow);
        medium.addActionListener(this);
        medium.setFocusable(false);
        medium.setVisible(true);

        ///////////////////////////hard
        JLabel hardBorderLabel = new JLabel();
        hardBorderLabel.setBounds(400+250+250+3,150,Data.buttonWidth-150,Data.buttonHeight);
        hardBorderLabel.setBackground(Color.BLACK);
        hardBorderLabel.setOpaque(true);

        JRadioButton hard = new JRadioButton("Hard");
        hard.setBounds(400+250+250+3+3,150+3,Data.buttonWidth-150-6,Data.buttonHeight-6);
        hard.setFont(new Font("MV Boli",Font.BOLD,30));
        hard.setForeground(Color.BLACK);
        hard.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
        hard.setBackground(Data.red);
        hard.addActionListener(this);
        hard.setFocusable(false);
        hard.setVisible(true);

        ButtonGroup group = new ButtonGroup();
        group.add(easy);
        group.add(medium);
        group.add(hard);


        //////////////////////////////characters
        ImageIcon marioImage = null;
        ImageIcon marioGreenImage = null;
        ImageIcon ghostImage = null;
        ImageIcon snakeImage = null;
        ImageIcon rabbitImage = null;
//        try {
////            marioImage = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("./src/main/java/marioImages/bigMario.png")));
////            marioGreenImage = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("./src/main/java/marioImages/bigMarioGreen.png")));
////            ghostImage = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("src/main/java/marioImages/bigGhost.png")));
////            snakeImage = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("src/main/java/marioImages/bigSnake.png")));
////            rabbitImage = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("src/main/java/marioImages/bigRabbit.png")));
//
//
////            marioImage = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("src/main/resources/marioImages/bigMario.png")));
////            marioImage = new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("resources/marioImages/bigMario.png"))));
////            marioGreenImage = new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("./src/main/java/marioImages/bigMarioGreen.png"))));
////            ghostImage = new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("src/main/java/marioImages/bigGhost.png"))));
////            snakeImage = new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("src/main/java/marioImages/bigSnake.png"))));
////            rabbitImage = new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("src/main/java/marioImages/bigRabbit.png"))));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        marioLabel = new JLabelPlus(marioImage,350);
        marioLabel.setBorder(BorderFactory.createLineBorder(Color.GREEN,3));
        marioGreenLabel = new JLabelPlus(marioGreenImage,500);
        ghostLabel = new JLabelPlus(ghostImage,650);
        snakeLabel = new JLabelPlus(snakeImage,800);
        rabbitLabel = new JLabelPlus(rabbitImage,950);


        //////////////////
        marioButton = new JButton();
        marioButton.setBounds(350,400,100,100);
        marioButton.setBackground(null);
        marioButton.setFocusable(false);
        marioButton.addActionListener(this);
        marioButton.setVisible(true);
        marioButton.setContentAreaFilled(false);

        marioGreenButton = new JButton();
        marioGreenButton.setBounds(500,400,100,100);
        marioGreenButton.setBackground(null);
        marioGreenButton.setFocusable(false);
        marioGreenButton.addActionListener(this);
        marioGreenButton.setVisible(true);
        marioGreenButton.setContentAreaFilled(false);

        ghostButton = new JButton();
        ghostButton.setBounds(650,400,100,100);
        ghostButton.setBackground(null);
        ghostButton.setFocusable(false);
        ghostButton.addActionListener(this);
        ghostButton.setVisible(true);
        ghostButton.setContentAreaFilled(false);

        snakeButton = new JButton();
        snakeButton.setBounds(800,400,100,100);
        snakeButton.setBackground(null);
        snakeButton.setFocusable(false);
        snakeButton.addActionListener(this);
        snakeButton.setVisible(true);
        snakeButton.setContentAreaFilled(false);

        rabbitButton = new JButton();
        rabbitButton.setBounds(950,400,100,100);
        rabbitButton.setBackground(null);
        rabbitButton.setFocusable(false);
        rabbitButton.addActionListener(this);
        rabbitButton.setVisible(true);
        rabbitButton.setContentAreaFilled(false);


        start = new JButtonPlus("Start",550,Data.green,this);

        back = new JButtonPlus(this);
        //////////////////////////////////////////////////////////
        JPanel panel = new JPanel();
        panel.setBounds(0,0, Data.screenWidth,Data.screenHeight);
        panel.setBackground(new Color(0x1298D6));
        panel.setOpaque(true);
        panel.setLayout(null);
        panel.add(label);
        panel.add(submit1);
        panel.add(submit2);
        panel.add(easy);
        panel.add(easyBorderLabel);
        panel.add(medium);
        panel.add(mediumBorderLabel);
        panel.add(hard);
        panel.add(hardBorderLabel);

        panel.add(marioLabel);
        panel.add(marioButton);

        panel.add(marioGreenLabel);
        panel.add(marioGreenButton);
        panel.add(ghostLabel);
        panel.add(ghostButton);
        panel.add(snakeLabel);
        panel.add(snakeButton);
        panel.add(rabbitLabel);
        panel.add(rabbitButton);
        panel.add(start);
        panel.add(back);
        panel.add(backgroundLabel);

        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setSize(Data.screenWidth,Data.screenHeight);
        this.setTitle("Create Account");
        this.add(panel);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == marioButton) {
            if(UserManager.doesUserHaveThisCharacter(1)) {
                id = 1;
                marioLabel.setBorder(BorderFactory.createLineBorder(Color.GREEN,3));
                marioGreenLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
                ghostLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
                snakeLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
                rabbitLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
            } else {
                JOptionPane.showMessageDialog(null,"You do not have this character","Invalid choice",JOptionPane.WARNING_MESSAGE);
            }
        }
        if(e.getSource() == marioGreenButton) {
            if(UserManager.doesUserHaveThisCharacter(2)) {
                id = 2;
                marioLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
                marioGreenLabel.setBorder(BorderFactory.createLineBorder(Color.GREEN,3));
                ghostLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
                snakeLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
                rabbitLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
            } else {
                JOptionPane.showMessageDialog(null,"You do not have this character","Invalid choice",JOptionPane.WARNING_MESSAGE);
            }
        }
        if(e.getSource() == ghostButton) {
            if(UserManager.doesUserHaveThisCharacter(3)) {
                id = 3;
                marioLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
                marioGreenLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
                ghostLabel.setBorder(BorderFactory.createLineBorder(Color.GREEN,3));
                snakeLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
                rabbitLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
            } else {
                JOptionPane.showMessageDialog(null,"You do not have this character","Invalid choice",JOptionPane.WARNING_MESSAGE);
            }
        }
        if(e.getSource() == snakeButton) {
            if(UserManager.doesUserHaveThisCharacter(4)) {
                id = 4;
                marioLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
                marioGreenLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
                ghostLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
                snakeLabel.setBorder(BorderFactory.createLineBorder(Color.GREEN,3));
                rabbitLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
            } else {
                JOptionPane.showMessageDialog(null,"You do not have this character","Invalid choice",JOptionPane.WARNING_MESSAGE);
            }
        }
        if(e.getSource() == rabbitButton) {
            if(UserManager.doesUserHaveThisCharacter(5)) {
                id = 5;
                marioLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
                marioGreenLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
                ghostLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
                snakeLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
                rabbitLabel.setBorder(BorderFactory.createLineBorder(Color.GREEN,3));
            } else {
                JOptionPane.showMessageDialog(null, "You do not have this character", "Invalid choice", JOptionPane.WARNING_MESSAGE);
            }
        }



        if(e.getSource() == start) {
            Data.gameFrame = new GameFrame(new GamePanel(id));
            this.dispose();
        }

        if(e.getSource() == back) {
            new LoadGames();
            this.dispose();
        }
    }
}
