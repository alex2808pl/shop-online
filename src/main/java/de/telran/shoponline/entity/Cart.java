package de.telran.shoponline.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "userID")
    private int userID;

}
