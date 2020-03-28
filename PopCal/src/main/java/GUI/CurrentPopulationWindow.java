package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CurrentPopulationWindow {
    queries queries = new queries();

    public void showWindow()
    {
        int width = 500;
        int height = 500;

        JFrame frame = new JFrame("Population in Thailand");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(width, height);
        frame.setResizable(false);

        JPanel topPanel = new JPanel();
        JLabel label = new JLabel("Population in Thailand");
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
        centerPanel.add(yearPanel);
        centerPanel.add(regionPanel);
        centerPanel.add(calculateButton);
        centerPanel.add(resetButton);

        final JTextArea resultLabel = new JTextArea();
        resultLabel.setSize(new Dimension(width-50, 3*height/4 - 50));
        resultLabel.setLineWrap(true);
        resultLabel.setWrapStyleWord(true);
        resultLabel.setBackground(UIManager.getColor("Label.background"));
        resultLabel.setFont(UIManager.getFont("Label.font"));
        resultLabel.setBorder(UIManager.getBorder("Label.border"));

        JPanel bottomPanel = new JPanel();
        bottomPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
        bottomPanel.add(resultLabel);
        bottomPanel.setBorder(BorderFactory.createTitledBorder("Result"));
        bottomPanel.setPreferredSize(new Dimension(width, 3*height/4));

        frame.getContentPane().add(topPanel, BorderLayout.NORTH);
        frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
        frame.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        frame.setVisible(true);

        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                queries.setRegion(regionList.getSelectedItem().toString()); //store value into variable region
                queries.setYear(yearList.getSelectedItem().toString()); //store value into variable year
                List<Double> results = queries.getPopulationRegion(); // get List<String> results from database
//                StringBuilder stringBuilder = new StringBuilder(); //build String for print
//                for (String res: results){
//                    stringBuilder.append(res);
//                    stringBuilder.append(" ");
//                }
                resultLabel.setText("The year " + yearList.getSelectedItem() + " at region " +
                regionList.getSelectedItem() + "has a population " + results.get(0).toString());
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
