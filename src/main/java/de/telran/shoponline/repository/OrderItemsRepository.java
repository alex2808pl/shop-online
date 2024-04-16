package de.telran.shoponline.repository;

import de.telran.shoponline.entity.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemsRepository extends JpaRepository<OrderItems, Integer> {

}
