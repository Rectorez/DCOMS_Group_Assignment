package Server;

import AccountPackage.AccountHandler;
import AccountPackage.AccountType;
import AccountPackage.AdminAccount;
import AccountPackage.ExecutiveAccount;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIRegistry {
    public static void main(String[] args) throws RemoteException {
        try {
            Registry reg = LocateRegistry.createRegistry(1099);
            reg.rebind("AccountServer", new AccountServer());
            reg.rebind("InventoryServer", new InventoryServer());

            AccountHandler.AddAccount(AccountType.EXECUTIVE, new ExecutiveAccount("eic", "efn", "eln", "eun", "ee", "ep"));
            AccountHandler.AddAccount(AccountType.ADMIN, new AdminAccount("aic", "afn", "aln", "aun", "ae", "ap"));
            System.out.println("Server is ready!");
        } catch(RemoteException e) {
            System.out.println("Server error!");
            e.printStackTrace();
        }

    }
}
