package ui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.UserFinancesList;

public class AssetPanel extends JPanel implements ActionListener{
    JPanel displayPanel;
    JPanel buttonPanel;
    JButton backButton;
    JButton addButton;
    JButton remButton;
    JLabel responseLabel;
    GridBagConstraints gbc;
    JTextField responseField;
    
    FinanceTracker financeTracker;
    UserFinancesList balanceSheet;

    public AssetPanel(FinanceTracker financeTracker, UserFinancesList balanceSheet){
        this.financeTracker = financeTracker;
        this.balanceSheet = balanceSheet;

        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();

        // Add panels
        displayPanel = new JPanel();
        buttonPanel = new JPanel(new GridBagLayout());

        displayPanel.add(new JLabel("Assets Displayed Here"));

        // add buttons 
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        addButton = new JButton("Add Asset");
        addButton.addActionListener(this);
        buttonPanel.add(addButton, gbc);


        gbc.gridy = 2;
        remButton = new JButton("Remove Asset");
        remButton.addActionListener(this);
        buttonPanel.add(remButton, gbc);


        gbc.gridy = 4;
        backButton = new JButton("Back");
        backButton.addActionListener(this);
        buttonPanel.add(backButton, gbc);

        // ButtonPanel
        gbc.gridwidth = 1;
        gbc.gridheight = 5;
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(buttonPanel, gbc);
        
        // ResponsePanel
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(displayPanel, gbc);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backButton) {
            financeTracker.show("Menu");
        } else if (e.getSource() == addButton) {
            askSpecifications("add");
        } else if (e.getSource() == remButton) {
            askSpecifications("remove");
        }
    }

    private void askSpecifications(String typeButton) {
        gbc = new GridBagConstraints();  // Reset GridBagConstraints

        buttonPanel.removeAll();

        if (typeButton == "add") {
            createLabel("Insert name of the asset to add");
            createTextField();
        } else if (typeButton == "remove") {
            createLabel("Insert asset to remove");
            createTextField();
        }

        repaint();
        revalidate();
    }

    private void createTextField() {
        //create textfield
        responseField = new JTextField(10);
        responseField.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        responseField.addActionListener(this);
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 2;
        buttonPanel.add(responseField, gbc);
        repaint();
        revalidate();
    }

    private void createLabel(String response) {
        //create textfield
        responseLabel = new JLabel(response);
        responseLabel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        buttonPanel.add(responseLabel, gbc);
        repaint();
        revalidate();
    }

}
