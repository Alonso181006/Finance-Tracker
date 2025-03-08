package ui;

import javax.swing.*;

public class FinanceTracker extends JFrame{

    public FinanceTracker() {
        super("Finance Tracker App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setVisible(true);

        //to not be able to resize
        setResizable(true);

        // adding component 
        JButton button = new JButton("Button");
        add(button);

        //To visualize the button
        repaint();
        revalidate();
    }

}
