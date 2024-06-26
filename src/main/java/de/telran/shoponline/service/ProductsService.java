package de.telran.shoponline.service;

import de.telran.shoponline.config.MapperUtil;
import de.telran.shoponline.dto.ProductsDto;
import de.telran.shoponline.entity.Categories;
import de.telran.shoponline.entity.Products;
import de.telran.shoponline.entity.query.ProductsCount;
import de.telran.shoponline.mapper.Mappers;
import de.telran.shoponline.repository.CartRepository;
import de.telran.shoponline.repository.CategoriesRepository;
import de.telran.shoponline.repository.ProductsRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductsService {
    private static final Logger log = LoggerFactory.getLogger(ProductsService.class);
    private final ProductsRepository productsRepository;
    private final CategoriesRepository categoriesRepository;
    private final Mappers mappers;

    public List<ProductsDto> getProducts(Long categoryId, Double minPrice, Double maxPrice,
                                         Boolean isDiscount, String sort) {

        log.info("categoryId = " + categoryId);
        log.info("minPrice = " + minPrice);
        log.info("maxPrice = " + maxPrice);
        log.info("isDiscount = " + isDiscount);
        log.info("sort = " + sort); //name,(asc|desc); price(asc|desc); date

        Categories category = categoriesRepository.findById(categoryId).orElse(null);

       // List<Products> productsEntity = productsRepository.findAll(Sort.by(Sort.Direction.DESC, "name"));
        List<Products> productsEntity = productsRepository.findProductsByFilter(category, minPrice, maxPrice,
                isDiscount, sort);
        List<ProductsDto> productsDtoList = MapperUtil.convertList(productsEntity, mappers::convertToProductsDto);
        return productsDtoList;


    }

    public List<ProductsCount> getTop10Products(String status) {
        List<ProductsCount> list = (List<ProductsCount>)(List<?>)productsRepository.findTop10Products(status);
        return list;
    }
}
