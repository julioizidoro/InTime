package br.com.financemate.facade;

import br.com.financemate.dao.ConfigDao;
import br.com.financemate.model.Config;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wolverine
 */
public class ConfigFacade {
    
    ConfigDao configDao;
    
    public Config consultar() {
        configDao = new ConfigDao();
        try {
            return configDao.consultar();
        } catch (SQLException ex) {
            Logger.getLogger(ConfigFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}