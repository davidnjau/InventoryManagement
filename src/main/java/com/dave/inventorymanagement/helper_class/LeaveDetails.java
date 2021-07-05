package com.dave.inventorymanagement.helper_class;

public class LeaveDetails {

    private String reasons;
    private String from_date;
    private String to_date;
    private Long id;
    private String user_name;
    private String user_department;

    public LeaveDetails() {
    }

    public LeaveDetails(String reasons, String from_date, String to_date, Long id, String user_name, String user_department) {
        this.reasons = reasons;
        this.from_date = from_date;
        this.to_date = to_date;
        this.id = id;
        this.user_name = user_name;
        this.user_department = user_department;
    }

    public String getReasons() {
        return reasons;
    }

    public void setReasons(String reasons) {
        this.reasons = reasons;
    }

    public String getFrom_date() {
        return from_date;
    }

    public void setFrom_date(String from_date) {
        this.from_date = from_date;
    }

    public String getTo_date() {
        return to_date;
    }

    public void setTo_date(String to_date) {
        this.to_date = to_date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_department() {
        return user_department;
    }

    public void setUser_department(String user_department) {
        this.user_department = user_department;
    }
}
