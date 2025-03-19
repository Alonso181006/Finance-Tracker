package ui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LiabilitiesPanel extends JPanel implements ActionListener {
    JButton backButton;

    public LiabilitiesPanel(){
        add(new JLabel("Remove Asset Options Here"));
        backButton = new JButton("Back");
        backButton.addActionListener(this);
        add(backButton);
    }

}
