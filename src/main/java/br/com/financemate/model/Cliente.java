
package br.com.financemate.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author Wolverine
 */
@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    private List<Atividades> atividadesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    private List<Processosituacao> processosituacaoList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcliente")
    private Integer idcliente;
    @Size(max = 100)
    @Column(name = "razaosocial")
    private String razaosocial;
    @Size(max = 100)
    @Column(name = "nomefantasia")
    private String nomefantasia;
    @Size(max = 18)
    @Column(name = "cnpj")
    private String cnpj;
    @Size(max = 20)
    @Column(name = "tipogradouro")
    private String tipogradouro;
    @Size(max = 100)
    @Column(name = "logradouro")
    private String logradouro;
    @Size(max = 10)
    @Column(name = "numero")
    private String numero;
    @Size(max = 50)
    @Column(name = "complemento")
    private String complemento;
    @Size(max = 50)
    @Column(name = "bairro")
    private String bairro;
    @Size(max = 50)
    @Column(name = "cidade")
    private String cidade;
    @Size(max = 9)
    @Column(name = "cep")
    private String cep;
    @Size(max = 2)
    @Column(name = "estado")
    private String estado;
    @Size(max = 100)
    @Column(name = "contato")
    private String contato;
    @Size(max = 15)
    @Column(name = "telefone")
    private String telefone;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="E-mail inválido")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 100)
    @Column(name = "email")
    private String email;
    @Lob
    @Size(max = 65535)
    @Column(name = "observacao")
    private String observacao;
    @Column(name = "datacontrato")
    @Temporal(TemporalType.DATE)
    private Date datacontrato;
    @Column(name = "datarenovacao")
    @Temporal(TemporalType.DATE)
    private Date datarenovacao;
    @Column(name = "getaofinanceira")
    private Boolean getaofinanceira;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valorgestaofinanceira")
    private Float valorgestaofinanceira;
    @Column(name = "contabilidade")
    private Boolean contabilidade;
    @Column(name = "valorcontabilidade")
    private Float valorcontabilidade;
    @Column(name = "tercerizacao")
    private Boolean tercerizacao;
    @Column(name = "valortercerizacao")
    private Float valortercerizacao;
    @Column(name = "outros")
    private Boolean outros;
    @Column(name = "valoroutros")
    private Float valoroutros;
    @Size(max = 20)
    @Column(name = "inscricaoestadual")
    private String inscricaoestadual;
    @Size(max = 20)
    @Column(name = "inscricaomunicipal")
    private String inscricaomunicipal;
    @Size(max = 15)
    @Column(name = "situacao")
    private String situacao;

    public Cliente() {
    }

    public Cliente(Integer idcliente) {
        this.idcliente = idcliente;
    }

    public Integer getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Integer idcliente) {
        this.idcliente = idcliente;
    }

    public String getRazaosocial() {
        return razaosocial;
    }

    public void setRazaosocial(String razaosocial) {
        this.razaosocial = razaosocial;
    }

    public String getNomefantasia() {
        return nomefantasia;
    }

    public void setNomefantasia(String nomefantasia) {
        this.nomefantasia = nomefantasia;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTipogradouro() {
        return tipogradouro;
    }

    public void setTipogradouro(String tipogradouro) {
        this.tipogradouro = tipogradouro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Date getDatacontrato() {
        return datacontrato;
    }

    public void setDatacontrato(Date datacontrato) {
        this.datacontrato = datacontrato;
    }

    public Date getDatarenovacao() {
        return datarenovacao;
    }

    public void setDatarenovacao(Date datarenovacao) {
        this.datarenovacao = datarenovacao;
    }

    public Boolean getGetaofinanceira() {
        return getaofinanceira;
    }

    public void setGetaofinanceira(Boolean getaofinanceira) {
        this.getaofinanceira = getaofinanceira;
    }

    public Float getValorgestaofinanceira() {
        return valorgestaofinanceira;
    }

    public void setValorgestaofinanceira(Float valorgestaofinanceira) {
        this.valorgestaofinanceira = valorgestaofinanceira;
    }

    public Boolean getContabilidade() {
        return contabilidade;
    }

    public void setContabilidade(Boolean contabilidade) {
        this.contabilidade = contabilidade;
    }

    public Float getValorcontabilidade() {
        return valorcontabilidade;
    }

    public void setValorcontabilidade(Float valorcontabilidade) {
        this.valorcontabilidade = valorcontabilidade;
    }

    public Boolean getTercerizacao() {
        return tercerizacao;
    }

    public void setTercerizacao(Boolean tercerizacao) {
        this.tercerizacao = tercerizacao;
    }

    public Float getValortercerizacao() {
        return valortercerizacao;
    }

    public void setValortercerizacao(Float valortercerizacao) {
        this.valortercerizacao = valortercerizacao;
    }

    public Boolean getOutros() {
        return outros;
    }

    public void setOutros(Boolean outros) {
        this.outros = outros;
    }

    public Float getValoroutros() {
        return valoroutros;
    }

    public void setValoroutros(Float valoroutros) {
        this.valoroutros = valoroutros;
    }

    public String getInscricaoestadual() {
        return inscricaoestadual;
    }

    public void setInscricaoestadual(String inscricaoestadual) {
        this.inscricaoestadual = inscricaoestadual;
    }

    public String getInscricaomunicipal() {
        return inscricaomunicipal;
    }

    public void setInscricaomunicipal(String inscricaomunicipal) {
        this.inscricaomunicipal = inscricaomunicipal;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }



    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcliente != null ? idcliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.idcliente == null && other.idcliente != null) || (this.idcliente != null && !this.idcliente.equals(other.idcliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.financemate.model.Cliente[ idcliente=" + idcliente + " ]";
    }

    public List<Atividades> getAtividadesList() {
        return atividadesList;
    }

    public void setAtividadesList(List<Atividades> atividadesList) {
        this.atividadesList = atividadesList;
    }

    public List<Processosituacao> getProcessosituacaoList() {
        return processosituacaoList;
    }

    public void setProcessosituacaoList(List<Processosituacao> processosituacaoList) {
        this.processosituacaoList = processosituacaoList;
    }

   
}