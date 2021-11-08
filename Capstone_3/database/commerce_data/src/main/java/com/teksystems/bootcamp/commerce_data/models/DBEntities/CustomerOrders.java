package com.teksystems.bootcamp.commerce_data.models.DBEntities;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "user_orders")
@Entity
public class CustomerOrders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    private Integer id;

    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "shipped_date")
    private LocalDate shippedDate;

    public LocalDate getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(LocalDate shippedDate) {
        this.shippedDate = shippedDate;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}