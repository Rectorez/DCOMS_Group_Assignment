package AccountPackage;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class AccountHandler {
    private static List<Account> AccountList = new ArrayList<>();

    public static List<Account> GetAccounts() {
        return AccountList.stream()
                .filter(a -> a.GetStatus().equals(AccountStatus.ACTIVE))
                .toList();
    }
    public static List<Account> GetFullAccounts() {
        return AccountList;
    }
    public static List<AdminAccount> GetAdminAccounts() {
        return GetAccounts().stream()
                .filter(a -> a.getClass().equals(AdminAccount.class))
                .map(a -> (AdminAccount)a)
                .toList();
    }
    public static List<AdminAccount> GetFullAdminAccounts() {
        return GetFullAccounts().stream()
                .filter(a -> a.getClass().equals(AdminAccount.class))
                .map(a -> (AdminAccount)a)
                .toList();
    }
    public static List<ExecutiveAccount> GetExecutiveAccounts() {
        return GetAccounts().stream()
                .filter(a -> a.getClass().equals(ExecutiveAccount.class))
                .map(a -> (ExecutiveAccount)a)
                .toList();
    }
    public static List<ExecutiveAccount> GetFullExecutiveAccounts() {
        return GetFullAccounts().stream()
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
    public static Account GetAccount(AccountType accountType, String username, String password) {
        return GetAccountsOfType(accountType)
                .stream()
                .filter(x -> (x.GetUsername().equals(username) &&
                        (x.GetPassword().equals(password))))
                .findFirst()
                .orElse(null);
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
    public static boolean DeleteAccount(AccountType accountType, Account targetAccount) {
        if (!HasExistingAccountFull(accountType, targetAccount.GetUsername(), targetAccount.GetPassword())) return false;
        Account account = GetAccountsOfType(accountType).stream()
                .filter(a -> a.equals(targetAccount))
                .findFirst()
                .orElse(null);
        if (account == null) return false;
        return account.Delete();
    }

    private static boolean WriteAccountsToFile(){
        //Check dir exist
        String folderDir = Path.of(System.getProperty("user.dir"), "ServerDatabase").toString();
        File f = new File(folderDir);
        if(!f.exists()){
            //If not create
            f.mkdir();
            System.out.println("Created directory: " + folderDir);
        }
        String fileDir = Path.of(folderDir, "Accounts.ser").toString();
        try{
            FileOutputStream file = new FileOutputStream(fileDir);
            ObjectOutputStream oos = new ObjectOutputStream(file);
            oos.writeObject(AccountList);

            oos.close();
            file.close();

            return true;
        }
        catch (IOException e) {
            return false;
        }
    }

    private static boolean ReadAccountsToList() {
        File target = Path.of(System.getProperty("user.dir"), "ServerDatabase", "Accounts.ser").toFile();
        if(target.exists()){
            try {
                FileInputStream file = new FileInputStream(target);
                ObjectInputStream obj = new ObjectInputStream(file);

                AccountList = (List<Account>) obj.readObject();
                return true;
            }
            catch (IOException | ClassNotFoundException e) {
                return false;
            }
        }
        else{
            System.out.println("Accounts.ser does not exist");
            return false;
        }
    }
}
