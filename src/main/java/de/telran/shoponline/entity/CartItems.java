package de.telran.shoponline.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class CartItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartItemID;


    @Column(name = "CartID")
    private int cartID;

    @Column(name = "ProductID")
    private int productID;
    @Column(name = "Quantity")
    private int quantity;

}
