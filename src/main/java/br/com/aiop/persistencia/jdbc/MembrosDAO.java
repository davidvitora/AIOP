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
public class MembrosDAO { 
    Connection con;
    JSONObject membro = new JSONObject();
    JSONArray membros = new JSONArray();
    
    
    public MembrosDAO() throws ClassNotFoundException{
        this.con = ConexaoFactory.getConnection();
    }
    
    public String getMembros(int idProjeto){
        String sql = "select * from aiop.registropermissoes where idProjeto = ? ;";
        ResultSet resultadoMembro;
        try{
            PreparedStatement prep = con.prepareStatement(sql);
            prep.setString(1, Integer.toString(idProjeto));
            ResultSet resultado = prep.executeQuery();
            while(resultado.next()){
                membro.put("id", resultado.getString("idUser"));
                membro.put("permition", resultado.getString("idPermissao"));
                sql = "select * from aiop.user where id = ? ;";
                //Consulta os dados do membro
                prep = con.prepareStatement(sql);
                prep.setString(1, resultado.getString("idUser"));
                resultadoMembro = prep.executeQuery();
                while(resultadoMembro.next()){
                    membro.put("name", resultadoMembro.getString("name"));
                    membro.put("contact", resultadoMembro.getString("contact"));
                }
                //---
                membros.put(membro);
                membro = new JSONObject();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return membros.toString();
    }
}
