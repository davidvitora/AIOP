/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aiop.util;

import br.com.aiop.persistencia.entidades.Member;
import br.com.aiop.persistencia.entidades.Project;
import br.com.aiop.persistencia.entidades.User;
import br.com.aiop.persistencia.jdbc.MemberDAO;
import br.com.aiop.persistencia.jdbc.ProjectDAO;
import br.com.aiop.persistencia.jdbc.UserDAO;
import br.com.aiop.session.AIOPSession;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author David .V
 */
public class Validate {
    
    //realizará a validação do usuário, retornará courier com excessoes e caso seja valido retorná null
    public static Courier validateUser(User user, UserDAO dao){
        Courier courier = new Courier();
        courier.setCode(400);
        //Validar login
        if(dao.isAvaliable(user.getLogin()) == false){
            courier.insertMessages("O login informado já está sendo utilizado");
        }
        else if( user.getLogin().length() < 5 ){
            courier.insertMessages("O login deverá ter no minimo 5 caracteres");
        }
        
        else if(user.getLogin().length() > 16){
            courier.insertMessages("O login deverá ter no maximo 16 caracteres");
        }else if(user.getLogin().matches("^[a-zA-Z0-9]+$") == false){
            courier.insertMessages("O login não deverá ter caracteres especiais");
        }
        //Validar senha
        if( user.getPassword().length() < 5 ){
            courier.insertMessages("A senha deverá ter no minimo 5 caracteres");
        } else if(user.getPassword().length() > 20){
            courier.insertMessages("A senha deverá ter no maximo 20 caracteres");
        }
        //Validar nome
        if( user.getName().length() < 2 ){
            courier.insertMessages("O nome deverá ter no minimo 2 caracteres");
        } else if(user.getName().length() > 30){
            courier.insertMessages("O nome deverá ter no maximo 30 caracteres");
        }else if(user.getName().replaceAll(" ", "").matches("^[a-zA-Z]+$") == false){
            courier.insertMessages("O nome não deverá ter caracteres especiais ou numeross");
        }
        //Validar email
        if(user.getEmail().matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])") == false){
            courier.insertMessages("Por favor digite um e-mail valido");
        }
        
        //Testa a data
        Calendar cal = Calendar.getInstance();
        cal.set(1900, 1, 1);
        if(user.getBirthDay().before(cal.getTime())){
            courier.insertMessages("Insirá uma data valida");
        }
        cal.setTime(Calendar.getInstance().getTime());
        if(user.getBirthDay().after(cal.getTime())){
            courier.insertMessages("Insirá uma data valida");
        }
        
        //Validar contato
        if(user.getContact().replaceAll(" ", "").matches("^[0-9]+$") == false){
            courier.insertMessages("O contato deverá ter apenas números");
        }else if(user.getContact().length() < 8 ){
            courier.insertMessages("O número deverá ter pelo menos 8 digitos");
        }else if(user.getContact().length() > 15 ){
            courier.insertMessages("O número deverá ter no maximo 15 digitos");
        }
        
        //Retornará o courier se a lista de mensagens com pelo menos um item
        if(courier.getMessages().isEmpty() == false){
            return courier;
        }
        
        return null;
    }
    
    public static Courier validateProject(Project project, ProjectDAO dao){
        Courier courier = new Courier();
        
        //Validando o nome
        if(dao.isAvaliable(project.getName()) == false){
            courier.insertMessages("O nome do projeto já está sendo utilizado");
        }else if( project.getName().replaceAll(" ", "").length() < 5 ){
            courier.insertMessages("O nome do projeto deverá ter no minimo 6 caracteres");
        }
        else if(project.getName().length() > 16){
            courier.insertMessages("O nome do projeto deverá ter no maximo 16 caracteres");
        }else if(project.getName().replaceAll(" ", "").matches("^[a-zA-Z0-9]+$") == false){
            courier.insertMessages("O nome do projeto não deverá ter caracteres especiais");
        }
        
        //Validando a descrição
        if(project.getDescription().length() > 1000){
            courier.insertMessages("A descrição do projeto deverá ter no maximo 10 caracteres");
        }
        
        if(courier.getMessages().isEmpty() == false){
            return courier;
        }
        
        return null;
    }
    
    public static Courier validateMember(Member member, MemberDAO dao, AIOPSession session) throws SQLException{
        Courier courier = new Courier();
        if(dao.isMember(member)){
            courier.setCode(400);
            courier.insertMessages("O Usuário já é membro do projeto");
        }
        if(member.getPermission() == 1){
            courier.setCode(400);
            courier.insertMessages("Não é possivel definir mais de um dono para o projeto");
        }
        if(member.getPermission() <= 1 || member.getPermission() > 3){
            courier.setCode(400);
            courier.insertMessages("Nivel de permissão não valido");
        }
        if(dao.isProjectValid(member.getIdProject()) == false){
            courier.setCode(400);
            courier.insertMessages("Projeto não é valido");
        }
        if(dao.isUserValid(member.getId()) == false){
            courier.setCode(400);
            courier.insertMessages("Usuário não é valido");
        }
        if(session.getProjectPermission() < 1 && session.getProjectPermission() > 2){
            courier.setCode(400);
            courier.insertMessages("Você não tem permissão para adicionar membros ao projeto");
        }
        if(courier.getMessages().isEmpty() == false){
            return courier;
        }
        return null;
    }
}
