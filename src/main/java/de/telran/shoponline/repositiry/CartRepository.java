package de.telran.shoponline.repositiry;

import de.telran.shoponline.entity.Cart;
import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends CrudRepository<Cart,Long> {
}
