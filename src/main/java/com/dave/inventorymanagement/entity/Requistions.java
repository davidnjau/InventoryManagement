package com.dave.inventorymanagement.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "requisitions")
public class Requistions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String product_name;
    @Column
    private String product_quantity;
    @Column
    private String user_id;
    @Column
    private double details;
    @CreationTimestamp
    private Date created_at;

    public Requistions() {
    }

    public Requistions(String product_name, String product_quantity, String user_id, double details) {
        this.product_name = product_name;
        this.product_quantity = product_quantity;
        this.user_id = user_id;
        this.details = details;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_quantity() {
        return product_quantity;
    }

    public void setProduct_quantity(String product_quantity) {
        this.product_quantity = product_quantity;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public double getDetails() {
        return details;
    }

    public void setDetails(double details) {
        this.details = details;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
}
