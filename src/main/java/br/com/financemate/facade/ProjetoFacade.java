package br.com.financemate.facade;


import br.com.financemate.dao.ProjetoDao;
import br.com.financemate.model.Projeto;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ProjetoFacade {
    
    ProjetoDao projetoDao;
    
    public Projeto salvar(Projeto projeto) {
        projetoDao = new ProjetoDao();
        try {
            return projetoDao.salvar(projeto);
        } catch (SQLException ex) {
            Logger.getLogger(ProjetoFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    
    public List<Projeto> listar(String nome) {
        projetoDao = new ProjetoDao();
        try {
            return projetoDao.listar(nome);
        } catch (SQLException ex) {
            Logger.getLogger(ProjetoFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}