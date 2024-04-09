package de.telran.shoponline.mapper;

import de.telran.shoponline.dto.CartDto;
import de.telran.shoponline.dto.UsersDto;
import de.telran.shoponline.entity.Cart;
import de.telran.shoponline.entity.Users;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mappers {
    @Autowired
    private ModelMapper modelMapper;

    public UsersDto convertToUsersDto(Users user) {
        if(user==null)
            return null; //new UsersDto();
        modelMapper.typeMap(Users.class, UsersDto.class)
                .addMappings(mapper -> mapper.skip(UsersDto::setPasswordHash));
        UsersDto usersDto = modelMapper.map(user, UsersDto.class); //автомат
        usersDto.setCartDto(convertToCartDto(user.getCart()));
//        usersDto.setPasswordHash("***");
        return usersDto;
    }

    private CartDto convertToCartDto(Cart cart) {
        CartDto cartDto = modelMapper.map(cart, CartDto.class);
        return cartDto;
    }


}
