package Server;

import AccountPackage.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class AccountServer extends UnicastRemoteObject implements AccountInterface {
    public AccountServer() throws RemoteException{
        super();
    }

    //Getters
    @Override
    public List<Account> GetAccounts() {
        return AccountHandler.GetAccounts();
    }
    @Override
    public List<AdminAccount> GetAdminAccounts() {
        return AccountHandler.GetAdminAccounts();
    }
    @Override
    public List<ExecutiveAccount> GetExecutiveAccounts() {
        return AccountHandler.GetExecutiveAccounts();
    }
    @Override
    public List<Account> GetAccountsOfType(AccountType accountType) {
        return AccountHandler.GetAccountsOfType(accountType);
    }
    @Override
    public Account GetAccount(AccountType accountType, String username, String password) {
        return AccountHandler.GetAccount(accountType, username, password);
    }

    //Verification
    @Override
    public boolean HasExistingAccountPartial(AccountType accountType, String username) {
        return AccountHandler.HasExistingAccountPartial(accountType, username);
    }
    @Override
    public boolean HasExistingAccountFull(AccountType accountType, String username, String password) {
        return AccountHandler.HasExistingAccountFull(accountType, username, password);
    }
    public boolean VerifyAccountWithEmailPassword(AccountType accountType, String email, String password) {
        return AccountHandler.VerifyAccountWithEmailPassword(accountType, email, password);
    }
    public boolean VerifyAccountWithUsernamePassword(AccountType accountType, String username, String password) {
        return AccountHandler.VerifyAccountWithUsernamePassword(accountType, username, password);
    }

    //Login / Register
    @Override
    public boolean Login(AccountType accountType, String username, String password) {
        return AccountHandler.VerifyAccountWithUsernamePassword(accountType, username, password);
    }
    @Override
    public void Register(AccountType accountType, Account account) {
        AccountHandler.AddAccount(accountType, account);
    }

    //Manipulation
    @Override
    public boolean DeleteAccount(AccountType accountType, Account targetAccount) {
        return AccountHandler.DeleteAccount(accountType, targetAccount);
    }
}
