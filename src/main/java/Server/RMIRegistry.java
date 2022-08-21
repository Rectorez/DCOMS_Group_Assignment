package Server;

import AccountPackage.AccountHandler;
import AccountPackage.AccountType;
import AccountPackage.AdminAccount;
import AccountPackage.ExecutiveAccount;
import InventoryPackage.Inventory;
import InventoryPackage.InventoryHandler;
import InventoryPackage.Item;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIRegistry {
    public static void main(String[] args) throws RemoteException {
        try {
            Registry reg = LocateRegistry.createRegistry(1099);
            reg.rebind("AccountServer", new AccountServer());
            reg.rebind("InventoryServer", new InventoryServer());

            //TestAccountFunctions();
            //TestInventoryFunctions();

            AccountHandler.AddAccount(AccountType.EXECUTIVE, new ExecutiveAccount("eic", "efn", "eln", "eun", "ee", "ep"));
            AccountHandler.AddAccount(AccountType.ADMIN, new AdminAccount("aic", "afn", "aln", "aun", "ae", "ap"));
            InventoryHandler.AddInventory(new Inventory("TEST INVENTORY", "TEST DESCRIPTION"));
            InventoryHandler.AddItem(new Item(InventoryHandler.GetInventories().get(0).GetID(), "TEST ITEM 1", 1.0, 2.0));
            InventoryHandler.AddItem(new Item(InventoryHandler.GetInventories().get(0).GetID(), "TEST ITEM 2", 1.0, 2.0));

            System.out.println("Server is ready!");
        } catch(RemoteException e) {
            System.out.println("Server error!");
            e.printStackTrace();
        }
    }

    private static void TestAccountFunctions() {
        AccountHandler.AddAccount(AccountType.EXECUTIVE, new ExecutiveAccount("eic1", "efn", "eln", "eun1", "ee", "ep"));
        AccountHandler.AddAccount(AccountType.EXECUTIVE, new ExecutiveAccount("eic2", "efn", "eln", "eun2", "ee", "ep"));
        AccountHandler.AddAccount(AccountType.EXECUTIVE, new ExecutiveAccount("eic3", "efn", "eln", "eun3", "ee", "ep"));
        AccountHandler.AddAccount(AccountType.ADMIN, new AdminAccount("aic1", "afn", "aln", "aun1", "ae", "ap"));
        AccountHandler.AddAccount(AccountType.ADMIN, new AdminAccount("aic2", "afn", "aln", "aun2", "ae", "ap"));
        AccountHandler.AddAccount(AccountType.ADMIN, new AdminAccount("aic3", "afn", "aln", "aun3", "ae", "ap"));

        AccountHandler.DeleteAccount(AccountType.ADMIN, AccountHandler.GetAccountsOfType(AccountType.ADMIN).get(1));
        AccountHandler.DeleteAccount(AccountType.EXECUTIVE, AccountHandler.GetAccountsOfType(AccountType.EXECUTIVE).get(0));

        AccountHandler.AddAccount(AccountType.EXECUTIVE, new ExecutiveAccount("eic4", "efn", "eln", "eun4", "ee", "ep"));
        AccountHandler.AddAccount(AccountType.ADMIN, new AdminAccount("aic4", "afn", "aln", "aun4", "ae", "ap"));
        AccountHandler.AddAccount(AccountType.ADMIN, new AdminAccount("aic5", "afn", "aln", "aun5", "ae", "ap"));

        System.out.println(AccountHandler.GetFullAccounts());
    }
    private static void TestInventoryFunctions() {
        InventoryHandler.AddInventory(new Inventory("Food Inventory", "List of Foods"));
        InventoryHandler.AddInventory(new Inventory("Drink Inventory", "List of Drinks"));
        InventoryHandler.AddInventory(new Inventory("Car Inventory", "List of Cars"));
        InventoryHandler.AddInventory(new Inventory("Table Inventory", "List of Tables"));

        InventoryHandler.AddItem(new Item(InventoryHandler.GetInventories().get(0).GetID(), "Fries", 2.0, 3.0));
        InventoryHandler.AddItem(new Item(InventoryHandler.GetInventories().get(0).GetID(), "Burger", 4.0, 5.5));
        InventoryHandler.AddItem(new Item(InventoryHandler.GetInventories().get(0).GetID(), "Steak", 6.0, 8.0));
        InventoryHandler.DeleteItem(InventoryHandler.GetItems(InventoryHandler.GetInventories().get(0)).get(1));
        InventoryHandler.AddItem(new Item(InventoryHandler.GetInventories().get(0).GetID(), "Kebab", 8.0, 9.5));

        InventoryHandler.DeleteInventory(InventoryHandler.GetInventories().get(2));

        InventoryHandler.AddItem(new Item(InventoryHandler.GetInventories().get(2).GetID(), "Oak Wood Table", 150.0, 180.0));
        InventoryHandler.AddItem(new Item(InventoryHandler.GetInventories().get(2).GetID(), "Birch Wood Table", 150.0, 180.0));
        InventoryHandler.AddItem(new Item(InventoryHandler.GetInventories().get(2).GetID(), "Spruce Wood Table", 150.0, 180.0));
        InventoryHandler.AddItem(new Item(InventoryHandler.GetInventories().get(2).GetID(), "Jungle Wood Table", 150.0, 180.0));

        InventoryHandler.DeleteInventory(InventoryHandler.GetInventories().get(2));

        InventoryHandler.AddItem(new Item(InventoryHandler.GetInventories().get(1).GetID(), "100 PLUS", 2.0, 2.5));
        InventoryHandler.AddItem(new Item(InventoryHandler.GetInventories().get(1).GetID(), "Water", 1.0, 1.2));
        InventoryHandler.AddItem(new Item(InventoryHandler.GetInventories().get(1).GetID(), "Tea", 2.5, 3.2));

        InventoryHandler.AddInventory(new Inventory("Appliance Inventory", "List of Appliances"));
        Inventory applianceInventory = InventoryHandler.GetInventories().get(2);
        applianceInventory.AddItem(new Item(applianceInventory.GetID(), "Water Heater", 280.0, 350.0));

        System.out.println(String.join("\n", InventoryHandler.GetFullInventories().stream().map(Inventory::toFullString).toList()));
    }
}
