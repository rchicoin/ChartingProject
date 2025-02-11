package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestChart {

    Patient testPatient;
    Patient testPatient2;
    Vitals testVitals;
    Chart testChart;

    @BeforeEach
    void runBefore() {
        testPatient = new Patient("testName", "testLastName", 4);
        testPatient2 = new Patient("second", "patient", 5);
        testVitals = new Vitals(12, 97, false, 36.5, 120, 80, true, 60);
        testChart = new Chart();
    }

    @Test
    void testConstructor() {
        assertEquals(0, testChart.getChartList().size());
    }

    @Test
    void testAddPatient() {
        assertEquals(0, testChart.getChartList().size());

        testChart.addPatient(testPatient);
        assertEquals(1, testChart.getChartList().size());

        testChart.addPatient(testPatient);
        assertEquals(2, testChart.getChartList().size());
    }

    @Test
    void testGetPatient() {
        testChart.addPatient(testPatient);
        assertEquals(testPatient, testChart.getPatient(4));
    }

    @Test
    void testGetPatientNotExist() {
        testChart.addPatient(testPatient);
        assertEquals(null, testChart.getPatient(10));
    }

    @Test
    void testGetPatientSecondInList() {
        testChart.addPatient(testPatient);
        testChart.addPatient(testPatient2);
        assertEquals(testPatient2, testChart.getPatient(5));
    }

}
