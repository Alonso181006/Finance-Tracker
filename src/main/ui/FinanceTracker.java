package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class FinanceTracker extends JFrame implements ActionListener{
    private JButton button;

    public FinanceTracker() {
        super("Finance Tracker App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setVisible(true);

        //to not be able to resize
        setResizable(true);

        // adding component 
        button = new JButton("Button");
        button.addActionListener(this);
        add(button);

        //To visualize the button
        repaint();
        revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button) {
            System.out.println("I pressed the button");
        }

    }

}
