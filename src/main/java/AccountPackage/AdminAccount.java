package AccountPackage;

import java.time.LocalDateTime;

public class AdminAccount extends Account {
    private String AdminID;

    public AdminAccount(String firstName, String lastName, String username, String email, String password) {
        super(firstName, lastName, username, email, password);
    }
    public AdminAccount(String IC, String firstName, String lastName, String username, String email, String password, String status, LocalDateTime createDate, LocalDateTime deleteDate, String adminID) {
        super(IC, firstName, lastName, username, email, password, status, createDate, deleteDate);
        AdminID = adminID;
    }

    public String getAdminID() {
        return AdminID;
    }
}
