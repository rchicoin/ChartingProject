package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.Chart;
import model.Patient;
import model.Vitals;

// Constructs a panel where the user can view a list of the selected  patients vitals from newest recorded to oldest 
public class VitalListUINew extends JPanel implements ActionListener {

    private VitalsMainMenuUI mainFrame;
    private Chart chart;
    private JPanel mainPanel;

    private int patientId;
    private JTextField patientIdT;
    private JTextArea textArea;
    private JScrollPane scrollPane;

    // EFFECTS: creates a panel where first a patient is is verified and then a list
    // of previously entered vitals is displayed from newest recorded to oldest
    public VitalListUINew(VitalsMainMenuUI mainFrame, Chart chart, JPanel mainPanel) {
        this.mainFrame = mainFrame;
        this.chart = chart;
        this.mainPanel = mainPanel;

        patientIdT = new JTextField("enter id");
        add(patientIdT);

        JButton getPatientButton = new JButton("Enter the patients ID");
        add(getPatientButton);
        getPatientButton.setActionCommand("GETPATIENT");
        getPatientButton.addActionListener(this);

        JButton backToMain = new JButton("Back to Main Menu");
        add(backToMain);
        backToMain.setActionCommand("BACK");
        backToMain.addActionListener(this);

    }

    // REQUIRES: the patient ID must be valid/present in the chart
    // MODIFIES: this, mainFrame
    // EFFECTS: once the user presses the "enter patient" button this will result in
    // a pop-up window containing a scroll pane of all the patients vitals recorded
    // from newest to oldest, if the user clicks the "back to main menu"
    // button they will be re-directed to the mainPanel(main menu)
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("GETPATIENT")) {
            patientId = returnInteger(patientIdT.getText());
            Patient patient = chart.getPatient(patientId);

            textArea = new JTextArea(vitalsToString(patient));
            textArea.setWrapStyleWord(true);
            textArea.setLineWrap(true);
            textArea.setEditable(false);

            scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(300, 300));
            this.add(scrollPane, BorderLayout.CENTER);
            revalidate();
            repaint();
        }
        if (e.getActionCommand().equals("BACK")) {
            mainFrame.remove(this);
            mainFrame.add(mainPanel);
            mainFrame.revalidate();
            mainFrame.repaint();
        }

    }

    // REQUIRES: the string must only contain digits
    // EFFECTS: converts a string of digits to a new variable of type int.
    public int returnInteger(String string) {
        int integer = Integer.parseInt(string);
        return integer;
    }

    // EFFECTS: converts a patients vitals data to a string so that it can be passed
    // into the JTextArea, which is then subsequently passed to the Jscrollpane.
    // (reverses the order of the patients original vital list in order to properly
    // print the values, and then reverse back again for proper record keeping)
    public String vitalsToString(Patient patient) {
        String text = "";
        List<Vitals> vitalList = patient.getVitalList();
        Collections.reverse(vitalList);
        int i = vitalList.size();

        for (Vitals vital : vitalList) {
            text = text.concat("Recording: " + i + "\n" + "Respiratory Rate:" + vital.getRespRate() + "\n" + "Spo2:"
                    + vital.getSpo2() + "\n" + "Supplemental O2 status:" + vital.getSupplementalOxygen() + "\n"
                    + "Temperature:" + vital.getTemperature() + "\n" + "Systolic Blood Pressure:"
                    + vital.getSystolicBp() + "\n" + "Diastolic Blood Pressure:" + vital.getDiastolicBp() + "\n"
                    + "Was the patient alert?:" + vital.getAvpu() + "\n" + "Heart Rate:" + vital.getRespRate() + "\n" 
                    + "NEWS Score:" + vital.getNewsScore() + "\n"
                    + "======================================" + "\n");
            i--;
        }
        Collections.reverse(vitalList);
        return text;

    }

}
