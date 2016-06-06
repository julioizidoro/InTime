package br.com.financemate.dao;

import br.com.financemate.connection.ConectionFactory;
import br.com.financemate.model.Modulos;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;


public class ModuloDao {
    
    public Modulos salvar(Modulos modulos) throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        modulos = manager.merge(modulos);
        manager.getTransaction().commit();
        return modulos;
    }
    
    public List<Modulos> listar(String sql) throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery(sql);
        List<Modulos> lista = q.getResultList();
        manager.getTransaction().commit();
        return lista;
    }
    
}