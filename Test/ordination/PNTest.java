package ordination;

import controller.Controller;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class PNTest {
    Controller controller = Controller.getController();

    @Test
    void givDosis() {
        Patient patient1 = Controller.getController().opretPatient("Victor", "navn", 24);
        Laegemiddel laegemiddel1 = Controller.getController().opretLaegemiddel("Janick",10,
                20,30,"enhed");
        PN pn = controller.opretPNOrdination(LocalDate.of(2025,01,1),
                LocalDate.of(2025,01,8),patient1,laegemiddel1,20);
        assertEquals(true,pn.givDosis(LocalDate.of(2025,01,6)));
        assertEquals(false,pn.givDosis(LocalDate.of(2025,03,01)));
    }

    @Test
    void doegnDosis() {
        Patient patient1 = Controller.getController().opretPatient("Victor", "navn", 24);
        Laegemiddel laegemiddel1 = Controller.getController().opretLaegemiddel("Janick",10,
                20,30,"enhed");
        PN pn = controller.opretPNOrdination(LocalDate.of(2025,01,1),
                LocalDate.of(2025,01,8),patient1,laegemiddel1,10);
        pn.givDosis(LocalDate.of(2025,01,4));
        pn.givDosis(LocalDate.of(2025,01,6));
        pn.givDosis(LocalDate.of(2025,01,8));
        assertEquals(6,pn.doegnDosis());
    }

    @Test
    void samletDosis() {
        Patient patient1 = Controller.getController().opretPatient("Victor", "navn", 24);
        Laegemiddel laegemiddel1 = Controller.getController().opretLaegemiddel("Janick",10,
                20,30,"enhed");
        PN pn = controller.opretPNOrdination(LocalDate.of(2025,01,1),
                LocalDate.of(2025,01,8),patient1,laegemiddel1,10);
        pn.givDosis(LocalDate.of(2025,01,4));
        pn.givDosis(LocalDate.of(2025,01,6));
        pn.givDosis(LocalDate.of(2025,01,8));
        assertEquals(30,pn.samletDosis());
    }

    @Test
    void getAntalGangeGivet() {
        Patient patient1 = Controller.getController().opretPatient("Victor", "navn", 24);
        Laegemiddel laegemiddel1 = Controller.getController().opretLaegemiddel("Janick",10,
                20,30,"enhed");
        PN pn = controller.opretPNOrdination(LocalDate.of(2025,01,1),
                LocalDate.of(2025,01,8),patient1,laegemiddel1,10);
        pn.givDosis(LocalDate.of(2025,01,4));
        pn.givDosis(LocalDate.of(2025,01,6));
        pn.givDosis(LocalDate.of(2025,01,8));
        assertEquals(3,pn.getAntalGangeGivet());
    }

    @Test
    void getAntalEnheder() {
        Patient patient1 = Controller.getController().opretPatient("Victor", "navn", 24);
        Laegemiddel laegemiddel1 = Controller.getController().opretLaegemiddel("Janick",10,
                20,30,"enhed");
        PN pn = controller.opretPNOrdination(LocalDate.of(2025,01,1),
                LocalDate.of(2025,01,8),patient1,laegemiddel1,10);
        pn.givDosis(LocalDate.of(2025,01,4));
        pn.givDosis(LocalDate.of(2025,01,6));
        pn.givDosis(LocalDate.of(2025,01,8));
        assertEquals(10,pn.getAntalEnheder());
    }
}