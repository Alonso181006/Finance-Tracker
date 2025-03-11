package ui;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class FinanceTracker extends JFrame implements ActionListener{
    private JButton assetButton, liabilityButton, backButton1, backButton2;
    private JPanel mainPanel, menuPanel, assestPanel, liabilityPanel;
    private CardLayout cardLayout;


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

        // Create other Menu
        menuPanel = new JPanel();

        // adding component 
        assetButton = new JButton("Liabilities");
        assetButton.addActionListener(this);
        menuPanel.add(assetButton);

        liabilityButton = new JButton("Assets");
        liabilityButton.addActionListener(this);
        menuPanel.add(liabilityButton);


        // Panel for Assets
        assestPanel = new JPanel();
        assestPanel.add(new JLabel("Add Liability Options Here"));
        backButton1 = new JButton("Back");
        backButton1.addActionListener(this);
        assestPanel.add(backButton1);

        // Panel for Liabilities
        liabilityPanel = new JPanel();
        liabilityPanel.add(new JLabel("Remove Asset Options Here"));
        backButton2 = new JButton("Back");
        backButton2.addActionListener(this);
        liabilityPanel.add(backButton2);

        mainPanel.add(menuPanel, "Menu");
        mainPanel.add(assestPanel, "Assets");
        mainPanel.add(liabilityPanel, "Liabilities");
        add(mainPanel);

        //To visualize the button
        repaint();
        revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == assetButton) {
            cardLayout.show(mainPanel, "Assets");
        } else if (e.getSource() == liabilityButton) {
            cardLayout.show(mainPanel, "Liabilities");
        } else if (e.getSource() == backButton1 | e.getSource() == backButton2) {
            cardLayout.show(mainPanel, "Menu");
        }

    }

}
