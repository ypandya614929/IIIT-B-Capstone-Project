package bookmyconsultation.appointmentservice.security;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;


@Entity
@Table(name = "user", schema = "fooddelivery")
public class User {

    @Id
    @NotBlank(message = "User Name cannot be blank")
    @Column(name = "user_name")
    private String username;
    @NotBlank(message = "Password cannot be blank")
    @Column(name = "password")
    private String password;
    @NotBlank(message = "Role cannot be blank")
    @Column(name = "role")
    private String role;

    public User() {
    }

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
