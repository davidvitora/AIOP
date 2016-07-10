package br.com.aiop.persistencia.entidades;

import java.text.DateFormat;

public class Event {
    private int id;
    private int idProject;
    private int idUserCreated;
    private String title;
    private String description;
    private DateFormat created;
    private DateFormat modified;
    private int stats;
    
    public Event(){
        
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the idProject
     */
    public int getIdProject() {
        return idProject;
    }

    /**
     * @param idProject the idProject to set
     */
    public void setIdProject(int idProject) {
        this.idProject = idProject;
    }

    /**
     * @return the idUserCreated
     */
    public int getIdUserCreated() {
        return idUserCreated;
    }

    /**
     * @param idUserCreated the idUserCreated to set
     */
    public void setIdUserCreated(int idUserCreated) {
        this.idUserCreated = idUserCreated;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the created
     */
    public DateFormat getCreated() {
        return created;
    }

    /**
     * @param created the created to set
     */
    public void setCreated(DateFormat created) {
        this.created = created;
    }

    /**
     * @return the modified
     */
    public DateFormat getModified() {
        return modified;
    }

    /**
     * @param modified the modified to set
     */
    public void setModified(DateFormat modified) {
        this.modified = modified;
    }

    /**
     * @return the stats
     */
    public int getStats() {
        return stats;
    }

    /**
     * @param stats the stats to set
     */
    public void setStats(int stats) {
        this.stats = stats;
    }
    
}
