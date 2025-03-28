package ui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import model.Event;
import model.EventLog;

public class WindowHandler extends WindowAdapter{
    FinanceTracker financeTracker;

    public WindowHandler(FinanceTracker financeTracker) {
        this.financeTracker = financeTracker;
    }

    @Override
    public void windowClosing(WindowEvent e) {
        for (Event event: EventLog.getInstance()) {
            System.out.println(event.getDescription());
        }
    }
}
