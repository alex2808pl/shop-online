package de.telran.shoponline.service;

import de.telran.shoponline.dto.CartDto;
import de.telran.shoponline.dto.UsersDto;
import de.telran.shoponline.entity.Cart;
import de.telran.shoponline.entity.Users;
import de.telran.shoponline.exceptions.NotFoundInDbException;
import de.telran.shoponline.mapper.Mappers;
import de.telran.shoponline.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final Mappers mappers;

    public CartDto getCartById(Long id) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new NotFoundInDbException("Не найдена корзина с таким Id"));

        CartDto cartDto = new CartDto(cart.getCartID(), null);
//        CartDto cartDto = mappers.convertToCartDto(cart);
        return cartDto;
    }
}
