package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow {
    public static void main(String args[]) {
        JFrame frame = new JFrame("Home");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 200);
        frame.setResizable(false);

        JPanel topPanel = new JPanel();
        JLabel label = new JLabel("Main Screen");
        topPanel.add(BorderLayout.CENTER, label);

        JPanel centerPanel = new JPanel();
        JButton getPopButton = new JButton("Population in Thailand");
        JButton estimatePopButton = new JButton("Estimation of Population");
        centerPanel.add(getPopButton);
        centerPanel.add(estimatePopButton);

        frame.getContentPane().add(topPanel, BorderLayout.NORTH);
        frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
        frame.setVisible(true);

        final CurrentPopulationWindow currentPopulationWindow = new CurrentPopulationWindow();
        final EstimationWindow estimationWindow = new EstimationWindow();

        getPopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentPopulationWindow.showWindow();
            }
        });

        estimatePopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                estimationWindow.showWindow();
            }
        });
    }
}