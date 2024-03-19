package de.telran.shoponline.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CartID")
    private int cartID;
    @Column(name = "UserID")
    private int userID;

    @OneToOne(mappedBy = "cart")
    private User user;
}
