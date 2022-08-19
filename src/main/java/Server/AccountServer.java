package Server;

import AccountPackage.Account;
import AccountPackage.AccountHandler;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class AccountServer extends UnicastRemoteObject implements AccountInterface {

    public AccountServer() throws RemoteException{
        super();
    }

    @Override
    public boolean Login(String username, String pass) throws RemoteException {
        return AccountHandler.VerifyAccountWithUsername(username, pass);
    }

    @Override
    public void Register(Account a) throws RemoteException {
        AccountHandler.AddAccount(a);
    }

    @Override
    public boolean checkExist(String username, String pass) throws RemoteException{
        return AccountHandler.VerifyAccountWithUsername(username, pass);
    }

    @Override
    public int checkAccountListSize() throws RemoteException {
        return AccountHandler.GetAccounts().size();
    }

    @Override
    public List<Account> getAllAccounts() throws RemoteException{
        return AccountHandler.GetAccounts();
    }

    @Override
    public Account getAccount(String user, String pass) throws RemoteException {
        return AccountHandler.GetAccounts().stream().filter(x -> (x.GetUsername().equals(user) &&
                (x.GetPassword().equals(pass)))).findFirst().get();
    }

}
