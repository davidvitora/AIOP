/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aiop.persistencia.jdbc;

import br.com.aiop.persistencia.entidades.timeline.Comment;
import br.com.aiop.persistencia.entidades.timeline.TimelinePost;
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
public class CommentDAO {

    private final Connection con;
    
    public CommentDAO() throws ClassNotFoundException{
        this.con = ConexaoFactory.getConnection();
    }
    
    public List<Comment> getComment( int projectId, String uuid, int limit){
        Comment comment;
        List<Comment> comments = new ArrayList();
        String sql = "SELECT * FROM aiop.comment where idProject = ? and uuid = ? limit ? ";
        try{
            PreparedStatement prep = con.prepareStatement(sql);
            prep.setInt(1, projectId);
            prep.setString( 2, uuid);
            prep.setInt(3, limit);
            ResultSet result = prep.executeQuery();
            while(result.next()){
                comment = new Comment();
                comment.setId(result.getInt("id"));
                comment.setIdProject(result.getInt("idProject"));
                comment.setIdUser(result.getInt("idUser"));
                comment.setUuid(result.getString("uuid"));
                comment.setDate(result.getDate("commentdate"));
                comment.setContent(result.getString("content"));
                comments.add(comment);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
       return comments; 
    }
    
    public boolean saveComment(AIOPSession session, Comment comment){
        String sql = "Insert into comment (idUser,content,idProject,commentdate,uuid) values (?,?,?,?,?)";
        try{
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, session.getUser().getId());
            pre.setString(2, comment.getContent());
            pre.setInt(3, session.getProject().getId());
            pre.setDate(4, new java.sql.Date(System.currentTimeMillis()));
            pre.setString(5, comment.getUuid());
            pre.execute();
            sql = "update timelinepost set commentCont = commentCont+1 where uuid = ?";
            pre = con.prepareStatement(sql);
            pre.setString(1, comment.getUuid());
            pre.execute();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
    
}
