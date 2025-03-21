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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Asset;
import model.Finances;
import model.UserFinancesList;

public class AssetPanel extends JPanel implements ActionListener{
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
    private JList list;
    private String[] data;
    
    private FinanceTracker financeTracker;
    private UserFinancesList balanceSheet;
    private String typeButton;
    private String assetName;

    public AssetPanel(FinanceTracker financeTracker, UserFinancesList balanceSheet){
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
        displayPanel.add(new JLabel("Assets Displayed Here:"), gbc);

        //List
        List<Asset> myList = balanceSheet.getAssets();
        List<String> names = new ArrayList<>();
        for (Finances f: myList) {
            String input = String.format("%s - $%.2f", f.getName(), f.getValue());
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

        repaint();
        revalidate();
    }

    private void preformAction(){
        gbc = new GridBagConstraints();  // Reset GridBagConstraints

        if (typeButton == "add") {
            getAssetName();
        } else if (typeButton == "remove") {
            removeAsset();
        } else if (typeButton == "value") {
            getAssetValue();    
        }
    }

    private void getAssetName() {
        assetName = responseField.getText();
        buttonPanel.removeAll();
        createLabel("Insert value of the asset ($0.00)");
        createNumberField();
        typeButton = "value";
    }

    private void getAssetValue() {
        double amount = ((Number) numberField.getValue()).doubleValue();
        balanceSheet.addFinances(new Asset(assetName, amount));
        resetPanel();    
    }

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
