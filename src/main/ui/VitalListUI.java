package ui;

import javax.swing.JButton;
import javax.swing.JPanel;

// Constructs a panel where the user can view a list of the selected  patients vitals
public class VitalListUI extends JPanel {

    // EFFECTS: creates a panel where first a patient is is verified and then a list
    // of previously entered vitals is displayed
    public VitalListUI() {
        JButton next = new JButton("Next");
        add(next);
    }
}
