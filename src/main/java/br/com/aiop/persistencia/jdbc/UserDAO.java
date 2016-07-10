/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aiop.persistencia.jdbc;

import br.com.aiop.persistencia.entidades.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

/**
 *
 * @author David .V
 */
public class UserDAO {
    Connection con;
    
    public UserDAO() throws ClassNotFoundException{
        this.con = ConexaoFactory.getConnection();
    }
    
    public User tryLogin(String login, String password) throws SQLException{
        User user = new User();
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
                user.setCreated(new SimpleDateFormat(resultado.getString("created")));
                user.setBirthDay(new SimpleDateFormat(resultado.getString("created")));
                user.setEmail(resultado.getString("email"));
                user.setContact(resultado.getString("contact"));
                return user;
            }
            }catch(SQLException e){
                e.printStackTrace();
        }
        
        return null;
    }
}
