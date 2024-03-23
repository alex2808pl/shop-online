package de.telran.shoponline.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CartID")
    private int cartID;

    @OneToMany(mappedBy = "cart")
    private List<CartItems> cartItems;

    @OneToOne/*(mappedBy = "cart")*/
    @JoinColumn(name = "UserID", referencedColumnName = "UserID")
    private Users user;
}
