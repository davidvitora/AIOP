/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aiop.rest.projeto;

import br.com.aiop.persistencia.entidades.ProjectFile;
import br.com.aiop.persistencia.jdbc.FileDAO;
import br.com.aiop.session.AIOPSession;
import br.com.aiop.util.Courier;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import java.util.UUID;


@Path("/files")
public class RESTArquivo{
    
    @EJB
    private AIOPSession aiopSession;
    
    @POST
    @Path("/{fileName}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response upload(@PathParam("fileName") String fileName, @FormDataParam("file") InputStream uploadedInputStream,  
            @FormDataParam("file") FormDataContentDisposition fileDetail) throws ClassNotFoundException{
        FileDAO dao = new FileDAO();
        Courier courier = new Courier();
        //retorna nulo caso o usuário não esteja logado ou não esteja em uma sessão
        if(aiopSession.getProject() == null || aiopSession.getUser() == null){
            return null;
        }
        
        //Gera a chave de identificação para o arquivo e verifica que já existe uma no banco
        boolean Taken = false;
        String key = UUID.randomUUID().toString();
        //while(dao.isKeyTaken(key)){
        //    key = UUID.randomUUID().toString();
        //}
        
        //Criando entidade do arquivo
        ProjectFile file = new ProjectFile();
        file.setIdProject(aiopSession.getProject().getId());
        file.setName(fileName);
        file.setVersion(1);
        file.setKey(key);
        
        //Criando o diretório
        File diretorio = new File("c:\\Teste\\" + file.getKey());
        diretorio.mkdir();
        
        String fileLocation = "c:\\Teste\\" + file.getKey() + "\\" + fileDetail.getFileName();
            //saving file  
            try {  
                FileOutputStream out = new FileOutputStream(new File(fileLocation));  
                int read = 0;  
                byte[] bytes = new byte[1024];  
                out = new FileOutputStream(new File(fileLocation));  
                while ((read = uploadedInputStream.read(bytes)) != -1) {  
                    out.write(bytes, 0, read);  
                }  
                out.flush();  
                out.close();
                dao.save(file);
                courier.setCode(200);
                courier.setMessage("Arquivo gravado com sucesso com chave" + file.getKey());
            } catch (IOException e) {
                e.printStackTrace();
                courier.setCode(400);
                courier.setMessage("Erro ao gravar arquivo");
            }  
            return Response.status(200).entity(courier).build();  
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProjectFile> getFiles() throws ClassNotFoundException{
        FileDAO dao = new FileDAO();
        List<ProjectFile> files = dao.getFiles(aiopSession);
        return files;
    }
    
    @GET
    @Path("/{chave}")
    @Produces("image/png")
    public Response download(@PathParam("chave") String chave){
        File file = new File("c:\\Teste\\" + chave);
        File[] read = file.listFiles();
        file = read[0];
        ResponseBuilder builder = Response.ok((Object)file);
        builder.header("Content-disposition","attachment; filename= '" + file.getName() + "'");
        return builder.build();
    }
}
