package br.com.aiop.rest.projeto;
import br.com.aiop.session.AIOPSession;
import br.com.aiop.util.LoginCred;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.text.ParseException;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/session")
public class RESTSession{
    
    @EJB
    private AIOPSession aiopSession;
    
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response TryLogin(LoginCred credentials) throws URISyntaxException, ClassNotFoundException, SQLException, ParseException{
        if(aiopSession.isLoged()){
            return Response.status(400).entity("Usuário já logado").build();
        }else if(aiopSession.Login(credentials.getLogin(), credentials.getPassword())){
            return Response.status(200).build();
        }
        return Response.status(401).entity("Credenciais incorretas").build();
    }
    
    @POST
    @Path("/logOff")
    @Produces(MediaType.TEXT_PLAIN)
    public String logOff(){
        aiopSession.logOff();
        return "Loged off";
    }
 
}
