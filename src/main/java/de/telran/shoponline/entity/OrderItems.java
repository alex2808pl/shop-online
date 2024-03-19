package de.telran.shoponline.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
public class OrderItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderItemID")
    private int orderItemID;

    @Column(name = "Quantity")
    private int quantity;

    @Column(name = "PriceAtPurchase")
    private double priceAtPurchase;

    private int orderID;

    private int productID;
}
