package br.com.financemate.facade;

import br.com.financemate.dao.PerfilDao;
import br.com.financemate.model.Perfil;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kamila
 */
public class PerfilFacade {
    
    PerfilDao perfilDao;
    
    public Perfil salvar(Perfil perfil) {
        perfilDao = new PerfilDao();
        try {
            return perfilDao.salvar(perfil);
        } catch (SQLException ex) {
            Logger.getLogger(PerfilFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public Perfil consultar(int idPerfil){
        perfilDao = new PerfilDao();
        try {
            return perfilDao.consultar(idPerfil);
        } catch (SQLException ex) {
            Logger.getLogger(PerfilFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public List<Perfil> listar(String nomeTipoacesso){
        perfilDao = new PerfilDao();
        try {
            return perfilDao.listar(nomeTipoacesso);
        } catch (SQLException ex) {
            Logger.getLogger(PerfilFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}