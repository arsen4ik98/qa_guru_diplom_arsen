package tests.models;

public class PetTag {
    int id;
    String name;

    // Добавляем конструктор с параметрами
    public PetTag(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Геттеры (и сеттеры, если нужно)
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}