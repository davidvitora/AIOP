/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aiop.persistencia.jdbc;

import br.com.aiop.persistencia.entidades.User;
import br.com.aiop.util.DateRest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.ws.rs.core.GenericEntity;

/**
 *
 * @author David .V
 */
public class UserDAO {
    private Connection con;
    

    public UserDAO() throws ClassNotFoundException{
        this.con = ConexaoFactory.getConnection();
    }
    
    public User tryLogin(String login, String password) throws SQLException, ParseException{
        User user = new User();
        Date date = new Date();
        String sql = "select * from aiop.user where login = ? and password = ?;";
        try{
            PreparedStatement prep = con.prepareStatement(sql);
            prep.setString(1, login);
            prep.setString(2, password);
            ResultSet resultado = prep.executeQuery();
            while(resultado.next()){
                user.setId(resultado.getInt("id"));
                user.setLogin(resultado.getString("login"));
                user.setName(resultado.getString("name"));
                user.setPassword(resultado.getString("password"));
                date = resultado.getDate("created");
                user.setBirthDay(date);
                user.setBirthDay(date);
                user.setEmail(resultado.getString("email"));
                user.setContact(resultado.getString("contact"));
                return user;
            }
            }catch(SQLException e){
                e.printStackTrace();
        }
        
        return null;
    }
    
    //Verifica se um login está disponivel para uso;
    public boolean isAvaliable(String login){
        String sql = "SELECT * FROM aiop.user where login = ? ";
        try{
            PreparedStatement prep = con.prepareStatement(sql);
            prep.setString(1, login);
            ResultSet result = prep.executeQuery();
            while(result.next()){
                return false;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return true;
    }
    
    public boolean save(User user){
        String sql = "INSERT INTO `aiop`.`user`"+
        "(`name`, `login`, `password`, `email`, `birthDay`, `contact`, `created`)" +
        "VALUES ( ?, ?, ?, ?, ?, ?, ?)";
        
        try{
            PreparedStatement prep = con.prepareStatement(sql);
            prep.setString(1, user.getName());
            prep.setString(2, user.getLogin());
            prep.setString(3, user.getPassword());
            prep.setString(4, user.getEmail());
            prep.setDate(5, new java.sql.Date(user.getBirthDay().getTime()));
            prep.setString(6, user.getContact());
            prep.setDate(7, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
            if(prep.execute()){
                return true;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
        
    }
}
