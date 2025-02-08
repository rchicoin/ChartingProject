package ui;
import model.*;
import java.util.*;
// User interface for patient vital recording 
public class VitalsApp {

    private Chart chart;
    private Scanner input; 

    // EFFECTS: initiates the ability to add patients and vitals to the chart
    public VitalsApp() {
        runVitalsApp();
    }

    // MODIFIES: this
    // EFFECTS: processes user input on the main menu
    public void runVitalsApp() {
        chart= new Chart();
        input = new Scanner(System.in);
        boolean selection = true; 
        String command= null; 

        while (selection){
            System.out.println("Please choose from the following options:");
            System.out.println("Enter \"a\" to enter a new patient in the chart.");
            System.out.println("Enter \"b\" to add vitals for a patient.");
            System.out.println("Enter \"c\" to get a list of recorded vitals for a patient.");
            System.out.println("Enter \"d\" to exit.");

            command= input.next();
            command= command.toLowerCase();

            if (command.equals("d")){
                selection= false;
            }else {
                readInput(command);
            }

        }
    }

    // MODIFIES: this
    // EFFECTS: performs the corresponding task related to the chosen value on the
    // main menu
    public void readInput(String command) {

    }

    // MODIFIES: this, Chart
    // EFFECTS: reads user input to make a new patient
    public void makePatient() {

    }

    // MODIFIES: this, Patient
    // EFFECTS: reads user input to first choose a patient, and then reads user
    // input for vitals
    public void addVitalsToPatient() {

    }

    // EFFECTS: reads user input to first choose a patient, and then prints off a
    // list of all the patients vital readings
    public void getListOfPatientVitals() {

    }
}
