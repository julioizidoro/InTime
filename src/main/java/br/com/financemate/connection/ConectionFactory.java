package br.com.financemate.connection;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;




/**
 *
 * @author Wolverine
 */

@ApplicationScoped
public class ConectionFactory {

    private static EntityManager manager;
    private static Connection conn;


    public static EntityManager getConnection() {
        EntityManagerFactory emf = null;
        manager = null;
        emf = Persistence.createEntityManagerFactory("intimePU");
        manager = emf.createEntityManager();
        if (!manager.isOpen()) {
            JOptionPane.showMessageDialog(null, "Conexão fechada");
        }
        return manager;
    }
    
    public static Connection getConexao(){
        if (conn==null){
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ConectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
            String driverName = "com.mysql.jdbc.Driver";
            String serverName = "200.175.61.147";
            String mydatabase ="intime";
            String url = "jdbc:mysql://" + serverName + ":8082/" + mydatabase;
            String username = "root";
            String password = "simples";
            try {
                conn = DriverManager.getConnection(url, username, password);
            } catch (SQLException ex) {
                Logger.getLogger(ConectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return conn;
        
    }
    
    public static Connection getConexaoDS(){
        Connection conexao = null;
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env/");
            javax.sql.DataSource ds = (javax.sql.DataSource) envContext.lookup("jdbc/intimeDS");//estou usando o Spring nessecaso por isso utilizo essa sinxtaxe
            conexao = (Connection) ds.getConnection();
        } catch (NamingException ex) {
            Logger.getLogger(ConectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conexao;
    }
    
}