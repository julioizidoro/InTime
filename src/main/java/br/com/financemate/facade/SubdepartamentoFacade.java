package br.com.financemate.facade;

import br.com.financemate.dao.SubdepartamentoDao;
import br.com.financemate.model.Subdepartamento;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kamila
 */
public class SubdepartamentoFacade {
    SubdepartamentoDao subdepartamentoDao;
    
    public Subdepartamento salvar(Subdepartamento subdepartamento) {
        subdepartamentoDao = new SubdepartamentoDao();
        try {
            return subdepartamentoDao.salvar(subdepartamento);
        } catch (SQLException ex) {
            Logger.getLogger(SubdepartamentoFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public Subdepartamento consultar(int idSubdepartamento) {
        subdepartamentoDao = new SubdepartamentoDao();
        try {
            return subdepartamentoDao.consultar(idSubdepartamento);
        } catch (SQLException ex) {
            Logger.getLogger(SubdepartamentoFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public List<Subdepartamento> listar(String nomeDepartamento, int idDepartamento) {
        subdepartamentoDao = new SubdepartamentoDao();
        try {
            return subdepartamentoDao.listar(nomeDepartamento, idDepartamento);
        } catch (SQLException ex) {
            Logger.getLogger(SubdepartamentoFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public List<Subdepartamento> listar(int idconfig) {
        subdepartamentoDao = new SubdepartamentoDao();
        try {
            return subdepartamentoDao.listar(idconfig);
        } catch (SQLException ex) {
            Logger.getLogger(SubdepartamentoFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}