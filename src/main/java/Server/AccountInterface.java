package Server;

import AccountPackage.Account;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface AccountInterface extends Remote {
    boolean Login(String username, String pass) throws RemoteException;
    void Register(Account a) throws RemoteException;

    boolean checkExist(String username, String pass) throws RemoteException;

    public int checkAccountListSize() throws RemoteException;

    public List<Account> getAllAccounts() throws RemoteException;

    public Account getAccount(String user, String pass) throws RemoteException;
}
