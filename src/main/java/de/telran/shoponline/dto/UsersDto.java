package de.telran.shoponline.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.telran.shoponline.entity.Cart;
import de.telran.shoponline.entity.Favorites;
import de.telran.shoponline.entity.Orders;
import de.telran.shoponline.entity.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "Invalid name: Empty name")
    @Size(min = 2, max = 30, message = "Invalid firstName: Must be of 2 - 30 characters")
    private String name;

    @Email(message = "Invalid email")
    private String email;

    @NotBlank(message = "Invalid Phone number: Empty number")
    @Pattern(regexp = "^\\d{12}$", message = "Invalid phone number")
    private String phoneNumber;

    private String passwordHash;
    private Role role;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("cart")
    CartDto cartDto;

}
