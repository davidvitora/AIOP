package br.com.aiop.rest.projeto;

import br.com.aiop.persistencia.entidades.Member;
import br.com.aiop.persistencia.entidades.User;
import br.com.aiop.persistencia.jdbc.MemberDAO;
import br.com.aiop.session.AIOPSession;
import br.com.aiop.util.Courier;
import br.com.aiop.util.Validate;
import java.sql.SQLException;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/membro")
public class RESTMembro {
    
    @EJB
    private AIOPSession aiopSession;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Member> getmember() throws ClassNotFoundException, SQLException{
        MemberDAO dao = new MemberDAO();
        List<Member> members = dao.getMembers(aiopSession);
        return members;
    }
    
    @GET
    @Path("/{search}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> searchUser(@PathParam("search") String search) throws ClassNotFoundException, SQLException{
        MemberDAO dao = new MemberDAO();
        List<User> resultSearchUsers = dao.getUsers(search);
        return resultSearchUsers;
    }
    
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveUser(Member member) throws ClassNotFoundException, SQLException{
        member.setIdProject(aiopSession.getProject().getId());
        Courier courier;
        MemberDAO dao = new MemberDAO();
        courier = Validate.validateMember(member, dao, aiopSession);
        if(courier == null){
            courier = new Courier();
            if(dao.save(member)){
                courier.setCode(200);
                courier.setMessage("Membro adicionado com sucesso");
                return Response.status(200).entity(courier).build();
            }else{
                courier.setCode(400);
                courier.setMessage("Falha ao adicionar membro");
                return Response.status(400).entity(courier).build();
            }
        }
        return Response.status(400).entity(courier).build();
    }
    
}
