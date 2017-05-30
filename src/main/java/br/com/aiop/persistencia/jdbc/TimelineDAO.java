/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aiop.persistencia.jdbc;

import br.com.aiop.persistencia.entidades.Project;
import br.com.aiop.persistencia.entidades.timeline.GenericTimeline;
import br.com.aiop.persistencia.entidades.timeline.TimelinePost;
import br.com.aiop.persistencia.entidades.timeline.file.TimelineFileUpload;
import br.com.aiop.session.AIOPSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author David .V
 */
public class TimelineDAO {
    Connection con;
    
    public TimelineDAO() throws ClassNotFoundException{
        this.con = ConexaoFactory.getConnection();
    }
    
    /* Consulta o banco e monta o JSON da timeline*/
    public JSONObject getTimeline(Project project){
        JSONObject obj;
        JSONArray array;
        array = new JSONArray();
        String sql = "SELECT * FROM aiop.timelinepost where idProject = ?";
        try{
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, project.getId());
            ResultSet result = pre.executeQuery();
            while(result.next()){
                obj = new JSONObject();
                obj.put("id", result.getInt("id"));
                obj.put("tipo", result.getInt("type"));
                obj.put("data", result.getDate("eventDate"));
                obj.put("usuario", result.getInt("idUser"));
                obj.put("uuid", result.getString("uuid"));
                obj.put("commentCont", result.getString("commentCont"));
                obj.put("limit", 0 );
                if(result.getInt("type") == 1){
                    obj.put("conteudo", result.getString("content"));
                }
                else if(result.getInt("type") == 2){
                    obj.put("nome_do_arquivo", result.getString("fileName"));
                }
                array.put(obj);
            }
        }catch(SQLException e){
            
        }
        obj = new JSONObject();
        obj.put("Lista", array);
        return obj;
    }
    
    public boolean saveTimelinePost(AIOPSession session, TimelinePost post){
        String sql = "Insert into timelinepost (type,idUser,idProject,eventDate,content,uuid) values (?,?,?,?,?,?)";
        try{
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, post.getType());
            pre.setInt(2, session.getUser().getId());
            pre.setInt(3, session.getProject().getId());
            pre.setDate(4, new java.sql.Date(System.currentTimeMillis()));
            pre.setString(5, post.getContent());
            pre.setString(6, post.getUuid());
            pre.execute();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean saveTimelineFileUpload(AIOPSession session, TimelineFileUpload post){
        String sql = "Insert into timelinefileupload (type,idUser,idProject,eventDate,fileName,uuid) values (?,?,?,?,?,?)";
        try{
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, post.getType());
            pre.setInt(2, session.getUser().getId());
            pre.setInt(3, session.getProject().getId());
            pre.setDate(4, new java.sql.Date(System.currentTimeMillis()));
            pre.setString(4, post.getFileName());
            pre.setString(6, post.getUuid());
            pre.execute();
            return true;
        }catch(SQLException e){
            
        }
        return false;
    }
}
