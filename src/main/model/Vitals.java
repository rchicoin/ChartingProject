package model;

// Represents the recording of one set of vitals for a patient. 
public class Vitals {

    private int respRate; // respiratory rate
    private int spo2; // oxygen saturation levels in the blood
    private boolean supplementalOxygen; // represents if the patient is on supplemental oxygen or not
    private double temperature; // temperature
    private int systolicBp; // systolic blood pressure
    private int diastolicBp; // diastolic blood pressure
    private boolean avpuScore; // AVPU score is patient awake, do they respond to verbal or painful stimuli, or
                               // none at all
    private int newsScore; // final calculated NEWS score based on vitals

    // REQUIRES: resp rate >= 0, spo2 >=0 and =<100, temperature must be greater
    // than or equal to 0, systolicBp >=0, diastolicBp>=0.
    // EFFECTS: assigns parameter values to respective vital signs as seen above.
    // calculates the NEWS score.

    public Vitals(int respRate, int spo2, boolean supplementalOxygen, double temperature, int systolicBp,
            int diastolicBp, boolean avpuScore) {

    }

    // REQUIRES: respRate must be >=0.
    // MODIFIES: this
    // EFFECTS: returns the respRate contribution to the overall NEWS score
    public int calculateRespRate() {
        return 0;
    }

    // REQUIRES: spo2 must be >=0 and <=100.
    // MODIFIES: this
    // EFFECTS: returns the spo2 contribution to the overall NEWS score
    public int calculateSpo2() {
        return 0;
    }

    // MODIFIES: this
    // EFFECTS: returns the supplemental o2 contribution to the overall NEWS score
    public int calculateSupplementalOxygen() {
        return 0;
    }

    // REQUIRES: temperature must be >=0
    // MODIFIES: this
    // EFFECTS: returns the temperature contribution to the overall NEWS score
    public int calculateTemperature() {
        return 0;
    }

    // REQUIRES: systolicBp must be >=0.
    // MODIFIES: this
    // EFFECTS: returns the systolicBp contribution to the overall NEWS score
    public int calculateSystolicBp() {
        return 0;
    }

    // MODIFIES: this
    // EFFECTS: returns the avpuScore contribution to the overall NEWS score
    public int calculateAvpuScore() {
        return 0;
    }

    // SETTERS

    public void setRespRate(int respRate) {

    }

    public void setSpo2(int spo2) {

    }

    public void setSupplementalOxygen(boolean supplementalOxygen) {

    }

    public void setTemperature(double temperature) {

    }

    public void setSystolicBp(int systolic) {

    }

    public void setDiastolicBp(int diastolic) {

    }

    public void setAvpu(boolean avpuScore) {

    }

    // GETTERS

    public int getRespRate() {

        return 0;
    }

    public int getSpo2() {
        return 0;
    }

    public boolean getSupplementalOxygen() {
        return false;
    }

    public double getTemperature() {
        return 0;
    }

    public int getSystolicBp() {
        return 0;
    }

    public int getDiastolicBp() {
        return 0;
    }

    public boolean getAvpu() {
        return false;
    }

    public int getNewsScore() {
        return 0;
    }

}
