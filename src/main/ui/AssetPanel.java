package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.UserFinancesList;

public class AssetPanel extends JPanel implements ActionListener{
    JButton backButton;

    FinanceTracker financeTracker;
    UserFinancesList balanceSheet;

    public AssetPanel(FinanceTracker financeTracker, UserFinancesList balanceSheet){
        this.financeTracker = financeTracker;
        this.balanceSheet = balanceSheet;

        add(new JLabel("Remove Asset Options Here"));
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
