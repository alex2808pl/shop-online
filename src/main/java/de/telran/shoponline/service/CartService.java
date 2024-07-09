package de.telran.shoponline.service;

import de.telran.shoponline.dto.CartDto;
import de.telran.shoponline.dto.UsersDto;
import de.telran.shoponline.entity.Cart;
import de.telran.shoponline.entity.Users;
import de.telran.shoponline.exceptions.NotFoundInDbException;
import de.telran.shoponline.mapper.Mappers;
import de.telran.shoponline.repository.CartRepository;
import de.telran.shoponline.security.jwt.JwtAuthentication;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final Mappers mappers;

    public CartDto getCartById(Long id) {
        //Можно получить инфу из токена, которыю мы перед этим извлекли из фильтра и положили в конетекст Security
        final JwtAuthentication jwtInfoToken = (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Service - "+jwtInfoToken.getUsername());
        //-------
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new NotFoundInDbException("Не найдена корзина с таким Id"));
        CartDto cartDto = mappers.convertToCartDto(cart);
        return cartDto;
    }
}
