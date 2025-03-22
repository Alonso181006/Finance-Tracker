package ui;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import model.UserFinancesList;

// Finance Tracker Application
public class FinanceTracker extends JFrame implements ActionListener {
    private JButton assetButton;
    private JButton liabilityButton;
    private StartScreen startScreenPanel;
    private JPanel mainPanel;
    private JPanel menuPanel;
    private JPanel assestPanel;
    private JPanel liabilityPanel;
    private CardLayout cardLayout;
    protected UserFinancesList balanceSheet;

    public FinanceTracker() {
        super("Finance Tracker App");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setVisible(true);

        //to not be able to resize
        setResizable(true);

        // main Panel that contains other switchable  
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        //Start Screen
        startScreenPanel = new StartScreen(this);

        mainPanel.add(startScreenPanel, "Start Screen");
        add(mainPanel);


        //To visualize the button
        repaint();
        revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == assetButton) {
            cardLayout.show(mainPanel, "Assets");
        } else if (e.getSource() == liabilityButton) {
            cardLayout.show(mainPanel, "Liabilities");
        }
    }

    public void show(String panel) {
        balanceSheet = startScreenPanel.getBalanceSheet();
        String jsonStore = startScreenPanel.getJsonStore();
        menuPanel = new MenuPanel(this, balanceSheet, jsonStore);
        mainPanel.add(menuPanel, "Menu");

        cardLayout.show(mainPanel, panel);
        // Panel for Assets
        assestPanel = new AssetPanel(this, balanceSheet);

        // Panel for Liabilities
        liabilityPanel = new LiabilitiesPanel(this, balanceSheet);

        mainPanel.add(assestPanel, "Assets");
        mainPanel.add(liabilityPanel, "Liabilities");
    }

}
