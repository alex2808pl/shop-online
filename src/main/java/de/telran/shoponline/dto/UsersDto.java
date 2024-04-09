package de.telran.shoponline.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.telran.shoponline.entity.Cart;
import de.telran.shoponline.entity.Favorites;
import de.telran.shoponline.entity.Orders;
import de.telran.shoponline.entity.enums.Role;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class UsersDto {
    private long userID;
    private String name;
    private String email;
    private String phoneNumber;
    private String passwordHash;
    private Role role;

    @JsonProperty("cart")
    CartDto cartDto;


//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private Set<Orders> orders = new HashSet<>();
//
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private Set<Favorites> favorites = new HashSet<>();
//
//    @OneToOne(mappedBy = "user")
//    private Cart cart;
}
