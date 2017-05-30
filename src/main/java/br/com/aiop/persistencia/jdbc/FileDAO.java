/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aiop.persistencia.jdbc;

import br.com.aiop.persistencia.entidades.ProjectFile;
import br.com.aiop.session.AIOPSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author David .V
 */
public class FileDAO { 
    private static Connection con;
    
    
    public FileDAO() throws ClassNotFoundException{
        this.con = ConexaoFactory.getConnection();
    }
    
    //Retorna arquivo do projeto
    public List<ProjectFile> getFiles(AIOPSession session){
        List<ProjectFile> files = new ArrayList();
        ProjectFile file;
        String sql;
        try{
            sql = "select * from aiop.file where idProject = ? ;";
            PreparedStatement prep = con.prepareStatement(sql);
            prep.setInt(1, session.getProject().getId());
            ResultSet resultado = prep.executeQuery();
            while(resultado.next()){
                    file = new ProjectFile();
                    file.setName(resultado.getString("name"));
                    file.setId(resultado.getInt("id"));
                    file.setIdProject(resultado.getInt("idProject"));
                    file.setModified(resultado.getDate("modified"));
                    file.setVersion(resultado.getInt("version"));
                    file.setKey(resultado.getString("fileKey"));
                    files.add(file);
                }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return files;
    }
    
    //Verifica se o arquivo é valido
    public boolean isValid(ProjectFile file){
        String sql = "select * from aiop.file where idProject = ? and id = ? ;";
        try{
            PreparedStatement prep = con.prepareStatement(sql);
            prep.setInt(1, file.getIdProject());
            prep.setInt(2, file.getId());
            ResultSet resultado = prep.executeQuery();
            while(resultado.next()){
                return true;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
    //Verifica se a chave de indentificação já foi gerada anteriormente
    public boolean isKeyTaken(String key){
        String sql = "select * from aiop.file where fileKey = ? ;";
        try{
            PreparedStatement prep = con.prepareStatement(sql);
            prep.setString(1, key);
            ResultSet resultado = prep.executeQuery();
            while(resultado.next()){
                return true;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
    //Salva o registro do arquivo
    public boolean save(ProjectFile file){
        String sql = "INSERT INTO aiop.file (idProject, modified , name, version, fileKey) values (?,?,?,?,?)";
        try{
            PreparedStatement prep = con.prepareStatement(sql);
            prep.setInt(1, file.getIdProject());
            prep.setDate(2, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
            prep.setString(3, file.getName());
            prep.setInt(4, file.getVersion());
            prep.setString(5, file.getKey());
            prep.execute();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
