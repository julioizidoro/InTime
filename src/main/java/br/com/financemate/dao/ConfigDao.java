package br.com.financemate.dao;

import br.com.financemate.connection.ConectionFactory;
import br.com.financemate.model.Config;
import java.sql.SQLException;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Wolverine
 */
public class ConfigDao {
    
     public Config consultar() throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery("select c from Config c");
        Config config = null;
        if (q.getResultList().size()>0){
            config = (Config) q.getResultList().get(0);
        }
        manager.getTransaction().commit();
        return config;
    }
    
}