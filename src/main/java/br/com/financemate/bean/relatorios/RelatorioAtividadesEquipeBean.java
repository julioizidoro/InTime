package br.com.financemate.bean.relatorios;

import java.util.Date;

/**
 *
 * @author Wolverine
 */
public class RelatorioAtividadesEquipeBean {
    
    private String atividades;
    private String responsavel;
    private String subDepartamento;
    private String cliente;
    private Date prazo;
    private String situacao;
    private String prioridade;
    

    public String getAtividades() {
        return atividades;
    }

    public void setAtividades(String atividades) {
        this.atividades = atividades;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getSubDepartamento() {
        return subDepartamento;
    }

    public void setSubDepartamento(String subDepartamento) {
        this.subDepartamento = subDepartamento;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Date getPrazo() {
        return prazo;
    }

    public void setPrazo(Date prazo) {
        this.prazo = prazo;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }
    
    
    
}