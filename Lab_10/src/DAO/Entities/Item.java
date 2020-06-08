package DAO.Entities;

public class Item {

    public Item(int id){
        if (id >= 0){
            Id = id;
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        if (this.Valid(name)){
            Name = name;
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    public int getId() {
        return Id;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder
                .append(this.Id+",")
                .append(this.Name+";");
        return builder.toString();
    }

    private boolean Valid(String value){
        if (value.isEmpty() || value.length() > 45) {
            return false;
        }
        else {
            return true;
        }
    }

    private int Id;
    private String Name;
}
