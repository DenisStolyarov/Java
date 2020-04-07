package company.system.DAO.Interfaces;

import java.sql.SQLException;

public interface DatabaseDAO<T> {
    int insert(T value) throws SQLException;
    boolean delete(int id) throws SQLException;
    boolean update(T value) throws SQLException;
    T select(int id) throws SQLException;
    Iterable<T> selectAll() throws SQLException;
    void Close() throws SQLException;
}
