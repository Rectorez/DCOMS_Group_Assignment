package AccountPackage;

import java.time.LocalDateTime;

public class AdminAccount extends Account {
    private String AdminID;

    private String GenerateID() {
        int value = AccountHandler.GetAdminAccounts().size() + 1;
        int length = 4;
        StringBuilder ID = new StringBuilder("ADM-");
        ID.append("0".repeat(Math.max(0, length - String.valueOf(value).length())));
        ID.append(value);

        return ID.toString();
    }
    public AdminAccount(String IC, String firstName, String lastName, String username, String email, String password) {
        super(IC, firstName, lastName, username, email, password);
        AdminID = GenerateID();
    }
    public AdminAccount(String IC, String firstName, String lastName, String username, String email, String password,
                        String status, LocalDateTime createDate, LocalDateTime deleteDate, String adminID) {
        super(IC, firstName, lastName, username, email, password, status, createDate, deleteDate);
        AdminID = adminID;
    }

    public String GetAdminID() {
        return AdminID;
    }
}
