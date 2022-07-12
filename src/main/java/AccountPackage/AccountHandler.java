package AccountPackage;

import java.util.ArrayList;
import java.util.List;

public class AccountHandler {
    private static List<Account> AccountList;

    public static List<Account> GetAccounts() {
        return new ArrayList<>();
    }
    public static List<AdminAccount> GetAdminAccounts() {
        return new ArrayList<>();
    }
    public static List<ExecutiveAccount> GetExecutiveAccounts() {
        return new ArrayList<>();
    }

    public static boolean VerifyAccountWithEmail(String email, String password) {
        return true;
    }
    public static boolean VerifyAccountWithUsername(String username, String password) {
        return true;
    }
    public static boolean AddAccount(Account newAccount) {
        return true;
    }
}
