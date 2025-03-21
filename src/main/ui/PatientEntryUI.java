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

    }

    // MODIFIES: chart, mainFrame
    // EFFECTS: reads in values from the textfields to record the patients first
    // name, last name, and ID. Creates a new patient and then adds that patient to
    // the chart. Once this is done returns to the mainPanel (main menu).
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("ADDPATIENT")) {
            first1 = first.getText();
            last1 = first.getText();
            String id1 = id.getText();
            idint = Integer.parseInt(id1);
            Patient patient = new Patient(first1, last1, idint);
            chart.addPatient(patient);
        }

        mainFrame.remove(this);
        mainFrame.add(mainPanel);
        mainFrame.revalidate();
        mainFrame.repaint();
    }

}
