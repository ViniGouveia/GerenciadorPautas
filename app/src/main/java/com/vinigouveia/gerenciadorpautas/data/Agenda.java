package com.vinigouveia.gerenciadorpautas.data;

import java.io.Serializable;

public class Agenda implements Serializable {
    private Integer id;
    private String title;
    private String shortDescription;
    private String description;
    private String author;
    private String authorEmail;
    private Boolean status;

    public Agenda() {
    }

    public Agenda(Integer id, String title, String shortDescription, String description, String author, String authorEmail) {
        this.id = id;
        this.title = title;
        this.shortDescription = shortDescription;
        this.description = description;
        this.author = author;
        this.authorEmail = authorEmail;
        this.status = true;
    }

    public int getAgendaId() {
        return this.id;
    }

    public void setAgendaId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDescription() {
        return this.shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthorEmail() {
        return this.authorEmail;
    }

    public void setAuthorEmail(String authorEmail) {
        this.authorEmail = authorEmail;
    }

    public Boolean isOpened() {
        return this.status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}
