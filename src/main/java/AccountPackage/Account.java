package AccountPackage;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Account implements Serializable {
    private String IC;
    private String FirstName;
    private String LastName;
    private String Username;
    private String Email;
    private String Password;
    private AccountStatus Status;
    private LocalDateTime CreateDate;
    private LocalDateTime DeleteDate;

    public Account(String IC, String firstName, String lastName, String username, String email, String password) {
        this.IC = IC;
        FirstName = firstName;
        LastName = lastName;
        Username = username;
        Email = email;
        Password = password;
        Status = AccountStatus.ACTIVE;
        CreateDate = LocalDateTime.now();
        DeleteDate = null;
    }
    public Account(String IC, String firstName, String lastName, String username, String email,
                   String password, AccountStatus status, LocalDateTime createDate, LocalDateTime deleteDate) {
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
    public AccountStatus GetStatus() {
        return Status;
    }
    public LocalDateTime GetCreateDate() {
        return CreateDate;
    }
    public LocalDateTime GetDeleteDate() {
        return DeleteDate;
    }

    public boolean Delete() {
        if (Status.equals(AccountStatus.DELETED)) return false;
        Status = AccountStatus.DELETED;
        DeleteDate = LocalDateTime.now();
        return true;
    }

    public String toString(){
        return String.format("%s %s | %s | %s | %s", FirstName, LastName, Username, Email, Password);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(IC, account.IC) && Objects.equals(FirstName, account.FirstName) && Objects.equals(LastName, account.LastName) && Objects.equals(Username, account.Username) && Objects.equals(Email, account.Email) && Objects.equals(Password, account.Password) && Status == account.Status && Objects.equals(CreateDate, account.CreateDate) && Objects.equals(DeleteDate, account.DeleteDate);
    }
    @Override
    public int hashCode() {
        return Objects.hash(IC, FirstName, LastName, Username, Email, Password, Status, CreateDate, DeleteDate);
    }
}
