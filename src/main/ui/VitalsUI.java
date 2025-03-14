package ui;

import javax.swing.JButton;
import javax.swing.JPanel;

// Constructs a panel where the user can add vitals to a patients chart
public class VitalsUI extends JPanel {

    // EFFECTS: creates a panel where first a patient is is verified and then vitals
    // information can be entered into a field sequentially
    public VitalsUI() {
        JButton next = new JButton("Next");
        add(next);
    }

}
