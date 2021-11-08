package com.teksystems.bootcamp.commerce_data.models.DBEntities;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name = "products")
@Entity
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sku", nullable = false)
    private Integer id;

    @Column(name = "title", nullable = false, length = 300)
    private String title;

    @Column(name = "category", length = 700)
    private String category;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "product_description", length = 700)
    private String productDescription;

    @Column(name = "qty", nullable = false)
    private Integer qty;

    @Column(name = "product_img", length = 300)
    private String productImg;

    @Column(name = "alt_text", length = 300)
    private String altText;

    public String getAltText() {
        return altText;
    }

    public void setAltText(String altText) {
        this.altText = altText;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}