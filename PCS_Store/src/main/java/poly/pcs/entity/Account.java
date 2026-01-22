package poly.pcs.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Accounts")
@Data
public class Account {
    @Id
    private String username;
    private String password;
    private String fullname;
    private String email;
    private String photo;
    private Boolean activated;
    private Boolean admin;
}
