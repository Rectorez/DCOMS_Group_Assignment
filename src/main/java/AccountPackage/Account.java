package AccountPackage;

import java.io.Serializable;
import java.time.LocalDateTime;

public abstract class Account implements Serializable {
    private String IC;
    private String FirstName;
    private String LastName;
    private String Username;
    private String Email;
    private String Password;
    private String Status;
    private LocalDateTime CreateDate;
    private LocalDateTime DeleteDate;

    public Account(String IC, String firstName, String lastName, String username, String email, String password) {
        this.IC = IC;
        FirstName = firstName;
        LastName = lastName;
        Username = username;
        Email = email;
        Password = password;
        Status = "N/A";
        CreateDate = LocalDateTime.now();
        DeleteDate = null;
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

    public String GetIC() {
        return IC;
    }
    public String GetFirstName() {
        return FirstName;
    }
    public String GetLastName() {
        return LastName;
    }
    public String GetUsername() {
        return Username;
    }
    public String GetEmail() {
        return Email;
    }
    public String GetPassword() {
        return Password;
    }
    public String GetStatus() {
        return Status;
    }
    public LocalDateTime GetCreateDate() {
        return CreateDate;
    }
    public LocalDateTime GetDeleteDate() {
        return DeleteDate;
    }

    public String toString(){
        return String.format("%s %s | %s | %s | %s", FirstName, LastName, Username, Email, Password);
    }
}
