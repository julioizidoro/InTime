package br.com.financemate.dao;

import br.com.financemate.connection.ConectionFactory;
import br.com.financemate.model.Subdepartamento;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Kamila
 */
public class SubdepartamentoDao {
    
    public Subdepartamento salvar(Subdepartamento subdepartamento) throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        subdepartamento = manager.merge(subdepartamento);
        manager.getTransaction().commit();
        return subdepartamento;
    }
    
    public List<Subdepartamento> listar(String nomeDepartamento, int idDepartamento) throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery("select a from Subdepartamento a where a.departamento.nome like '%" + nomeDepartamento + "%' " +
                " and a.departamento.iddepartamento=" + idDepartamento + " and a.situacao='Ativo' order by a.nome");
        List<Subdepartamento> lista = q.getResultList();
        manager.getTransaction().commit();
        return lista;
    }
    
    public Subdepartamento consultar(int idSubdepartamento) throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery("select s from Subdepartamento s where s.idsubdepartamento=" + idSubdepartamento);
        Subdepartamento atividades = null;
        if (q.getResultList().size()>0){
            atividades = (Subdepartamento) q.getResultList().get(0);
        }
        manager.getTransaction().commit();
        return atividades;
    }
    
    public List<Subdepartamento> listar(int idconfig) throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery("select a from Subdepartamento a where a.idsubdepartamento<>" + idconfig + " "
                + " and a.situacao='ATIVO' order by a.departamento.nome, a.nome");
        List<Subdepartamento> lista = q.getResultList();
        manager.getTransaction().commit();
        return lista;
    }
}