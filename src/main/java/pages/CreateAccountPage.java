package pages;

import management.Data;
import management.JButtonPlus;
import management.JLabelPlus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateAccountPage extends JFrame implements ActionListener {
    JTextField textField1;
    JTextField textField2;
    JButtonPlus save;
    JButtonPlus back;

    public CreateAccountPage() {
        /////////////////background
        ImageIcon background = new ImageIcon("src/logoAndCloudsImages/background.PNG");
        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setIcon(background);
        backgroundLabel.setBounds(70,40,1420,915);
        ////////////////
        JButtonPlus label = new JButtonPlus("Enter username and password, then save");

        JLabelPlus submit1 = new JLabelPlus("Username:",150,Data.red);

        JLabelPlus submit2 = new JLabelPlus("Password:",400,Data.yellow);

        textField1 = new JTextField();
        textField1.setBounds(submit1.getX()+submit1.getWidth()+100,submit1.getY()
                ,Data.screenWidth-submit1.getWidth()-300,submit1.getHeight());
        textField1.setFont(new Font("MV Boli",Font.BOLD,30));
        textField1.setForeground(Color.BLACK);
        textField1.setBackground(Color.PINK);
        textField1.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));

        textField2 = new JTextField();
        textField2.setBounds(submit2.getX()+submit2.getWidth()+100,submit2.getY()
                ,Data.screenWidth-submit2.getWidth()-300,submit2.getHeight());
        textField2.setFont(new Font("MV Boli",Font.BOLD,30));
        textField2.setForeground(Color.BLACK);
        textField2.setBackground(Color.PINK);
        textField2.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));

        save = new JButtonPlus("Save",550,Data.green,this);

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
        panel.add(textField1);
        panel.add(textField2);
        panel.add(save);
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
        if(e.getSource() == save) {
            String username = textField1.getText();
            String password = textField2.getText();
            if(!username.equals("") && !password.equals("")) {
                if(!management.UserManager.doesUserExist(username)) {
                    management.UserManager.addUser(username,password);
                    JOptionPane.showMessageDialog(null,"Your account was created successfully.","Save",JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "This username already exists."
                            ,"Invalid Input", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please fill both textFields."
                        ,"Invalid Input", JOptionPane.WARNING_MESSAGE);
            }

        }

        if(e.getSource() == back) {
            new EnterPage();
            this.dispose();
        }
    }
}
