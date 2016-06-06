package br.com.financemate.dao;

import br.com.financemate.connection.ConectionFactory;
import br.com.financemate.model.Comentarios;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Kamila
 */
public class ComentariosDao {
    
    public Comentarios consultar(int idComentarios) throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery("select r from Comentarios r where r.idcomentarios=" + idComentarios);
        Comentarios comentarios = null;
        if (q.getResultList().size()>0){
            comentarios = (Comentarios) q.getResultList().get(0);
        }
        manager.getTransaction().commit();
        return comentarios;
    }
    
    public List<Comentarios> listar(int idAtividade) throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery("select c from Comentarios c where c.atividades.idatividades=" + idAtividade + " order by c.comentario");
        List<Comentarios> lista = q.getResultList();
        manager.getTransaction().commit();
        return lista;
    }
    
    public Comentarios salvar(Comentarios comentarios) throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        comentarios = manager.merge(comentarios);
        manager.getTransaction().commit();
        return comentarios;
    }
}