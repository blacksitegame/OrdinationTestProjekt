package ordination;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class OrdinationTest {
    Ordination ordination;

    @BeforeEach
    void setUp() {
        ordination = new DagligFast(LocalDate.of(2025, 01, 1), LocalDate.of(2025, 01, 8));
    }

    @Test
    void getStartDato() {
        LocalDate expected = (LocalDate.of(2025, 01, 01));
        LocalDate actual = ordination.getStartDato();
    }

    @Test
    void getSlutDato() {
        LocalDate expected = (LocalDate.of(2025, 01, 8));
        LocalDate actual = ordination.getSlutDato();
    }

    @Test
    void antalDage() {
        int expected = 8;

        int actual = ordination.antalDage();

        assertEquals(expected, actual);
    }

    @Test
    void samletDosis() {

    }

    @Test
    void doegnDosis() {
    }

    @Test
    void getType() {
    }

    @Test
    void getLaegemiddel() {
        Laegemiddel laegemiddel = new Laegemiddel("Jannick", 9.7, 60, 120, "Piller");
        ordination.setLaegemiddel(laegemiddel);

        Laegemiddel expected = laegemiddel;
        Laegemiddel actual = ordination.getLaegemiddel();

        assertEquals(expected, actual);
    }

    @Test
    void setLaegemiddel() {
        Laegemiddel laegemiddel = new Laegemiddel("Jannick", 9.7, 60, 120, "Piller");
        ordination.setLaegemiddel(laegemiddel);

        Laegemiddel expected = laegemiddel;
        Laegemiddel actual = ordination.getLaegemiddel();

        assertEquals(expected, actual);
    }
}