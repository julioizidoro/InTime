package br.com.financemate.facade;

import br.com.financemate.dao.ProcessoRotinaDao;
import br.com.financemate.model.Processorotina;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Julio
 */
public class ProcessoRotinaFacade {
    
     ProcessoRotinaDao processoRotinaDao;
    
    public Processorotina salvar(Processorotina processorotina) {
        processoRotinaDao = new ProcessoRotinaDao();
        try {
            return processoRotinaDao.salvar(processorotina);
        } catch (SQLException ex) {
            Logger.getLogger(ProcessoRotinaFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public List<Processorotina> listar(String sql){
        processoRotinaDao = new ProcessoRotinaDao();
        try {
            return processoRotinaDao.listar(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ProcessoAtividadeFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public void excluir(Processorotina processorotina) {
        processoRotinaDao = new ProcessoRotinaDao();
        try {
            processoRotinaDao.excluir(processorotina);
        } catch (SQLException ex) {
            Logger.getLogger(ProcessoAtividadeFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Processorotina consultar(String sql) {
        processoRotinaDao = new ProcessoRotinaDao();
        try {
            return processoRotinaDao.consultar(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ProcessoAtividadeFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}