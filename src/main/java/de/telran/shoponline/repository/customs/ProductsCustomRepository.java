package de.telran.shoponline.repository.customs;

import de.telran.shoponline.entity.Categories;
import de.telran.shoponline.entity.Products;

import java.util.List;

public interface ProductsCustomRepository {
    List<Products> findProductsByFilter(Categories category, Double minPrice, Double maxPrice,
                                        Boolean isDiscount, String sort);
}
