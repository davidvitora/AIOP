package br.com.aiop.session; 

import br.com.aiop.persistencia.entidades.Project;
import br.com.aiop.persistencia.entidades.User;
import br.com.aiop.persistencia.jdbc.ProjectDAO;
import br.com.aiop.persistencia.jdbc.UserDAO;
import br.com.aiop.util.Courier;
import java.sql.SQLException;
import java.text.ParseException;
import javax.ejb.Stateless;

@Stateless
public class AIOPSession {
    
    private User user;
    private Project project;
    private int projectPermission;
    
    public AIOPSession(){
        
    }
    
    /** Verifica se há uma instancia de User pra verificar se o usuário está logado
     * @return retorna true caso logado e false caso não logado  */
    public boolean isLoged(){
        return getUser() != null;
    }
    
    //Retorna usuário da sessão
    public User getUser() {
        return user;
    }
    
    /** Realiza login do usuário conforme as credenciais enviadas
     * @param login
     * @param password
     * @return Retorna true em caso de sucesso e false em caso de falha ao logar
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.text.ParseException*/
    public boolean Login(String login, String password) throws ClassNotFoundException, SQLException, ParseException{
        UserDAO dao = new UserDAO();
        user = dao.tryLogin(login, password);
        return user != null;
    }
    
    public Project getProject() {
        return project;
    }
    
    //Verifica se o usuário está vizualizando algum projeto
    public boolean isSeeProject(){
        return project != null;
    }
    
    private void setProject(Project project) {
        this.project = project;
    }
    
    //Permissioes do projeto
    public int getProjectPermission() {
        return projectPermission;
    }

    private void setProjectPermission(int projectPermission) {
        this.projectPermission = projectPermission;
    }
    
    
    /** Tenta acesso ao projeto conforme o usuário logado na sessão atualmente
     * @param idProject inteiro que representa o id do projeto
     * @return Retorna o objeto de reposta a pagina
     * em caso de falha
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException*/
    public Courier acessProject(int idProject) throws ClassNotFoundException, SQLException {
        Courier courier = new Courier(); // Classe mensageira
        ProjectDAO dao = new ProjectDAO(); // Classe de acesso a dados
        //Verifica se o usuário possui acesso ao projeto
        if(dao.isMember(user, idProject)){
            //Define o acesso ao projeto e a permissao
            setProject(dao.getProject(idProject));
            setProjectPermission(dao.getPermission(user, idProject));
            //Informa para o courier que o projeto foi acessado
            if(isSeeProject()){
                courier.setCode(661);
                courier.setMessage("Projeto acessado");
            }
        }else{
            //Informa para o courier que o projeto não pode ser acessado
            courier.setCode(651);
            courier.setMessage("Sem permissao de acesso ao projeto ou não existente");
        }
        return courier;
    }
    
    /** Finaliza os objetos de usuário e projeto acessado da sessão*/
    public void logOff(){
        user = null;
        project = null;
    }

    

}
