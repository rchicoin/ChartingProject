package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;
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
            assertEquals("first1", patients.get(0).getFirstName()); //WILL NEED TO UPDATE NAME INPUT
            assertEquals("last1", patients.get(0).getLastName()); // WILL NEED TO UPDATE 
            assertEquals(1, patients.get(0).getId()); // WILL NEED TO UPDATE 


            assertEquals(2, patients.get(1).getVitalList().size());
            assertEquals("first2", patients.get(1).getFirstName());
            assertEquals("last2", patients.get(1).getLastName());
            assertEquals(2, patients.get(1).getId());

            checkVital(patients.get(0).getVitalList().get(0), 12, 96, false, 36.5, 120, 80, false, 60);
            checkVital(patients.get(0).getVitalList().get(1), 14, 98, true, 36.7, 130, 90, false, 70);
            assertEquals(3,patients.get(0).getVitalList().get(0).getNewsScore());
            assertEquals(5,patients.get(0).getVitalList().get(1).getNewsScore());
            


            checkVital(patients.get(1).getVitalList().get(0), 13, 97, false, 36.6, 125, 85, true, 65);
            checkVital(patients.get(1).getVitalList().get(1), 15, 99, true, 36.8, 135, 95, true, 75);
            assertEquals(0,patients.get(1).getVitalList().get(0).getNewsScore());
            assertEquals(2,patients.get(1).getVitalList().get(1).getNewsScore());
            

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }



}
