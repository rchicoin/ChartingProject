package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestVitals {

    Vitals testVitals;

    @BeforeEach
    void runBefore() {
        testVitals = new Vitals(12, 97, false, 36.5, 120, 80, true, 60);
    }

    @Test
    void testConstructor() {
        assertEquals(12, testVitals.getRespRate());
        assertEquals(97, testVitals.getSpo2());
        assertEquals(false, testVitals.getSupplementalOxygen());
        assertEquals(36.5, testVitals.getTemperature());
        assertEquals(120, testVitals.getSystolicBp());
        assertEquals(80, testVitals.getDiastolicBp());
        assertEquals(true, testVitals.getAvpu());
        assertEquals(0, testVitals.getNewsScore());
        assertEquals(60, testVitals.getHeartRate());
    }

    @Test
    void testCalculateRespRateAllRanges() {
        assertEquals(0, testVitals.calculateRespRate());

        testVitals.setRespRate(0);
        assertEquals(3, testVitals.calculateRespRate());

        testVitals.setRespRate(7);
        assertEquals(3, testVitals.calculateRespRate());

        testVitals.setRespRate(8);
        assertEquals(3, testVitals.calculateRespRate());

        testVitals.setRespRate(9);
        assertEquals(1, testVitals.calculateRespRate());

        testVitals.setRespRate(11);
        assertEquals(1, testVitals.calculateRespRate());

        testVitals.setRespRate(12);
        assertEquals(0, testVitals.calculateRespRate());

        testVitals.setRespRate(20);
        assertEquals(0, testVitals.calculateRespRate());

        testVitals.setRespRate(21);
        assertEquals(2, testVitals.calculateRespRate());

        testVitals.setRespRate(24);
        assertEquals(2, testVitals.calculateRespRate());

        testVitals.setRespRate(25);
        assertEquals(3, testVitals.calculateRespRate());

        testVitals.setRespRate(35);
        assertEquals(3, testVitals.calculateRespRate());

    }

    @Test
    void testCalculateSpo2AllRanges() {
        assertEquals(0, testVitals.calculateSpo2());

        testVitals.setSpo2(0);
        assertEquals(3, testVitals.calculateSpo2());

        testVitals.setSpo2(90);
        assertEquals(3, testVitals.calculateSpo2());

        testVitals.setSpo2(91);
        assertEquals(3, testVitals.calculateSpo2());

        testVitals.setSpo2(92);
        assertEquals(2, testVitals.calculateSpo2());

        testVitals.setSpo2(93);
        assertEquals(2, testVitals.calculateSpo2());

        testVitals.setSpo2(94);
        assertEquals(1, testVitals.calculateSpo2());

        testVitals.setSpo2(95);
        assertEquals(1, testVitals.calculateSpo2());

        testVitals.setSpo2(96);
        assertEquals(0, testVitals.calculateSpo2());

        testVitals.setSpo2(100);
        assertEquals(0, testVitals.calculateSpo2());

    }

    @Test
    void testCalculateSupplementalOxygenAllRanges() {
        assertEquals(0, testVitals.calculateSupplementalOxygen());

        testVitals.setSupplementalOxygen(true);
        assertEquals(2, testVitals.calculateSupplementalOxygen());

    }

    @Test

    void testCalculateTemperatureAllRanges() {
        assertEquals(0, testVitals.calculateTemperature());

        testVitals.setTemperature(0);
        assertEquals(3, testVitals.calculateTemperature());

        testVitals.setTemperature(34);
        assertEquals(3, testVitals.calculateTemperature());

        testVitals.setTemperature(35);
        assertEquals(3, testVitals.calculateTemperature());

        testVitals.setTemperature(35.1);
        assertEquals(1, testVitals.calculateTemperature());

        testVitals.setTemperature(36);
        assertEquals(1, testVitals.calculateTemperature());

        testVitals.setTemperature(36.1);
        assertEquals(0, testVitals.calculateTemperature());

        testVitals.setTemperature(38);
        assertEquals(0, testVitals.calculateTemperature());

        testVitals.setTemperature(38.1);
        assertEquals(1, testVitals.calculateTemperature());

        testVitals.setTemperature(39);
        assertEquals(1, testVitals.calculateTemperature());

        testVitals.setTemperature(39.1);
        assertEquals(2, testVitals.calculateTemperature());

        testVitals.setTemperature(45);
        assertEquals(2, testVitals.calculateTemperature());

    }

    @Test
    void testCalculateSystolicBpAllRanges() {
        assertEquals(0, testVitals.calculateSystolicBp());

        testVitals.setSystolicBp(0);
        assertEquals(3, testVitals.calculateSystolicBp());
        
        testVitals.setSystolicBp(89);
        assertEquals(3, testVitals.calculateSystolicBp());

        testVitals.setSystolicBp(90);
        assertEquals(3, testVitals.calculateSystolicBp());

        testVitals.setSystolicBp(91);
        assertEquals(2, testVitals.calculateSystolicBp());

        testVitals.setSystolicBp(100);
        assertEquals(2, testVitals.calculateSystolicBp());

        testVitals.setSystolicBp(101);
        assertEquals(1, testVitals.calculateSystolicBp());

        testVitals.setSystolicBp(110);
        assertEquals(1, testVitals.calculateSystolicBp());

        testVitals.setSystolicBp(111);
        assertEquals(0, testVitals.calculateSystolicBp());

        testVitals.setSystolicBp(219);
        assertEquals(0, testVitals.calculateSystolicBp());

        testVitals.setSystolicBp(220);
        assertEquals(3, testVitals.calculateSystolicBp());

        testVitals.setSystolicBp(221);
        assertEquals(3, testVitals.calculateSystolicBp());

        testVitals.setSystolicBp(250);
        assertEquals(3, testVitals.calculateSystolicBp());

    }

    @Test
    void testCalculateAvpuScoreAllRanges() {
        assertEquals(0, testVitals.calculateTemperature());

        testVitals.setAvpu(false);
        assertEquals(3, testVitals.calculateAvpuScore());
    }

    @Test
    void testCalculateHeartRateAllRanges() {
        assertEquals(0, testVitals.calculateHeartRate());

        testVitals.setHearRate(0);
        assertEquals(3, testVitals.calculateHeartRate());

        testVitals.setHearRate(39);
        assertEquals(3, testVitals.calculateHeartRate());

        testVitals.setHearRate(40);
        assertEquals(3, testVitals.calculateHeartRate());

        testVitals.setHearRate(41);
        assertEquals(1, testVitals.calculateHeartRate());

        testVitals.setHearRate(50);
        assertEquals(1, testVitals.calculateHeartRate());

        testVitals.setHearRate(51);
        assertEquals(0, testVitals.calculateHeartRate());

        testVitals.setHearRate(90);
        assertEquals(0, testVitals.calculateHeartRate());

        testVitals.setHearRate(91);
        assertEquals(1, testVitals.calculateHeartRate());

        testVitals.setHearRate(110);
        assertEquals(1, testVitals.calculateHeartRate());

        testVitals.setHearRate(111);
        assertEquals(2, testVitals.calculateHeartRate());

        testVitals.setHearRate(130);
        assertEquals(2, testVitals.calculateHeartRate());

        testVitals.setHearRate(131);
        assertEquals(3, testVitals.calculateHeartRate());
    }

    @Test
    void testSetDiastolicBp() {
        testVitals.setDiastolicBp(85);
        assertEquals(85, testVitals.getDiastolicBp());
    }
}
