package Server;

import AccountPackage.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface AccountInterface extends Remote {
    //Getters
    List<Account> GetAccounts() throws RemoteException;
    List<AdminAccount> GetAdminAccounts() throws RemoteException;
    List<ExecutiveAccount> GetExecutiveAccounts() throws RemoteException;
    List<Account> GetAccountsOfType(AccountType accountType) throws RemoteException;

    Account GetAccount(String username, String password) throws RemoteException;

    //Verification
    boolean HasExistingAccountPartial(AccountType accountType, String username) throws RemoteException;
    boolean HasExistingAccountFull(AccountType accountType, String username, String password) throws RemoteException;
    boolean VerifyAccountWithEmailPassword(AccountType accountType, String email, String password) throws RemoteException;
    boolean VerifyAccountWithUsernamePassword(AccountType accountType, String username, String password) throws RemoteException;

    //Login / Register
    boolean Login(AccountType accountType, String username, String password) throws RemoteException;
    void Register(AccountType accountType, Account account) throws RemoteException;

}
