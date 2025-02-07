package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class TestPatient {

    Patient testPatient;
    Vitals testVitals;

    @BeforeEach
    void runBefore(){
        testPatient = new Patient("testName", "testLastName");
        testVitals=  new Vitals (12, 97, false, 36.5, 120, 80, true);
    }
    
    @Test 
    void testConstructor(){
        assertEquals("testName", testPatient.getFirstName());
        assertEquals("testLastName", testPatient.getLastName());
        assertEquals(0, testPatient.getVitalList().size());
    }
    
    @Test 
    void testAddVitals(){
        assertEquals(0, testPatient.getVitalList().size());
        testPatient.addVitals(testVitals);
        assertEquals(1, testPatient.getVitalList().size());
    }

}
