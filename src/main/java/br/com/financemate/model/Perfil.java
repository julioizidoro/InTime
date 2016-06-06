package br.com.financemate.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Wolverine
 */
@Entity
@Table(name = "perfil")
public class Perfil implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "perfil")
    private List<Usuario> usuarioList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idperfil")
    private Integer idperfil;
    @Size(max = 15)
    @Column(name = "tipoacesso")
    private String tipoacesso;
    @Column(name = "cadcliente")
    private Boolean cadcliente;
    @Column(name = "cadclienteincluir")
    private Boolean cadclienteincluir;
    @Column(name = "cadclienteeditar")
    private Boolean cadclienteeditar;
    @Column(name = "cadclientesituacao")
    private Boolean cadclientesituacao;
    @Column(name = "caddepartamento")
    private Boolean caddepartamento;
    @Column(name = "caddepartamentoincluir")
    private Boolean caddepartamentoincluir;
    @Column(name = "caddepartamentoeditar")
    private Boolean caddepartamentoeditar;
    @Column(name = "cadsubdepartamento")
    private Boolean cadsubdepartamento;
    @Column(name = "cadsubdepartamentoincluir")
    private Boolean cadsubdepartamentoincluir;
    @Column(name = "cadsubdepartamentoeditar")
    private Boolean cadsubdepartamentoeditar;
    @Column(name = "cadsubdepartamentosituacao")
    private Boolean cadsubdepartamentosituacao;
    @Column(name = "cadrotina")
    private Boolean cadrotina;
    @Column(name = "cadrotinaincluir")
    private Boolean cadrotinaincluir;
    @Column(name = "cadrotinaeditar")
    private Boolean cadrotinaeditar;
    @Column(name = "cadrotinaexcluir")
    private Boolean cadrotinaexcluir;
    @Column(name = "cadusuario")
    private Boolean cadusuario;
    @Column(name = "cadusuarioincluir")
    private Boolean cadusuarioincluir;
    @Column(name = "cadusuarioeditar")
    private Boolean cadusuarioeditar;
    @Column(name = "cadusuariosituacao")
    private Boolean cadusuariosituacao;
    @Column(name = "tarefasincluir")
    private Boolean tarefasincluir;
    @Column(name = "tarefaseditar")
    private Boolean tarefaseditar;
    @Column(name = "tarefasoutros")
    private Boolean tarefasoutros;
    @Column(name = "tarefatempo")
    private Boolean tarefatempo;
    @Column(name = "tarefaeditaroutros")
    private Boolean tarefaeditaroutros;
    @Column(name = "situacao")
    private Boolean situacao;
    @Column(name = "cadperfil")
    private Boolean cadperfil;
    @Column(name = "cadperfilincluir")
    private Boolean cadperfilincluir;
    @Column(name = "cadperfileditar")
    private Boolean cadperfileditar;
    
    public Perfil() {
    }

    public Perfil(Integer idperfil) {
        this.idperfil = idperfil;
    }

    public Integer getIdperfil() {
        return idperfil;
    }

    public void setIdperfil(Integer idperfil) {
        this.idperfil = idperfil;
    }

    public String getTipoacesso() {
        return tipoacesso;
    }

    public void setTipoacesso(String tipoacesso) {
        this.tipoacesso = tipoacesso;
    }

    public Boolean getCadcliente() {
        return cadcliente;
    }

    public void setCadcliente(Boolean cadcliente) {
        this.cadcliente = cadcliente;
    }

    public Boolean getCadclienteincluir() {
        return cadclienteincluir;
    }

    public void setCadclienteincluir(Boolean cadclienteincluir) {
        this.cadclienteincluir = cadclienteincluir;
    }

    public Boolean getCadclienteeditar() {
        return cadclienteeditar;
    }

    public void setCadclienteeditar(Boolean cadclienteeditar) {
        this.cadclienteeditar = cadclienteeditar;
    }

    public Boolean getCadclientesituacao() {
        return cadclientesituacao;
    }

    public void setCadclientesituacao(Boolean cadclientesituacao) {
        this.cadclientesituacao = cadclientesituacao;
    }

    public Boolean getCaddepartamento() {
        return caddepartamento;
    }

    public void setCaddepartamento(Boolean caddepartamento) {
        this.caddepartamento = caddepartamento;
    }

    public Boolean getCaddepartamentoincluir() {
        return caddepartamentoincluir;
    }

    public void setCaddepartamentoincluir(Boolean caddepartamentoincluir) {
        this.caddepartamentoincluir = caddepartamentoincluir;
    }

    public Boolean getCaddepartamentoeditar() {
        return caddepartamentoeditar;
    }

    public void setCaddepartamentoeditar(Boolean caddepartamentoeditar) {
        this.caddepartamentoeditar = caddepartamentoeditar;
    }

    public Boolean getCadsubdepartamento() {
        return cadsubdepartamento;
    }

    public void setCadsubdepartamento(Boolean cadsubdepartamento) {
        this.cadsubdepartamento = cadsubdepartamento;
    }

    public Boolean getCadsubdepartamentoincluir() {
        return cadsubdepartamentoincluir;
    }

    public void setCadsubdepartamentoincluir(Boolean cadsubdepartamentoincluir) {
        this.cadsubdepartamentoincluir = cadsubdepartamentoincluir;
    }

    public Boolean getCadsubdepartamentoeditar() {
        return cadsubdepartamentoeditar;
    }

    public void setCadsubdepartamentoeditar(Boolean cadsubdepartamentoeditar) {
        this.cadsubdepartamentoeditar = cadsubdepartamentoeditar;
    }

    public Boolean getCadsubdepartamentosituacao() {
        return cadsubdepartamentosituacao;
    }

    public void setCadsubdepartamentosituacao(Boolean cadsubdepartamentosituacao) {
        this.cadsubdepartamentosituacao = cadsubdepartamentosituacao;
    }

    public Boolean getCadrotina() {
        return cadrotina;
    }

    public void setCadrotina(Boolean cadrotina) {
        this.cadrotina = cadrotina;
    }

    public Boolean getCadrotinaincluir() {
        return cadrotinaincluir;
    }

    public void setCadrotinaincluir(Boolean cadrotinaincluir) {
        this.cadrotinaincluir = cadrotinaincluir;
    }

    public Boolean getCadrotinaeditar() {
        return cadrotinaeditar;
    }

    public void setCadrotinaeditar(Boolean cadrotinaeditar) {
        this.cadrotinaeditar = cadrotinaeditar;
    }

    public Boolean getCadrotinaexcluir() {
        return cadrotinaexcluir;
    }

    public void setCadrotinaexcluir(Boolean cadrotinaexcluir) {
        this.cadrotinaexcluir = cadrotinaexcluir;
    }

    public Boolean getCadusuario() {
        return cadusuario;
    }

    public void setCadusuario(Boolean cadusuario) {
        this.cadusuario = cadusuario;
    }

    public Boolean getCadusuarioincluir() {
        return cadusuarioincluir;
    }

    public void setCadusuarioincluir(Boolean cadusuarioincluir) {
        this.cadusuarioincluir = cadusuarioincluir;
    }

    public Boolean getCadusuarioeditar() {
        return cadusuarioeditar;
    }

    public void setCadusuarioeditar(Boolean cadusuarioeditar) {
        this.cadusuarioeditar = cadusuarioeditar;
    }

    public Boolean getCadusuariosituacao() {
        return cadusuariosituacao;
    }

    public void setCadusuariosituacao(Boolean cadusuariosituacao) {
        this.cadusuariosituacao = cadusuariosituacao;
    }

    public Boolean getTarefasincluir() {
        return tarefasincluir;
    }

    public void setTarefasincluir(Boolean tarefasincluir) {
        this.tarefasincluir = tarefasincluir;
    }

    public Boolean getTarefaseditar() {
        return tarefaseditar;
    }

    public void setTarefaseditar(Boolean tarefaseditar) {
        this.tarefaseditar = tarefaseditar;
    }

    public Boolean getTarefasoutros() {
        return tarefasoutros;
    }

    public void setTarefasoutros(Boolean tarefasoutros) {
        this.tarefasoutros = tarefasoutros;
    }

    public Boolean getTarefatempo() {
        return tarefatempo;
    }

    public void setTarefatempo(Boolean tarefatempo) {
        this.tarefatempo = tarefatempo;
    }

    public Boolean getTarefaeditaroutros() {
        return tarefaeditaroutros;
    }

    public void setTarefaeditaroutros(Boolean tarefaeditaroutros) {
        this.tarefaeditaroutros = tarefaeditaroutros;
    }

    public Boolean getSituacao() {
        return situacao;
    }

    public void setSituacao(Boolean situacao) {
        this.situacao = situacao;
    }

    public Boolean getCadperfil() {
        return cadperfil;
    }

    public void setCadperfil(Boolean cadperfil) {
        this.cadperfil = cadperfil;
    }

    public Boolean getCadperfilincluir() {
        return cadperfilincluir;
    }

    public void setCadperfilincluir(Boolean cadperfilincluir) {
        this.cadperfilincluir = cadperfilincluir;
    }

    public Boolean getCadperfileditar() {
        return cadperfileditar;
    }

    public void setCadperfileditar(Boolean cadperfileditar) {
        this.cadperfileditar = cadperfileditar;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idperfil != null ? idperfil.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Perfil)) {
            return false;
        }
        Perfil other = (Perfil) object;
        if ((this.idperfil == null && other.idperfil != null) || (this.idperfil != null && !this.idperfil.equals(other.idperfil))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.financemate.model.Perfil[ idperfil=" + idperfil + " ]";
    }

    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }
    
}