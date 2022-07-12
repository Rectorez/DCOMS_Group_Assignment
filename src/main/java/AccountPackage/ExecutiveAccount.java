package AccountPackage;

import InventoryPackage.Inventory;

import java.time.LocalDateTime;

public class ExecutiveAccount extends Account {
    private String ExecutiveID;

    public ExecutiveAccount(String firstName, String lastName, String username, String email, String password) {
        super(firstName, lastName, username, email, password);
    }
    public ExecutiveAccount(String IC, String firstName, String lastName, String username, String email, String password, String status, LocalDateTime createDate, LocalDateTime deleteDate, String executiveID) {
        super(IC, firstName, lastName, username, email, password, status, createDate, deleteDate);
        ExecutiveID = executiveID;
    }

    public String getExecutiveID() {
        return ExecutiveID;
    }
}
