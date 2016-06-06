package br.com.financemate.facade;

import br.com.financemate.dao.RotinaDao;
import br.com.financemate.model.Rotina;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Kamila
 */
public class RotinaFacade {
    
    RotinaDao rotinaDao;
    
    public Rotina salvar(Rotina rotina) {
        rotinaDao = new RotinaDao();
        try {
            return rotinaDao.salvar(rotina);
        } catch (SQLException ex) {
            Logger.getLogger(RotinaFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public Rotina consultar(int idRotina) {
        rotinaDao = new RotinaDao();
        try {
            return rotinaDao.consultar(idRotina);
        } catch (SQLException ex) {
            Logger.getLogger(RotinaFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public List<Rotina> listar(String nome) {
        rotinaDao = new RotinaDao();
        try {
            return rotinaDao.listar(nome);
        } catch (SQLException ex) {
            Logger.getLogger(RotinaFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public List<Rotina> listarSql(String sql) {
        rotinaDao = new RotinaDao();
        try {
            return rotinaDao.listarSql(sql);
        } catch (SQLException ex) {
            Logger.getLogger(RotinaFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public List<Rotina> listar(int idSubDepartamento) {
        rotinaDao = new RotinaDao();
        try {
            return rotinaDao.listar(idSubDepartamento);
        } catch (SQLException ex) {
            Logger.getLogger(RotinaFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public void excluir(int idRotina) {
        rotinaDao = new RotinaDao();
        try {
            rotinaDao.excluir(idRotina);
        } catch (SQLException ex) {
            Logger.getLogger(RotinaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}