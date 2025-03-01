package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import java.io.IOException;
import java.util.List;

import org.junit.Test;
import model.Chart;
import model.Patient;

// Inspiration for this code was used from: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public class TestJsonReader extends JsonTest {


    @Test
    public void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Chart chart = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testReaderEmptyChart() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyChart.json");
        try {
            Chart chart = reader.read();
            assertEquals(0, chart.getChartList().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    public void testReaderGeneralChart() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralChart.json");
        try {
            Chart chart = reader.read();
            List<Patient> patients = chart.getChartList();
            assertEquals(2, patients.size());

            assertEquals(2, patients.get(0).getVitalList().size());
            assertEquals("", patients.get(0).getFirstName()); //WILL NEED TO UPDATE NAME INPUT
            assertEquals("", patients.get(0).getLastName()); // WILL NEED TO UPDATE 
            assertEquals(1, patients.get(0).getId()); // WILL NEED TO UPDATE 


            assertEquals(2, patients.get(1).getVitalList().size());
            assertEquals("", patients.get(1).getFirstName());
            assertEquals("", patients.get(1).getLastName());
            assertEquals(1, patients.get(1).getId());

            checkVital(patients.get(0).getVitalList().get(0), 0, 0, false, 0, 0, 0, false, 0);
            checkVital(patients.get(0).getVitalList().get(1), 0, 0, false, 0, 0, 0, false, 0);
            assertEquals(1,patients.get(0).getVitalList().get(0).getNewsScore());
            assertEquals(1,patients.get(0).getVitalList().get(1).getNewsScore());
            


            checkVital(patients.get(1).getVitalList().get(0), 0, 0, false, 0, 0, 0, false, 0);
            checkVital(patients.get(1).getVitalList().get(1), 0, 0, false, 0, 0, 0, false, 0);
            assertEquals(1,patients.get(1).getVitalList().get(0).getNewsScore());
            assertEquals(1,patients.get(1).getVitalList().get(1).getNewsScore());
            

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }



}
