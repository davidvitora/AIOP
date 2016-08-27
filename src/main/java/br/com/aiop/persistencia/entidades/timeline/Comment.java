/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aiop.persistencia.entidades.timeline;

import java.util.Date;

/**
 *
 * @author David .V
 */
public class Comment {
    private int idUser;
    private int idTimeline;
    private int idProject;
    private String content;
    private Date date;
    
    public Comment(){
        
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdTimeline() {
        return idTimeline;
    }

    public void setIdTimeline(int idTimeline) {
        this.idTimeline = idTimeline;
    }

    public int getIdProject() {
        return idProject;
    }

    public void setIdProject(int idProject) {
        this.idProject = idProject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
