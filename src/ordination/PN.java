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
        if (getStartDato().isBefore(givetDato) && getSlutDato().isAfter(givetDato)
                || getStartDato().equals(givetDato) || getSlutDato().equals(givetDato)){
            givDosisTid.add(givetDato);
            givDosisTid.sort(LocalDate::compareTo);
            return true;
        }else {
            return false;
        }
    }

    //TODO
    public double doegnDosis() {
        if (givDosisTid.isEmpty()) {
            return antalEnheder;
        }
        else {
            int days = givDosisTid.getFirst().until(givDosisTid.getLast()).getDays()+1;
            return (givDosisTid.size()*antalEnheder)/days;
        }
    }

    @Override
    public String getType() {
        return "PN";
    }


    public double samletDosis() {
        return antalEnheder*givDosisTid.size();
    }
    
    public int getAntalGangeGivet() {
        return givDosisTid.size();
    }

    public double getAntalEnheder() {
        return antalEnheder;
    }

}
