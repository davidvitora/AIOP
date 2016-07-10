package br.com.aiop.persistencia.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class ConexaoFactory {
    
    public static Connection getConnection() throws ClassNotFoundException{
     String  host = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
     String  port =  System.getenv("OPENSHIFT_MYSQL_DB_PORT");
     String user = System.getenv("OPENSHIFT_MYSQL_DB_USERNAME");
     String pw = System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD"); 
        try{
            Class.forName("com.mysql.jdbc.Driver"); // essa linha pode resolver o problema
            return DriverManager.getConnection(
                   "jdbc:mysql://" + host +":"+ port + "/aiop", user , pw);
        }catch (SQLException e) {
            throw new RuntimeException(e);}
    }
}
