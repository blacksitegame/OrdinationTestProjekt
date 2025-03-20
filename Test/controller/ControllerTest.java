package controller;

import ordination.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
class ControllerTest {

    @Test
    void anbefaletDosisPrDoegn() {
        Laegemiddel laegemiddel = new Laegemiddel("laegemiddel", 1, 2, 3, "enhed");
        Patient patient = new Patient("cpr", "navn", 24);
        assertEquals(1, Controller.getController().anbefaletDosisPrDoegn(patient, laegemiddel));
        patient.setVaegt(25);
        assertEquals(2, Controller.getController().anbefaletDosisPrDoegn(patient, laegemiddel));
        patient.setVaegt(121);
        assertEquals(3, Controller.getController().anbefaletDosisPrDoegn(patient, laegemiddel));
        patient.setVaegt(-64);
        assertEquals(0, Controller.getController().anbefaletDosisPrDoegn(patient, laegemiddel));


    }

    @Test
    void antalOrdinationerPrVaegtPrLaegemiddel() {
        Laegemiddel laegemiddel1 = Controller.getController().opretLaegemiddel("Janick",10,
                20,30,"enhed");
        Laegemiddel laegemiddel2 = Controller.getController().opretLaegemiddel("Janick",10,
                20,30,"enhed");

        Patient patient1 = Controller.getController().opretPatient("Victor", "navn", 24);
        Patient patient2 = Controller.getController().opretPatient("Janick", "navn", 60);
        Patient patient3 = Controller.getController().opretPatient("Sara", "navn", 121);

        Controller.getController().opretPNOrdination(LocalDate.of(2024,01,1),
                LocalDate.of(2024,01,8),patient1,laegemiddel1,20);
        Controller.getController().opretPNOrdination(LocalDate.of(2024,01,1),
                LocalDate.of(2024,01,8),patient2,laegemiddel1,20);
        Controller.getController().opretPNOrdination(LocalDate.of(2024,01,1),
                LocalDate.of(2024,01,8),patient3,laegemiddel2,20);

        assertEquals(0,Controller.getController().antalOrdinationerPrVaegtPrLaegemiddel(0,23,laegemiddel1));
        assertEquals(1,Controller.getController().antalOrdinationerPrVaegtPrLaegemiddel(0,24,laegemiddel1));
        assertEquals(2,Controller.getController().antalOrdinationerPrVaegtPrLaegemiddel(0,61,laegemiddel1));
        assertEquals(0,Controller.getController().antalOrdinationerPrVaegtPrLaegemiddel(0,120,laegemiddel2));
        assertEquals(1,Controller.getController().antalOrdinationerPrVaegtPrLaegemiddel(0,121,laegemiddel2));
    }

    @Test
    void opretPNOrdination() {
        Patient patient1 = Controller.getController().opretPatient("Victor", "navn", 24);
        Laegemiddel laegemiddel1 = Controller.getController().opretLaegemiddel("Janick",10,
                20,30,"enhed");
        PN pn = Controller.getController().opretPNOrdination(LocalDate.of(2025,01,1),
                LocalDate.of(2025,01,8), patient1,laegemiddel1,20);
        for (Ordination ordination : patient1.getOrdinationer()) {
            assertEquals(pn,ordination);
        }
    }

    @Test
    void opretDagligFastOrdination() {
        Patient patient1 = Controller.getController().opretPatient("Victor", "navn", 24);
        Laegemiddel laegemiddel1 = Controller.getController().opretLaegemiddel("Janick",10,
                20,30,"enhed");
        DagligFast dagligFast = Controller.getController().opretDagligFastOrdination(LocalDate.of(2025,01,1),
                LocalDate.of(2025,01,8),patient1,laegemiddel1,10,10,10,10);
        for (Ordination ordination : patient1.getOrdinationer()) {
            assertEquals(dagligFast,ordination);
        }
    }

    @Test
    void opretDagligSkaevOrdination() {
        LocalTime[] localtimeArr = {
                LocalTime.of(06,00),
                LocalTime.of(12,00),
                LocalTime.of(18,00),
                LocalTime.of(00,00)
        };

        double[] dosisArr = {10,10,10,10};

        Patient patient1 = Controller.getController().opretPatient("Victor", "navn", 24);
        Laegemiddel laegemiddel1 = Controller.getController().opretLaegemiddel("Janick",10,
                20,30,"enhed");
        DagligSkaev dagligSkaev = Controller.getController().opretDagligSkaevOrdination(LocalDate.of(2025,01,1),
                LocalDate.of(2025,01,8),patient1,laegemiddel1,localtimeArr,dosisArr);
        for (Ordination ordination : patient1.getOrdinationer()) {
            assertEquals(dagligSkaev,ordination);
        }
    }
}