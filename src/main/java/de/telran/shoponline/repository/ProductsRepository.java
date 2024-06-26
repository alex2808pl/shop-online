package de.telran.shoponline.repository;

import de.telran.shoponline.entity.Categories;
import de.telran.shoponline.entity.Products;
import de.telran.shoponline.entity.query.ProductsCount;
import de.telran.shoponline.repository.customs.ProductsCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProductsRepository  extends JpaRepository<Products, Long>, ProductsCustomRepository {
    //Топ 10 купленных товаров
    //Топ 10 часто отменяемых товаров
//    @Query(value =
//            "SELECT p.ProductID, p.Name, CAST(SUM(Quantity) as Integer) Count, CAST(SUM(Quantity*Price) as Double) Sum " +
//            "FROM Products p JOIN OrderItems oi ON p.ProductID = oi.ProductID " +
//            "JOIN Orders o ON oi.OrderId = o.OrderID " +
//            "WHERE o.Status=?1 " +
//            "GROUP BY p.ProductID, p.Name " +
//            "ORDER BY Count DESC  " +
//            "LIMIT 10",
//            nativeQuery = true)
    @Query(value =
            "SELECT p.ProductID, p.Name, SUM(Quantity) Count, SUM(Quantity*Price) Sum " +
                    "FROM Products p JOIN OrderItems oi ON p.ProductID = oi.ProductID " +
                    "JOIN Orders o ON oi.OrderId = o.OrderID " +
                    "WHERE o.Status=?1 " +
                    "GROUP BY p.ProductID, p.Name " +
                    "ORDER BY Count DESC  " +
                    "LIMIT 10",
            nativeQuery = true)
    List findTop10Products(String status);

// Тут можно посмотреть пример, если нужно сделать еще интервал дат
// https://stackoverflow.com/questions/72978636/how-to-do-decimal-precision-in-spring-data-jpa-query-annotation



}
