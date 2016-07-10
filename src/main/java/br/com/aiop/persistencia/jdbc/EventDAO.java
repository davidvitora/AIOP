/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aiop.persistencia.jdbc;

import br.com.aiop.persistencia.entidades.Event;
import br.com.aiop.persistencia.entidades.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author David .V
 */

public class EventDAO {
    Connection con;
    JSONObject item = new JSONObject();
    JSONArray planejamento = new JSONArray();
    
    public EventDAO() throws ClassNotFoundException{
        this.con = ConexaoFactory.getConnection();
    }
    
    public void savePlanejamento(int idProjeto, String title, String description, String date, String stats, int idUser){
        String sql = "insert into aiop.planejamento"
                + "(title, description, date, stats, idProjeto, idUserModifier)"
                + "values (?, ?, ?, ?, ?, ?)";
        try{
            PreparedStatement prep = con.prepareStatement(sql);
            prep.setString(1, title);
            prep.setString(2, description);
            prep.setString(3, date);
            prep.setString(4, stats);
            prep.setString(5, Integer.toString(idProjeto));
            prep.setString(6, Integer.toString(idUser));
            prep.execute();
            
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
//Pega todos os eventos baseado no id do projeto
    public List<Event> getEvents(int idProject){
        List<Event> events = new ArrayList();
        Event event;
        String sql = "select * from aiop.event where idProject = ? ;";
        try{
            PreparedStatement prep = con.prepareStatement(sql);
            prep.setString(1, Integer.toString(idProject));
            ResultSet resultado = prep.executeQuery();
            while(resultado.next()){
                event = new Event();
                event.setId(Integer.parseInt(resultado.getString("id")));
                event.setIdProject(Integer.parseInt(resultado.getString("idProject")));
                event.setIdUserCreated(Integer.parseInt(resultado.getString("idUserCreated")));
                event.setTitle(resultado.getString("title"));
                event.setDescription(resultado.getString("description"));
                event.setCreated(new SimpleDateFormat(resultado.getString("created")));
                event.setModified(new SimpleDateFormat(resultado.getString("modified")));
                event.setStats(Integer.parseInt(resultado.getString("stats")));
                events.add(event);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return events;
    }
}
