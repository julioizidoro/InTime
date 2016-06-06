package br.com.financemate.dao;

import br.com.financemate.connection.ConectionFactory;
import br.com.financemate.model.Processo;
import br.com.financemate.model.Processosituacao;
import br.com.financemate.model.Usuario;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;


/**
 *
 * @author Greici
 */
public class ProcessoSituacaoDao {
    
    public Processosituacao salvar(Processosituacao  processosituacao) throws  SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        processosituacao = manager.merge(processosituacao);
        manager.getTransaction().commit();
        return processosituacao;
        
    }
    public Processosituacao consultar(int idusuario) throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery("select p from Processo p where p.ididusuario" + idusuario);
        Processosituacao processosituacao = null;
        if (q.getResultList().size()>0){
            processosituacao = (Processosituacao) q.getResultList().get(0);
        }
        manager.getTransaction().commit();
        return processosituacao;
    
}
       public List<Processosituacao> listar(String sql) throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery(sql);
        List<Processosituacao> lista = q.getResultList();
        manager.getTransaction().commit();
        return lista;
    }
       public void excluir(int idusuario){
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Usuario usuario = manager.find(Usuario.class, idusuario);
        manager.remove(usuario);
        manager.getTransaction().commit();
    }
 
}