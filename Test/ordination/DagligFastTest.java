package ordination;

import controller.Controller;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class DagligFastTest {

    @Test
    void opretDosis() {
        Patient patient1 = Controller.getController().opretPatient("Victor", "navn", 24);
        Laegemiddel laegemiddel1 = Controller.getController().opretLaegemiddel("Janick",10,
                20,30,"enhed");
        DagligFast dagligFast = Controller.getController().opretDagligFastOrdination(LocalDate.of(2025,01,1),
                LocalDate.of(2025,01,8),patient1,laegemiddel1,0,0,0,0);
        Dosis dosis = dagligFast.opretDosis(LocalTime.of(06,00),20);
        for (Dosis dosis1 : dagligFast.getDoser()) {
            if (dosis1!=null){
                assertEquals(dosis,dosis1);
            }
        }
    }

    @Test
    void getDoser() {
        Patient patient1 = Controller.getController().opretPatient("Victor", "navn", 24);
        Laegemiddel laegemiddel1 = Controller.getController().opretLaegemiddel("Janick",10,
                20,30,"enhed");
        DagligFast dagligFast = Controller.getController().opretDagligFastOrdination(LocalDate.of(2025,01,1),
                LocalDate.of(2025,01,8),patient1,laegemiddel1,10,10,10,10);
        Dosis dosis = dagligFast.opretDosis(LocalTime.of(06,00),20);
        for (Dosis dosis1 : dagligFast.getDoser()) {
            if (dosis1!=null){
                assertEquals(dosis,dosis1);
            }
        }
    }

    @Test
    void samletDosis() {
        Patient patient1 = Controller.getController().opretPatient("Victor", "navn", 24);
        Laegemiddel laegemiddel1 = Controller.getController().opretLaegemiddel("Janick",10,
                20,30,"enhed");
        DagligFast dagligFast = Controller.getController().opretDagligFastOrdination(LocalDate.of(2025,01,1),
                LocalDate.of(2025,01,4),patient1,laegemiddel1,10,10,10,10);
        assertEquals(160,dagligFast.samletDosis());
    }

    @Test
    void doegnDosis() {
        Patient patient1 = Controller.getController().opretPatient("Victor", "navn", 24);
        Laegemiddel laegemiddel1 = Controller.getController().opretLaegemiddel("Janick",10,
                20,30,"enhed");
        DagligFast dagligFast = Controller.getController().opretDagligFastOrdination(LocalDate.of(2025,01,1),
                LocalDate.of(2025,01,4),patient1,laegemiddel1,10,10,10,10);
        assertEquals(40,dagligFast.doegnDosis());
    }
}