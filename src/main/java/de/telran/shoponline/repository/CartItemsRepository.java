package de.telran.shoponline.repository;

import de.telran.shoponline.entity.CartItems;
import org.springframework.data.repository.CrudRepository;

public interface CartItemsRepository extends CrudRepository<CartItems,Long> {
}
