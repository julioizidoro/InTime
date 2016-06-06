package br.com.financemate.dao;

import br.com.financemate.connection.ConectionFactory;
import br.com.financemate.model.Processoatividade;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;


/**
 *
 * @author Julio
 */
public class ProcessoAtividadeDao {
    
    public Processoatividade salvar(Processoatividade processoatividade) throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        processoatividade = manager.merge(processoatividade);
        manager.getTransaction().commit();
        return processoatividade;
    }
    
    public List<Processoatividade> listar(String sql)throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery(sql);
        List<Processoatividade> lista = q.getResultList();
        manager.getTransaction().commit();
        return lista;
    }
    
    public void excluir(Processoatividade processoatividade) throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        processoatividade = manager.find(Processoatividade.class, processoatividade.getIdprocessoatividade());
        manager.remove(processoatividade);
        manager.getTransaction().commit();
    }
    
    public Processoatividade consultar(String sql) throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery(sql);
        List<Processoatividade> lista = q.getResultList();
        manager.getTransaction().commit();
        if (lista.size()>0){
            return lista.get(0);
                    
        }
        return null;
    }
}