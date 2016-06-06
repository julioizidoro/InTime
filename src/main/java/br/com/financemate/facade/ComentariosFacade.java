package br.com.financemate.facade;

import br.com.financemate.dao.ComentariosDao;
import br.com.financemate.model.Comentarios;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kamila
 */
public class ComentariosFacade {
    
    ComentariosDao comentariosDao;
    
    public Comentarios salvar(Comentarios comentarios) {
        comentariosDao = new ComentariosDao();
        try {
            return comentariosDao.salvar(comentarios);
        } catch (SQLException ex) {
            Logger.getLogger(SubdepartamentoFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public Comentarios consultar(int idAtividades) {
        comentariosDao = new ComentariosDao();
        try {
            return comentariosDao.consultar(idAtividades);
        } catch (SQLException ex) {
            Logger.getLogger(SubdepartamentoFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public List<Comentarios> listar(int idAtividades) {
        comentariosDao = new ComentariosDao();
        try {
            return comentariosDao.listar(idAtividades);
        } catch (SQLException ex) {
            Logger.getLogger(SubdepartamentoFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}