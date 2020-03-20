package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CurrentPopulationWindow {
    public void showScreen()
    {
        JFrame frame = new JFrame("Population in Thailand");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 200);
        frame.setResizable(false);

        JPanel topPanel = new JPanel();
        JLabel label = new JLabel("(Heading)");
        topPanel.add(BorderLayout.CENTER, label);

        //Enter Year Panel
        JPanel yearPanel = new JPanel();
        String[] years = {"2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016",
                "2017", "2018", "2019", "2020"};
        final JComboBox yearList = new JComboBox(years);
        yearPanel.add(new JLabel("Choose a year: "));
        yearPanel.add(yearList);

        //Enter Region Panel
        JPanel regionPanel = new JPanel();
        String[] regions = {"Bangkok", "Central", "North-Eastern", "Northern", "Southern"};
        final JComboBox regionList = new JComboBox(regions);
        regionPanel.add(new JLabel("Choose a region: "));
        regionPanel.add(regionList);

        JButton calculateButton = new JButton("Get Population");
        JButton resetButton = new JButton("Reset");

        JPanel centerPanel = new JPanel();
        centerPanel.add(BorderLayout.NORTH, yearPanel);
        centerPanel.add(regionPanel);
        centerPanel.add(calculateButton);
        centerPanel.add(resetButton);

        final JLabel resultLabel = new JLabel();
        JPanel bottomPanel = new JPanel();
        resultLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
        bottomPanel.add(resultLabel);
        bottomPanel.setBorder(BorderFactory.createTitledBorder("Result"));
        bottomPanel.setPreferredSize(new Dimension(500, 75));

        frame.getContentPane().add(topPanel, BorderLayout.NORTH);
        frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
        frame.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        frame.setVisible(true);

        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resultLabel.setText("You have chosen year " + yearList.getSelectedItem() + " and region " +
                regionList.getSelectedItem());
            }
        });

        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resultLabel.setText("");
                yearList.setSelectedIndex(0);
                regionList.setSelectedIndex(0);
            }
        });
    }
}
