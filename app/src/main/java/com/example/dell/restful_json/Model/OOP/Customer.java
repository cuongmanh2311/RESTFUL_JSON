package com.example.dell.restful_json.Model.OOP;

/**
 * Created by Dell on 3/28/2018.
 */

public class Customer {
    private String name;
    private String email;
    private int role;
    private String password;
    private String created_at;
    private String updated_at;
    private int id;

    public Customer(int id,String name, String email, int role, String password) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.password = password;
        this.id=id;
    }

    public Customer() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
