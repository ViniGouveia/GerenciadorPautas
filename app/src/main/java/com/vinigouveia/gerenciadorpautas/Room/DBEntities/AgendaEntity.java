package com.vinigouveia.gerenciadorpautas.Room.DBEntities;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "agendas")
public class AgendaEntity {
    @PrimaryKey(autoGenerate = true)
    private Integer id;

    private String title;
    private String shortDescription;
    private String description;
    private String authorName;
    private String authorEmail;
    private Boolean status;

    public AgendaEntity(Integer id, String title, String shortDescription, String description, String authorName, String authorEmail) {
        this.id = id;
        this.title = title;
        this.shortDescription = shortDescription;
        this.description = description;
        this.authorName = authorName;
        this.authorEmail = authorEmail;
        this.status = true;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorEmail() {
        return authorEmail;
    }

    public void setAuthorEmail(String authorEmail) {
        this.authorEmail = authorEmail;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
