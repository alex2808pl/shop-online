package de.telran.shoponline.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "cartitems")
public class CartItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CartItemID")
    private long cartItemID;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CartID")
    private Cart cart;

//    @Column(name = "ProductID")
//    private int productID;

    @Column(name = "Quantity")
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ProductID")
    private Products product;

}
