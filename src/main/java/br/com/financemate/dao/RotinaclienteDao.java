package br.com.financemate.dao;

import br.com.financemate.connection.ConectionFactory;
import br.com.financemate.model.Rotinacliente;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Kamila
 */
public class RotinaclienteDao {
    
     public Rotinacliente consultar(int idRotinacliente) throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery("select r from Rotinacliente r where r.idrotinacliente=" + idRotinacliente);
        Rotinacliente rotinacliente = null;
        if (q.getResultList().size()>0){
            rotinacliente = (Rotinacliente) q.getResultList().get(0);
        }
        manager.getTransaction().commit();
        return rotinacliente;
    }
    
     public Rotinacliente salvar(Rotinacliente rotinacliente) throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        rotinacliente = manager.merge(rotinacliente);
        manager.getTransaction().commit();
        return rotinacliente;
    }
     
    public List<Rotinacliente> listar(String nomeCliente) throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery("select r from Rotinacliente r where r.cliente.nomefantasia like '%" + nomeCliente+ "%' order by r.data");
        List<Rotinacliente> lista = q.getResultList();
        manager.getTransaction().commit();
        return lista;
    }
    
    public Rotinacliente getRotinaCliente(int idCliente, int idRotina) throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Query q = manager.createQuery("select r from Rotinacliente r where r.cliente.idcliente=" + idCliente + " and r.rotina.idrotina=" + idRotina);
        List<Rotinacliente> lista = q.getResultList();
        manager.getTransaction().commit();
        if (lista.size()>0){
            return (Rotinacliente) q.getResultList().get(0);
        }
        return null;
    }
    
    public void Excluir(int idRotinaCliente) throws SQLException{
        EntityManager manager = ConectionFactory.getConnection();
        manager.getTransaction().begin();
        Rotinacliente rotinacliente = manager.find(Rotinacliente.class, idRotinaCliente);
        manager.remove(rotinacliente);
        manager.getTransaction().commit();
    }
}