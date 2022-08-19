package Server;

import AccountPackage.Account;
import AccountPackage.AccountHandler;
import AccountPackage.AdminAccount;
import AccountPackage.ExecutiveAccount;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class AccountServer extends UnicastRemoteObject implements AccountInterface {
    public AccountServer() throws RemoteException{
        super();
    }

    //Getters
    @Override
    public List<Account> GetAccounts() throws RemoteException{
        return AccountHandler.GetAccounts();
    }
    @Override
    public List<AdminAccount> GetAdminAccounts() throws RemoteException{
        return AccountHandler.GetAdminAccounts();
    }
    @Override
    public List<ExecutiveAccount> GetExecutiveAccounts() throws RemoteException{
        return AccountHandler.GetExecutiveAccounts();
    }
    @Override
    public Account GetAccount(String username, String password) throws RemoteException {
        return AccountHandler
                .GetAccounts()
                .stream()
                .filter(x -> (x.GetUsername().equals(username) &&
                        (x.GetPassword().equals(password))))
                .findFirst()
                .orElse(null);
    }

    //Verification
    @Override
    public boolean HasExistingAccountPartial(String username) throws RemoteException{
        return AccountHandler.HasExistingAccountPartial(username);
    }
    @Override
    public boolean HasExistingAccountFull(String username, String password) throws RemoteException{
        return AccountHandler.HasExistingAccountFull(username, password);
    }
    public boolean VerifyAccountWithEmailPassword(String email, String password) {
        return AccountHandler.VerifyAccountWithEmailPassword(email, password);
    }
    public boolean VerifyAccountWithUsernamePassword(String username, String password) {
        return AccountHandler.VerifyAccountWithUsernamePassword(username, password);
    }

    //Login / Register
    @Override
    public boolean Login(String usernameOrEmail, String password) throws RemoteException {
        return AccountHandler.VerifyAccountWithUsernamePassword(usernameOrEmail, password);
    }
    @Override
    public void Register(Account account) throws RemoteException {
        AccountHandler.AddAccount(account);
    }
}
