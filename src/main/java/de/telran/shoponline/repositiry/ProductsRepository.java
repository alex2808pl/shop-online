package de.telran.shoponline.repositiry;

import de.telran.shoponline.entity.Categories;
import de.telran.shoponline.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository   extends JpaRepository<Products, Long> {
}
