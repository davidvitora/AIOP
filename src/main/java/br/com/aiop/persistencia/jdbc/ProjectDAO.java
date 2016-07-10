/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aiop.persistencia.jdbc;

import br.com.aiop.persistencia.entidades.Project;
import br.com.aiop.persistencia.entidades.User;
import br.com.aiop.session.AIOPSession;
import br.com.aiop.util.Courier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/**
 *
 * @author David .V
 */
public class ProjectDAO {
    Connection con;
    JSONObject response = new JSONObject();
    
    
    public ProjectDAO() throws ClassNotFoundException{
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
    
    //Retorna a lista de projetos em que o usuário participa
    public List<Project> getOtherProjects(User user){
        List<Project> projects = new ArrayList();
        Project project;
        ResultSet resultadoProjeto;
        String sql = "select * from aiop.permission where idUSer = ?";
        try{
            PreparedStatement prep = con.prepareStatement(sql);
            prep.setString(1, Integer.toString(user.getId()));
            ResultSet resultadoPermissoes = prep.executeQuery();
            sql = "select * from aiop.project where id = ?";
            prep = con.prepareStatement(sql);
            while(resultadoPermissoes.next()){
                // Ira executar busca para empresa apenas se o usuário não é dono
                if(resultadoPermissoes.getInt("userPermission") > 1){
                    //Inicia um projeto de define a permissão para o usuário
                    project = new Project();
                    project.setUserPermission(resultadoPermissoes.getInt("userPermission"));
                    
                    //Preparando prep para buscar empresa e pega resultado
                    prep.setString(1, resultadoPermissoes.getString("idProject"));
                    resultadoProjeto = prep.executeQuery();
                    while(resultadoProjeto.next()){
                        //Define os valores consultados para o projeto
                        project.setId(resultadoProjeto.getInt("id"));
                        project.setIdOwner(resultadoProjeto.getInt("idOwner"));
                        project.setName(resultadoProjeto.getString("name"));
                        project.setDescription(resultadoProjeto.getString("description"));
                        project.setCreated(new SimpleDateFormat(resultadoProjeto.getString("created")));
                    }
                    //Adicona o projeto na lista
                    projects.add(project);
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return projects;
    }
    
    //Retorna lista de projetos em que o usuário é dono
    public List<Project> getOwnerProjects(User user) throws SQLException{
        List<Project> projects = new ArrayList();
        Project project;
        String sql = "select * from aiop.project where idOwner = ?";
        try{
            PreparedStatement prep = con.prepareStatement(sql);
            prep.setString(1, Integer.toString(user.getId()));
            ResultSet resultProjects = prep.executeQuery();
            //Consulta os projetos e adiciona na lista
            while(resultProjects.next()){
                project = new Project();
                project.setUserPermission(1);
                project.setId(resultProjects.getInt("id"));
                project.setIdOwner(resultProjects.getInt("idOwner"));
                project.setName(resultProjects.getString("name"));
                project.setDescription(resultProjects.getString("description"));
                project.setCreated(new SimpleDateFormat(resultProjects.getString("created")));
                projects.add(project);
            }
      
        }catch(SQLException e){
            e.printStackTrace();
        }
        return projects;
    }
    
    //Verifica se o usuário possui acesso ao projeto
    public boolean isMember(User user, int idProject) throws SQLException{
        String sql = "select * from aiop.permission where idUSer = ? and idProject = ?";
        try{
            PreparedStatement prep = con.prepareStatement(sql);
            prep.setString(1, Integer.toString(user.getId()));
            prep.setString(2, Integer.toString(idProject));
            ResultSet resultProject = prep.executeQuery();
            //Se o usuario tiver acesso ao projeto, retornara verdadeiro
            while(resultProject.next()){
                    return true;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
    //Tenta acessar o projeto e retorna e resposta para o courier
    public Project getProject(int idProject) throws SQLException{
        Project project; // Classe projeto
        String sql = "select * from aiop.project where id = ?";
        
        try{
            PreparedStatement prep = con.prepareStatement(sql);
            prep.setString(1, Integer.toString(idProject));
            ResultSet resultProjects = prep.executeQuery();
            //Consulta os projetos e adiciona na lista
            while(resultProjects.next()){
                project = new Project();
                project.setId(resultProjects.getInt("id"));
                project.setIdOwner(resultProjects.getInt("idOwner"));
                project.setName(resultProjects.getString("name"));
                project.setDescription(resultProjects.getString("description"));
                project.setCreated(new SimpleDateFormat(resultProjects.getString("created")));
                return project;
            }
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    
    //Retorna a permissão do usuário para um projeto
    public int getPermission(User user, int idProject) throws SQLException{
        String sql = "select * from aiop.permission where idUSer = ? and idProject = ?";
        try{
            PreparedStatement prep = con.prepareStatement(sql);
            prep.setString(1, Integer.toString(user.getId()));
            prep.setString(2, Integer.toString(idProject));
            ResultSet resultProject = prep.executeQuery();
            //Se o usuario tiver acesso ao projeto, retornara verdadeiro
            while(resultProject.next()){
                return resultProject.getInt("userPermission");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
   
}

