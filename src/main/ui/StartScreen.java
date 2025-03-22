package ui;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;


import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.UserFinancesList;
import persistence.JsonReader;

// Represents an JPanel for the Start Screen
public class StartScreen extends JPanel implements ActionListener {
    private JButton signInButton;
    private JButton logInButton;
    private JPanel buttonPanel;
    private JPanel responsePanel;
    private JTextField responseField;
    private FinanceTracker financeTracker;
    private GridBagConstraints gbc;
    private JLabel responseLabel;
    private UserFinancesList balanceSheet;
    private JsonReader jsonReader;
    private String jsonStore;
    private String button;

    // REQUIRES: financeTracker to not be null
    // EFFECTS: Constructs the start screen  panel with a reference to the main 
    //          Finance Tracker application
    public StartScreen(FinanceTracker financeTracker) {
        // init 
        this.financeTracker = financeTracker;
        button = "none";
        responseLabel = null;

        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();

        initComponents();
        initGridLayout();
    } 

    // MODIFIES: this
    // EFFECTS:  initializes all the buttons and sub panels
    private void initComponents() {
        // Panels
        buttonPanel = new JPanel(new FlowLayout());
        responsePanel = new JPanel(new FlowLayout());

        // Buttons
        signInButton = new JButton("Sign in");
        logInButton = new JButton("Log in");
        signInButton.addActionListener(this);
        logInButton.addActionListener(this);

        buttonPanel.add(signInButton);
        buttonPanel.add(logInButton);
    }

    // MODIFIES: this
    // EFFECTS:  initializes the grid layout between the two sub panels
    private void initGridLayout() {
        // ButtonPanel
        gbc.gridwidth = 4;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(buttonPanel, gbc);
        
        // ResponsePanel
        gbc.gridy = 1;
        add(responsePanel, gbc);
    }

    @Override
    // MODIFIES: this
    // EFFECTS:  performs different actions based on the source of the
    //           ActionListener
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signInButton) {
            responsePanel.removeAll();
            createTextField();
            button = "signIn";
        } else if (e.getSource() == logInButton) {
            responsePanel.removeAll();
            createTextField();
            button = "logIn";
        } else if (e.getSource() == responseField) {
            if (button == "signIn") {
                findUserInfo();
            } else if (button == "logIn") {
                createUserInfo();
            }
        }
    }

    // MODIFIES: this
    // EFFECTS:  creates a new JTextfield for reponse panel above any JLabel
    private void createTextField() {
        responseField = new JTextField(10);
        responseField.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        responseField.addActionListener(this);
        gbc.gridwidth = 4;
        gbc.gridx = 0;
        gbc.gridy = 1;
        responsePanel.add(responseField, gbc);
        repaint();
        revalidate();
    }

    // MODIFIES: this
    // EFFECTS: creates a new JLabel based on the response for reponse panel
    //          below any JTextField
    private void createLabel(String response) {
        responseLabel = new JLabel(response);
        responseLabel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        gbc.gridwidth = 4;
        gbc.gridx = 0;
        gbc.gridy = 2;
        responsePanel.add(responseLabel, gbc);
        repaint();
        revalidate();
    }

    // MODIFIES: this
    // EFFECTS: finds the user within the saved json files
    private void findUserInfo() {
        String userInput = responseField.getText();
        jsonStore = "./data/" + userInput + ".json";

        try {
            jsonReader = new JsonReader(jsonStore);
            balanceSheet = jsonReader.read();
            financeTracker.show("Menu");
        } catch (IOException e) {
            responsePanel.removeAll();
            createLabel("No such username exists. Try again!");
        }
    }

    // MODIFIES: this
    // EFFECTS: creates a new User with associated json file
    private void createUserInfo() {
        String userInput = responseField.getText();
        jsonStore = "./data/" + userInput + ".json";

        File f = new File(jsonStore);
        if (f.exists()) { 
            responsePanel.removeAll();
            createLabel("Username already exists. Try again!");
        } else {
            balanceSheet = new UserFinancesList(userInput);
            financeTracker.show("Menu");
        }
    }

    // getters
    public UserFinancesList getBalanceSheet() {
        return this.balanceSheet;
    }

    public String getJsonStore() {
        return this.jsonStore;
    }
}

