package de.telran.shoponline.entity;

import de.telran.shoponline.entity.enums.Role;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "password_hash")
    private String passwordHash;
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;
}
