package ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;

import model.Chart;
import persistence.JsonReader;
import persistence.JsonWriter;

// Represents a main menu with choices to either enter a new patient, 
// add vitals to a patient, view a list of patient vitals from newest to oldest or reversed.
// Also has options to only view patient vitals with a NEWS score above 5, and either save or load a chart.
public class VitalsMainMenuUI extends JFrame implements ActionListener {

    private static final String JSON_STORE = "./data/chart.json";
    private Chart chart;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private JPanel mainPanel;
    private PatientEntryUI patientPanel;
    private VitalListUIOld vitalListPanel;
    private VitalListUINew vitalListPanelNew;
    private VitalListUIAbove5 vitalListPanelAbove5;
    private VitalsEntryUI vitalEntryPanel;

    // EFFECTS: constructs a main JFrame which starts with the main menu (mainPanel)
    // with choices listed above.
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

        ImageIcon alert = new ImageIcon("data/crossImage.png");
        setIconImage(alert.getImage());

        chart = new Chart();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // MODIFIES: this
    // EFFECTS: creates a mainPanel with the options listed above and correlating
    // buttons to execute those options.
    public void mainPanel() {
        mainPanel = new JPanel(new GridLayout(0, 1));

        refactorButtonsCheckstyle();

        JButton viewVitalList = new JButton("View Patients Vitals List Oldest to Newest");
        mainPanel.add(viewVitalList);
        viewVitalList.setActionCommand("VIEWVITALSOLD");
        viewVitalList.addActionListener(this);

        JButton viewVitalListNew = new JButton("View Patients Vitals List Newest to Oldest");
        mainPanel.add(viewVitalListNew);
        viewVitalListNew.setActionCommand("VIEWVITALSNEW");
        viewVitalListNew.addActionListener(this);

        JButton viewVitalListAbove5 = new JButton("View Patients Vitals List NEWS Score Above 5");
        mainPanel.add(viewVitalListAbove5);
        viewVitalListAbove5.setActionCommand("VIEWVITALSABOVE5");
        viewVitalListAbove5.addActionListener(this);

        JButton saveApplication = new JButton("Save Chart");
        mainPanel.add(saveApplication);
        saveApplication.setActionCommand("SAVECHART");
        saveApplication.addActionListener(this);

        JButton loadApplication = new JButton("Load Previous Chart");
        mainPanel.add(loadApplication);
        loadApplication.setActionCommand("LOADCHART");
        loadApplication.addActionListener(this);

    }

    // EFFECTS: creates both "enter new patient button" and "enter vitals button"
    // and adds them to the mainFrame
    private void refactorButtonsCheckstyle() {
        JButton makePatient = new JButton("Enter a new Patient");
        mainPanel.add(makePatient);
        makePatient.setActionCommand("NEWPATIENT");
        makePatient.addActionListener(this);

        JButton enterVitals = new JButton("Enter Vitals");
        mainPanel.add(enterVitals);
        enterVitals.setActionCommand("ENTERVITALS");
        enterVitals.addActionListener(this);
    }

    // EFFECTS: handles the users choices based on the main menu buttons, and
    // re-directs to the relevant UI panel
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("NEWPATIENT")) {
            refactorNewPatientCommand();
        } else if (e.getActionCommand().equals("ENTERVITALS")) {
            refactorEnterVitalsCommand();
        } else if (e.getActionCommand().equals("VIEWVITALSOLD")) {
            this.remove(mainPanel);
            vitalListPanel = new VitalListUIOld(this, chart, mainPanel);
            this.add(vitalListPanel);
        } else if (e.getActionCommand().equals("VIEWVITALSNEW")) {
            this.remove(mainPanel);
            vitalListPanelNew = new VitalListUINew(this, chart, mainPanel);
            this.add(vitalListPanelNew);
        } else if (e.getActionCommand().equals("VIEWVITALSABOVE5")) {
            this.remove(mainPanel);
            vitalListPanelAbove5 = new VitalListUIAbove5(this, chart, mainPanel);
            this.add(vitalListPanelAbove5);
        } else if (e.getActionCommand().equals("SAVECHART")) {
            saveChart();
        } else if (e.getActionCommand().equals("LOADCHART")) {
            loadChart();
        }

        revalidate();
        repaint();
    }

    // EFFECTS: creates the enter vitals panel and displays it
    private void refactorEnterVitalsCommand() {
        this.remove(mainPanel);
        vitalEntryPanel = new VitalsEntryUI(this, chart, mainPanel);
        this.add(vitalEntryPanel);
    }

    // EFFECTS: creates the enter new patient panel and displays it
    private void refactorNewPatientCommand() {
        this.remove(mainPanel);
        patientPanel = new PatientEntryUI(this, chart, mainPanel);
        this.add(patientPanel);
    }

    // EFFECTS: runs the relevant chart
    public static void main(String[] args) {
        new VitalsMainMenuUI();

    }

    // EFFECTS: saves the chart to a file, catches FileNotFoundException, will
    // notify if unable to write to JSON_STORE file
    private void saveChart() {
        try {
            jsonWriter.open();
            jsonWriter.write(this.chart);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // EFFECTS: loads the chart, catches IOException, will notify if unable to load
    // chart from JSON_STORE
    private void loadChart() {
        try {
            chart = jsonReader.read();
            System.out.println("Successfully loaded the chart from: " + JSON_STORE);

        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }

    }
}
