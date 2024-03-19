package de.telran.shoponline.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class CartItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartItemID;


    @Column(name = "cartID")
    private int cartID;

    @Column(name = "productID")
    private int productID;
    @Column(name = "quantity")
    private int quantity;

}
