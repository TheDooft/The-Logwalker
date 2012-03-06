package report;

public class Damage {

    private final int amount;
    private final boolean critical;
    private final boolean glancing;
    private final int school;
    private final int resisted;
    private final int blocked;
    private final int absorbed;
    private final boolean crushing;
    private final int overkill;

    public Damage(int amount, int overkill, int school, int resisted, int blocked, int absorbed, boolean critical, boolean glancing, boolean crushing) {
        this.amount = amount;
        this.overkill = overkill;
        this.school = school;
        this.resisted = resisted;
        this.blocked = blocked;
        this.absorbed = absorbed;
        this.critical = critical;
        this.glancing = glancing;
        this.crushing = crushing;

    }

    public int getAmount() {
        return amount;
    }

    public boolean isCritical() {
        return critical;
    }

    public boolean isGlancing() {
        return glancing;
    }

    public int getSchool() {
        return school;
    }

    public int getResisted() {
        return resisted;
    }

    public int getBlocked() {
        return blocked;
    }

    public int getAbsorbed() {
        return absorbed;
    }

    public boolean isCrushing() {
        return crushing;
    }

    public int getOverkill() {
        return overkill;
    }



}
