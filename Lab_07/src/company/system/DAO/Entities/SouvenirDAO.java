package company.system.DAO.Entities;

import company.system.DAO.Interfaces.DatabaseDAO;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class SouvenirDAO implements DatabaseDAO<Souvenir> {

    private Connection connection = null;
    private final static String SQL_INSERT = "INSERT INTO SOUVENIRS(NAME, MANUFACTURER_ID, PRODACTION_DATE, PRICE) VALUES(?,?,?,?)";
    private final static String SQL_DELETE = "DELETE FROM SOUVENIRS WHERE ID=";
    private final static String SQL_SELECT = "SELECT * FROM SOUVENIRS S INNER JOIN MANUFACTURERS M on S.MANUFACTURER_ID = M.ID";
    private final static String SQL_UPDATE_SOUVENIR = "update SOUVENIRS set SOUVENIRS.NAME=?, SOUVENIRS.PRICE = ?, SOUVENIRS.PRODACTION_DATE = ? where SOUVENIRS.ID = ";
    private final static String SQL_FIND_MANUFACTURER = "SELECT ID FROM MANUFACTURERS M WHERE M.MANUFACTURER_NAME =";
    private final static String SQL_FIND_SOUVENIR = "SELECT * FROM SOUVENIRS S INNER JOIN MANUFACTURERS M on S.MANUFACTURER_ID = M.ID WHERE S.ID = ";

    public SouvenirDAO() throws SQLException {
        this.connection = DatabaseConnection.getConnection();
        this.connection.setAutoCommit(false);
    }

    @Override
    public int insert(Souvenir value) throws SQLException {
        var pr = this.connection.prepareStatement(SQL_INSERT);
        var format = new SimpleDateFormat("dd.MM.yyyy");
        pr.setString(1, value.getName());
        pr.setInt(2, this.GetManufactureId(value.getManufacture()));
        pr.setDate(3, new java.sql.Date(value.getProdactionDate().getTime()));
        pr.setFloat(4, value.getPrice());

        return pr.executeUpdate();
    }

    @Override
    public boolean delete(int id) throws SQLException {
        var st = this.connection.createStatement();
        return st.execute(SQL_DELETE+id);
    }

    @Override
    public boolean update(Souvenir value) throws SQLException {
        var pr = this.connection.prepareStatement(SQL_UPDATE_SOUVENIR+value.getId());
        var format = new SimpleDateFormat("dd.MM.yyyy");
        pr.setString(1, value.getName());
        pr.setDate(3, new java.sql.Date(value.getProdactionDate().getTime()));
        pr.setFloat(2, value.getPrice());

        return pr.executeUpdate() < 1;
    }

    @Override
    public Souvenir select(int id) throws SQLException {
        Souvenir souvenir = null;
        var st = this.connection.createStatement();
        var res = st.executeQuery(SQL_FIND_SOUVENIR+id);
        res.next();
        return MakeSouvenir(res);
    }

    @Override
    public Iterable<Souvenir> selectAll() throws SQLException {
        var list = new ArrayList<Souvenir>();
        var st = this.connection.createStatement();
        var res = st.executeQuery(SQL_SELECT);
        while (res.next())
        {
            list.add(MakeSouvenir(res));
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

    private int GetManufactureId(Manufacture manufacture) throws SQLException {
        var st = this.connection.createStatement();
        var res = st.executeQuery(SQL_FIND_MANUFACTURER+"\'"+manufacture.getName().toUpperCase()+"\'");
        res.next();
        return res.getInt(1);
    }

    private Souvenir MakeSouvenir(ResultSet res) throws SQLException {
        var souvenir = new Souvenir(res.getInt(1));
        souvenir.setName(res.getString("NAME"));
        souvenir.setPrice(res.getFloat("PRICE"));
        souvenir.setProdactionDate(res.getDate("PRODACTION_DATE"));
        var manufactore = new Manufacture();
        manufactore.setName(res.getString("MANUFACTURER_NAME"));
        manufactore.setCountry(res.getString("COUNTRY"));
        souvenir.setManufacture(manufactore);
        return souvenir;
    }
}
