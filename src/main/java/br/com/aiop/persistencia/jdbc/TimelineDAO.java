/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aiop.persistencia.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author David .V
 */
public class TimelineDAO {
    Connection con;
    JSONObject response = new JSONObject();
    JSONArray responses = new JSONArray();
     public TimelineDAO() throws ClassNotFoundException{
        this.con = ConexaoFactory.getConnection();
    }
    
    public void saveResposta(String titulo, String corpo, int idtopico){
        String sql = "insert into respostas (titulo,corpo,idtopico) values ( ? , ? , ?);";
        try{
        PreparedStatement prep = con.prepareStatement(sql);
        prep.setString(1, titulo);
        prep.setString(2, corpo);
        prep.setString(3, Integer.toString(idtopico));
        prep.execute();
        }catch(SQLException e){
            
        }
    }
    public String getTimeline(int idProjeto){
        String sql = "select * from aiop.timeline where idProjeto = ? ;";
        try{
            PreparedStatement prep = con.prepareStatement(sql);
            prep.setString(1, Integer.toString(idProjeto));
            ResultSet resultado = prep.executeQuery();
            while(resultado.next()){
                response.put("id", resultado.getString("id"));
                response.put("description", resultado.getString("description"));
                responses.put(response);
                response = new JSONObject();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return responses.toString();
    }
    
    public void saveTimeline_EventoCriado(int idProjeto, JSONObject evento){
        String description = "<div class='timeline' style='border-top:1px solid; padding-top:5px; width: auto;'>" +
                            "<h3 class=\"timeline center-block\" > NOVO EVENTO </h3>" +
                            "<h4 align='center'>" + evento.getString("title") + "</h4>" +
                            "<span class=\"glyphicon glyphicon-calendar\" aria-hidden=\"true\"></span>" +
                            "<h4 align='left' style='font-size:20px;'>Data do evento:" + evento.getString("date") + "</h4>" +
                            "<h4 align='left' style='font-size:20px;'>Descrição: " + evento.getString("description") + "</h4>" +
                            "<p align='center'> Stats:" + evento.getString("stats") + "</p>" +
                            "</div>";

        String sql = "insert into aiop.timeline"
                + "(idProjeto, description)"
                + "values (?, ?)";
        try{
            PreparedStatement prep = con.prepareStatement(sql);
            prep.setString(1, Integer.toString(idProjeto));
            prep.setString(2, description);
            prep.execute();
            
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void saveTimeline_EventoDataModificada(int idProjeto, JSONObject evento, String newDate){
        String description = "<div class='timeline' style='border-top:1px solid; padding-top:5px; width: auto;'>" +
                            "<h3 class=\"timeline center-block\" > DATA DE EVENTO ALTERADA</h3>" +
                            "<h4 align='center'>" + evento.getString("title") + "</h4>" +
                            "<span class=\"glyphicon glyphicon-calendar\" aria-hidden=\"true\"></span>" +
                            "<h4 align='left' style='font-size:20px;'>Antiga data:" + evento.getString("date") + "</h4>" +
                            "<h4 align='left' style='font-size:20px;'>Nova data: " + newDate + "</h4>" +
                            "<p align='center'> Stats:" + evento.getString("stats") + "</p>" +
                            "</div>";

        String sql = "insert into aiop.timeline"
                + "(idProjeto, description)"
                + "values (?, ?)";
        try{
            PreparedStatement prep = con.prepareStatement(sql);
            prep.setString(1, Integer.toString(idProjeto));
            prep.setString(2, description);
            prep.execute();
            
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
}
