package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;


import model.Chart;
import model.Patient;
import model.Vitals;

// Inspiration for this code was used from: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public class TestJsonWriter extends JsonTest {

    @Test
    public void testWriterInvalidFile() {
        try {
            Chart chart = new Chart();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testWriterEmptyChart() {
        try {
            Chart chart = new Chart();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyChart.json");
            writer.open();
            writer.write(chart);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyChart.json");
            chart = reader.read();
            assertEquals(0, chart.getChartList().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testWriterGeneralWorkroom() {
        try {
            Chart chart = new Chart();
            Patient patient1= new Patient ("first1", "last1", 1);
            Patient patient2= new Patient ("first2", "last2", 2);

            Vitals vital1 = new Vitals(12, 96, false, 36.5, 120, 80, false, 60);
            Vitals vital2 = new Vitals(13, 97, false, 36.6, 125, 85, true, 65);
            Vitals vital3 = new Vitals(14, 98, true, 36.7, 130, 90, false, 70);
            Vitals vital4 = new Vitals(15, 99, true, 36.8, 135, 95, true, 75);

            patient1.addVitals(vital1);
            patient1.addVitals(vital3);

            patient2.addVitals(vital2);
            patient2.addVitals(vital4);

            chart.addPatient(patient1);
            chart.addPatient(patient2);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralChart.json");
            writer.open();
            writer.write(chart);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralChart.json");
            chart = reader.read();
            
            List<Patient> patients = chart.getChartList();
            assertEquals(2, patients.size());
            assertEquals("first1", patients.get(0).getFirstName());
            assertEquals("last1", patients.get(0).getLastName());
            assertEquals(1, patients.get(0).getId());

            assertEquals("first2", patients.get(1).getFirstName());
            assertEquals("last2", patients.get(1).getLastName());
            assertEquals(2, patients.get(1).getId());

            checkVital(patients.get(0).getVitalList().get(0), 12, 96, false, 36.5, 120, 80, false, 60);
            checkVital(patients.get(0).getVitalList().get(1), 14, 98, true, 36.7, 130, 90, false, 70);
            
            checkVital(patients.get(1).getVitalList().get(0), 13, 97, false, 36.6, 125, 85, true, 65);
            checkVital(patients.get(1).getVitalList().get(1), 15, 99, true, 36.8, 135, 95, true, 75);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
