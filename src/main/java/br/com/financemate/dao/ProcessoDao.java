package br.com.financemate.dao;

import br.com.financemate.connection.ConectionFactory;
import br.com.financemate.model.Processo;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Greici
 */
public class ProcessoDao {
    
    public Processo salvar(Processo processo) throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        processo = manager.merge(processo);
        manager.getTransaction().commit();
        return processo;
    }
    
    
     public Processo consultar(int idProcesso) throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery("select p from Processo p where p.idprocesso=" + idProcesso);
        Processo processo = null;
        if (q.getResultList().size()>0){
            processo = (Processo) q.getResultList().get(0);
        }
        manager.getTransaction().commit();
        return processo;
    }
     public List<Processo> listar(String sql) throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery(sql);
        List<Processo> lista = q.getResultList();
        manager.getTransaction().commit();
        return lista;
    }
}