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

import model.Asset;
import model.Finances;
import model.UserFinancesList;

// JPanel to edit the Assets of the user
public class AssetPanel extends JPanel implements ActionListener {
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
    private String assetName;

    // REQUIRES: financeTracker & balanceSheet to not be null
    // EFFECTS: The reference to the main Finance Tracker is set to financeTracker, and the balance 
    //          sheet of user is set to balanceSheet and the panel is initialized
    public AssetPanel(FinanceTracker financeTracker, UserFinancesList balanceSheet) {
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
        gbc = new GridBagConstraints();  // Reset GridBagConstraints
        buttonPanel.removeAll();
        setUpButtons();
        repaint();
        revalidate();
    }

    // MODIFIES: this
    // EFFECTS: updates the display panel
    public void updateDisplay() {
        gbc = new GridBagConstraints();  // Reset GridBagConstraints
        displayPanel.removeAll();
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        displayPanel.add(new JLabel("Assets Displayed Here:"), gbc);

        updateList();
    }

    // MODIFIES: this
    // EFFECTS: updates the list based on any additions or removals of assets
    private void updateList() {
        //List
        List<Asset> myList = balanceSheet.getAssets();
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
    }

    // MODIFIES: this
    // EFFECTS: prompt the user with a question to answer in the text field provided
    private void askSpecifications(String typeButton) {
        this.typeButton = typeButton;
        gbc = new GridBagConstraints();  // Reset GridBagConstraints

        buttonPanel.removeAll();

        if (typeButton == "add") {
            createLabel("Insert name of the asset to add");
            createTextField();
            createExitButton();
        } else if (typeButton == "remove") {
            createLabel("Insert asset to remove");
            createTextField();
            createExitButton();
        }
    }

    // MODIFIES: this
    // EFFECTS: based on value of typeButton utilize the text field for different 
    //          usage cases
    private void preformAction() {
        gbc = new GridBagConstraints();  // Reset GridBagConstraints

        if (typeButton == "add") {
            getAssetName();
        } else if (typeButton == "remove") {
            removeAsset();
        } else if (typeButton == "value") {
            getAssetValue();    
        }
    }

    // MODIFIES: this 
    // EFFECTS: gets asset name from text field and prompts question for 
    //          value of asses
    private void getAssetName() {
        assetName = responseField.getText();
        buttonPanel.removeAll();
        createLabel("Insert value of the asset ($0.00)");
        createNumberField();
        typeButton = "value";
    }

    // MODIFIES: this 
    // EFFECTS: gets asset value from number field and adds asset to balance sheet
    private void getAssetValue() {
        double amount = ((Number) numberField.getValue()).doubleValue();
        balanceSheet.addFinances(new Asset(assetName, amount));
        resetPanel();    
    }

    // MODIFIES: this 
    // EFFECTS: removes the asset from the balance sheet
    private void removeAsset() {
        String name = responseField.getText();
        Asset currentAsset = findAsset(name);      
        if (currentAsset == null) {
            buttonPanel.removeAll();
            createLabel("\nNo asset matches name provided, try again!");
            createExitButton();

        } else {
            balanceSheet.removeFinances(currentAsset);
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

    // EFFECTS: If balance sheet contains the asset with same name that is passed, return the asset
    //          else return null
    private Asset findAsset(String name) {
        for (Asset asset: balanceSheet.getAssets()) {
            if (asset.getName().equals(name)) {
                return asset;
            }
        }   
        return null;
    }

}
