package de.telran.shoponline.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Categories")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Categories {
    @Id
    @Column(name = "CategoryID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryID;

    @Column(name = "Name")
    private String name;
}
