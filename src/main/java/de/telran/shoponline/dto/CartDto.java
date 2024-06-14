package de.telran.shoponline.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.telran.shoponline.entity.CartItems;
import de.telran.shoponline.entity.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {
    private long cartID;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("user")
    UsersDto userDto;

}
