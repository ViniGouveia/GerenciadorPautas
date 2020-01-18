package com.vinigouveia.gerenciadorpautas.data;

import androidx.room.Ignore;

import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private String email;
    private String password;

    public User() {
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getUserName() {
        return this.name;
    }

    public void setUserName(String name) {
        this.name = name;
    }

    public String getUserEmail() {
        return this.email;
    }

    public void setUserEmail(String email) {
        this.email = email;
    }

    public String getUserPassword() {
        return this.password;
    }

    public void setUserPassword(String password) {
        this.password = password;
    }
}
