package br.com.financemate.dao;

import br.com.financemate.connection.ConectionFactory;
import br.com.financemate.model.Usuario;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;



/**
 *
 * @author Wolverine
 */
public class UsuarioDao {
    
    
    public Usuario salvar(Usuario usuario) throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        //abrindo uma transação
        manager.getTransaction().begin();
        usuario = manager.merge(usuario);
        //fechando uma transação
        manager.getTransaction().commit();
        return usuario;
    }
    
    public Usuario consultar(String login, String senha) throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery("select u from Usuario u where u.login='" + login + "' and u.senha='" + senha + "' and u.situacao='Ativo' order by u.nome");
        Usuario usuario = null;
        if (q.getResultList().size()>0){
            usuario = (Usuario) q.getResultList().get(0);
        }
        manager.getTransaction().commit();
        return usuario;
    }
    
    public List<Usuario> listarTodos(String nomeUsuario) throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery("select u from Usuario u where u.nome like '%" + nomeUsuario + "%' order by u.nome");
        List<Usuario> lista = q.getResultList();
        manager.getTransaction().commit();
        return  lista;
    }
    
    public List<Usuario> listarAtivos() throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery("select u from Usuario u where u.situacao='Ativo' order by u.nome");
        List<Usuario> lista = q.getResultList();
        manager.getTransaction().commit();
        return  lista;
    }
    
    public Usuario consultar(int idUsuario) throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Usuario usuario = manager.find(Usuario.class, idUsuario);
        manager.getTransaction().commit();
        return usuario;
    }
    
}