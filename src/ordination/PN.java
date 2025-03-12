package ordination;

import java.time.LocalDate;
import java.util.ArrayList;

public class PN extends Ordination{
    private double antalEnheder;
    private ArrayList<LocalDate> givDosisTid = new ArrayList<>();

    public PN(LocalDate startDato, LocalDate slutDato, double antalEnheder) {
        super(startDato, slutDato);
        this.antalEnheder=antalEnheder;
    }

    /**
     * Registrerer at der er givet en dosis paa dagen givetDato
     * Returnerer true hvis givetDato er inden for ordinationens gyldighedsperiode og datoen huskes
     * Retrurner false ellers og datoen givetDato ignoreres
     * @param givetDato
     * @return
     */
    public boolean givDosis(LocalDate givetDato) {
        if (this.getStartDato().isAfter(givetDato) && this.getSlutDato().isBefore(givetDato)){
            givDosisTid.add(givetDato);
            return true;
        }else {
            return false;
        }
    }

    //TODO

    public double doegnDosis() {
        int ordinationer = givDosisTid.size();
        double enheder = this.antalEnheder;
        int dage = givDosisTid.getFirst().compareTo(givDosisTid.getLast());
        double result = (ordinationer*enheder)/dage;
        return result;
    }

    @Override
    public String getType() {
        return "PN";
    }


    public double samletDosis() {
        return doegnDosis()*givDosisTid.size();
    }
    
    public int getAntalGangeGivet() {
        return givDosisTid.size();
    }

    public double getAntalEnheder() {
        return antalEnheder;
    }

}
