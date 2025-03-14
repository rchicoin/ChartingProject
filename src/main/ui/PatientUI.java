package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.Chart;
import model.Patient;

// Constructs a panel where the user can enter a new patient
public class PatientUI extends JPanel implements ActionListener {

    private VitalsMainMenuUI mainFrame;
    private Chart chart;
    private JPanel mainPanel;

    private String first1;
    private String last1;
    private int idint;

    private JTextField first;
    private JTextField last;
    private JTextField id;

    // EFFECTS: creates a panel where a user can enter a new patient
    public PatientUI(VitalsMainMenuUI mainFrame, Chart chart, JPanel mainPanel) {

        this.mainFrame = mainFrame;
        this.chart = chart;
        this.mainPanel = mainPanel;

        first = new JTextField("enter first name");
        last = new JTextField("enter last name");
        id = new JTextField("enter id");

        JButton next = new JButton("Enter/Next");

        next.setActionCommand("ADDPATIENT");
        next.addActionListener(this);

        add(first);
        add(last);
        add(id);
        add(next);

    }

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
