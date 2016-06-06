package br.com.financemate.facade;

import br.com.financemate.dao.DepartamentoDao;
import br.com.financemate.model.Departamento;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Kamila
 */
public class DepartamentoFacade {
    
    DepartamentoDao departamentoDao;
    
    public Departamento salvar(Departamento departamento) {
        departamentoDao = new DepartamentoDao();
        try {
            return departamentoDao.salvar(departamento);
        } catch (SQLException ex) {
            Logger.getLogger(DepartamentoFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public Departamento consultar(int idDepartamento) {
        departamentoDao = new DepartamentoDao();
        try {
            return departamentoDao.consultar(idDepartamento);
        } catch (SQLException ex) {
            Logger.getLogger(DepartamentoFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public List<Departamento> listar(String nome, int idconfig){
        departamentoDao = new DepartamentoDao();
        try {
            return departamentoDao.listar(nome, idconfig);
        } catch (SQLException ex) {
            Logger.getLogger(DepartamentoFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}