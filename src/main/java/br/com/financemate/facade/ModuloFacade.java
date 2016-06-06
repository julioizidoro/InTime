package br.com.financemate.facade;


import br.com.financemate.dao.ModuloDao;
import br.com.financemate.model.Modulos;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ModuloFacade {
    
    ModuloDao moduloDao;
    
    public Modulos salvar(Modulos modulos) {
        moduloDao = new ModuloDao();
        try {
            return moduloDao.salvar(modulos);
        } catch (SQLException ex) {
            Logger.getLogger(ModuloFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    
    public List<Modulos> listar(String nome) {
        moduloDao = new ModuloDao();
        try {
            return moduloDao.listar(nome);
        } catch (SQLException ex) {
            Logger.getLogger(ModuloFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}