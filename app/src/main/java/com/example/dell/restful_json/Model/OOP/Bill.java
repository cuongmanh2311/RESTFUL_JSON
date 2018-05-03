package com.example.dell.restful_json.Model.OOP;

/**
 * Created by Dell on 4/24/2018.
 */

public class Bill {
    private int id;
    private int member_id;
    private long total;
    private  int status;

    public Bill()
    {

    }
    public Bill(int id, int member_id, long total, int status) {
        this.id = id;
        this.member_id = member_id;
        this.total = total;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
