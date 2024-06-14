package de.telran.shoponline.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.telran.shoponline.entity.Cart;
import de.telran.shoponline.entity.Favorites;
import de.telran.shoponline.entity.Orders;
import de.telran.shoponline.entity.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsersDto {
    private Long userID;
    private String name;
    private String email;
    private String phoneNumber;
    private String passwordHash;
    private Role role;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("cart")
    CartDto cartDto;

}
