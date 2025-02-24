package tests.models;


public class Category {
    private int id;
    private String name;

    // Конструктор с параметрами
    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Геттеры
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // Сеттеры, если нужно
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}