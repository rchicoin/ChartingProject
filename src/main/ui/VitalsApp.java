package ui;

import model.*;
import java.util.*;

// User interface for patient vital recording 
public class VitalsApp {

    private Chart chart;
    private Scanner input;

    // EFFECTS: initiates the ability to add patients and vitals to the chart
    public VitalsApp() {
        chart = new Chart();
        input = new Scanner(System.in);
        runVitalsApp();
    }

    // MODIFIES: this
    // EFFECTS: Displays the main menu and corresponding menu options based on user
    // selections.
    // The framework for running this main menu was used from the example code
    // provided for this phase of the project
    public void runVitalsApp() {
        boolean selection = true;
        String command = null;

        while (selection) {
            System.out.println("Please choose from the following options:");
            System.out.println("Enter \"a\" to enter a new patient in the chart.");
            System.out.println("Enter \"b\" to add vitals for a patient.");
            System.out.println("Enter \"c\" to get a list of recorded vitals for a patient.");
            System.out.println("Enter \"d\" to exit.");
            System.out.println();

            command = input.next();
            command = command.toLowerCase();

            if (command.equals("d")) {
                selection = false;
            } else {
                readInput(command);
            }

        }
    }

    // MODIFIES: this
    // EFFECTS: performs the corresponding task related to the chosen value on the
    // main menu
    public void readInput(String command) {
        if (command.equals("a")) {
            makePatient();
        } else if (command.equals("b") && chart.getChartList().size() > 0) {
            addVitalsToPatient();
        } else if (command.equals("c") && chart.getChartList().size() > 0) {
            getListOfPatientVitals();
        } else {
            if (chart.getChartList().size() == 0) {
                System.out.println(
                        "Please add a patient to the chart before attempting to add vitals or view a vitals list.");
                System.out.println();
            } else {
                System.out.println("Please enter a valid selection listed below.");
                System.out.println();
            }
        }
    }

    // REQUIRES: The patient id must not be the same as any other patient id.
    // MODIFIES: this
    // EFFECTS: reads user input to make a new patient, adds a patient to the chart.
    public void makePatient() {
        String first;
        String last;
        int id;
        System.out.println("Please enter the patients first name.");
        first = input.next();

        System.out.println("Please enter the patients last name.");
        last = input.next();

        System.out.println("Please enter the patients Id");
        id = input.nextInt();

        Patient newPatient = new Patient(first, last, id);
        chart.addPatient(newPatient);

        System.out
                .println("The patient" + " " + first + " " + last + " " + "has been successfully added to the chart.");
        System.out.println();
    }

    // MODIFIES: this
    // EFFECTS: reads user input to first choose a patient based on patient id, and
    // then reads user input for vitals, once vitals have been recorded they are
    // added to the patients vitals list.

    public void addVitalsToPatient() {
        int id;
        System.out.println("Please enter the patients id");
        id = input.nextInt();
        boolean patientSearch = searchForPatient(id);

        if (!patientSearch) {
            System.out.println("That patient does not exist in the chart, please try again.");
            return;
        }

        Patient patient = null;

        for (Patient patients : chart.getChartList()) {
            if (patients.getId() == id) {
                patient = patients;
            }
        }

        int respRate;
        int spo2;
        double temperature;
        int systolicBp;
        int diastolicBp;
        int heartRate;
        boolean avpuScore = false;
        boolean supplementalOxygen = false;

        boolean oxygenPromptValid = false;
        boolean avpuPromptValid = false;
        boolean baselinePrompt = false;

        System.out.println("Please enter the patients respiratory rate.");
        respRate = input.nextInt();

        System.out.println("Please enter the patients spo2.");
        spo2 = input.nextInt();

        System.out.println("Please enter the patients temperature.");
        temperature = input.nextDouble();

        System.out.println("Please enter the patients systolic blood pressure.");
        systolicBp = input.nextInt();

        System.out.println("Please enter the patients diastolic blood pressure.");
        diastolicBp = input.nextInt();

        System.out.println("Please enter the patients heart rate.");
        heartRate = input.nextInt();

        System.out.println("Is the patient on supplemental oxygen? Enter y for yes or n for no.");
        while (!oxygenPromptValid) {
            String ans = input.next();
            ans = ans.toLowerCase();
            if (ans.equals("y")) {
                supplementalOxygen = true;
                oxygenPromptValid = true;
            } else if (ans.equals("n")) {
                supplementalOxygen = false;
                oxygenPromptValid = true;
            } else {
                System.out.println("Please enter a valid answer y/n if patient is on supplemental oxygen.");
            }

        }

        System.out.println(
                "Please enter a if the patient is alert, or b if the patient only responds to verbal/painful stimuli or not at all.");
        while (!avpuPromptValid) {
            String ans = input.next();
            ans = ans.toLowerCase();
            if (ans.equals("a")) {
                avpuScore = true;
                avpuPromptValid = true;
            } else if (ans.equals("b")) {
                avpuScore = false;
                avpuPromptValid = true;
            } else {
                System.out.println("Please enter a valid answer a/b regarding patients AVPU status.");
            }

        }

        Vitals vitalRecord = new Vitals(respRate, spo2, supplementalOxygen, temperature, systolicBp, diastolicBp,
                avpuScore, heartRate);

        patient.addVitals(vitalRecord);

        if (vitalRecord.getNewsScore() >= 5) {
            System.out.println("Are these vitals within the patients baseline? Please enter y for yes or n for no.");
            while (!baselinePrompt) {
                String ans = input.next();
                ans.toLowerCase();
                if (ans.equals("y")) {
                    baselinePrompt = true;
                } else if (ans.equals("n")) {
                    baselinePrompt = true;
                    System.out.println(
                            "PLEASE ESCALATE CARE FOR THIS PATIENT, THIS NEWS SCORE MAY INDICATE SEPSIS OR DETERIORATING PATIENT STATUS");
                    System.out.println(
                            "=========================================================================================================");
                    System.out.println();
                } else {
                    System.out.println("Please enter a valid answer y/n regarding patients baseline status.");
                }
            }
        }
        System.out.println(patient.getFirstName() + " " + patient.getLastName() + "s NEWS score for this recording: "
                + vitalRecord.getNewsScore());
        System.out.println();
        System.out.println("You have succesfully added vitals to " + patient.getFirstName() + " "
                + patient.getLastName() + "s chart.");
        System.out.println();
    }

    // EFFECTS: reads user input to first choose a patient, and then prints off a
    // list of all the patients vital readings

    public void getListOfPatientVitals() {
        int id;
        System.out.println("Please enter the patients id");
        id = input.nextInt();
        boolean patientSearch = searchForPatient(id);

        if (!patientSearch) {
            System.out.println("That patient does not exist in the chart, please try again.");
            return;
        }

        Patient patient = null;

        for (Patient patients : chart.getChartList()) {
            if (patients.getId() == id) {
                patient = patients;
            }
        }

        int i = 0;
        for (Vitals vital : patient.getVitalList()) {
            System.out.println("Recording: " + i);
            System.out.println("Respiratory Rate:" + vital.getRespRate());
            System.out.println("Spo2:" + vital.getSpo2());
            System.out.println("Supplemental O2 status:" + vital.getSupplementalOxygen());
            System.out.println("Temperature:" + vital.getTemperature());
            System.out.println("Systolic Blood Pressure:" + vital.getSystolicBp());
            System.out.println("Diastolic Blood Pressure:" + vital.getDiastolicBp());
            System.out.println("Was the patient alert?:" + vital.getAvpu());
            System.out.println("Heart Rate:" + vital.getRespRate());
            System.out.println("==========================================================================");
            i++;
        }

        if (patient.getVitalList().size() == 0) {
            System.out.println("There are no vitals recorded for this patient yet.");
            System.out.println();
        }
    }

    // EFFECTS: returns true if the patient id exists in the chart and returns false
    // if the patient id does not exist in the chart
    public Boolean searchForPatient(int id) {
        for (Patient patient : chart.getChartList()) {
            if (patient.getId() == id) {
                return true;
            }
        }
        return false;
    }
}
