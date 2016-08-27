/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aiop.persistencia.jdbc;

import br.com.aiop.persistencia.entidades.Project;
import br.com.aiop.persistencia.entidades.timeline.GenericTimeline;
import br.com.aiop.persistencia.entidades.timeline.TimelinePost;
import br.com.aiop.persistencia.entidades.timeline.file.FileUploadTimeline;
import java.sql.Connection;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author David .V
 */
public class TimelineDAO {
    Connection con;
    
    public TimelineDAO() throws ClassNotFoundException{
        this.con = ConexaoFactory.getConnection();
    }
    
    public List<GenericTimeline> getTimeline(Project project){
        GenericTimeline timeline;
        List<GenericTimeline> timelineList = new ArrayList();
        TimelinePost timelinepost = new TimelinePost();
        timelinepost.setId(1);
        timelinepost.setType(1);
        timelinepost.setContent("Conteudo");
        timelineList.add(timelinepost);
        FileUploadTimeline fuTimeline = new FileUploadTimeline();
        fuTimeline.setId(2);
        fuTimeline.setType(2);
        fuTimeline.setFileName("Anderson.jsp");
        timelineList.add(fuTimeline);
        return timelineList;
    }
  
}
