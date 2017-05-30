package br.com.aiop.rest.projeto;

import br.com.aiop.persistencia.entidades.User;
import br.com.aiop.persistencia.jdbc.UserDAO;
import br.com.aiop.session.AIOPSession;
import br.com.aiop.util.Courier;
import br.com.aiop.util.Validate;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/usuario")
public class RESTUser {
    
    @EJB
    private AIOPSession aiopSession;
    
    /**
     *
     * @param user Objeto do usuário que será convertido do JSON enviado a
     * solicitação POST
     * @return retorna Courier de resposta em formato JSON
     * @throws ClassNotFoundException
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveUser(User user) throws ClassNotFoundException{
        UserDAO dao = new UserDAO();
        Courier courier;
        courier = Validate.validateUser(user, dao);
        if(courier == null){
            courier = new Courier();
            if(dao.save(user)){
                courier.setCode(200);
                courier.setMessage("All rigth");
            return Response.status(200).entity(courier).build();
            }else{
                courier.setCode(404);
                courier.insertMessages("Erro ao tentar criar usuário");
                return Response.status(404).entity(courier).build();
            }
        }
        return Response.status(404).entity(courier).build();
    }
    
    /**
     *
     * @return Retorna JSON com o usuário que foi procurado
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(){
        return Response.status(200).entity(aiopSession.getUser()).build();
    }

}
