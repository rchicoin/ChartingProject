package ui;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.Chart;

// Constructs a panel where the user can view a list of the selected  patients vitals
public class VitalListUI extends JPanel {

    private VitalsMainMenuUI mainFrame;
    private Chart chart;
    private JPanel mainPanel;
    // EFFECTS: creates a panel where first a patient is is verified and then a list
    // of previously entered vitals is displayed
    public VitalListUI(VitalsMainMenuUI mainFrame, Chart chart, JPanel mainPanel) {
        JButton next = new JButton("Next");
        add(next);
    }
}
