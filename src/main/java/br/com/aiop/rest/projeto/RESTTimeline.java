package br.com.aiop.rest.projeto;

import br.com.aiop.persistencia.entidades.timeline.Comment;
import br.com.aiop.persistencia.entidades.timeline.GenericTimeline;
import br.com.aiop.persistencia.entidades.timeline.TimelinePost;
import br.com.aiop.persistencia.jdbc.CommentDAO;
import br.com.aiop.persistencia.jdbc.TimelineDAO;
import br.com.aiop.session.AIOPSession;
import br.com.aiop.util.Courier;
import br.com.aiop.util.JsonMaker;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import javax.ejb.EJB;
import javax.json.JsonArray;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import javax.ws.rs.core.Response;
import org.json.JSONArray;
import org.json.JSONObject;

@Path("/timeline")
public class RESTTimeline {
    
    @EJB
    private AIOPSession aiopSession;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getTimeline() throws ClassNotFoundException{
        TimelineDAO dao = new TimelineDAO();
        return dao.getTimeline(aiopSession.getProject()).toString();
    }
    
    @POST
    @Path("/post")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response savePost(TimelinePost timeline) throws ClassNotFoundException{
        TimelineDAO dao = new TimelineDAO();
        Courier courier = new Courier();
        /**Preparando o timeline*/
        timeline.setType(1);
        timeline.setUuid(UUID.randomUUID().toString());
        /**Preparando o timeline*/
        if(dao.saveTimelinePost(aiopSession, timeline)){
           courier.setCode(200);
           courier.setMessage("OK");
           return Response.ok().entity(courier).build();
        }
        courier.setCode(400);
        courier.setMessage("Ocorreu um erro ao salvar a postagem");
        return Response.status(400).entity(courier).build();
    }
    
}
