package DAO.Entities;

public class User {
    public User(String login, String password){
        if (this.Valid(login) && this.Valid(password)){
            this.Login = login;
            this.Password = password;
        }
        else{
            throw  new IllegalArgumentException();
        }
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        if (this.Valid(login)){
            Login = login;
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        if (this.Valid(password)){
            Password = password;
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    private boolean Valid(String value){
        if (value.isEmpty() || value.length() > 45) {
            return false;
        }
        else {
            return true;
        }
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    private String Login;
    private String Password;
    private int Id;
}
