package org.BinarAcademy.Challenge_4.model.users;

import lombok.NoArgsConstructor;
import org.BinarAcademy.Challenge_4.model.order.Order;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )

    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "nama")
    private String nama;

    @Enumerated(EnumType.STRING)
    @Column(name = "google_id")
    private org.BinarAcademy.Challenge_4.service.UserService.Provider provider;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User(String nama, String password, String email){
        this.nama = nama;
        this.password = password;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }


    public void setProvider(org.BinarAcademy.Challenge_4.service.UserService.Provider provider) {
        this.provider=provider;
    }
}