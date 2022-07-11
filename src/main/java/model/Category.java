package model;

public class Category {
    private int id ;
    private int nameC;

    public Category() {
    }

    public Category(int id, int nameC) {
        this.id = id;
        this.nameC = nameC;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNameC() {
        return nameC;
    }

    public void setNameC(int nameC) {
        this.nameC = nameC;
    }
}
