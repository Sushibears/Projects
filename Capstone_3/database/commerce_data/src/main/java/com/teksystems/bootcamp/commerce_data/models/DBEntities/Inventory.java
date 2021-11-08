package com.teksystems.bootcamp.commerce_data.models.DBEntities;

import javax.persistence.*;
import java.time.Instant;

@Table(name = "inventory")
@Entity
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id", nullable = false)
    @JoinColumn(name = "item_id")
    private Integer id;

    @Column(name = "stock_num", nullable = false)
    @JoinColumn(name = "stock_num")
    private Integer stockNum;

    @Column(name = "last_update")
    private Instant lastUpdate;

    public Instant getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Instant lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Integer getStockNum() {
        return stockNum;
    }

    public void setStockNum(Integer stockNum) {
        this.stockNum = stockNum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}