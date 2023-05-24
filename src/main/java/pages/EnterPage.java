package pages;

import management.Data;
import management.JButtonPlus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EnterPage extends JFrame implements ActionListener {
    JButtonPlus createAccount;
    JButtonPlus login;
    JButtonPlus exit;

    public EnterPage() {
        /////////////////background
        ImageIcon background = new ImageIcon("src/main/java/logoAndCloudsImages/background.PNG");
        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setIcon(background);
        backgroundLabel.setBounds(70,40,1420,915);
        ////////////////
        ImageIcon icon = new ImageIcon("src/main/java/logoAndCloudsImages/super-mario-logo.png");
        JLabel label1 = new JLabel();
        label1.setIcon(icon);
        label1.setFont(new Font("MV Boli",Font.BOLD,30));
        label1.setBounds((Data.screenWidth-300)/2,40,300,140);

        createAccount = new JButtonPlus("Create Account",250,Data.red,this);

        login = new JButtonPlus("Login",360,Data.yellow,this);

        exit = new JButtonPlus("Exit Game",470,Data.green,this);



        JPanel panel = new JPanel();
        panel.setBounds(0,0, Data.screenWidth,Data.screenHeight);
        panel.setBackground(new Color(0x1298D6));
        panel.setOpaque(true);
        panel.setLayout(null);
        panel.add(createAccount);
        panel.add(login);
        panel.add(exit);
        panel.add(label1);
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
        if(e.getSource()==exit) {
            System.exit(0);
        }
        if(e.getSource()==login) {
            new LoginPage();
            this.dispose();
        }
        if(e.getSource()==createAccount) {
            new CreateAccountPage();
            this.dispose();
        }
    }
}
