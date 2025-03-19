package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.UserFinancesList;

public class LiabilitiesPanel extends JPanel implements ActionListener {
    JButton backButton;

    FinanceTracker financeTracker;
    UserFinancesList balanceSheet;

    public LiabilitiesPanel(FinanceTracker financeTracker, UserFinancesList balanceSheet){
        this.financeTracker = financeTracker;
        this.balanceSheet = balanceSheet;

        add(new JLabel("Remove Liabilities Options Here"));
        backButton = new JButton("Back");
        backButton.addActionListener(this);
        add(backButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backButton) {
            financeTracker.show("Menu");
        }
    }

}
