package de.telran.shoponline.entity.query;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ProductsCount {
    @Id
    @Column(name = "ProductID")
    private long productID;

    @Column(name = "Name")
    private String name;

    @Column(name = "Count")
    private Long count;

    @Column(name = "Sum")
    private Double sum;
}
