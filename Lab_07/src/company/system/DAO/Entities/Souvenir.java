package company.system.DAO.Entities;

import java.util.Date;

public class Souvenir {

    public Souvenir(int id){
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

    public float getPrice() {
        return Price;
    }

    public void setPrice(float price) {
        if (price >= 0){
            Price = price;
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    private boolean Valid(String value){
        if (value.isBlank() || value.length() > 50) {
            return false;
        }
        else {
            return true;
        }
    }

    public Date getProdactionDate() {
        return ProdactionDate;
    }

    public void setProdactionDate(Date prodactionDate) {
        if (!prodactionDate.after(new Date())){
            ProdactionDate = prodactionDate;
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    public Manufacture getManufacture() {
        return Manufacture;
    }

    public void setManufacture(Manufacture manufacture) {
        if (manufacture == null){
            throw new IllegalArgumentException();
        }
        if (manufacture.getCountry() == null || manufacture.getName() == null) {
            throw new IllegalArgumentException();
        }
        else{
            Manufacture = manufacture;
        }
    }

    public int getId() {
        return Id;
    }

    @Override
    public String toString() {
        var builder = new StringBuilder();
        builder
                .append(this.Id+",")
                .append(this.Name+",")
                .append(this.Price+",")
                .append(this.ProdactionDate+",")
                .append(this.Manufacture.getName()+",")
                .append(this.Manufacture.getCountry()+";");
        return builder.toString();
    }

    private int Id;
    private Manufacture Manufacture;
    private Date ProdactionDate;
    private float Price;
    private String Name;
}
