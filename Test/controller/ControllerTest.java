package controller;

import ordination.Laegemiddel;
import ordination.Patient;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    @org.junit.jupiter.api.Test
    void anbefaletDosisPrDoegn() {
        Laegemiddel laegemiddel = new Laegemiddel("laegemiddel", 1, 2, 3, "enhed");
        Patient patient = new Patient("cpr", "navn", 24);
        assertEquals(1, Controller.getController().anbefaletDosisPrDoegn(patient, laegemiddel));

    }

    @org.junit.jupiter.api.Test
    void antalOrdinationerPrVaegtPrLaegemiddel() {
    }
}