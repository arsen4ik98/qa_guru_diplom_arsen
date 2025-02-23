package tests.models;

public class PetTag {
    int id;
    String name;

    public PetTag(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}