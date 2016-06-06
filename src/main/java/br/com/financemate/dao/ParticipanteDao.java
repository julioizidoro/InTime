package br.com.financemate.dao;

import br.com.financemate.connection.ConectionFactory;
import br.com.financemate.model.Participante;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Wolverine
 */
public class ParticipanteDao {
    
    
    public Participante salvar(Participante participante) throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        participante = manager.merge(participante);
        manager.getTransaction().commit();
        return participante;
    }
    
    
    public List<Participante> listar(String sql) throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery(sql);
        List<Participante> lista = q.getResultList();
        manager.getTransaction().commit();
        return lista;
    }
    
    public void excluir(int idParticipante) throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Participante participante = manager.find(Participante.class, idParticipante);
        manager.remove(participante);
        manager.getTransaction().commit();
    }
    
}