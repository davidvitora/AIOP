/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aiop.persistencia.jdbc;

import br.com.aiop.persistencia.entidades.Member;
import br.com.aiop.persistencia.entidades.Project;
import br.com.aiop.persistencia.entidades.User;
import br.com.aiop.session.AIOPSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author David .V
 */
public class MemberDAO { 
    Connection con;
    
    
    public MemberDAO() throws ClassNotFoundException{
        this.con = ConexaoFactory.getConnection();
    }
    
    //Verifica a tabela de permissoes os usuários do projeto e retorna a lista de instacia desses usuários
    public List<Member> getMembers(AIOPSession session){
        List<Member> members = new ArrayList();
        Member member;
        String sql = "select * from aiop.permission where idProject = ? ;";
        ResultSet resultadoMembro;
        try{
            PreparedStatement prep = con.prepareStatement(sql);
            prep.setInt(1, session.getProject().getId());
            ResultSet resultado = prep.executeQuery();
            while(resultado.next()){
                sql = "select * from aiop.user where id = ? ;";
                //Consulta os dados dos membros
                prep = con.prepareStatement(sql);
                prep.setString(1, resultado.getString("idUser"));
                resultadoMembro = prep.executeQuery();
                while(resultadoMembro.next()){
                    member = new Member();
                    member.setPermission(resultado.getInt("userPermission"));
                    member.setBirthDay(resultadoMembro.getDate("birthDay"));
                    member.setEmail(resultadoMembro.getString("email"));
                    member.setName(resultadoMembro.getString("name"));
                    member.setId(resultadoMembro.getInt("id"));
                    member.setLogin(resultadoMembro.getString("login"));
                    members.add(member);
                    System.out.println(member.toString());
                }
                
            }
            //Pega o registro do dono do projeto
            sql = "select * from aiop.user where id = ? ;";
            prep = con.prepareStatement(sql);
            System.out.println("ID do dono" + session.getProject().getIdOwner());
            prep.setInt(1, session.getProject().getIdOwner());
            resultadoMembro = prep.executeQuery();
            while(resultadoMembro.next()){
                member = new Member();
                member.setPermission(1);
                member.setBirthDay(resultadoMembro.getDate("birthDay"));
                member.setEmail(resultadoMembro.getString("email"));
                member.setName(resultadoMembro.getString("name"));
                member.setId(resultadoMembro.getInt("id"));
                member.setLogin(resultadoMembro.getString("login"));
                members.add(member);
            }  
        }catch(SQLException e){
            e.printStackTrace();
        }
        return members;
    }
    
    //Procura usuario para ser adicionados
    public List<User> getUsers(String search){
        List<User> searchResult = new ArrayList();
        User user;
        String sql = "select * from aiop.user where name Like ?";
        try{
            PreparedStatement prep = con.prepareStatement(sql);
            prep.setString(1, "%" + search + "%");
            ResultSet resultado = prep.executeQuery();
            while(resultado.next()){
                user = new User();
                user.setId(resultado.getInt("id"));
                user.setName(resultado.getString("name"));
                user.setLogin(resultado.getString("login"));
                searchResult.add(user);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return searchResult;
    }
    
    //Verifica se um membro pertence ao projeto e retorna verdadeiro caso sim
    public boolean isMember(Member member){
        String sql = "SELECT * FROM aiop.permission where idUser = ? and idProject = ?;";
        try{
            PreparedStatement prep = con.prepareStatement(sql);
            prep.setInt(1, member.getId());
            prep.setInt(2, member.getIdProject());
            ResultSet resultado = prep.executeQuery();
            while(resultado.next()){
                return true;
            }
            sql = "SELECT * FROM aiop.project where idOwner = ? and id = ?;";
            prep = con.prepareStatement(sql);
            prep.setInt(1, member.getId());
            prep.setInt(2, member.getIdProject());
            resultado = prep.executeQuery();
            while(resultado.next()){
                return true;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
    //Verifica se o projeto é valido
    public boolean isProjectValid(int idProject) throws SQLException{
        String sql = "select * from aiop.project where id = ?";
        try{
            PreparedStatement prep = con.prepareStatement(sql);
            prep.setInt(1, idProject);
            ResultSet resultProject = prep.executeQuery();
            //Se o projeto ser valido retornará verdadeiro
            while(resultProject.next()){
                    return true;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
    //Verifica se o usuário é valido
    public boolean isUserValid(int idUser) throws SQLException{
        String sql = "select * from aiop.user where id = ?";
        try{
            PreparedStatement prep = con.prepareStatement(sql);
            prep.setInt(1, idUser);
            ResultSet resultProject = prep.executeQuery();
            //Se o usuário ser valido retornará verdadeiro
            while(resultProject.next()){
                    return true;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean save(Member member){
        String sql = "INSERT INTO aiop.permission (idUser, idProject, userPermission) values (?,?,?)";
        try{
            PreparedStatement prep = con.prepareStatement(sql);
            prep.setInt(1, member.getId());
            prep.setInt(2, member.getIdProject());
            prep.setInt(3, member.getPermission());
            prep.execute();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
