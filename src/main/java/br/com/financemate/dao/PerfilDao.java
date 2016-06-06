package br.com.financemate.dao;

import br.com.financemate.connection.ConectionFactory;
import br.com.financemate.model.Perfil;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Kamila
 */
public class PerfilDao {
    
    public Perfil consultar(int idPerfil) throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery("select p from Perfil p where p.idperfil=" + idPerfil);
        Perfil perfil = null;
        if (q.getResultList().size()>0){
            perfil = (Perfil) q.getResultList().get(0);
        }
        manager.getTransaction().commit();
        return perfil;
    }
    
    public Perfil salvar(Perfil perfil) throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        perfil = manager.merge(perfil);
        manager.getTransaction().commit();
        return perfil;
    }
    
    public List<Perfil> listar(String nomeTipoacesso) throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery("select p from Perfil p where p.tipoacesso like '%" + nomeTipoacesso + "%' order by p.tipoacesso");
        List<Perfil> lista = q.getResultList();
        manager.getTransaction().commit();
        return lista;
    }
}