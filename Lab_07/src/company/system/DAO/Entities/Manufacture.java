package company.system.DAO.Entities;

public class Manufacture {
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

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        if (this.Valid(country)){
            Country = country;
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

    private String Name;
    private String Country;
}
