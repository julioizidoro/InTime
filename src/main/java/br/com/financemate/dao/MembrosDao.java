package br.com.financemate.dao;

import br.com.financemate.connection.ConectionFactory;
import br.com.financemate.model.Membros;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;


public class MembrosDao {
    
     public Membros salvar(Membros membros) throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        //abrindo uma transação
        manager.getTransaction().begin();
        membros = manager.merge(membros);
        //fechando uma transação
        manager.getTransaction().commit();
        return membros;
    }
    
    
    public List<Membros> listar(String sql) throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery(sql);
        List<Membros> lista = q.getResultList();
        manager.getTransaction().commit();
        return lista;
    }
    
    public void excluir(int idMembro) throws SQLException {
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Membros membros = manager.find(Membros.class, idMembro);
        manager.remove(membros);
        manager.getTransaction().commit();
    }
}
   