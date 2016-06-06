package br.com.financemate.facade;

import br.com.financemate.dao.RaciDao;
import br.com.financemate.model.Raci;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Julio
 */
public class RaciFacade {
    RaciDao raciDao;
    
    
    public Raci salvar(Raci raci) {
        raciDao = new RaciDao();
        try {
            return raciDao.salvar(raci);
        } catch (SQLException ex) {
            Logger.getLogger(RaciFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public List<Raci> listar(String nome) {
        raciDao = new RaciDao();
        try {
            return raciDao.listar(nome);
        } catch (SQLException ex) {
            Logger.getLogger(RaciFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public void excluir(int idRaci) {
        raciDao = new RaciDao();
        try {
            raciDao.excluir(idRaci);
        } catch (SQLException ex) {
            Logger.getLogger(RaciFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}