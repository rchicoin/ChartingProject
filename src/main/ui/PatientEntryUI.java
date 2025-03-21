package ui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import model.Chart;
import model.Patient;

// Constructs a panel where the user can enter a new patient
public class PatientEntryUI extends JPanel implements ActionListener {

    private VitalsMainMenuUI mainFrame;
    private Chart chart;
    private JPanel mainPanel;

    private String first1;
    private String last1;
    private int idint;

    private JTextField first;
    private JTextField last;
    private JTextField id;

    private JLabel idPrompt;
    private JLabel firstPrompt;
    private JLabel lastPrompt;
    private JLabel error;
    private JLabel idError;

    // EFFECTS: creates a panel where a user can enter a new patient
    public PatientEntryUI(VitalsMainMenuUI mainFrame, Chart chart, JPanel mainPanel) {

        setLayout(new GridLayout(0, 1));

        this.mainFrame = mainFrame;
        this.chart = chart;
        this.mainPanel = mainPanel;

        Border border = BorderFactory.createLineBorder(Color.BLUE, 10);
        setBorder(border);

        first = new JTextField(5);
        last = new JTextField(5);
        id = new JTextField(5);

        idPrompt = new JLabel("Please enter the Patients ID: ");
        add(idPrompt);
        add(id);

        firstPrompt = new JLabel("Please enter the Patients first name: ");
        add(firstPrompt);
        add(first);

        lastPrompt = new JLabel("Please enter the Patients last name: ");
        add(lastPrompt);
        add(last);

        JButton next = new JButton("Enter/Next");
        next.setActionCommand("ADDPATIENT");
        next.addActionListener(this);
        add(next);

        addErrorMessages();

    }

    // MODIFIES: this
    // EFFECTS: creates new JLabels specifically for the error messages.
    private void addErrorMessages() {
        error = new JLabel("Please only enter numbers for the patient ID");
        idError = new JLabel("That patient ID already exists in the chart. Please enter a different one.");
    }

    // MODIFIES: chart, mainFrame
    // EFFECTS: reads in values from the textfields to record the patients first
    // name, last name, and ID. Creates a new patient and then adds that patient to
    // the chart. Once this is done returns to the mainPanel (main menu).
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("ADDPATIENT")) {
            try {
                first1 = first.getText();
                last1 = last.getText();
                String id1 = id.getText();
                idint = Integer.parseInt(id1);
                Patient patient = new Patient(first1, last1, idint);
                checkForPatientId();

                chart.addPatient(patient);
                updateMainFrameToMainMenu();
            } catch (NumberFormatException e1) {
                wrongValueIdEntryErrorHandling();
            } catch (Exception e3) {
                idDuplicateErrorHandling();
            }
        }

    }

    // MODIFIES: this
    // EFFECTS: Notifies the user if the ID is already taken. Returns the right
    // error message and removes unrelated error message
    private void idDuplicateErrorHandling() {
        add(idError);
        error.setVisible(false);
        idError.setVisible(true);
        revalidate();
        repaint();
    }

    // MODIFIES: this
    // EFFECTS: Notifies the user if the ID value entered is invalid (for example if
    // it contains characters), returns the corresponding error message and removes
    // unrelated error message
    private void wrongValueIdEntryErrorHandling() {
        add(error);
        idError.setVisible(false);
        error.setVisible(true);
        revalidate();
        repaint();
    }

    // MODIFIES: this, mainFrame
    // EFFECTS: removes this panel from the pain frame, returns to the main menu
    // (mainPanel)
    private void updateMainFrameToMainMenu() {
        mainFrame.remove(this);
        mainFrame.add(mainPanel);
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    // EFFECTS: checks to see if a patient ID exists in the chart, if the id is
    // present an error is thrown (checking for duplicate ids)
    private void checkForPatientId() throws Exception {
        for (Patient p : chart.getChartList()) {
            if (p.getId() == idint) {
                throw new Exception();
            }
        }
    }

}
