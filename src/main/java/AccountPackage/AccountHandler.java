package AccountPackage;

import java.util.ArrayList;
import java.util.List;

public class AccountHandler {
    private static List<Account> AccountList = new ArrayList<>();

    public static List<Account> GetAccounts() {
        return AccountList;
    }
    public static List<AdminAccount> GetAdminAccounts() {
        return AccountList.stream()
                .filter(a -> a.getClass().equals(AdminAccount.class))
                .map(a -> (AdminAccount)a)
                .toList();
    }
    public static List<ExecutiveAccount> GetExecutiveAccounts() {
        return AccountList.stream()
                .filter(a -> a.getClass().equals(ExecutiveAccount.class))
                .map(a -> (ExecutiveAccount)a)
                .toList();
    }
    public static List<Account> GetAccountsOfType(AccountType accountType) {
        switch (accountType) {
            case ADMIN: return GetAdminAccounts().stream().map(a -> (Account) a).toList();
            case EXECUTIVE: return GetExecutiveAccounts().stream().map(a -> (Account) a).toList();
            case ALL: default: return GetAccounts();
        }
    }

    public static boolean HasExistingAccountPartial(AccountType accountType, String username) {
        return AccountHandler.GetAccountsOfType(accountType)
                .stream()
                .anyMatch(a -> a.GetUsername().equals(username));
    }
    public static boolean HasExistingAccountFull(AccountType accountType, String username, String password) {
        return AccountHandler.GetAccountsOfType(accountType)
                .stream()
                .anyMatch(a -> a.GetUsername().equals(username) && a.GetPassword().equals(password));
    }
    public static boolean VerifyAccountWithEmailPassword(AccountType accountType, String email, String password) {
        return AccountHandler.GetAccountsOfType(accountType)
                .stream()
                .anyMatch(a -> a.GetEmail().equals(email) && a.GetPassword().equals(password));
    }
    public static boolean VerifyAccountWithUsernamePassword(AccountType accountType, String username, String password) {
        return AccountHandler.GetAccountsOfType(accountType)
                .stream()
                .anyMatch(a -> a.GetUsername().equals(username) && a.GetPassword().equals(password));
    }

    public static boolean AddAccount(AccountType accountType, Account newAccount) {
        if (HasExistingAccountFull(accountType, newAccount.GetUsername(), newAccount.GetPassword())) return false;
        AccountList.add(newAccount);
        return true;
    }
}
