package DAO.Entities;

import DAO.Interfaces.DatabaseDAO;

import java.sql.*;
import java.util.ArrayList;

public class UserDAO implements DatabaseDAO<User> {

    private Connection connection = null;
    private final static String SQL_INSERT = "INSERT INTO users(login, password) VALUES(?,?)";
    private final static String SQL_DELETE = "DELETE FROM users WHERE ID=";
    private final static String SQL_SELECT = "SELECT * FROM users";
    private final static String SQL_UPDATE = "UPDATE users SET users.login=?, users.password = ? WHERE users.id = ";
    private final static String SQL_FIND_USER = "SELECT * FROM users S WHERE S.id = ";

    public UserDAO() throws SQLException {
        this.connection = DatabaseConnection.getConnection();
        this.connection.setAutoCommit(false);
    }

    @Override
    public int insert(User value) throws SQLException {
        PreparedStatement pr = this.connection.prepareStatement(SQL_INSERT);
        pr.setString(1, value.getLogin());
        pr.setString(2, value.getPassword());
        return pr.executeUpdate();
    }

    @Override
    public boolean delete(int id) throws SQLException {
        Statement st = this.connection.createStatement();
        return st.execute(SQL_DELETE+id);
    }

    @Override
    public boolean update(User value) throws SQLException {
        PreparedStatement pr = this.connection.prepareStatement(SQL_UPDATE+value.getId());
        pr.setString(1, value.getLogin());
        pr.setString(2, value.getPassword());
        return pr.executeUpdate() > 0;
    }

    @Override
    public User select(int id) throws SQLException {
        Statement st = this.connection.createStatement();
        ResultSet resultSet = st.executeQuery(SQL_FIND_USER+id);
        resultSet.next();
        return MakeUser(resultSet);
    }

    @Override
    public Iterable<User> selectAll() throws SQLException {
        ArrayList<User> list = new ArrayList();
        Statement st = this.connection.createStatement();
        ResultSet res = st.executeQuery(SQL_SELECT);
        while (res.next())
        {
            list.add(MakeUser(res));
        }
        return list;
    }

    @Override
    public void Close() throws SQLException {
        if (this.connection != null){
            this.connection.commit();
            this.connection.close();
        }
    }

    private User MakeUser(ResultSet set) throws SQLException {
        int id = set.getInt(1);
        String login = set.getString("login");
        String password = set.getString("password");
        User user = new User(login, password);
        user.setId(id);
        return user;
    }
}
