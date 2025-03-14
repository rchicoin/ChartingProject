package ui;

import java.awt.Dimension;

import javax.swing.*;

// Represents a main menu with choices a/b/c/d/e to either enter a new patient, 
// add vitals to a patient, view a list of patient vitals, and either save or load a chart
public class VitalsMainMenuUI extends JFrame {

    JFrame frame;
    JPanel mainPanel;
    // EFFECTS: constructs the main menu with choices a/b/c/d/e/f
    public VitalsMainMenuUI() {
        super("Budget Cerner App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,500);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);


        mainPanel();
        add(mainPanel);
        
    }

    public void mainPanel(){
        mainPanel = new JPanel();

        JButton aMakePatient = new JButton("Enter a new Patient");
        mainPanel.add(aMakePatient);

        JButton bEnterVitals = new JButton("Enter Vitals");
        mainPanel.add(bEnterVitals);

        JButton cViewVitalList = new JButton("View Patients Vitals List");
        mainPanel.add(cViewVitalList);

        JButton dSaveApplication = new JButton("Save Chart");
        mainPanel.add(dSaveApplication);

        JButton eLoadApplication = new JButton("Load Previous Chart");
        mainPanel.add(eLoadApplication);

        JButton fExitApplication = new JButton("Exit");
        mainPanel.add(fExitApplication);
    }

    // EFFECTS: handles the users choices based on the main menu buttons, and
    // re-directs to the relevant UI panel
    public void buttonHandler() {

    }

    // EFFECTS: runs the relevant chart
    public static void main(String[] args) {
        new VitalsMainMenuUI();

    }
}
