package br.com.financemate.facade;

import br.com.financemate.dao.ProcessoAtividadeDao;
import br.com.financemate.model.Processoatividade;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Julio
 */
public class ProcessoAtividadeFacade {
    
    ProcessoAtividadeDao processoAtividadeDao;
    
    public Processoatividade salvar(Processoatividade processoatividade) {
        processoAtividadeDao = new ProcessoAtividadeDao();
        try {
            return processoAtividadeDao.salvar(processoatividade);
        } catch (SQLException ex) {
            Logger.getLogger(ProcessoAtividadeFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public List<Processoatividade> listar(String sql){
        processoAtividadeDao = new ProcessoAtividadeDao();
        try {
            return processoAtividadeDao.listar(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ProcessoAtividadeFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public void excluir(Processoatividade processoatividade) {
        processoAtividadeDao = new ProcessoAtividadeDao();
        try {
            processoAtividadeDao.excluir(processoatividade);
        } catch (SQLException ex) {
            Logger.getLogger(ProcessoAtividadeFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Processoatividade consultar(String sql) {
        processoAtividadeDao = new ProcessoAtividadeDao();
        try {
            return processoAtividadeDao.consultar(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ProcessoAtividadeFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}