package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EstimationWindow
{
    public void showWindow()
    {
        int width = 500;
        int height = 500;

        JFrame frame = new JFrame("Population in Thailand");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(width, height);
        frame.setResizable(false);

        JPanel topPanel = new JPanel();
        JLabel label = new JLabel("(Heading)");
        topPanel.add(BorderLayout.CENTER, label);

        //Enter Region Panel
        JPanel regionPanel = new JPanel();
        String[] regions = {"Bangkok", "Central", "North-Eastern", "Northern", "Southern"};
        final JComboBox regionList = new JComboBox(regions);
        regionPanel.add(new JLabel("Choose a region: "));
        regionPanel.add(regionList);

        JButton calculateButton = new JButton("Estimate Population");
        JButton resetButton = new JButton("Reset");

        JPanel centerPanel = new JPanel();
        centerPanel.add(regionPanel);
        centerPanel.add(calculateButton);
        centerPanel.add(resetButton);

        final JLabel resultLabel = new JLabel();
        JPanel bottomPanel = new JPanel();
        resultLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
        bottomPanel.add(resultLabel);
        bottomPanel.setBorder(BorderFactory.createTitledBorder("Result"));
        bottomPanel.setPreferredSize(new Dimension(width, 3*height/4));

        frame.getContentPane().add(topPanel, BorderLayout.NORTH);
        frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
        frame.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        frame.setVisible(true);

        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resultLabel.setText("You have chosen region " + regionList.getSelectedItem());
            }
        });

        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resultLabel.setText("");
                regionList.setSelectedIndex(0);
            }
        });
    }
}
