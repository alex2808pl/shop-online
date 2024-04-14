package de.telran.shoponline.repositiry;

import de.telran.shoponline.entity.CartItems;
import org.springframework.data.repository.CrudRepository;

public interface CartItemsRepository extends CrudRepository<CartItems,Long> {
}
