package ordination;

import java.util.ArrayList;

public class Patient {
    private String cprnr;
    private String navn;
    private double vaegt;
    ArrayList<Ordination> ordinationArrayList = new ArrayList<>();

    public Patient(String cprnr, String navn, double vaegt) {
        try {
            if (vaegt>0){
                this.cprnr = cprnr;
                this.navn = navn;
                this.vaegt = vaegt;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getCprnr() {
        return cprnr;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public double getVaegt(){
        return vaegt;
    }

    public void setVaegt(double vaegt){
        this.vaegt = vaegt;
    }


    public ArrayList<Ordination> getOrdinationer() {
        return ordinationArrayList;
    }

    @Override
    public String toString(){
        return navn + "  " + cprnr;
    }

    public void addOrdination(Ordination ordination){
        if (!ordinationArrayList.contains(ordination)) {
            ordinationArrayList.add(ordination);
        }
    }
}
