package br.com.financemate.facade;

import br.com.financemate.dao.ParticipanteDao;
import br.com.financemate.model.Participante;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wolverine
 */
public class ParticipanteFacade {
    
    private ParticipanteDao participanteDao;
    
    public Participante salvar(Participante participante){
        participanteDao = new ParticipanteDao();
        try {
            return participanteDao.salvar(participante);
        } catch (SQLException ex) {
            Logger.getLogger(ParticipanteFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public List<Participante> listar(String sql) {
        participanteDao = new ParticipanteDao();
        try {
            return participanteDao.listar(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ParticipanteFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public void excluir(int idParticipante) {
        participanteDao = new ParticipanteDao();
        try {
            participanteDao.excluir(idParticipante);
        } catch (SQLException ex) {
            Logger.getLogger(ParticipanteFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}