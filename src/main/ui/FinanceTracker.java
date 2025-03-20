package ui;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import model.Finances;

public class FinanceTracker extends JFrame implements ActionListener {
    private JButton assetButton, liabilityButton;
    private JPanel startScreenPanel, mainPanel, menuPanel, assestPanel, liabilityPanel;
    private CardLayout cardLayout;
    private JList<String> list;
    protected BalanceSheet balanceSheet;
    private String[] data;


    public FinanceTracker() {
        super("Finance Tracker App");

        balanceSheet = new BalanceSheet(null);

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

        List<String> names = new ArrayList<>();
        names.add("hi");
        data = names.toArray(new String[0]);

        list = new JList<>(data);
        list.setBounds(100, 100, 200, 1000);
        menuPanel.add(list);


        mainPanel.add(startScreenPanel, "Start Screen");
        mainPanel.add(menuPanel, "Menu");
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
        }
    }

    public void show(String panel) {
        cardLayout.show(mainPanel, panel);
        updateList();
        // Panel for Assets
        assestPanel = new AssetPanel(this, balanceSheet.getList());

        // Panel for Liabilities
        liabilityPanel = new LiabilitiesPanel(this, balanceSheet.getList());

        mainPanel.add(assestPanel, "Assets");
        mainPanel.add(liabilityPanel, "Liabilities");
    }

    public void updateList() {
        menuPanel.remove(list);
        //List
        List<Finances> myList = balanceSheet.getList().getFinances();
        List<String> names = new ArrayList<>();
        for (Finances f: myList) {
            String input = String.format("%s - $%.2f", f.getName(), f.getValue());
            names.add(input);
        }
        data = names.toArray(new String[0]);
        list = new JList<>(data);

        menuPanel.add(list);
    }

}
