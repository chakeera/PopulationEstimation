package GUI;

import javax.swing.*;
import java.awt.*;

public class MainWindow {
    public static void main(String args[]) {
        JFrame frame = new JFrame("Home");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 200);

        JPanel topPanel = new JPanel();
        JLabel label = new JLabel("Main Screen");
        topPanel.add(BorderLayout.CENTER, label);

        JPanel centerPanel = new JPanel();
        JButton button1 = new JButton("Get Year Population");
        JButton button2 = new JButton("Estimate Region Population");
        centerPanel.add(button1);
        centerPanel.add(button2);

        frame.getContentPane().add(BorderLayout.NORTH, topPanel);
        frame.getContentPane().add(BorderLayout.CENTER, centerPanel);
        frame.setVisible(true);
    }
}