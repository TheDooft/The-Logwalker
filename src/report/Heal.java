package report;

public class Heal {

    private final int amount;
    private final boolean critical;
    private final int absorbed;
    private final int overhealing;

    public Heal(int amount, int overhealing, int absorbed, boolean critical) {
        this.amount = amount;
        this.overhealing = overhealing;

        this.absorbed = absorbed;
        this.critical = critical;


    }

    public int getAmount() {
        return amount;
    }

    public boolean isCritical() {
        return critical;
    }

    public int getAbsorbed() {
        return absorbed;
    }



    public int getOverHealing() {
        return overhealing;
    }



}
