/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aiop.rest.projeto;

import br.com.aiop.persistencia.entidades.timeline.Comment;
import br.com.aiop.persistencia.jdbc.CommentDAO;
import br.com.aiop.persistencia.jdbc.TimelineDAO;
import br.com.aiop.session.AIOPSession;
import br.com.aiop.util.Courier;
import java.util.List;
import java.util.UUID;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author David .V
 */
@Path("/comentario/{commentuuid}")
public class RESTComentarios {
    
    @EJB
    private AIOPSession aiopSession;
    
    @GET
    @Path("/limit/{limit}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Comment> getTimelineComments(@PathParam("commentuuid") String uuid, @PathParam("limit") int limit) throws ClassNotFoundException{
        CommentDAO dao = new CommentDAO();
      
        return dao.getComment(aiopSession.getProject().getId(), uuid, limit);
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveComment(Comment comment) throws ClassNotFoundException{
        CommentDAO dao = new CommentDAO();
        Courier courier = new Courier();
        if(dao.saveComment(aiopSession, comment)){
           courier.setCode(200);
           courier.setMessage("OK");
           return Response.ok().entity(courier).build();
        }
        courier.setCode(400);
        courier.setMessage("Ocorreu um erro ao salvar o comentário");
        return Response.status(400).entity(courier).build();
    }
}
