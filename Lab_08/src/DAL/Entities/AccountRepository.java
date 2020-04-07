package DAL.Entities;

import DAL.Interfaces.IRepository;

import java.util.ArrayList;

public class AccountRepository implements IRepository<Account>
{
    private ArrayList<Account> accounts = new ArrayList<Account>();

    @Override
    public Account Get(int id)
    {
        var item = this.accounts
                .stream()
                .filter((i) -> i.getId() == id)
                .findFirst()
                .get();
        return item;

    }

    @Override
    public void Create(Account item)
    {
        this.CheckNull(item);
        this.accounts.add(item);
    }

    @Override
    public void Update(Account item)
    {
        this.CheckNull(item);
        var itemIndex = this.accounts.indexOf(item);
        this.accounts.add(itemIndex, item);
    }

    @Override
    public void Delete(Account item)
    {
        this.CheckNull(item);
        this.accounts.remove(item);
    }

    @Override
    public Iterable<Account> GetAll()
    {
        return this.accounts;
    }

    private void CheckNull(Account item)
    {
        if (item == null)
        {
            throw new NullPointerException();
        }
    }
}
