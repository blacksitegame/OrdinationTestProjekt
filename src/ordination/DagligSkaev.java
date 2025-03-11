package ordination;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class DagligSkaev extends Ordination{
    private ArrayList<Dosis> dosisArrayList = new ArrayList<>();

    public DagligSkaev(LocalDate startDato, LocalDate slutDato) {
        super(startDato, slutDato);
    }


    public Dosis opretDosis(LocalTime tid, double antal) {
        Dosis dosis = new Dosis(tid,antal);
        dosisArrayList.add(dosis);
        return dosis;
    }

    public ArrayList<Dosis> getDoser() {
        return new ArrayList<>(dosisArrayList);
    }

    @Override
    public double samletDosis() {
        double result = 0;
        for (int i = 0; i<this.antalDage(); i++) {
            for (Dosis dosis : dosisArrayList) {
                result += dosis.getAntal();
            }
        }
        return result;
    }

    @Override
    public double doegnDosis() {
        double result = 0;
        for (Dosis dosis : dosisArrayList) {
            result+=dosis.getAntal();
        }
        return result;
    }

    @Override
    public String getType() {
        return "Daglig Skæv";
    }
}
