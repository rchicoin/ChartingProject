package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.*;

import model.Chart;
import persistence.JsonReader;
import persistence.JsonWriter;

// Represents a main menu with choices a/b/c/d/e to either enter a new patient, 
// add vitals to a patient, view a list of patient vitals, and either save or load a chart
public class VitalsMainMenuUI extends JFrame implements ActionListener {

    private static final String JSON_STORE = "./data/chart.json";
    private Chart chart;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private JPanel mainPanel;
    private PatientUI patientPanel;
    private VitalListUI vitalListPanel;
    private VitalsUI vitalEntryPanel;

    // EFFECTS: constructs the main menu with choices a/b/c/d/e/f
    public VitalsMainMenuUI() {
        super("Budget Cerner App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);

        mainPanel();
        add(mainPanel);
        mainPanel.setVisible(true);

        chart = new Chart();
    }

    public void mainPanel() {
        mainPanel = new JPanel();

        JButton makePatient = new JButton("Enter a new Patient");
        mainPanel.add(makePatient);
        makePatient.setActionCommand("NEWPATIENT");
        makePatient.addActionListener(this);

        JButton enterVitals = new JButton("Enter Vitals");
        mainPanel.add(enterVitals);
        enterVitals.setActionCommand("ENTERVITALS");
        enterVitals.addActionListener(this);

        JButton viewVitalList = new JButton("View Patients Vitals List");
        mainPanel.add(viewVitalList);
        viewVitalList.setActionCommand("VIEWVITALS");
        viewVitalList.addActionListener(this);

        JButton saveApplication = new JButton("Save Chart");
        mainPanel.add(saveApplication);
        saveApplication.setActionCommand("SAVECHART");
        saveApplication.addActionListener(this);

        JButton loadApplication = new JButton("Load Previous Chart");
        mainPanel.add(loadApplication);
        loadApplication.setActionCommand("LOADCHART");
        loadApplication.addActionListener(this);

    }


    // EFFECTS: handles the users choices based on the main menu buttons, and
    // re-directs to the relevant UI panel
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("NEWPATIENT")) {
            this.remove(mainPanel);
            patientPanel = new PatientUI(this, chart, mainPanel);
            this.add(patientPanel);
        } else if (e.getActionCommand().equals("ENTERVITALS")) {
            this.remove(mainPanel);
            vitalEntryPanel = new VitalsUI(this, chart, mainPanel);
            this.add(vitalEntryPanel);
        } else if (e.getActionCommand().equals("VIEWVITALS")) {
            this.remove(mainPanel);
            vitalListPanel = new VitalListUI(this, chart, mainPanel);
            this.add(vitalListPanel);
        } else if (e.getActionCommand().equals("SAVECHART")) {
            System.out.println("SAVE CHART");
        } else if (e.getActionCommand().equals("LOADCHART")) {
            System.out.println("LOAD CHART");
        }

        revalidate();
        repaint();
    }

    // EFFECTS: runs the relevant chart
    public static void main(String[] args) {
        new VitalsMainMenuUI();

    }
}
