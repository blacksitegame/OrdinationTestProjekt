package ordination;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class DagligFast extends Ordination {
    private Dosis[] dosisArr = new Dosis[4];
    private int count = 0;

    public DagligFast(LocalDate startDato, LocalDate slutDato) {
        super(startDato, slutDato);
    }

    public Dosis opretDosis(LocalTime tid, double antal) {
        Dosis dosis = new Dosis(tid, antal);
        for (int i = 0; i < antal; i++) {
            dosisArr[count] = dosis;
            count++;
        }
        return dosis;
    }

    public Dosis[] getDoser() {
        return dosisArr;
    }

    @Override
    public double samletDosis() {
        double result = 0;
        for (int i = 0; i < this.antalDage(); i++) {
            for (Dosis dosis : dosisArr) {
                if (dosis!=null){
                    result += dosis.getAntal();
                }
            }
        }
        return result;
    }

    @Override
    public double doegnDosis() {
        double result = 0;
        for (Dosis dosis : dosisArr) {
            if (dosis != null) {
                result += dosis.getAntal();
            }
        }
        return result;
    }

    @Override
    public String getType() {
        return "Daglig fast";
    }
}
