package GUI;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class MainWindow {

    public static void main(String args[]) {
        int width = 500;
        int height = 500;
        final queries queries = new queries();

        JFrame frame = new JFrame("Home");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setResizable(false);

        JPanel centerPanel = new JPanel();

        JPanel topPanel = new JPanel();
        TitledBorder topTitle = BorderFactory.createTitledBorder("Population in Thailand");
        topTitle.setTitleJustification(TitledBorder.CENTER);
        topPanel.setBorder(topTitle);
        topPanel.setPreferredSize(new Dimension(width-25, height/2));
//
        //Enter Year Panel
        JPanel yearPanel = new JPanel();
        String[] years = {"2013", "2014", "2015", "2016",
                "2017", "2018", "2019", "2020"};
        final JComboBox yearList = new JComboBox(years);
        yearPanel.add(new JLabel("Choose a year: "));
        yearPanel.add(yearList);
//
//        //Enter Region Panel
        JPanel regionPanel = new JPanel();
        String[] regions = {"Bangkok", "Central ", "North-eastern", "Northern", "Southern"};
        final JComboBox regionList = new JComboBox(regions);
        regionPanel.add(new JLabel("Choose a region: "));
        regionPanel.add(regionList);

        JButton calculateButton = new JButton("Get Population");
        JButton resetButton = new JButton("Reset");

        final JLabel resultLabel = new JLabel();

        JPanel resultPanelTop = new JPanel();
        resultPanelTop.setBorder(BorderFactory.createTitledBorder("Result"));
        resultPanelTop.setPreferredSize(new Dimension(width-50, (height/2)/2));
        resultPanelTop.add(resultLabel);

        topPanel.add(yearPanel);
        topPanel.add(regionPanel);
        topPanel.add(calculateButton);
        topPanel.add(resetButton);
        topPanel.add(resultPanelTop);

        JPanel bottomPanel = new JPanel();
        TitledBorder bottomTitle = BorderFactory.createTitledBorder("Population Estimation");
        bottomTitle.setTitleJustification(TitledBorder.CENTER);
        bottomPanel.setBorder(bottomTitle);
        bottomPanel.setPreferredSize(new Dimension(width-25, height/4));

        JButton estimatePopButton = new JButton("Estimate Population");
        final JLabel resultLabelBottom = new JLabel();
        bottomPanel.add(estimatePopButton);
        bottomPanel.add(resultLabelBottom);

        centerPanel.add(topPanel);
        centerPanel.add(bottomPanel);
        frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
//        frame.getContentPane().add(topPanel, BorderLayout.NORTH);
//        frame.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        frame.setVisible(true);

        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                queries.setRegion(regionList.getSelectedItem().toString()); //store value into variable region
                queries.setYear(yearList.getSelectedItem().toString()); //store value into variable year
                List<String> results = queries.getPopulationRegion();
                resultLabel.setText("The year " + yearList.getSelectedItem() + " at region " +
                        regionList.getSelectedItem() + " has a population " + results.get(0));
            }
        });

        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resultLabel.setText("");
                yearList.setSelectedIndex(0);
                regionList.setSelectedIndex(0);
            }
        });

        estimatePopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double[] results = queries.getEstPop();
                NumberFormat formatter = new DecimalFormat("#0.00");
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("<html><body>The estimated population for the following age range is:<br>");
                stringBuilder.append("0-7: " + formatter.format(results[0]) + "<br>");
                stringBuilder.append("8-14: " + formatter.format(results[1]) + "<br>");
                stringBuilder.append("15-21: " + formatter.format(results[2]) + "<br>");
                stringBuilder.append("22-28: " + formatter.format(results[3]) + "<br>");
                stringBuilder.append("29-35: " + formatter.format(results[4]) + "<br>");
                stringBuilder.append("40 and above: " + formatter.format(results[5]) + "</body></html>");

                resultLabelBottom.setText(stringBuilder.toString());
            }
        });
    }
}