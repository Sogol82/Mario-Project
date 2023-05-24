package management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class JButtonPlus extends JButton {
    public JButtonPlus(String text, int y, Color color, ActionListener actionListener) {
        super(text);
        this.setBounds((Data.screenWidth-Data.buttonWidth)/2,y,Data.buttonWidth,Data.buttonHeight);
        this.setFont(new Font("MV Boli",Font.BOLD,30));
        this.setForeground(Color.BLACK);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
        this.setBackground(color);
        this.addActionListener(actionListener);
        this.setFocusable(false);
        this.setVisible(true);
    }

    public JButtonPlus(ActionListener actionListener) {
        super("Back");
        this.setBounds(50,Data.screenHeight-Data.backButtonHeight-100,Data.backButtonWidth,Data.backButtonHeight);
        this.setFont(new Font("MV Boli",Font.BOLD,22));
        this.setForeground(Color.BLACK);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
        this.setBackground(new Color(0xFF6F00));
        this.addActionListener(actionListener);
        this.setFocusable(false);
        this.setVisible(true);
    }

    public JButtonPlus(String text) {
        super(text);
        this.setFont(new Font("MV Boli",Font.BOLD,50));
        this.setForeground(Color.BLACK);
        this.setBounds(0,0,Data.screenWidth,70);
        this.setBackground(null);
        this.setFocusable(false);
        this.setBorder(null);
        this.setVisible(true);
        this.setContentAreaFilled(false);
    }

    public JButtonPlus(String text, int x, ActionListener actionListener) {
        super(text);
        this.setFont(new Font("MV Boli",Font.BOLD,20));
        this.setForeground(Color.BLACK);
        this.setBounds(x,500,100,50);
        this.addActionListener(actionListener);
        this.setBackground(new Color(0xFF6F00));
        this.setFocusable(false);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
        this.setVisible(true);
    }
}
