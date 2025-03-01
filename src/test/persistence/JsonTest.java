package persistence;

import static org.junit.Assert.assertEquals;
import model.Vitals;

// Inspiration for this code was used from: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonTest {

    double delta = 0.05;

    protected void checkVital(Vitals vital, int respRate, int spo2, boolean supplementalOxygen, double temperature,
            int systolicBp,
            int diastolicBp, boolean avpuScore, int heartRate) {

        assertEquals(respRate, vital.getRespRate());
        assertEquals(spo2, vital.getSpo2());
        assertEquals(supplementalOxygen, vital.getSupplementalOxygen());
        assertEquals(temperature, vital.getTemperature(), delta);
        assertEquals(systolicBp, vital.getSystolicBp());
        assertEquals(diastolicBp, vital.getDiastolicBp());
        assertEquals(avpuScore, vital.getAvpu());
        assertEquals(heartRate, vital.getHeartRate());
    }

}
