package de.telran.shoponline.controller;

import de.telran.shoponline.dto.CartDto;
import de.telran.shoponline.dto.ProductsDto;
import de.telran.shoponline.entity.query.ProductsCount;
import de.telran.shoponline.service.CartService;
import de.telran.shoponline.service.ProductsService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
@RequiredArgsConstructor
public class ProductsController {

    private final ProductsService productsService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<ProductsDto> getProducts(
            @RequestParam(value = "category", required = false) Long categoryId,
            @RequestParam(value = "min_price", required = false)  Double minPrice,
            @RequestParam(value = "max_price", required = false)  Double maxPrice,
            @RequestParam(value = "is_discount", required = false, defaultValue = "false")  Boolean isDiscount,
            @RequestParam(value = "sort", required = false)  String sort
            ) {
        List<ProductsDto> productList = productsService.getProducts(
                categoryId,
                minPrice,
                maxPrice,
                isDiscount,
                sort
                );
        return productList;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/top10")
    public List<ProductsCount> getTop10Products( @RequestParam(value = "status", required = false) String status) {
        return  productsService.getTop10Products(status);
    }

}
