package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Chart;
import model.Patient;
import model.Vitals;

// Constructs a panel where the user can add vitals to a patients chart
public class VitalsEntryUI extends JPanel implements ActionListener {

    private VitalsMainMenuUI mainFrame;
    private Chart chart;
    private JPanel mainPanel;

    private int patientId;
    private int respRate;
    private int spo2;
    private boolean supplementalOxygen;
    private double temperature;
    private int systolicBp;
    private int diastolicBp;
    private boolean avpuScore;
    private int heartRate;

    private JTextField patientIdT;
    private JTextField respRateT;
    private JTextField spo2T;
    private JTextField supplementalOxygenT;
    private JTextField temperatureT;
    private JTextField systolicBpT;
    private JTextField diastolicBpT;
    private JTextField avpuScoreT;
    private JTextField heartRateT;

    // EFFECTS: creates a panel where first a patient is is verified and then vitals
    // information can be entered into a field sequentially
    public VitalsEntryUI(VitalsMainMenuUI mainFrame, Chart chart, JPanel mainPanel) {
        this.mainFrame = mainFrame;
        this.chart = chart;
        this.mainPanel = mainPanel;

        patientIdT = new JTextField("enter patients id");
        respRateT = new JTextField("enter respiratory rate");
        spo2T = new JTextField("enter spo2");
        supplementalOxygenT = new JTextField("enter y or n based on oxygenation status");
        temperatureT = new JTextField("enter temperature");
        systolicBpT = new JTextField("enter systolic blood pressure");
        diastolicBpT = new JTextField("enter diastolic blood pressure");
        avpuScoreT = new JTextField("enter AVPU SCORE");
        heartRateT = new JTextField("enter heart rate");

        JButton next = new JButton("Enter/Next");

        next.setActionCommand("ADDVITALS");
        next.addActionListener(this);

        addButtons(next);
    }

    // MODIFIES: this
    // EFFECTS: adds all buttons created in the constructor to the panel itself.
    private void addButtons(JButton next) {
        add(patientIdT);
        add(respRateT);
        add(spo2T);
        add(supplementalOxygenT);
        add(temperatureT);
        add(systolicBpT);
        add(diastolicBpT);
        add(avpuScoreT);
        add(heartRateT);
        add(next);
    }

    // REQUIRES: the patient id, respRate, spo2, supplementalOxygen, systolic and
    // diastolic bp, and heartrate must all only contain digits, the temperature
    // must only be a double value, the supplemental oxygen must be a y if the
    // patient is on supplemental oxygen, and the AVPU score must be "a" if the
    // patient is alert.
    // MODIFIES: chart
    // EFFECTS: adds the values entered to the patients vital list in the chart.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("ADDVITALS")) {

            patientId = returnInteger(patientIdT.getText());
            Patient patient = chart.getPatient(patientId);

            respRate = returnInteger(respRateT.getText());
            spo2 = returnInteger(spo2T.getText());
            supplementalOxygen = returnSupplementalOxygen(supplementalOxygenT.getText());
            temperature = returnDouble(temperatureT.getText());
            systolicBp = returnInteger(systolicBpT.getText());
            diastolicBp = returnInteger(diastolicBpT.getText());
            avpuScore = returnAvpu(avpuScoreT.getText());
            heartRate = returnInteger(heartRateT.getText());

            Vitals vitals = new Vitals(respRate, spo2, supplementalOxygen, temperature, systolicBp, diastolicBp,
                    avpuScore, heartRate);
            patient.addVitals(vitals);

            determineNextPanel(vitals);

        }
    }

    // MODIFIES: mainFrame
    // EFFECTS: if the recently entered vitals have a NEWS score greater than 5 it
    // will re-direct the user to the BaseLine panel, if no they will be re-directed
    // to the mainPanel(main menu)
    private void determineNextPanel(Vitals vitals) {
        if (vitals.getNewsScore() >= 5) {
            mainFrame.remove(this);
            mainFrame.add(new BaseLinePanel(mainFrame, chart, mainPanel));
            mainFrame.revalidate();
            mainFrame.repaint();
        } else {
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

    // REQUIRES: the string must only contain digits and decimals (.)
    // EFFECTS: converts a string of digits to a new variable of type double
    public double returnDouble(String string) {
        double dbl = Double.parseDouble(string);
        return dbl;
    }

    // REQUIRES: the string must be a single character y or Y, to indicate the
    // patient is on oxygen, otherwise will read that the patient is not on oxygen
    // EFFECTS: if the user enters y or Y the method will return true, otherwise the
    // method will return false
    public boolean returnSupplementalOxygen(String string) {
        string = string.toLowerCase();
        if (string.equals("y")) {
            return true;
        } else {
            return false;
        }
    }

    // REQUIRES: the string must be a single character a or A, to indicate the
    // patient is alert, otherwise will read that the patient is not alert
    // EFFECTS: if the user enters a or A the method will return true, otherwise the
    // method will return false
    public boolean returnAvpu(String string) {
        string = string.toLowerCase();
        if (string.equals("a")) {
            return true;
        } else {
            return false;
        }
    }
}
