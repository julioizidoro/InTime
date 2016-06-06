package br.com.financemate.facade;

import br.com.financemate.dao.NotificacaoDao;
import br.com.financemate.model.Notificacao;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wolverine
 */
public class NotificacaoFacade {
    
    NotificacaoDao notificacaoDao;
    
    public Notificacao salvar(Notificacao notificacao){
        notificacaoDao = new NotificacaoDao();
        try {
            return notificacaoDao.salvar(notificacao);
        } catch (SQLException ex) {
            Logger.getLogger(NotificacaoFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public List<Notificacao> listar(int idUsuario){
        notificacaoDao = new NotificacaoDao();
        try {
            return notificacaoDao.listar(idUsuario);
        } catch (SQLException ex) {
            Logger.getLogger(NotificacaoFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}