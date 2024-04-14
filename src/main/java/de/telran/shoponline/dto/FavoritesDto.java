package de.telran.shoponline.dto;

import de.telran.shoponline.entity.Products;
import de.telran.shoponline.entity.Users;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
public class FavoritesDto {
    private long favoriteID;


//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "UserID")
//    private Users user;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "ProductID")
//    private Products product;

}
