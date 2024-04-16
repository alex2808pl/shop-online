package de.telran.shoponline.repository;

import de.telran.shoponline.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepository  extends JpaRepository<Categories, Long> {
}
