package controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import ordination.*;
import storage.Storage;

public class Controller {
	private Storage storage;
	private static Controller controller;

	private Controller() {
		storage = new Storage();
	}

	public static Controller getController() {
		if (controller == null) {
			controller = new Controller();
		}
		return controller;
	}

	public static Controller getTestController() {
		return new Controller();
	}

	/**
	 * @return opretter og returnerer en PN ordination.
	 */
	public PN opretPNOrdination(LocalDate startDato, LocalDate slutDato,
			Patient patient, Laegemiddel laegemiddel, double antal) {
		PN pn = new PN(startDato,slutDato,antal);
		pn.setLaegemiddel(laegemiddel);
		patient.addOrdination(pn);
		return pn;
	}

	/**
	 * Opretter og returnerer en DagligFast ordination.
	 */
	public DagligFast opretDagligFastOrdination(LocalDate startDato,
			LocalDate slutDato, Patient patient, Laegemiddel laegemiddel,
			double morgenAntal, double middagAntal, double aftenAntal,
			double natAntal) {
		DagligFast dagligFast = new DagligFast(startDato,slutDato);
		dagligFast.opretDosis(LocalTime.of(06,00),morgenAntal);
		dagligFast.opretDosis(LocalTime.of(12,00),middagAntal);
		dagligFast.opretDosis(LocalTime.of(18,00),aftenAntal);
		dagligFast.opretDosis(LocalTime.of(00,00),natAntal);
		dagligFast.setLaegemiddel(laegemiddel);
		patient.addOrdination(dagligFast);
		return dagligFast;
	}

	/**
	 * Opretter og returnerer en DagligSkæv ordination.
	 */
	public DagligSkaev opretDagligSkaevOrdination(LocalDate startDato,
			LocalDate slutDato, Patient patient, Laegemiddel laegemiddel,
			LocalTime[] klokkeSlet, double[] antalEnheder) {

		DagligSkaev dagligSkaev = new DagligSkaev(startDato,slutDato);
		for (int i = 0; i<antalEnheder.length; i++){
			dagligSkaev.opretDosis(klokkeSlet[i],antalEnheder[i]);
		}
		dagligSkaev.setLaegemiddel(laegemiddel);
		patient.addOrdination(dagligSkaev);
		return dagligSkaev;
	}

	/**
	 * En dato for hvornår ordinationen anvendes tilføjes ordinationen.
	 */
	public void ordinationPNAnvendt(PN ordination, LocalDate dato) {
		ordination.givDosis(dato);
	}

	/**
	 * Den anbefalede dosis for den pågældende patient (der skal tages hensyn
	 * til patientens vægt). Det er en forskellig enheds faktor der skal
	 * anvendes, og den er afhængig af patientens vægt.
	 */
	public double anbefaletDosisPrDoegn(Patient patient, Laegemiddel laegemiddel) {
		return laegemiddel.anbefaletDosisPrDoegn(patient.getVaegt());

	}

	/**
	 * For et givent vægtinterval og et givent lægemiddel, hentes antallet af
	 * ordinationer.
	 */
	public int antalOrdinationerPrVaegtPrLaegemiddel(double vaegtStart,
													 double vaegtSlut, Laegemiddel laegemiddel) {
		int result = 0;
		for (Patient patient : storage.getAllPatienter()){
			if (patient.getVaegt()>=vaegtStart && patient.getVaegt()<=vaegtSlut){
				for (Ordination ordination : patient.getOrdinationer()) {
					if (ordination.getLaegemiddel()==laegemiddel){
						result++;
					}
				}
			}
		}
		return result;
	}

	public List<Patient> getAllPatienter() {
		return storage.getAllPatienter();
	}

	public List<Laegemiddel> getAllLaegemidler() {
		return storage.getAllLaegemidler();
	}

	/**
	 * Metode der kan bruges til at checke at en startDato ligger før en
	 * slutDato.
	 *
	 * @return true hvis startDato er før slutDato, false ellers.
	 */
	private boolean checkStartFoerSlut(LocalDate startDato, LocalDate slutDato) {
		boolean result = true;
		if (slutDato.compareTo(startDato) < 0) {
			result = false;
		}
		return result;
	}

	public Patient opretPatient(String cpr, String navn, double vaegt) {
		Patient patient = new Patient(cpr, navn, vaegt);
		storage.addPatient(patient);
		return patient;
	}

	public Laegemiddel opretLaegemiddel(String navn,
			double enhedPrKgPrDoegnLet, double enhedPrKgPrDoegnNormal,
			double enhedPrKgPrDoegnTung, String enhed) {
		Laegemiddel laegemiddel = new Laegemiddel(navn, enhedPrKgPrDoegnLet,
				enhedPrKgPrDoegnNormal, enhedPrKgPrDoegnTung, enhed);
		storage.addLaegemiddel(laegemiddel);
		return laegemiddel;
	}

	public void createSomeObjects() {
		this.opretPatient("121256-0512", "Jane Jensen", 63.4);
		this.opretPatient("070985-1153", "Finn Madsen", 83.2);
		this.opretPatient("050972-1233", "Hans Jørgensen", 89.4);
		this.opretPatient("011064-1522", "Ulla Nielsen", 59.9);
		this.opretPatient("090149-2529", "Ib Hansen", 87.7);

		this.opretLaegemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");
		this.opretLaegemiddel("Paracetamol", 1, 1.5, 2, "Ml");
		this.opretLaegemiddel("Fucidin", 0.025, 0.025, 0.025, "Styk");
		this.opretLaegemiddel("Methotrexat", 0.01, 0.015, 0.02, "Styk");

		this.opretPNOrdination(LocalDate.of(2025, 1, 1), LocalDate.of(2025, 1, 12),
				storage.getAllPatienter().get(0), storage.getAllLaegemidler()
						.get(1),
				123);

		this.opretPNOrdination(LocalDate.of(2025, 2, 12), LocalDate.of(2025, 2, 14),
				storage.getAllPatienter().get(0), storage.getAllLaegemidler()
						.get(0),
				3);

		this.opretPNOrdination(LocalDate.of(2025, 1, 20), LocalDate.of(2025, 1, 25),
				storage.getAllPatienter().get(3), storage.getAllLaegemidler()
						.get(2),
				5);

		this.opretPNOrdination(LocalDate.of(2025, 1, 1), LocalDate.of(2025, 1, 12),
				storage.getAllPatienter().get(0), storage.getAllLaegemidler()
						.get(1),
				123);

		this.opretDagligFastOrdination(LocalDate.of(2025, 1, 10),
				LocalDate.of(2025, 1, 12), storage.getAllPatienter().get(1),
				storage.getAllLaegemidler().get(1), 2, 0, 1, 0);

		LocalTime[] kl = { LocalTime.of(12, 0), LocalTime.of(12, 40),
				LocalTime.of(16, 0), LocalTime.of(18, 45) };
		double[] an = { 0.5, 1, 2.5, 3 };

		this.opretDagligSkaevOrdination(LocalDate.of(2025, 1, 23),
				LocalDate.of(2025, 1, 24), storage.getAllPatienter().get(1),
				storage.getAllLaegemidler().get(2), kl, an);
	}

}
