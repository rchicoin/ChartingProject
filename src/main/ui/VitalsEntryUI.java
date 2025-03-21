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

    private JLabel idPrompt;
    private JLabel respPrompt;
    private JLabel spo2Prompt;
    private JLabel oxygenPrompt;
    private JLabel tempPrompt;
    private JLabel systolicPrompt;
    private JLabel diastolicPrompt;
    private JLabel avpuPrompt;
    private JLabel heartRatePrompt;

    // EFFECTS: creates a panel where first a patient is is verified and then vitals
    // information can be entered into a field sequentially
    public VitalsEntryUI(VitalsMainMenuUI mainFrame, Chart chart, JPanel mainPanel) {
        this.mainFrame = mainFrame;
        this.chart = chart;
        this.mainPanel = mainPanel;
        setLayout(new GridLayout(0, 1));
        makeTextFields();
        makeLabelsForTextFields();

        Border border = BorderFactory.createLineBorder(Color.BLUE, 10);
        setBorder(border);

        JButton next = new JButton("Enter");
        next.setActionCommand("ADDVITALS");
        next.addActionListener(this);
        addButtonsAndLabels(next);
    }

    // MODIFIES: this
    // EFFECTS: makes all the labels for the text fields to prompt vital signs entry
    private void makeLabelsForTextFields() {
        idPrompt = new JLabel("Please enter the patients id");
        respPrompt = new JLabel("Respiratory Rate: ");
        spo2Prompt = new JLabel("Spo2: ");
        oxygenPrompt = new JLabel("If the patient is on supplemental oxygen please enter y or Y, if not enter n or N");
        tempPrompt = new JLabel("Temperature: ");
        systolicPrompt = new JLabel("Systolic Blood Pressure: ");
        diastolicPrompt = new JLabel("Diastolic Blood Pressure: ");
        avpuPrompt = new JLabel("If the patient is alert and response please enter y or Y, if not enter n or N");
        heartRatePrompt = new JLabel("Heart Rate: ");
    }

    // MODIFIES: this
    // EFFECTS: makes all the text fields for this panel for vitals entry
    private void makeTextFields() {
        patientIdT = new JTextField(5);
        respRateT = new JTextField(5);
        spo2T = new JTextField(5);
        supplementalOxygenT = new JTextField(5);
        temperatureT = new JTextField(5);
        systolicBpT = new JTextField(5);
        diastolicBpT = new JTextField(5);
        avpuScoreT = new JTextField(5);
        heartRateT = new JTextField(5);
    }

    // MODIFIES: this
    // EFFECTS: adds all textfields and labels created in the constructor to the panel itself.
    private void addButtonsAndLabels(JButton next) {
        add(idPrompt);
        add(patientIdT);

        add(respPrompt);
        add(respRateT);

        add(spo2Prompt);
        add(spo2T);

        add(tempPrompt);
        add(temperatureT);

        add(systolicPrompt);
        add(systolicBpT);

        add(diastolicPrompt);
        add(diastolicBpT);

        add(heartRatePrompt);
        add(heartRateT);

        add(avpuPrompt);
        add(avpuScoreT);

        add(oxygenPrompt);
        add(supplementalOxygenT);
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
        if (string.equals("y")) {
            return true;
        } else {
            return false;
        }
    }
}
