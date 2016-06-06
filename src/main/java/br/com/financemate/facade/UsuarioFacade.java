package br.com.financemate.facade;

import br.com.financemate.dao.UsuarioDao;
import br.com.financemate.model.Usuario;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UsuarioFacade {
    
    UsuarioDao usuarioDao;
    
    public Usuario salvar(Usuario usuario) {
        usuarioDao = new UsuarioDao();
        try {
            return usuarioDao.salvar(usuario);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public Usuario consultar(String login, String senha) {
        usuarioDao = new UsuarioDao();
        try {
            return usuarioDao.consultar(login, senha);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public List<Usuario> listarTodos(String nomeUsuario) {
        usuarioDao = new UsuarioDao();
        try {
            return usuarioDao.listarTodos(nomeUsuario);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public List<Usuario> listarAtivos() {
        usuarioDao = new UsuarioDao();
        try {
            return usuarioDao.listarAtivos();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public Usuario consultar(int idUsuario) {
        usuarioDao = new UsuarioDao();
        try {
            return usuarioDao.consultar(idUsuario);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}