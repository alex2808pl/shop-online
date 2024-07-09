package de.telran.shoponline.controller;

import de.telran.shoponline.dto.CartDto;
import de.telran.shoponline.exceptions.NotFoundInDbException;
import de.telran.shoponline.security.jwt.JwtAuthentication;
import de.telran.shoponline.service.CartService;
import org.modelmapper.spi.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<CartDto> getCartById(@PathVariable Long id) {
        //Можно получить инфу из токена, которыю мы перед этим извлекли из фильтра и положили в конетекст Security
        final JwtAuthentication jwtInfoToken = (JwtAuthentication)SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Controller Cart - "+jwtInfoToken.getUsername());
        //-------
        CartDto cart = cartService.getCartById(id);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

}
