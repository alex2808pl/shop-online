package de.telran.shoponline.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@Data
@ToString
@Getter
@Setter
public class Favorites {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FavoriteID")
    private int favoriteID;

    private int userID;

    private int productID;

}
