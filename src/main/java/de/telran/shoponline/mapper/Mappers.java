package de.telran.shoponline.mapper;

import de.telran.shoponline.dto.CartDto;
import de.telran.shoponline.dto.ProductsDto;
import de.telran.shoponline.dto.UsersDto;
import de.telran.shoponline.entity.Cart;
import de.telran.shoponline.entity.Products;
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
//        modelMapper.typeMap(Users.class, UsersDto.class)
//                .addMappings(mapper -> mapper.skip(UsersDto::setPasswordHash));
        UsersDto usersDto = modelMapper.map(user, UsersDto.class); //автомат
        //Разруливаем вручную двухстороннюю связь один-к-одному
        Cart cart = user.getCart();
        if (cart!=null) {
            cart.setUser(null);
            usersDto.setCartDto(convertToCartDto(cart));
        }
//             usersDto.setPasswordHash("***"); // замещаем пароль фиктивным значением
        return usersDto;
    }

    public Users convertToUsers(UsersDto userDto) {
        Users users = modelMapper.map(userDto, Users.class); //автомат
        return users;
    }

    public CartDto convertToCartDto(Cart cart) {
        CartDto cartDto = modelMapper.map(cart, CartDto.class);
        //Разруливаем вручную двухстороннюю связь один-к-одному
        Users users = cart.getUser();
        if(users!=null) {
            users.setCart(null);
            cartDto.setUserDto(convertToUsersDto(users));
        }
        return cartDto;
    }

    public ProductsDto convertToProductsDto(Products products) {
        ProductsDto productsDto = modelMapper.map(products, ProductsDto.class);
        return productsDto;
    }

    public Products convertToProducts(ProductsDto productsDto) {
        Products products = modelMapper.map(productsDto, Products.class);
        return products;
    }


}
