package br.com.financemate.dao;

import br.com.financemate.connection.ConectionFactory;
import br.com.financemate.model.Projeto;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class ProjetoDao {
    
    public Projeto salvar(Projeto projeto) throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        projeto = manager.merge(projeto);
        manager.getTransaction().commit();
        return projeto;
    }
    
    public List<Projeto> listar(String nome) throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery("select p from Projeto p order by p.nome");
        List<Projeto> lista = q.getResultList();
        manager.getTransaction().commit();
        return lista;
    }
    
}