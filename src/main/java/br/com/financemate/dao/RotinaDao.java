package br.com.financemate.dao;

import br.com.financemate.connection.ConectionFactory;
import br.com.financemate.model.Rotina;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;


public class RotinaDao {
    
    public Rotina consultar(int idRotina) throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery("select r from Rotina r where r.idrotina=" + idRotina);
        Rotina rotina = null;
        if (q.getResultList().size()>0){
            rotina = (Rotina) q.getResultList().get(0);
        }
        manager.getTransaction().commit();
        return rotina;
    }
    
    public Rotina salvar(Rotina rotina) throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        rotina = manager.merge(rotina);
        manager.getTransaction().commit();
        return rotina;
    }
    
    public List<Rotina> listar(String nome) throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery("select r from Rotina r where r.nome like '%" +nome+ "%' order by r.nome");
        List<Rotina> lista = q.getResultList();
        manager.getTransaction().commit();
        return lista;
    }
    
    public List<Rotina> listarSql(String sql) throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery(sql);
        List<Rotina> lista = q.getResultList();
        manager.getTransaction().commit();
        return lista;
    }
    
    public List<Rotina> listar(int idSubDepartamento) throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery("select r from Rotina r where r.subdepartamento.idsubdepartamento=" + idSubDepartamento + " order by r.nome");
        List<Rotina> lista = q.getResultList();
        manager.getTransaction().commit();
        return lista;
    }
    
    public void excluir(int idRotina) throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Rotina rotina = manager.find(Rotina.class,idRotina);
        manager.remove(rotina);
        manager.getTransaction().commit();
    }
}