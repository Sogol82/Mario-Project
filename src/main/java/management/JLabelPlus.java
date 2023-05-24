package management;

import javax.swing.*;
import java.awt.*;

public class JLabelPlus extends JLabel {
    public JLabelPlus(String text, int y, Color color) {
        super(text);
        this.setBounds(50,y,250,80);
        this.setFont(new Font("MV Boli",Font.BOLD,30));
        this.setForeground(Color.BLACK);
        this.setBackground(color);
        this.setOpaque(true);
        this.setVerticalTextPosition(JLabel.CENTER);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
    }

    public JLabelPlus(ImageIcon icon, int x) {
        super(icon);
        this.setBounds(x,400,100,100);
        this.setBackground(null);
        this.setOpaque(true);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
    }
}
