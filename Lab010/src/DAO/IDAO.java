package DAO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IDAO {
    public ArrayList<User> SelectUsers() throws SQLException;
    public void InsertUser(String login, String password) throws SQLException;
    public ArrayList<Item> SelectTour(String login) throws SQLException;
    public void InsertTour(String login, String country, String price) throws SQLException;
    public void DeleteTour(int id) throws SQLException;
    public void UpdateTour(int id, String country, String price) throws SQLException;
    public Item SelectOne(int id) throws SQLException;
}
