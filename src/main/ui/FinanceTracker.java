package ui;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import model.UserFinancesList;

public class FinanceTracker extends JFrame implements ActionListener {
    private JButton assetButton, liabilityButton, backButton1, backButton2;
    private JPanel startScreenPanel, mainPanel, menuPanel, assestPanel, liabilityPanel;
    private CardLayout cardLayout;

    private UserFinancesList balanceSheet;


    public FinanceTracker() {
        super("Finance Tracker App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setVisible(true);

        //to not be able to resize
        setResizable(true);

        // main Panel that contains other switchable  
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        //Start Screen
        startScreenPanel = new StartScreen(this, balanceSheet);

        // Create other Menu
        menuPanel = new JPanel();

        // adding component 
        assetButton = new JButton("Assets");
        assetButton.addActionListener(this);
        menuPanel.add(assetButton);

        liabilityButton = new JButton("Liabilities");
        liabilityButton.addActionListener(this);
        menuPanel.add(liabilityButton);


        // Panel for Assets
        assestPanel = new AssetPanel(this, balanceSheet);

        // Panel for Liabilities
        liabilityPanel = new LiabilitiesPanel(this, balanceSheet);

        mainPanel.add(startScreenPanel, "Start Screen");
        mainPanel.add(menuPanel, "Menu");
        mainPanel.add(assestPanel, "Assets");
        mainPanel.add(liabilityPanel, "Liabilities");
        add(mainPanel);


        //To visualize the button
        repaint();
        revalidate();

        balanceSheet = null;


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == assetButton) {
            cardLayout.show(mainPanel, "Assets");
        } else if (e.getSource() == liabilityButton) {
            cardLayout.show(mainPanel, "Liabilities");
        } else if (e.getSource() == backButton1) {
            cardLayout.show(mainPanel, "Menu");
        }
    }

    public void show(String panel) {
        cardLayout.show(mainPanel, panel);
    }

}
