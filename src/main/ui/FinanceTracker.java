package ui;

import javax.swing.JButton;
import javax.swing.JFrame;

public class FinanceTracker extends JFrame{

    public static void main(String[] args) {
        JFrame window = new JFrame("Finance Tracker App");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(400, 400);
        window.setVisible(true);

        //to not be able to resize
        window.setResizable(true);

        // adding component 
        JButton button = new JButton("Button");
        window.add(button);

        //To visualize the button
        window.repaint();
        window.revalidate();
    }

}
