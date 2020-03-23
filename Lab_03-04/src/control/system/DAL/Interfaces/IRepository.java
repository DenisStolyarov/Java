package control.system.DAL.Interfaces;

public interface IRepository<T extends Object>
{
    T Get(int id);
    void Create(T item);
    void Update(T item);
    void Delete(T item);
    Iterable<T> GetAll();
}
