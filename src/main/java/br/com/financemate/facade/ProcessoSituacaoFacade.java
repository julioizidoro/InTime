package br.com.financemate.facade;


import br.com.financemate.dao.ProcessoSituacaoDao;
import br.com.financemate.model.Processo;
import br.com.financemate.model.Processosituacao;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Greici
 */
public class ProcessoSituacaoFacade {
    
    
     ProcessoSituacaoDao processoSituacaoDao;
    
   public Processosituacao salvar(Processosituacao processosituacao) {
    processoSituacaoDao = new ProcessoSituacaoDao();
        try {
            return processoSituacaoDao.salvar(processosituacao);
        } catch (Exception ex) {
            Logger.getLogger(ProcessoSituacaoFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public Processosituacao consultar(int idusuario) {
        processoSituacaoDao = new ProcessoSituacaoDao();
        try {
            return processoSituacaoDao.consultar(idusuario);
        } catch (SQLException ex) {
            Logger.getLogger(ProcessoSituacaoFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
     
    public List<Processosituacao> listar(String sql) {
        processoSituacaoDao = new ProcessoSituacaoDao();
        try {
            return processoSituacaoDao.listar(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ProcessoSituacaoFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}