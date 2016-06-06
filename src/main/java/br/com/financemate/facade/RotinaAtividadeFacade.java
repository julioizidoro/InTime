package br.com.financemate.facade;

import br.com.financemate.dao.RotinaAtividadeDao;
import br.com.financemate.model.Rotinaatividade;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wolverine
 */
public class RotinaAtividadeFacade {
    
    RotinaAtividadeDao rotinaAtividadeDao;
    
    public Rotinaatividade salvar(Rotinaatividade rotinaatividade) {
        rotinaAtividadeDao = new RotinaAtividadeDao();
        try {
            return rotinaAtividadeDao.salvar(rotinaatividade);
        } catch (SQLException ex) {
            Logger.getLogger(RotinaAtividadeFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public List<Rotinaatividade> listar(String sql){
        rotinaAtividadeDao = new RotinaAtividadeDao();
        try {
            return rotinaAtividadeDao.listar(sql);
        } catch (SQLException ex) {
            Logger.getLogger(RotinaAtividadeFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public void excluir(int idRotinaAtividade) {
        rotinaAtividadeDao = new RotinaAtividadeDao();
        try {
            rotinaAtividadeDao.excluir(idRotinaAtividade);
        } catch (SQLException ex) {
            Logger.getLogger(RotinaAtividadeFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Rotinaatividade consultar(int idRotina, int idCliente, String dataInicial, String dataFinal) {
        rotinaAtividadeDao = new RotinaAtividadeDao();
        try {
            return rotinaAtividadeDao.consultar(idRotina, idCliente, dataInicial, dataFinal);
        } catch (SQLException ex) {
            Logger.getLogger(RotinaAtividadeFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}