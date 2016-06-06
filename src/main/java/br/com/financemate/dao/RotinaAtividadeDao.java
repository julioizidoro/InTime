package br.com.financemate.dao;

import br.com.financemate.connection.ConectionFactory;
import br.com.financemate.model.Atividades;
import br.com.financemate.model.Rotinaatividade;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Wolverine
 */
public class RotinaAtividadeDao {
    
    
    public Rotinaatividade salvar(Rotinaatividade rotinaatividade) throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        rotinaatividade = manager.merge(rotinaatividade);
        manager.getTransaction().commit();
        return rotinaatividade;
    }
    
    public List<Rotinaatividade> listar(String sql)throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery(sql);
        List<Rotinaatividade> lista = q.getResultList();
        manager.getTransaction().commit();
        return lista;
    }
    
    public void excluir(int idRotinaAtividade) throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Rotinaatividade rotinaatividade = manager.find(Rotinaatividade.class, idRotinaAtividade);
        if (rotinaatividade!=null){
            manager.remove(rotinaatividade);
        }
        manager.getTransaction().commit();
    }
    
    public Rotinaatividade consultar(int idRotina, int idCliente, String dataInicial, String dataFinal) throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        String sql = "Select r From Rotinaatividade r where r.rotina.idrotina=" + idRotina +
                " and r.atividades.cliente.idcliente=" + idCliente + " and r.atividades.prazo>='" + dataInicial +
                "' and r.atividades.prazo<='" + dataFinal + "'";
        Query q = manager.createQuery(sql);
        List<Rotinaatividade> lista = q.getResultList();
        manager.getTransaction().commit();
        if (lista.size()>0){
            return lista.get(0);
                    
        }
        return null;
    }
    
}