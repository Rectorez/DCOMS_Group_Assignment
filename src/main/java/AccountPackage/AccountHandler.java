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

    public static boolean HasExistingAccountPartial(String username) {
        return AccountHandler.GetAccounts()
                .stream()
                .anyMatch(a -> a.GetUsername().equals(username));
    }
    public static boolean HasExistingAccountFull(String username, String password) {
        return AccountHandler.GetAccounts()
                .stream()
                .anyMatch(a -> a.GetUsername().equals(username) && a.GetPassword().equals(password));
    }
    public static boolean VerifyAccountWithEmailPassword(String email, String password) {
        return AccountHandler.GetAccounts()
                .stream()
                .anyMatch(a -> a.GetEmail().equals(email) && a.GetPassword().equals(password));
    }
    public static boolean VerifyAccountWithUsernamePassword(String username, String password) {
        return AccountHandler.GetAccounts()
                .stream()
                .anyMatch(a -> a.GetUsername().equals(username) && a.GetPassword().equals(password));
    }

    public static boolean AddAccount(Account newAccount) {
        if (HasExistingAccountFull(newAccount.GetUsername(), newAccount.GetPassword())) return false;
        AccountList.add(newAccount);
        return true;
    }
}
