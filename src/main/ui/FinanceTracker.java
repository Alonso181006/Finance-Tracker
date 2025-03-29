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

    // EFFECTS: creates an the main Jframe with a card layout to 
    //          organize the different panels
    public FinanceTracker() {
        super("Finance Tracker App");
        balanceSheet = null;

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

        addWindowListener(new WindowHandler(this));

        //To visualize changes
        repaint();
        revalidate();
    }

    @Override
    // MODIFIES: this
    // EFFECTS: performs different actions based on the source of the
    //          ActionListener
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == assetButton) {
            cardLayout.show(mainPanel, "Assets");
        } else if (e.getSource() == liabilityButton) {
            cardLayout.show(mainPanel, "Liabilities");
        }
    }

    // MODIFIES: this
    // EFFECTS: shows the panel listed within the card layout, as well 
    //          as creates the rest of the panels based on the data passed
    //          from the start screen
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
