package br.com.financemate.dao;

import br.com.financemate.connection.ConectionFactory;
import br.com.financemate.model.Notificacao;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Wolverine
 */
public class NotificacaoDao {
    
    public Notificacao salvar(Notificacao notificacao) throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        notificacao = manager.merge(notificacao);
        manager.getTransaction().commit();
        return notificacao;
    }
    
    public List<Notificacao> listar(int idUsuario) throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery("Select n from Notificacao n where n.usuario.idusuario=" + idUsuario + 
                " and n.lido=FALSE order by n.idnotificacao");
        List<Notificacao> lista = q.getResultList();
        manager.getTransaction().commit();
        return lista;
    }
    
}