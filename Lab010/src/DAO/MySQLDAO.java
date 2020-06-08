package DAO;

import java.sql.*;
import java.util.ArrayList;

public class MySQLDAO implements IDAO {

    private Connection connection = null;

    public MySQLDAO() throws SQLException, ClassNotFoundException {
        this.connection = DatabaseConnection.getConnection();
    }

    @Override
    public ArrayList<User> SelectUsers() throws SQLException {

        ArrayList<User> users = new ArrayList<User>();

        Statement statement = connection.createStatement();
        ResultSet res = statement.executeQuery("Select * From users");

        while(res.next())
        {
            users.add(new User(res.getString(1),res.getString(2)));
        }

        return users;
    }

    @Override
    public void InsertUser(String login, String password) throws SQLException {

        PreparedStatement statement = connection.prepareStatement("INSERT INTO users (login,password) VALUES (?,?)");
        statement.setString(1,login);
        statement.setString(2,String.valueOf(password.hashCode()));
        statement.executeUpdate();
    }

    @Override
    public ArrayList<Item> SelectTour(String login) throws SQLException {

        ArrayList<Item> items = new ArrayList<Item>();
        PreparedStatement statement = connection.prepareStatement("Select Id, name,price From items Where userId = ?");
        statement.setString(1,login);
        ResultSet res = statement.executeQuery();

        while(res.next())
        {
            items.add(new Item(res.getString(2),res.getString(3),res.getInt(1)));
        }

        return items;
    }

    @Override
    public void InsertTour(String login, String country, String price) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO items (userId,name,price) VALUES (?,?,?)");
        statement.setString(1,login);
        statement.setString(2,country);
        statement.setString(3,price);
        statement.executeUpdate();
    }

    @Override
    public void DeleteTour(int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE from items WHERE Id = ?");
        statement.setInt(1,id);
        statement.executeUpdate();
    }

    @Override
    public void UpdateTour(int id, String country, String price) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE items " +
                                                                            "SET name = ? ,price = ? " +
                                                                            "WHERE Id = ?");

        statement.setString(1,country);
        statement.setString(2,price);
        statement.setInt(3,id);
        statement.executeUpdate();
    }

    @Override
    public Item SelectOne(int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("Select Id, name,price From items Where Id = ?");
        statement.setInt(1,id);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){

            int tourId = resultSet.getInt(1);
            String country = resultSet.getString(2);
            String price = resultSet.getString(3);
            Item item = new Item(country, price,tourId);
            return item;
        }
        return null;
    }
}


