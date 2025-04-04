package ordination;

public class Laegemiddel {
    private String navn;
    private double enhedPrKgPrDoegnLet;   // faktor der anvendes hvis patient vejer < 25 kg
    private double enhedPrKgPrDoegnNormal;// faktor der anvendes hvis 25 kg <= patient vægt <= 120 kg
    private double enhedPrKgPrDoegnTung;  // faktor der anvendes hvis patient vægt > 120 kg 
    private String enhed;

    public Laegemiddel(String navn, double enhedPrKgPrDoegnLet, double enhedPrKgPrDoegnNormal, 
            double enhedPrKgPrDoegnTung, String enhed) {
        this.navn = navn;
        this.enhedPrKgPrDoegnLet = enhedPrKgPrDoegnLet;
        this.enhedPrKgPrDoegnNormal = enhedPrKgPrDoegnNormal;
        this.enhedPrKgPrDoegnTung = enhedPrKgPrDoegnTung;
        this.enhed = enhed;
    }

    public String getEnhed() {
        return enhed;
    }

    public String getNavn() {
        return navn;
    }

    public double anbefaletDosisPrDoegn(double vaegt) {
        if (vaegt>0) {
            if (vaegt < 25 && vaegt > 0) {
                return enhedPrKgPrDoegnLet;
            } else if (vaegt > 120) {
                return enhedPrKgPrDoegnTung;
            } else {
                return enhedPrKgPrDoegnNormal;
            }
        } else {
            return 0;
        }

    }

    @Override
    public String toString(){
        return navn;
    }
}
