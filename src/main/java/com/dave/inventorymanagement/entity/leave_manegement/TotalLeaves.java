package com.dave.inventorymanagement.entity.leave_manegement;

import javax.persistence.*;

@Entity
@Table(name = "total_leaves ")
public class TotalLeaves {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String user_id;

    @Column
    private int total_leaves;

    @Column
    private int total_used_leaves;

    public TotalLeaves() {
    }

    public TotalLeaves(String user_id, int total_leaves, int total_used_leaves) {
        this.user_id = user_id;
        this.total_leaves = total_leaves;
        this.total_used_leaves = total_used_leaves;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getTotal_leaves() {
        return total_leaves;
    }

    public void setTotal_leaves(int total_leaves) {
        this.total_leaves = total_leaves;
    }

    public int getTotal_used_leaves() {
        return total_used_leaves;
    }

    public void setTotal_used_leaves(int total_used_leaves) {
        this.total_used_leaves = total_used_leaves;
    }
}
