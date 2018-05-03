package com.example.dell.restful_json.Model.OOP;

import java.util.List;

/**
 * Created by Dell on 3/31/2018.
 */

public class Categories {
    private int id;
    private String name;
    private int parent_id;
    private String description;
    private String created_at;
    private String updated_at;

    public Categories() {
    }

    public Categories(int id, String name, int parent_id) {
        this.id = id;
        this.name = name;
        this.parent_id = parent_id;
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

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public List<Categories> getListcon() {
        return listcon;
    }

    public void setListcon(List<Categories> listcon) {
        this.listcon = listcon;
    }

    List<Categories>listcon;
}
