package br.com.aiop.rest.projeto;

import br.com.aiop.persistencia.entidades.timeline.GenericTimeline;
import br.com.aiop.persistencia.jdbc.TimelineDAO;
import br.com.aiop.session.AIOPSession;
import br.com.aiop.util.JsonMaker;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import javax.ws.rs.core.Response;

@Path("/timeline")
public class RESTTimeline {
    
    @EJB
    private AIOPSession aiopSession;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTimeline() throws ClassNotFoundException{
        TimelineDAO dao = new TimelineDAO();
        return Response.ok().entity(JsonMaker.Timeline(dao.getTimeline(aiopSession.getProject()))).build();
    }
    
}
