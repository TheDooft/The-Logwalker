package report;


public class Miss {

    private final int amount;
    private final Type type;

    public Miss(int amount, Type type) {
        this.amount = amount;
        this.type = type;
    }

    public int getAmount() {
		return amount;
	}
    
    public Type getType() {
		return type;
	}
    
    public enum Type {
        ABSORB,
        BLOCK,
        DEFLECT,
        DODGE,
        EVADE,
        IMMUNE,
        MISS,
        PARRY,
        REFLECT,
        RESIST
    }

}
