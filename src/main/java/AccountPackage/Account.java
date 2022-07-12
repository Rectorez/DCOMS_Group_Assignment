package AccountPackage;

import java.time.LocalDateTime;

public abstract class Account {
    private String IC;
    private String FirstName;
    private String LastName;
    private String Username;
    private String Email;
    private String Password;
    private String Status;
    private LocalDateTime CreateDate;
    private LocalDateTime DeleteDate;

    public Account() {
    }
    public Account(String firstName, String lastName, String username, String email, String password) {
        FirstName = firstName;
        LastName = lastName;
        Username = username;
        Email = email;
        Password = password;
    }
    public Account(String IC, String firstName, String lastName, String username, String email,
                   String password, String status, LocalDateTime createDate, LocalDateTime deleteDate) {
        this.IC = IC;
        FirstName = firstName;
        LastName = lastName;
        Username = username;
        Email = email;
        Password = password;
        Status = status;
        CreateDate = createDate;
        DeleteDate = deleteDate;
    }

    public String getIC() {
        return IC;
    }
    public String getFirstName() {
        return FirstName;
    }
    public String getLastName() {
        return LastName;
    }
    public String getUsername() {
        return Username;
    }
    public String getEmail() {
        return Email;
    }
    public String getPassword() {
        return Password;
    }
    public String getStatus() {
        return Status;
    }
    public LocalDateTime getCreateDate() {
        return CreateDate;
    }
    public LocalDateTime getDeleteDate() {
        return DeleteDate;
    }

    public boolean ConnectToServer() {
        return true;
    }
    public boolean Disconnect() {
        return true;
    }
    public boolean Login(String email, String password) {
        return true;
    }
}
