package br.com.aiop.persistencia.jdbc;

import br.com.aiop.persistencia.entidades.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author David .V
 */
public class UserDAO {
    private final Connection con;
    

    public UserDAO() throws ClassNotFoundException{
        this.con = ConexaoFactory.getConnection();
    }
    
    
   /** Tenta realizar o login do usuário na sessão utilizando credenciais 
     * enviadas
     * @param login String do login do usuário
     * @param password String do password do usuário
     * @return Retorna o objeto do usuário em caso de login de sucesso ou null
     * em caso de falha
     * @throws java.sql.SQLException
     * @throws java.text.ParseException*/
    public User tryLogin(String login, String password) throws SQLException, ParseException{
        User user = new User();
        Date date;
        String sql = "select * from aiop.user where login = ? and password = ?;";
        try{
            PreparedStatement prep = con.prepareStatement(sql);
            prep.setString(1, login);
            prep.setString(2, password);
            ResultSet resultado = prep.executeQuery();
            while(resultado.next()){
                user.setId(resultado.getInt("id"));
                user.setLogin(resultado.getString("login"));
                user.setName(resultado.getString("name"));
                user.setPassword(resultado.getString("password"));
                date = resultado.getDate("created");
                user.setBirthDay(date);
                user.setBirthDay(date);
                user.setEmail(resultado.getString("email"));
                user.setContact(resultado.getString("contact"));
                return user;
            }
            }catch(SQLException e){
        }
        
        return null;
    }
    
    /** Verifica se um login está disponivel para uso
     * @param login String do login a ser verificado
     * @return Retorna true caso esteja disponivel e falso caso não*/
    public boolean isAvaliable(String login){
        String sql = "SELECT * FROM aiop.user where login = ? ";
        try{
            PreparedStatement prep = con.prepareStatement(sql);
            prep.setString(1, login);
            ResultSet result = prep.executeQuery();
            while(result.next()){
                return false;
            }
        }catch(SQLException e){
        }
        return true;
    }
    
    /** Recebe  Objeto do usuário cadastrado e salva no banco de dados
     * @param user Objeto do usuário a ser salvo no banco de dados
     * @return retorna true em caso de saldo com sucesso e false em caso de falha*/
    public boolean save(User user){
        String sql = "INSERT INTO `aiop`.`user`"+
        "(`name`, `login`, `password`, `email`, `birthDay`, `contact`, `created`)" +
        "VALUES ( ?, ?, ?, ?, ?, ?, ?)";
        
        try{
            PreparedStatement prep = con.prepareStatement(sql);
            prep.setString(1, user.getName());
            prep.setString(2, user.getLogin());
            prep.setString(3, user.getPassword());
            prep.setString(4, user.getEmail());
            prep.setDate(5, new java.sql.Date(user.getBirthDay().getTime()));
            prep.setString(6, user.getContact());
            prep.setDate(7, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
            prep.execute();
            return true;
            
        }catch(SQLException e){
        }
        return false;
        
    }
}
