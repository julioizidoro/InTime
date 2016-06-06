package br.com.financemate.dao;

import br.com.financemate.connection.ConectionFactory;
import br.com.financemate.model.Processorotina;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Julio
 */
public class ProcessoRotinaDao {
   
    public Processorotina salvar(Processorotina processorotina) throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        processorotina = manager.merge(processorotina);
        manager.getTransaction().commit();
        return processorotina;
    }
    
    public List<Processorotina> listar(String sql)throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery(sql);
        List<Processorotina> lista = q.getResultList();
        manager.getTransaction().commit();
        return lista;
    }
    
    public void excluir(Processorotina processorotina) throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        processorotina = manager.find(Processorotina.class, processorotina.getIdprocessorotina());
        manager.remove(processorotina);
        manager.getTransaction().commit();
    }
    
    public Processorotina consultar(String sql) throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery(sql);
        List<Processorotina> lista = q.getResultList();
        manager.getTransaction().commit();
        if (lista.size()>0){
            return lista.get(0);
                    
        }
        return null;
    }
}