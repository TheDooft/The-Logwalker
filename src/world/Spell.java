package world;

public class Spell {

    String id;

    String name;

    int school;


    public Spell(String id, String name, int school) {
        this.id = id;
        this.name = name;
        this.school = school;

    }

    public String getName() {
        return name;
    }

}
