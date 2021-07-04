package com.dave.inventorymanagement.entity.leave_manegement;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "leave_response")
public class LeaveResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String user_id;

    @Column
    private String status;

    @Column
    private String leave_id;

    @Column
    private Date from_Date;

    @Column
    private Date to_Date;

    @Column
    private String reasons;

    public LeaveResponse() {
    }

    public LeaveResponse(String user_id, String status, String leave_id, Date from_Date, Date to_Date, String reasons) {
        this.user_id = user_id;
        this.status = status;
        this.leave_id = leave_id;
        this.from_Date = from_Date;
        this.to_Date = to_Date;
        this.reasons = reasons;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLeave_id() {
        return leave_id;
    }

    public void setLeave_id(String leave_id) {
        this.leave_id = leave_id;
    }

    public Date getFrom_Date() {
        return from_Date;
    }

    public void setFrom_Date(Date from_Date) {
        this.from_Date = from_Date;
    }

    public Date getTo_Date() {
        return to_Date;
    }

    public void setTo_Date(Date to_Date) {
        this.to_Date = to_Date;
    }

    public String getReasons() {
        return reasons;
    }

    public void setReasons(String reasons) {
        this.reasons = reasons;
    }
}
