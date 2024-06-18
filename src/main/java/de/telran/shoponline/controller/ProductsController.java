package de.telran.shoponline.controller;

import de.telran.shoponline.dto.CartDto;
import de.telran.shoponline.dto.ProductsDto;
import de.telran.shoponline.service.CartService;
import de.telran.shoponline.service.ProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
@RequiredArgsConstructor
public class ProductsController {

    private final ProductsService productsServiceService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<ProductsDto> getProducts(
            @RequestParam(value = "category", required = false) Long categoryId,
            @RequestParam(value = "min_price", required = false)  Double minPrice,
            @RequestParam(value = "max_price", required = false)  Double maxPrice,
            @RequestParam(value = "discount", required = false, defaultValue = "0.0")  Double discountPrice,
            @RequestParam(value = "sort", required = false)  String sort
            ) {
        List<ProductsDto> productList = productsServiceService.getProducts(
                categoryId,
                minPrice,
                maxPrice,
                discountPrice,
                sort
                );
        return productList;
    }

}
