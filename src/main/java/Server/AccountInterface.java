package Server;

import AccountPackage.Account;
import AccountPackage.AdminAccount;
import AccountPackage.ExecutiveAccount;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface AccountInterface extends Remote {
    //Getters
    List<Account> GetAccounts() throws RemoteException;
    List<AdminAccount> GetAdminAccounts() throws RemoteException;
    List<ExecutiveAccount> GetExecutiveAccounts() throws RemoteException;
    Account GetAccount(String username, String password) throws RemoteException;

    //Verification
    boolean HasExistingAccountPartial(String username) throws RemoteException;
    boolean HasExistingAccountFull(String username, String password) throws RemoteException;
    boolean VerifyAccountWithEmailPassword(String email, String password) throws RemoteException;
    boolean VerifyAccountWithUsernamePassword(String username, String password) throws RemoteException;

    //Login / Register
    boolean Login(String username, String password) throws RemoteException;
    void Register(Account account) throws RemoteException;

}
