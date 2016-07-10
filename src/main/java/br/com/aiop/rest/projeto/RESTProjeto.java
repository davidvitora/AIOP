package br.com.aiop.rest.projeto;

import br.com.aiop.persistencia.entidades.Project;
import br.com.aiop.persistencia.jdbc.ProjectDAO;
import br.com.aiop.session.AIOPSession;
import br.com.aiop.util.Courier;
import java.sql.SQLException;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/projetos")
public class RESTProjeto {
    
    @EJB
    private AIOPSession aiopSession;
    
    @GET
    @Path("/dono")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Project> getProjectsOwner() throws ClassNotFoundException, SQLException{
        ProjectDAO dao = new ProjectDAO();
        return dao.getOwnerProjects(aiopSession.getUser());
    }
    
    @GET
    @Path("/participante")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Project> getProjects() throws ClassNotFoundException, SQLException{
        ProjectDAO dao = new ProjectDAO();
        return dao.getOtherProjects(aiopSession.getUser());
    }
    
    @GET
    @Path("/acessar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProject() throws ClassNotFoundException, SQLException{
        ProjectDAO dao = new ProjectDAO();
        Courier courier = new Courier();
        if(aiopSession.isSeeProject() && aiopSession.isLoged()){
            return Response.status(Response.Status.OK).entity(dao.getProject(aiopSession.getProject().getId())).build();
        }
        courier.setCode(400);
        courier.setMessage("Não foi possivel obter dados do projeto");
        return Response.status(Response.Status.BAD_REQUEST).entity(courier).build();
    }
    
    @POST
    @Path("/acessar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response acessProject(Project project) throws ClassNotFoundException, SQLException{
        Courier courier;
        courier = aiopSession.acessProject(project.getId());
        if(courier.getCode() == 661){
            return Response.status(Response.Status.OK).entity(courier).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).entity(courier).build();
    }
    
}
