package ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import model.Asset;
import model.Finances;
import model.UserFinancesList;

public class MenuPanel extends JPanel implements ActionListener {
    private JButton assetButton;
    private JButton liabilityButton;
    private JPanel displayPanel;
    private JPanel buttonPanel;
    private GridBagConstraints gbc;

    private FinanceTracker financeTracker;
    private BalanceSheet balanceSheet;
    private String[] data;
    private JList<String> list;


    public MenuPanel(FinanceTracker financeTracker, BalanceSheet balanceSheet) {
        this.financeTracker = financeTracker;
        this.balanceSheet = balanceSheet;

        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();

        // Add panels
        displayPanel = new JPanel(new GridBagLayout());
        buttonPanel = new JPanel(new GridBagLayout());

        // adding component 
        assetButton = new JButton("Assets");
        assetButton.addActionListener(this);
        gbc.gridy = 0;
        buttonPanel.add(assetButton, gbc);

        liabilityButton = new JButton("Liabilities");
        liabilityButton.addActionListener(this);
        gbc.gridy = 1;
        buttonPanel.add(liabilityButton, gbc);


        List<String> names = new ArrayList<>();
        data = names.toArray(new String[0]);

        updateList();

        add(buttonPanel);
        add(displayPanel);

        //To visualize the button
        repaint();
        revalidate();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == assetButton) {
            financeTracker.show("Assets");
        } else if (e.getSource() == liabilityButton) {
            financeTracker.show("Liabilities");
        }
    }

    public void updateList() {
        gbc = new GridBagConstraints();  // Reset GridBagConstraints
        displayPanel.removeAll();
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        displayPanel.add(new JLabel("Displayed Finances Here:"), gbc);

        //List
        List<Finances> myList = balanceSheet.getList().getFinances();
        List<String> names = new ArrayList<>();
        for (Finances f: myList) {
            String input = String.format("%s  $%.2f", f.getName(), f.getValue());
            names.add(input);
        }
        data = names.toArray(new String[0]);
        list = new JList<>(data);
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        displayPanel.add(list, gbc);
    }

}
