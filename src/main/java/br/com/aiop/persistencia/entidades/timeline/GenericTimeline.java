package br.com.aiop.persistencia.entidades.timeline;

import java.util.Date;
import java.util.List;

public class GenericTimeline {
    private int id;
    private int type;
    private int idUser;
    private int idProject;
    private Date date;
    private String uuid;
    private int CommentCont;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdProject() {
        return idProject;
    }

    public void setIdProject(int idProject) {
        this.idProject = idProject;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getCommentCont() {
        return CommentCont;
    }

    public void setCommentCont(int CommentCont) {
        this.CommentCont = CommentCont;
    }
}
