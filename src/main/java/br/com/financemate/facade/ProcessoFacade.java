package br.com.financemate.facade;


import br.com.financemate.dao.ProcessoDao;
import br.com.financemate.model.Processo;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Greici
 */
public class ProcessoFacade {
    
    ProcessoDao processoDao;
    
    public Processo salvar(Processo processo){
    processoDao = new ProcessoDao();
        try {
            return processoDao.salvar(processo);
        } catch (SQLException ex) {
            Logger.getLogger(ProcessoFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public Processo consultar(int idProcesso) {
        processoDao = new ProcessoDao();
        try {
            return processoDao.consultar(idProcesso);
        } catch (SQLException ex) {
            Logger.getLogger(ProcessoFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public List<Processo> listar(String sql) {
        processoDao = new ProcessoDao();
        try {
            return processoDao.listar(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ProcessoFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}