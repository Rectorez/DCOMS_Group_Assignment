package AccountPackage;

import InventoryPackage.Inventory;

import java.time.LocalDateTime;

public class ExecutiveAccount extends Account {
    private String ExecutiveID;

    private String GenerateID() {
        int value = AccountHandler.GetExecutiveAccounts().size() + 1;
        int length = 4;
        StringBuilder ID = new StringBuilder("EXE-");
        ID.append("0".repeat(Math.max(0, length - String.valueOf(value).length())));
        ID.append(value);

        return ID.toString();
    }
    public ExecutiveAccount(String IC, String firstName, String lastName, String username, String email, String password) {
        super(IC, firstName, lastName, username, email, password);
        ExecutiveID = GenerateID();
    }
    public ExecutiveAccount(String IC, String firstName, String lastName, String username, String email, String password,
                            String status, LocalDateTime createDate, LocalDateTime deleteDate, String executiveID) {
        super(IC, firstName, lastName, username, email, password, status, createDate, deleteDate);
        ExecutiveID = executiveID;
    }

    public String GetExecutiveID() {
        return ExecutiveID;
    }
}
