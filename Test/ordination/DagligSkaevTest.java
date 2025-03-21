package ordination;

import controller.Controller;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class DagligSkaevTest {
    Controller controller = Controller.getController();

    @Test
    void opretDosis() {
        LocalTime[] localTimes = {
                LocalTime.of(06,00),
                LocalTime.of(12,00),
                LocalTime.of(14,00),
                LocalTime.of(18,00),
                LocalTime.of(20,00)
        };

        Patient patient1 = Controller.getController().opretPatient("Victor", "navn", 24);
        Laegemiddel laegemiddel1 = Controller.getController().opretLaegemiddel("Janick",10,
                20,30,"enhed");
        DagligSkaev dagligSkaev = controller.opretDagligSkaevOrdination(LocalDate.of(2025,01,1),
                LocalDate.of(2025,01,8),patient1,laegemiddel1,localTimes,new double[]{10,10,10,10,10});
        dagligSkaev.opretDosis(LocalTime.of(06,00),10);

        assertEquals(10,dagligSkaev.getDoser().get(0).getAntal());
    }

    @Test
    void getDoser() {
        LocalTime[] localTimes = {
                LocalTime.of(06,00),
                LocalTime.of(12,00),
                LocalTime.of(14,00),
                LocalTime.of(18,00),
                LocalTime.of(20,00)
        };

        Patient patient1 = Controller.getController().opretPatient("Victor", "navn", 24);
        Laegemiddel laegemiddel1 = Controller.getController().opretLaegemiddel("Janick",10,
                20,30,"enhed");
        DagligSkaev dagligSkaev = controller.opretDagligSkaevOrdination(LocalDate.of(2025,01,1),
                LocalDate.of(2025,01,8),patient1,laegemiddel1,localTimes,new double[]{10,10,10,10,10});
        dagligSkaev.opretDosis(LocalTime.of(06,00),10);

        assertEquals(10,dagligSkaev.getDoser().get(0).getAntal());
    }

    @Test
    void samletDosis() {
        LocalTime[] localTimes = {
                LocalTime.of(06,00),
                LocalTime.of(12,00),
                LocalTime.of(14,00),
                LocalTime.of(18,00),
                LocalTime.of(20,00)
        };

        Patient patient1 = Controller.getController().opretPatient("Victor", "navn", 24);
        Laegemiddel laegemiddel1 = Controller.getController().opretLaegemiddel("Janick",10,
                20,30,"enhed");
        DagligSkaev dagligSkaev = controller.opretDagligSkaevOrdination(LocalDate.of(2025,01,1),
                LocalDate.of(2025,01,8),patient1,laegemiddel1,localTimes,new double[]{10,10,10,10,10});

        assertEquals(400, dagligSkaev.samletDosis());


    }

    @Test
    void doegnDosis() {
        LocalTime[] localTimes = {
                LocalTime.of(06,00),
                LocalTime.of(12,00),
                LocalTime.of(14,00),
                LocalTime.of(18,00),
                LocalTime.of(20,00)
        };

        Patient patient1 = Controller.getController().opretPatient("Victor", "navn", 24);
        Laegemiddel laegemiddel1 = Controller.getController().opretLaegemiddel("Janick",10,
                20,30,"enhed");
        DagligSkaev dagligSkaev = controller.opretDagligSkaevOrdination(LocalDate.of(2025,01,1),
                LocalDate.of(2025,01,8),patient1,laegemiddel1,localTimes,new double[]{10,10,10,10,10});

        assertEquals(50, dagligSkaev.doegnDosis());
    }
}