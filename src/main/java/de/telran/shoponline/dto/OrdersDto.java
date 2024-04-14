package de.telran.shoponline.dto;

import de.telran.shoponline.entity.OrderItems;
import de.telran.shoponline.entity.Users;
import de.telran.shoponline.entity.enums.Status;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class OrdersDto {
    private long orderId;
    private Timestamp createdAt;
    private String deliveryAddress;
    private String contactPhone;
    private String deliveryMethod;
    private Status status;
    private Timestamp updatedAt;



//    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
//    private Set<OrderItems> orderItems = new HashSet<>();
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "UserID")
//    private Users user;
}
