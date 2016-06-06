package br.com.financemate.facade;

import br.com.financemate.dao.MembrosDao;
import br.com.financemate.model.Membros;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MembrosFacade {
    MembrosDao membrosDao;
    
    
    public Membros salvar(Membros membros) {
        membrosDao = new MembrosDao();
        try {
            return membrosDao.salvar(membros);
        } catch (SQLException ex) {
            Logger.getLogger(MembrosFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    
    public List<Membros> listar(String sql) {
        membrosDao = new MembrosDao();
        try {
            return membrosDao.listar(sql);
        } catch (SQLException ex) {
            Logger.getLogger(MembrosFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public void excluir(int idMembro) {
        membrosDao = new MembrosDao();
        try {
            membrosDao.excluir(idMembro);
        } catch (SQLException ex) {
            Logger.getLogger(MembrosFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}