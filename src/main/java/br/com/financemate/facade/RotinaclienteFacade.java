package br.com.financemate.facade;

import br.com.financemate.dao.RotinaclienteDao;
import br.com.financemate.model.Rotinaatividade;
import br.com.financemate.model.Rotinacliente;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kamila
 */
public class RotinaclienteFacade {
    
    RotinaclienteDao rotinaclienteDao;
    
     public Rotinacliente salvar(Rotinacliente rotinacliente){
        rotinaclienteDao = new RotinaclienteDao();
        try {
            return rotinaclienteDao.salvar(rotinacliente);
        } catch (SQLException ex) {
            Logger.getLogger(RotinaclienteFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
     
     public Rotinacliente consultar(int idRotinacliente) {
        rotinaclienteDao = new RotinaclienteDao();
        try {
            return rotinaclienteDao.consultar(idRotinacliente);
        } catch (SQLException ex) {
            Logger.getLogger(RotinaclienteFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public List<Rotinacliente> listar(String nomeCliente) {
        rotinaclienteDao = new RotinaclienteDao();
        try {
            return rotinaclienteDao.listar(nomeCliente);
        } catch (SQLException ex) {
            Logger.getLogger(RotinaclienteFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public Rotinacliente getRotinaCliente(int idCliente, int idRotina) {
        rotinaclienteDao = new RotinaclienteDao();
        try {
            return rotinaclienteDao.getRotinaCliente(idCliente, idRotina);
        } catch (SQLException ex) {
            Logger.getLogger(RotinaclienteFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public void Excluir(int idRotinaCliente) {
        rotinaclienteDao = new RotinaclienteDao();
        try {
            rotinaclienteDao.Excluir(idRotinaCliente);
        } catch (SQLException ex) {
            Logger.getLogger(RotinaclienteFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}