package br.com.financemate.dao;

import br.com.financemate.connection.ConectionFactory;
import br.com.financemate.model.Departamento;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Kamila
 */
public class DepartamentoDao {
    
    public Departamento consultar(int idDepartamento) throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery("select d from Departamento d where d.iddepartamento=" + idDepartamento);
        Departamento departamento = null;
        if (q.getResultList().size()>0){
            departamento = (Departamento) q.getResultList().get(0);
        }
        manager.getTransaction().commit();
        return departamento;
    }
    public Departamento salvar(Departamento departamento) throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        departamento = manager.merge(departamento);
        manager.getTransaction().commit();
        return departamento;
    }
     public List<Departamento> listar(String nome, int idconfig) throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery("select d from Departamento d where d.nome like '%" + nome + "%'  and d.iddepartamento<>  " 
                + idconfig +  " order by d.nome");
        List<Departamento> lista = q.getResultList();
        manager.getTransaction().commit();
        return lista;
    }
    
}