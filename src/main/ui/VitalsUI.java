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
public class VitalsUI extends JPanel implements ActionListener {

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
    public VitalsUI(VitalsMainMenuUI mainFrame, Chart chart, JPanel mainPanel) {
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

        }

        mainFrame.remove(this);
        mainFrame.add(mainPanel);
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    public int returnInteger(String string) {
        int integer = Integer.parseInt(string);
        return integer;
    }

    public double returnDouble(String string) {
        double dbl = Double.parseDouble(string);
        return dbl;
    }

    public boolean returnSupplementalOxygen(String string) {
        string = string.toLowerCase();
        if (string.equals("y")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean returnAvpu(String string) {
        string = string.toLowerCase();
        if (string.equals("a")) {
            return true;
        } else {
            return false;
        }
    }
}
