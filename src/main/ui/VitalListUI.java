package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.Chart;

// Constructs a panel where the user can view a list of the selected  patients vitals
public class VitalListUI extends JPanel implements ActionListener {

    private VitalsMainMenuUI mainFrame;
    private Chart chart;
    private JPanel mainPanel;

    private int patientId;
    JTextField patientIdT;
    JTextArea textArea;
    JScrollPane scrollPane;

    // EFFECTS: creates a panel where first a patient is is verified and then a list
    // of previously entered vitals is displayed
    public VitalListUI(VitalsMainMenuUI mainFrame, Chart chart, JPanel mainPanel) {
        this.mainFrame = mainFrame;
        this.chart = chart;
        this.mainPanel = mainPanel;

        JButton getPatientButton = new JButton("Enter the patients ID");
        add(getPatientButton);
        getPatientButton.setActionCommand("GETPATIENT");
        getPatientButton.addActionListener(this);

        textArea = new JTextArea(300,300);
        scrollPane = new JScrollPane(textArea);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("GETPATIENT")) {
            patientId = returnInteger(patientIdT.getText());
        }

    }

    public int returnInteger(String string) {
        int integer = Integer.parseInt(string);
        return integer;
    }
}
