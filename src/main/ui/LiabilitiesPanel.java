package ui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Finances;
import model.Liability;
import model.UserFinancesList;
import ui.console.LiabilityCommands;

public class LiabilitiesPanel extends JPanel implements ActionListener{
    private JPanel displayPanel;
    private JPanel buttonPanel;
    private JButton backButton;
    private JButton exitButton;
    private JButton addButton;
    private JButton remButton;
    private JLabel responseLabel;
    private GridBagConstraints gbc;
    private JTextField responseField;
    private JFormattedTextField numberField;
    private JList<String> list;
    private String[] data;
    
    private FinanceTracker financeTracker;
    private UserFinancesList balanceSheet;
    private String typeButton;
    private String liabilityName;

    public LiabilitiesPanel(FinanceTracker financeTracker, UserFinancesList balanceSheet){
        this.financeTracker = financeTracker;
        this.balanceSheet = balanceSheet;

        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();

        // Add panels
        displayPanel = new JPanel(new GridBagLayout());
        buttonPanel = new JPanel(new GridBagLayout());

        updateList();

        setUpButtons();

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
        } else if (e.getSource() == responseField | e.getSource() == numberField) {
            preformAction();
        } else if (e.getSource() == exitButton) {
            resetPanel();
        }
        updateList();
    }

    private void resetPanel (){
        gbc = new GridBagConstraints();  // Reset GridBagConstraints
        buttonPanel.removeAll();
        setUpButtons();
        repaint();
        revalidate();
    }

    public void updateList() {
        gbc = new GridBagConstraints();  // Reset GridBagConstraints
        displayPanel.removeAll();
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        displayPanel.add(new JLabel("Liabilities Displayed Here:"), gbc);

        //List
        List<Liability> myList = balanceSheet.getLiabilities();
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

    private void setUpButtons() {
        // add buttons 
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        addButton = new JButton("Add Liability");
        addButton.addActionListener(this);
        buttonPanel.add(addButton, gbc);


        gbc.gridy = 2;
        remButton = new JButton("Remove Liability");
        remButton.addActionListener(this);
        buttonPanel.add(remButton, gbc);


        gbc.gridy = 4;
        backButton = new JButton("Back");
        backButton.addActionListener(this);
        buttonPanel.add(backButton, gbc);
    }

    private void askSpecifications(String typeButton) {
        this.typeButton = typeButton;
        gbc = new GridBagConstraints();  // Reset GridBagConstraints

        buttonPanel.removeAll();

        if (typeButton == "add") {
            createLabel("Insert name of the liability to add");
            createTextField();
            createExitButton();
        } else if (typeButton == "remove") {
            createLabel("Insert liability to remove");
            createTextField();
            createExitButton();
        }

        repaint();
        revalidate();
    }

    private void preformAction(){
        gbc = new GridBagConstraints();  // Reset GridBagConstraints

        if (typeButton == "add") {
            getLiabilityName();
        } else if (typeButton == "remove") {
            removeLiability();
        } else if (typeButton == "value") {
            getLiabilityValue();    
        }
    }

    private void getLiabilityName() {
        liabilityName = responseField.getText();
        buttonPanel.removeAll();
        createLabel("Insert value of the Liability ($-0.00)");
        createNumberField();
        typeButton = "value";
    }

    private void getLiabilityValue() {
        double amount = ((Number) numberField.getValue()).doubleValue();
        balanceSheet.addFinances(new Liability(liabilityName, amount));
        resetPanel();    
    }

    private void removeLiability() {
        String name = responseField.getText();
        Liability currentLiability = findLiability(name);      
        if (currentLiability == null) {
            buttonPanel.removeAll();
            createLabel("\nNo asset matches name provided, try again!");
            createExitButton();

        } else {
            balanceSheet.removeFinances(currentLiability);
            resetPanel();
        }
    }

    private void createExitButton(){
        exitButton = new JButton("Exit");
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 3;
        exitButton.addActionListener(this);
        buttonPanel.add(exitButton, gbc);
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

    private void createNumberField() {
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberField = new JFormattedTextField(numberFormat);
        numberField.setColumns(10);
        numberField.addActionListener(this);
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 2;
        buttonPanel.add(numberField, gbc);
        repaint();
        revalidate();
    }

    // EFFECTS: If balance sheet contains the liability with same name that is passed, return the liability
    //          else return null
    private Liability findLiability(String name) {
        for (Liability liability: balanceSheet.getLiabilities()) {
            if (liability.getName().equals(name)) {
                return liability;
            }
        }   
        return null;
    }

}
