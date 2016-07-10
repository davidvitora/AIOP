package br.com.aiop.persistencia.entidades;

import java.text.DateFormat;


public class Project {
    private int id;
    private int idOwner;
    private String name;
    private String description;
    private DateFormat created;
    private int userPermission;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(int idOwner) {
        this.idOwner = idOwner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DateFormat getCreated() {
        return created;
    }

    public void setCreated(DateFormat created) {
        this.created = created;
    }

    public int getUserPermission() {
        return userPermission;
    }

    public void setUserPermission(int userPermission) {
        this.userPermission = userPermission;
    }
    
    
}
