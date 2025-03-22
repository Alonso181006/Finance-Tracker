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

// JPanel to edit the Liabilities of the user
public class LiabilitiesPanel extends JPanel implements ActionListener {
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

    // REQUIRES: financeTracker & balanceSheet to not be null
    // EFFECTS: The reference to the main Finance Tracker is set to financeTracker, and the balance 
    //          sheet of user is set to balanceSheet and the panel is initialized
    public LiabilitiesPanel(FinanceTracker financeTracker, UserFinancesList balanceSheet) {
        this.financeTracker = financeTracker;
        this.balanceSheet = balanceSheet;

        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();

        initPanels();
        updateDisplay();
        setUpButtons();
    }

    // MODIFIES: this
    // EFFECTS: initialize the panels in this
    private void initPanels() {
        // Add panels
        displayPanel = new JPanel(new GridBagLayout());
        buttonPanel = new JPanel(new GridBagLayout());

        // ButtonPanel
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(buttonPanel, gbc);
        
        // ResponsePanel
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(displayPanel, gbc);
    }

    @Override
    // MODIFIES: this
    // EFFECTS: performs different actions based on the source of the
    //          ActionListener
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
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
        updateDisplay();
    }

    // MODIFIES: this
    // EFFECTS: reset the panel to original state after performing an action
    private void resetPanel() {
        gbc = new GridBagConstraints();
        buttonPanel.removeAll();
        setUpButtons();
        repaint();
        revalidate();
    }

    // MODIFIES: this
    // EFFECTS: updates the display panel
    public void updateDisplay() {
        gbc = new GridBagConstraints();
        displayPanel.removeAll();
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        displayPanel.add(new JLabel("Liabilities Displayed Here:"), gbc);

        updateList();
    }

    // MODIFIES: this
    // EFFECTS: updates the list based on any additions or removals of liabilities
    private void updateList() {
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

    // MODIFIES: this
    // EFFECTS: initialize the buttons for the buttons panel
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

    // MODIFIES: this
    // EFFECTS: prompt the user with a question to answer in the text field provided
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
    }

    // MODIFIES: this
    // EFFECTS: based on value of typeButton utilize the text field for different 
    //          usage cases
    private void preformAction() {
        gbc = new GridBagConstraints(); 
        if (typeButton == "add") {
            getLiabilityName();
        } else if (typeButton == "remove") {
            removeLiability();
        } else if (typeButton == "value") {
            getLiabilityValue();    
        }
    }

    // MODIFIES: this 
    // EFFECTS: gets liability name from text field and prompts question for 
    //          value of liability
    private void getLiabilityName() {
        liabilityName = responseField.getText();
        buttonPanel.removeAll();
        createLabel("Insert value of the Liability ($-0.00)");
        createNumberField();
        typeButton = "value";
    }
    
    // MODIFIES: this 
    // EFFECTS: gets liability value from number field and adds liability to balance sheet
    private void getLiabilityValue() {
        double amount = ((Number) numberField.getValue()).doubleValue();
        balanceSheet.addFinances(new Liability(liabilityName, amount));
        resetPanel();    
    }

    // MODIFIES: this 
    // EFFECTS: removes the liability from the balance sheet
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

    // MODIFIES: this
    // EFFECTS: creates a new exit JButton for button panel with grid layout specifications
    private void createExitButton() {
        exitButton = new JButton("Exit");
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 3;
        exitButton.addActionListener(this);
        buttonPanel.add(exitButton, gbc);
    }

    // MODIFIES: this
    // EFFECTS: creates a new JTextfield for button panel with grid layout specifications
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

    // MODIFIES: this
    // EFFECTS: creates a new JLabel for button panel with grid layout specifications
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

    // MODIFIES: this
    // EFFECTS: creates a new NumberField for buttons panel with grid layout specifications
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
