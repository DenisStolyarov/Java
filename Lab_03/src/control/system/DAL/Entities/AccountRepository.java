package control.system.DAL.Entities;

import control.system.DAL.Interfaces.IRepository;

import java.util.ArrayList;

public class AccountRepository implements IRepository<Account>
{
    private ArrayList<Account> accounts = new ArrayList<Account>();

    @Override
    public Account Get(int id) {
        return null;
    }

    @Override
    public void Create(Account item) {

    }

    @Override
    public void Update(Account item) {

    }

    @Override
    public void Delete(Account item) {

    }

    @Override
    public Iterable<Account> GetAll() {
        return null;
    }
}
