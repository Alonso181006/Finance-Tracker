package ui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import model.Finances;
import model.UserFinancesList;
import persistence.JsonWriter;

// Modeled placing images on https://github.students.cs.ubc.ca/CPSC210/C3-LectureLabStarter

// Represents an JPanel for the Menu Screen
public class MenuPanel extends JPanel implements ActionListener {
    private JButton assetButton;
    private JButton liabilityButton;
    private JButton saveButton;
    private JButton quitButton;
    private JPanel displayPanel;
    private JPanel buttonPanel;
    private JLabel responseLabel;
    private GridBagConstraints gbc;
    private FinanceTracker financeTracker;
    private UserFinancesList balanceSheet;
    private String[] data;
    private JList<String> list;
    private JsonWriter jsonWriter;  
    private ImageIcon richImage;
    private ImageIcon middleImage;
    private ImageIcon brokeImage;
    private JLabel imageAsLabel;
    private String jsonStore;

    // REQUIRES: financeTracker & balanceSheet to not be null
    // EFFECTS: The reference to the main Finance Tracker is set to financeTracker, the balance 
    //          sheet of user is set to balanceSheet, and the string for the location of the data 
    //          in the system is set to jsonStore, the panel is initialized, and images loaded
    public MenuPanel(FinanceTracker financeTracker, UserFinancesList balanceSheet, String jsonStore) {
        this.financeTracker = financeTracker;
        this.balanceSheet = balanceSheet;
        this.jsonStore = jsonStore;

        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();

        initComponents();

        updateListDisplay();

        add(buttonPanel);
        add(displayPanel);

        loadImages();
        chooseImage();
    }

    // MODIFIES: this
    // EFFECTS: initializes all the buttons and sub panels
    private void initComponents() {
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

        saveButton = new JButton("Save");
        saveButton.addActionListener(this);
        gbc.gridy = 2;
        buttonPanel.add(saveButton, gbc);

        quitButton = new JButton("Quit");
        quitButton.addActionListener(this);
        gbc.gridy = 3;
        buttonPanel.add(quitButton, gbc);
    }

    @Override
    // MODIFIES: this
    // EFFECTS: performs different actions based on the source of the
    //          ActionListener
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == assetButton) {
            financeTracker.show("Assets");
        } else if (e.getSource() == liabilityButton) {
            financeTracker.show("Liabilities");
        } else if (e.getSource() == saveButton) {
            saveUserInfo();
        } else if (e.getSource() == quitButton) {
            System.exit(0);
        }
    }

    // MODIFIES: this
    // EFFECTS: updates the display panel for the list of finances 
    public void updateListDisplay() {
        gbc = new GridBagConstraints();  
        displayPanel.removeAll();

        // Heading
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        displayPanel.add(new JLabel("Displayed Finances Here:"), gbc);

        //List
        updateList();
    }
    
    // MODIFIES: this
    // EFFECTS: updates the JList based on the balanceSheet and its 
    //          ArrayList of finances 
    private void updateList() {
        List<Finances> myList = balanceSheet.getFinances();
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
    // EFFECTS: creates a new jsonWriter and saves the balanceSheet to file
    private void saveUserInfo() {
        try {
            UserFinancesList financesList = balanceSheet;
            jsonWriter = new JsonWriter(jsonStore);
            jsonWriter.createWriter();
            jsonWriter.write(financesList);
            jsonWriter.closeWriter();

            createLabel("Saved " + financesList.getUser() + " to " + jsonStore);
        } catch (FileNotFoundException e) {
            createLabel("Unable to write to file: " + jsonStore);
        }
    }

    // MODIFIES: this
    // EFFECTS: creates a new JLabel at the bottom of display Panel
    //          based on text parameter  
    private void createLabel(String text) {
        //create textfield
        responseLabel = new JLabel(text);
        responseLabel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 3;
        displayPanel.add(responseLabel, gbc);
        repaint();
        revalidate();
    }

    // MODIFIES: this
    // EFFECTS:  sets the images displayed in the display panel based
    //           on networth
    public void chooseImage() {
        if (balanceSheet.netWorth() >= 10000) {
            setImage(richImage);
        } else if (balanceSheet.netWorth() < 0) {
            setImage(brokeImage);
        } else {
            setImage(middleImage);
        }
    }

    // MODIFIES: this
    // EFFECTS:  sets the image displayed to image passed
    public void setImage(ImageIcon image) {
        removeExistingImage();
        imageAsLabel = new JLabel(image);
        gbc.gridy = 2;
        displayPanel.add(imageAsLabel, gbc);
    }

    // MODIFIES: this
    // EFFECTS: loads and scales the images that are going to used in the
    //          display panel
    private void loadImages() {
        String sep = System.getProperty("file.separator");

        richImage = scaleImage(new ImageIcon(System.getProperty("user.dir") 
        + sep + "images" + sep + "rich.png"),150, 200);
        middleImage = scaleImage(new ImageIcon(System.getProperty("user.dir") 
        + sep + "images" + sep + "middle.png"), 150, 200);
        brokeImage = scaleImage(new ImageIcon(System.getProperty("user.dir") 
        + sep + "images" + sep + "broke.png"), 150, 200);
    }

    // EFFECTS: creates a scaled version of the images based on width and height
    //          and returns it as ImageIcon
    private ImageIcon scaleImage(ImageIcon image, int width, int height) {
        return new ImageIcon(image.getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH));
    }

    // EFFECTS: removes the current image from the display panel if 
    //          it is not null
    private void removeExistingImage() {
        if (imageAsLabel != null) {
            displayPanel.remove(imageAsLabel);
        }
    }
}
