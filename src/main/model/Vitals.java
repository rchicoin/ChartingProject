package model;

import org.json.JSONObject;

import persistence.Writable;

// Represents the recording of one set of vitals for a patient. 
public class Vitals implements Writable {

    private int respRate;
    private int spo2;
    private boolean supplementalOxygen;
    private double temperature;
    private int systolicBp;
    private int diastolicBp;
    private boolean avpuScore;
    private int heartRate;
    private int newsScore;

    // REQUIRES: resp rate >= 0, spo2 >=0 and =<100, temperature must be greater
    // than or equal to 0, systolicBp >=0, diastolicBp>=0.
    // EFFECTS: assigns parameter values to respective vital signs as seen above.
    // calculates the NEWS score.
    public Vitals(int respRate, int spo2, boolean supplementalOxygen, double temperature, int systolicBp,
            int diastolicBp, boolean avpuScore, int heartRate) {
        this.respRate = respRate;
        this.spo2 = spo2;
        this.supplementalOxygen = supplementalOxygen;
        this.temperature = temperature;
        this.systolicBp = systolicBp;
        this.diastolicBp = diastolicBp;
        this.avpuScore = avpuScore;
        this.heartRate = heartRate;
        this.newsScore = calculateRespRate() + calculateSpo2() + calculateSupplementalOxygen() + calculateTemperature()
                + calculateSystolicBp() + calculateAvpuScore() + calculateHeartRate();

    }

    // EFFECTS: calculates the newsScore
    public int calculateNewsScore(int respRate, int spo2, boolean supplementalOxygen, double temperature,
            int systolicBp,
            int diastolicBp, boolean avpuScore, int heartRate) {
        int score = calculateRespRate() + calculateSpo2() + calculateSupplementalOxygen() + calculateTemperature()
                + calculateSystolicBp() + calculateAvpuScore() + calculateHeartRate();
        return score;

    }

    // REQUIRES: respRate must be >=0.
    // EFFECTS: returns the respRate contribution to the overall NEWS score
    public int calculateRespRate() {
        if (respRate <= 8) {
            return 3;
        } else if (respRate <= 11) {
            return 1;
        } else if (respRate <= 20) {
            return 0;
        } else if (respRate <= 24) {
            return 2;
        } else {
            return 3;
        }
    }

    // REQUIRES: spo2 must be >=0 and <=100.
    // EFFECTS: returns the spo2 contribution to the overall NEWS score
    public int calculateSpo2() {
        if (spo2 <= 91) {
            return 3;
        } else if (spo2 <= 93) {
            return 2;
        } else if (spo2 <= 95) {
            return 1;
        } else {
            return 0;
        }
    }

    // EFFECTS: returns the supplemental o2 contribution to the overall NEWS score
    public int calculateSupplementalOxygen() {
        if (supplementalOxygen) {
            return 2;
        } else {
            return 0;
        }
    }

    // REQUIRES: temperature must be >=0
    // EFFECTS: returns the temperature contribution to the overall NEWS score
    public int calculateTemperature() {

        if (temperature <= 35) {
            return 3;
        } else if (temperature <= 36) {
            return 1;
        } else if (temperature <= 38) {
            return 0;
        } else if (temperature <= 39) {
            return 1;
        } else {
            return 2;
        }
    }

    // REQUIRES: systolicBp must be >=0.
    // EFFECTS: returns the systolicBp contribution to the overall NEWS score
    public int calculateSystolicBp() {
        if (systolicBp <= 90) {
            return 3;
        } else if (systolicBp <= 100) {
            return 2;
        } else if (systolicBp <= 110) {
            return 1;
        } else if (systolicBp <= 219) {
            return 0;
        } else {
            return 3;
        }
    }

    // REQUIRES: heartRate must be >=0.
    // EFFECTS: returns the systolicBp contribution to the overall NEWS score
    public int calculateHeartRate() {
        if (heartRate <= 40) {
            return 3;
        } else if (heartRate <= 50) {
            return 1;
        } else if (heartRate <= 90) {
            return 0;
        } else if (heartRate <= 110) {
            return 1;
        } else if (heartRate <= 130) {
            return 2;
        } else {
            return 3;
        }
    }

    // EFFECTS: returns the avpuScore contribution to the overall NEWS score
    public int calculateAvpuScore() {
        if (avpuScore) {
            return 0;
        } else {
            return 3;
        }
    }

    // SETTERS

    public void setRespRate(int respRate) {
        this.respRate = respRate;

    }

    public void setSpo2(int spo2) {
        this.spo2 = spo2;

    }

    public void setSupplementalOxygen(boolean supplementalOxygen) {
        this.supplementalOxygen = supplementalOxygen;

    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;

    }

    public void setSystolicBp(int systolicBp) {

        this.systolicBp = systolicBp;
    }

    public void setDiastolicBp(int diastolicBp) {

        this.diastolicBp = diastolicBp;
    }

    public void setAvpu(boolean avpuScore) {
        this.avpuScore = avpuScore;

    }

    public void setHearRate(int heartRate) {
        this.heartRate = heartRate;
    }

    // GETTERS

    public int getRespRate() {

        return respRate;
    }

    public int getSpo2() {
        return spo2;
    }

    public boolean getSupplementalOxygen() {
        return supplementalOxygen;
    }

    public double getTemperature() {
        return temperature;
    }

    public int getSystolicBp() {
        return systolicBp;
    }

    public int getDiastolicBp() {
        return diastolicBp;
    }

    public boolean getAvpu() {
        return avpuScore;
    }

    public int getNewsScore() {
        return newsScore;
    }

    public int getHeartRate() {
        return heartRate;
    }

    // EFFECTS: converts vitals data into a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        json.put("respRate", respRate);
        json.put("spo2", spo2);
        json.put("supplementalOxygen", supplementalOxygen);
        json.put("temperature", temperature);
        json.put("systolicBp", systolicBp);
        json.put("diastolicBp", respRate);
        json.put("avpuScore", avpuScore);
        json.put("heartRate", heartRate);
        json.put("newsScore", newsScore);
        
        return json;
    }
}
