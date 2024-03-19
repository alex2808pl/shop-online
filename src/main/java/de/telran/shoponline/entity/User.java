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

    @Column(name = "Name")
    private String name;
    @Column(name = "Email")
    private String email;
    @Column(name = "Phone_number")
    private String phoneNumber;
    @Column(name = "Password_hash")
    private String passwordHash;
    @Enumerated(EnumType.STRING)
    @Column(name = "Role")
    private Role role;
}
