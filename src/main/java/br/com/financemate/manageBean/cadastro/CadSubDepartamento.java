/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.financemate.manageBean.cadastro;

import br.com.financemate.facade.DepartamentoFacade;
import br.com.financemate.facade.SubdepartamentoFacade;
import br.com.financemate.manageBean.usuairo.UsuarioLogadoMB;
import br.com.financemate.model.Departamento;
import br.com.financemate.model.Subdepartamento;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Wolverine
 */
@Named
@ViewScoped
public class CadSubDepartamento implements Serializable{
    
    @Inject 
    private UsuarioLogadoMB usuarioLogadoBean;
    private List<Departamento> listaDepartamento;
    private Subdepartamento subdepartamento;
    
    @PostConstruct
    public void init(){
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        subdepartamento = (Subdepartamento) session.getAttribute("subdepartamento");
        session.removeAttribute("subdepartamento");
        if (subdepartamento==null){
            subdepartamento= new Subdepartamento();
            subdepartamento.setSituacao("Ativo");
        }
        getUsuarioLogadoBean();
        gerarListaDepartamento();
    }

    public List<Departamento> getListaDepartamento() {
        return listaDepartamento;
    }

    public void setListaDepartamento(List<Departamento> listaDepartamento) {
        this.listaDepartamento = listaDepartamento;
    }

    public Subdepartamento getSubdepartamento() {
        return subdepartamento;
    }

    public void setSubdepartamento(Subdepartamento subdepartamento) {
        this.subdepartamento = subdepartamento;
    }

    public UsuarioLogadoMB getUsuarioLogadoBean() {
        return usuarioLogadoBean;
    }

    public void setUsuarioLogadoBean(UsuarioLogadoMB usuarioLogadoBean) {
        this.usuarioLogadoBean = usuarioLogadoBean;
    }
    
    public void gerarListaDepartamento(){
        DepartamentoFacade departamentoFacade = new DepartamentoFacade();
        listaDepartamento = departamentoFacade.listar("", usuarioLogadoBean.getConfig().getDepartamento().getIddepartamento());
        if (listaDepartamento==null){
            listaDepartamento = new ArrayList<Departamento>();
        }
    }
    
    public String salvar() throws SQLException {
        SubdepartamentoFacade subdepartamentoFacade = new SubdepartamentoFacade();
        subdepartamentoFacade.salvar(subdepartamento);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Cadastrado com Sucesso", ""));
        RequestContext.getCurrentInstance().closeDialog(null);
        return "consSubdepartamento";
    }
    
    public String cancelar(){
        RequestContext.getCurrentInstance().closeDialog(null);
        return "consSubdepartamento";

    }
    
    
}
