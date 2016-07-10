package br.com.aiop.persistencia.entidades;

import java.text.DateFormat;

public class User {
    private int id;
    private String name;
    private String login;
    private String password;
    private String email;
    private String contact;
    private DateFormat birthDay;
    private DateFormat created;
    

    public User(){
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public DateFormat getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(DateFormat birthDay) {
        this.birthDay = birthDay;
    }

    public DateFormat getCreated() {
        return created;
    }

    public void setCreated(DateFormat created) {
        this.created = created;
    }
    
}
