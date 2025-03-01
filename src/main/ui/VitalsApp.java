package ui;

import model.*;
import java.util.*;

import persistence.JsonReader;
import persistence.JsonWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

// User interface for patient vital recording 
public class VitalsApp {

    private static final String JSON_STORE = "./data/chart.json";
    private Chart chart;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

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
    // https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
    public void runVitalsApp() {
        boolean selection = true;
        String command = null;

        while (selection) {
            System.out.println("Please choose from the following options:");
            System.out.println("Enter \"a\" to enter a new patient in the chart.");
            System.out.println("Enter \"b\" to add vitals for a patient.");
            System.out.println("Enter \"c\" to get a list of recorded vitals for a patient.");
            System.out.println("Enter \"d\" to save the chart to file");
            System.out.println("Enter \"e\" to load the chart from file");
            System.out.println("Enter \"f\" to exit.");
            System.out.println();

            command = input.next();
            command = command.toLowerCase();

            if (command.equals("f")) {
                selection = false;
            } else {
                readMainMenuInput(command);
            }

        }
    }

    // EFFECTS: performs the corresponding task related to the chosen value on the
    // main menu
    public void readMainMenuInput(String command) {
        if (command.equals("a")) {
            makePatient();
        } else if (command.equals("b") && chart.getChartList().size() > 0) {
            addVitalsToPatient();
        } else if (command.equals("c") && chart.getChartList().size() > 0) {
            getListOfPatientVitals();
        } else if (command.equals("d") && chart.getChartList().size() > 0) {
            saveChart();
        } else if (command.equals("e") && chart.getChartList().size() > 0) {
            loadChart();
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
    // EFFECTS: reads user input to make a new patient, adds a patient to the chart,
    // prints a confirmation that a patient has been added to the chart
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
    // EFFECTS: reads user input to first choose a patient based on patient id (if
    // the id doesnt exist the user is redirected to the main menu and told so).
    // Then reads user input for vitals, once vitals have been recorded they are
    // added to the patients vitals list. A confirmation is printed out and the NEWS
    // score is printed out.
    public void addVitalsToPatient() {
        int id;
        System.out.println("Please enter the patients id");
        id = input.nextInt();
        boolean patientSearch = searchForPatientInChart(id);

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

        boolean avpuScore = false;
        boolean supplementalOxygen = false;
        boolean oxygenPromptValid = false;
        boolean avpuPromptValid = false;
        boolean baselinePrompt = false;

        Vitals vitalRecord = readUserVitalsInput(patient, avpuScore, supplementalOxygen, oxygenPromptValid,
                avpuPromptValid);

        determineNewsScoreBaseline(baselinePrompt, vitalRecord);
        printNewsScoreAndConfirmation(patient, vitalRecord);
    }

    // MODIFIES: this
    // EFFECTS: reads user input for the vital signs, creates a new vitals
    // object, adds the vitals to the patients record.
    private Vitals readUserVitalsInput(Patient patient, boolean avpuScore, boolean supplementalOxygen,
            boolean oxygenPromptValid, boolean avpuPromptValid) {
        System.out.println("Please enter the patients respiratory rate.");
        int respRate = input.nextInt();

        System.out.println("Please enter the patients spo2.");
        int spo2 = input.nextInt();

        System.out.println("Please enter the patients temperature.");
        double temperature = input.nextDouble();

        System.out.println("Please enter the patients systolic blood pressure.");
        int systolicBp = input.nextInt();

        System.out.println("Please enter the patients diastolic blood pressure.");
        int diastolicBp = input.nextInt();

        System.out.println("Please enter the patients heart rate.");
        int heartRate = input.nextInt();

        supplementalOxygen = readSupplementalOxygen(supplementalOxygen, oxygenPromptValid);

        avpuScore = readAvpuScore(avpuScore, avpuPromptValid);

        Vitals vitalRecord = new Vitals(respRate, spo2, supplementalOxygen, temperature, systolicBp, diastolicBp,
                avpuScore, heartRate);

        patient.addVitals(vitalRecord);
        return vitalRecord;
    }

    // EFFECTS: prompts user for supplemental oxygen input and validates the user
    // input is correct, returns true if the patient is on supplemental oxygen,
    // returns false if not.
    private boolean readSupplementalOxygen(boolean supplementalOxygen, boolean oxygenPromptValid) {
        System.out.println("Is the patient on supplemental oxygen? Enter y for yes or n for no.");
        supplementalOxygen = validateUserOxygenPrompt(supplementalOxygen, oxygenPromptValid);
        return supplementalOxygen;
    }

    // EFFECTS: prompts user for AVPU score and validates the user input is correct,
    // returns true if the patient is alert, returns false if not.
    private boolean readAvpuScore(boolean avpuScore, boolean avpuPromptValid) {
        System.out.println(
                "Please enter a if the patient is alert, or b if the patient "
                        + "only responds to verbal/painful stimuli or not at all.");

        avpuScore = validateUserAvpuPrompt(avpuScore, avpuPromptValid);
        return avpuScore;
    }

    // EFFECTS: returns true if the user enters a valid input stating that the
    // patient is on supplemental oxygen.
    // Returns false if the user enters that the patient is not on supplemental
    // oxygen. Verifies that the user has entered a valid key regarding the
    // supplemental oxygen status.
    private boolean validateUserOxygenPrompt(boolean supplementalOxygen, boolean oxygenPromptValid) {
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
        return supplementalOxygen;
    }

    // EFFECTS: returns true if the user enters a valid input stating that the
    // patient is alert.
    // Returns false if the user enters that the patient is not alert.
    // Verifies that the user has entered a valid key regarding the
    // AVPU status.
    private boolean validateUserAvpuPrompt(boolean avpuScore, boolean avpuPromptValid) {
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
        return avpuScore;
    }

    // EFFECTS: Determines if recently added vitals have a score above 5, if so this
    // method asks the user if these vitals are the patients baseline or not. If the
    // user answers yes no NEWS alert will fire, if the user answers no the NEWS
    // alert will go off.
    private void determineNewsScoreBaseline(boolean baselinePrompt, Vitals vitalRecord) {
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
                            "PLEASE ESCALATE CARE FOR THIS PATIENT, "
                                    + "THIS NEWS SCORE MAY INDICATE SEPSIS OR DETERIORATING PATIENT STATUS");
                    System.out.println(
                            "========================================================="
                                    + "================================================");
                    System.out.println();
                } else {
                    System.out.println("Please enter a valid answer y/n regarding patients baseline status.");
                }
            }
        }
    }

    // EFFECTS: prints the NEWS score for the entered vitals reading, and prints
    // verification that the vitals have been added to the chart
    private void printNewsScoreAndConfirmation(Patient patient, Vitals vitalRecord) {
        System.out.println(patient.getFirstName() + " " + patient.getLastName() + "s NEWS score for this recording: "
                + vitalRecord.getNewsScore());
        System.out.println();
        System.out.println("You have succesfully added vitals to " + patient.getFirstName() + " "
                + patient.getLastName() + "s chart.");
        System.out.println();
    }

    // EFFECTS: reads user input to first choose a patient based on patient id (if
    // the id doesnt exist the user is redirected to the main menu and told so).
    // If the id does exist a list of all the patients vital readings will be
    // printed to the console. Will also print a notice to the console if the
    // patient has not had any vitals recorded yet.
    public void getListOfPatientVitals() {
        int id;
        System.out.println("Please enter the patients id");
        id = input.nextInt();
        boolean patientSearch = searchForPatientInChart(id);

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

        printVitalsList(patient);

        if (patient.getVitalList().size() == 0) {
            System.out.println("There are no vitals recorded for this patient yet.");
            System.out.println();
        }
    }

    // EFFECTS: prints all the recorded vitals for a patient
    private void printVitalsList(Patient patient) {
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
            System.out.println();
            i++;
        }
    }

    // EFFECTS: returns true if the patient id exists in the chart and returns false
    // if the patient id does not exist in the chart
    public Boolean searchForPatientInChart(int id) {
        for (Patient patient : chart.getChartList()) {
            if (patient.getId() == id) {
                return true;
            }
        }
        return false;
    }

    // EFFECTS: saves the chart to a file, catches FileNotFoundException, will
    // notify if unable to write to JSON_STORE file

    private void saveChart() {
        try {
            jsonWriter.open();
            jsonWriter.write(chart);
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
