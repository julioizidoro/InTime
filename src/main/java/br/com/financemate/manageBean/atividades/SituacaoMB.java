package br.com.financemate.manageBean.atividades;

import br.com.financemate.manageBean.usuairo.UsuarioLogadoMB;
import br.com.financemate.bean.Formatacao;
import br.com.financemate.bean.SituacaoBean;
import br.com.financemate.facade.DepartamentoFacade;
import br.com.financemate.facade.RotinaAtividadeFacade;
import br.com.financemate.facade.RotinaFacade;
import br.com.financemate.facade.SubdepartamentoFacade;
import br.com.financemate.model.Cliente;
import br.com.financemate.model.Departamento;
import br.com.financemate.model.Rotina;
import br.com.financemate.model.Rotinaatividade;
import br.com.financemate.model.Subdepartamento;
import br.com.financemate.repository.ClienteRepository;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Wolverine
 */

@Named("SituacaoMB")
@SessionScoped
public class SituacaoMB implements Serializable{
    
    @Inject
    private UsuarioLogadoMB usuarioLogadoBean;
    private List<Departamento> listaDepartamento;
    private List<Subdepartamento> listaSubdepartamento;
    private List<Rotina> listaRotina;
    private List<SituacaoBean> listaSituacao;
    private String idDepartamento;
    private String idSubdepartamento;
    private String idRotina;
    private ClienteRepository clienteRepository;

    public UsuarioLogadoMB getUsuarioLogadoBean() {
        return usuarioLogadoBean;
    }

    public void setUsuarioLogadoBean(UsuarioLogadoMB usuarioLogadoBean) {
        this.usuarioLogadoBean = usuarioLogadoBean;
    }

    public List<Departamento> getListaDepartamento() {
        if (listaDepartamento==null){
            gerarListaDepartamento();
        }
        return listaDepartamento;
    }

    public void setListaDepartamento(List<Departamento> listaDepartamento) {
        this.listaDepartamento = listaDepartamento;
    }

    public List<Subdepartamento> getListaSubdepartamento() {
        return listaSubdepartamento;
    }

    public void setListaSubdepartamento(List<Subdepartamento> listaSubdepartamento) {
        this.listaSubdepartamento = listaSubdepartamento;
    }

    public List<Rotina> getListaRotina() {
        return listaRotina;
    }

    public void setListaRotina(List<Rotina> listaRotina) {
        this.listaRotina = listaRotina;
    }

    public List<SituacaoBean> getListaSituacao() {
        return listaSituacao;
    }

    public void setListaSituacao(List<SituacaoBean> listaSituacao) {
        this.listaSituacao = listaSituacao;
    }

    public String getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(String idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getIdSubdepartamento() {
        return idSubdepartamento;
    }

    public void setIdSubdepartamento(String idSubdepartamento) {
        this.idSubdepartamento = idSubdepartamento;
    }

    public String getIdRotina() {
        return idRotina;
    }

    public void setIdRotina(String idRotina) {
        this.idRotina = idRotina;
    }
    
    public void gerarListaDepartamento() {
        DepartamentoFacade departamentoFacade = new DepartamentoFacade();
        listaDepartamento = departamentoFacade.listar("", usuarioLogadoBean.getConfig().getDepartamento().getIddepartamento());
        if (listaDepartamento == null) {
            listaDepartamento = new ArrayList<Departamento>();
        }
    }
    
    public void gerarListaSubdepartamento() {
        int numDep = Integer.parseInt(idDepartamento);
        if (numDep>0){
            SubdepartamentoFacade subdepartamentoFacade = new SubdepartamentoFacade();
            listaSubdepartamento = subdepartamentoFacade.listar("", numDep);
            if (listaSubdepartamento == null) {
                listaSubdepartamento = new ArrayList<Subdepartamento>();
           }
        }else {
            listaSubdepartamento = new ArrayList<Subdepartamento>();
        }    
    }
    
    
    public void gerarListaRotina() {
        int numSub = Integer.parseInt(idSubdepartamento);
        if (numSub>0) {
            RotinaFacade rotinaFacade = new RotinaFacade();
            listaRotina = rotinaFacade.listar(numSub);
            if (listaRotina == null) {
                listaRotina = new ArrayList<Rotina>();
            }
        }else {
            listaRotina = new ArrayList<Rotina>();
        }
    }
    
    public String gerarListaSituacao(){
        
        
        int nDep = Integer.parseInt(idDepartamento);
        int nSub = Integer.parseInt(idSubdepartamento);
        int nRotina = Integer.parseInt(idRotina);
        RotinaFacade rotinaFacade = new RotinaFacade();
                String sql =("select c from Cliente c where c.nomefantasia like '%" + " " + "%' and c.situacao='Ativo' and c.idcliente<>"
                + usuarioLogadoBean.getConfig().getCliente().getIdcliente() + " order by c.nomefantasia");
        List<Cliente> listaCliente = clienteRepository.list(sql);
        if (listaCliente==null){
            listaCliente = new ArrayList<Cliente>();
        }
        listaSituacao = new ArrayList<SituacaoBean>();
        for(int i=0;i<listaCliente.size();i++){
            SituacaoBean situacaoBean = new SituacaoBean();
            situacaoBean.setCliente(listaCliente.get(i));
            situacaoBean.setJan("X");
            situacaoBean.setFev("X");
            situacaoBean.setMar("X");
            situacaoBean.setAbr("X");
            situacaoBean.setMai("X");
            situacaoBean.setJun("X");
            situacaoBean.setJul("X");
            situacaoBean.setAgo("X");
            situacaoBean.setSet("X");
            situacaoBean.setOut("X");
            situacaoBean.setNov("X");
            situacaoBean.setDez("X");
            listaSituacao.add(situacaoBean);
        }
        sql = "Select r From Rotina r ";
        if (nDep>0){
            sql = sql + " where r.subdepartamento.departamento.iddepartamento=" + nDep;
        }
        if (nSub>0){
            sql = sql + " and r.subdepartamento.idsubdepartamento=" + nSub;
        }
        if (nRotina>0){
            sql = sql + " and r.idrotina=" + nRotina;
        }
        String data = Formatacao.ConvercaoDataPadrao(new Date());
        String ano = data.substring(6, 10);
        List<Rotina> listaRotina = rotinaFacade.listarSql(sql);
        for (int i = 0; i < 12; i++) {
          verificarRotinas(listaRotina, (i + 1), ano);
        }
        verrifcarSituacaoCliente();
        return "consSituacao";
    }
    
    public void verrifcarSituacaoCliente(){
        List<SituacaoBean> novaLista = listaSituacao;
        listaSituacao = new ArrayList<SituacaoBean>();
        for (int i=0;i<novaLista.size();i++){
            boolean ver = verrifcarSituacaoClienteIndividual(novaLista.get(i));
            if (ver){
                listaSituacao.add(novaLista.get(i));
            }
        }
    }
    
    public boolean verrifcarSituacaoClienteIndividual(SituacaoBean situacaoBean) {
        if (!situacaoBean.getJan().equalsIgnoreCase("X")) {
            return true;
        }
        if (!situacaoBean.getFev().equalsIgnoreCase("X")) {
            return true;
        }
        if (!situacaoBean.getMar().equalsIgnoreCase("X")) {
            return true;
        }
        if (!situacaoBean.getAbr().equalsIgnoreCase("X")) {
            return true;
        }
        if (!situacaoBean.getMai().equalsIgnoreCase("X")) {
            return true;
        }
        if (!situacaoBean.getJun().equalsIgnoreCase("X")) {
            return true;
        }
        if (!situacaoBean.getJul().equalsIgnoreCase("X")) {
            return true;
        }
        if (!situacaoBean.getAgo().equalsIgnoreCase("X")) {
            return true;
        }
        if (!situacaoBean.getSet().equalsIgnoreCase("X")) {
            return true;
        }
        if (!situacaoBean.getOut().equalsIgnoreCase("X")) {
            return true;
        }
        if (!situacaoBean.getNov().equalsIgnoreCase("X")) {
            return true;
        }
        if (!situacaoBean.getDez().equalsIgnoreCase("X")) {
            return true;
        }
        return  false;
    }
    
    public void  verificarRotinas(List<Rotina> listaRotina, int mes, String ano){
        for(int i=0;i<listaRotina.size();i++){
            verificarClientes(listaRotina.get(i), mes, ano);
        }
    }
    
    public void verificarClientes(Rotina rotina, int mes, String ano){
        RotinaAtividadeFacade rotinaAtividadeFacade = new RotinaAtividadeFacade();
        for (int i=0;i<listaSituacao.size();i++){
            String dataInical = ano + "-" + Formatacao.retornaDataInicia(mes);
            String dataFinal = ano + "-" + Formatacao.retornaDataFinal(mes);
            Rotinaatividade rotinaatividade = rotinaAtividadeFacade.consultar(rotina.getIdrotina(), listaSituacao.get(i).getCliente().getIdcliente(),
                    dataInical, dataFinal);
            String resultado = verificarSituacao(rotinaatividade);
            if (mes==1){
                if (listaSituacao.get(i).getJan().equalsIgnoreCase("X")){
                    listaSituacao.get(i).setJan(resultado);
                }else if (listaSituacao.get(i).getJan().equalsIgnoreCase("AM")){
                    if (resultado.equalsIgnoreCase("VR")){
                        listaSituacao.get(i).setJan(resultado);
                    }
                }else if (listaSituacao.get(i).getJan().equalsIgnoreCase("VD")){
                    if (resultado.equalsIgnoreCase("VR")){
                        listaSituacao.get(i).setJan(resultado);
                    }else if (resultado.equalsIgnoreCase("AM")){
                        listaSituacao.get(i).setJan(resultado);
                    }
                }
            }else if (mes==2){
                if (listaSituacao.get(i).getFev().equalsIgnoreCase("X")){
                    listaSituacao.get(i).setJan(resultado);
                }else if (listaSituacao.get(i).getFev().equalsIgnoreCase("AM")){
                    if (resultado.equalsIgnoreCase("VR")){
                        listaSituacao.get(i).setFev(resultado);
                    }
                }else if (listaSituacao.get(i).getFev().equalsIgnoreCase("VD")){
                    if (resultado.equalsIgnoreCase("VR")){
                        listaSituacao.get(i).setFev(resultado);
                    }else if (resultado.equalsIgnoreCase("AM")){
                        listaSituacao.get(i).setFev(resultado);
                    }
                }
            }else if (mes==3){
                if (listaSituacao.get(i).getMar().equalsIgnoreCase("X")){
                    listaSituacao.get(i).setMar(resultado);
                }else if (listaSituacao.get(i).getMar().equalsIgnoreCase("AM")){
                    if (resultado.equalsIgnoreCase("VR")){
                        listaSituacao.get(i).setMar(resultado);
                    }
                }else if (listaSituacao.get(i).getMar().equalsIgnoreCase("VD")){
                    if (resultado.equalsIgnoreCase("VR")){
                        listaSituacao.get(i).setMar(resultado);
                    }else if (resultado.equalsIgnoreCase("AM")){
                        listaSituacao.get(i).setMar(resultado);
                    }
                }
            }else if (mes==4){
                if (listaSituacao.get(i).getAbr().equalsIgnoreCase("X")){
                    listaSituacao.get(i).setAbr(resultado);
                }else if (listaSituacao.get(i).getAbr().equalsIgnoreCase("AM")){
                    if (resultado.equalsIgnoreCase("VR")){
                        listaSituacao.get(i).setAbr(resultado);
                    }
                }else if (listaSituacao.get(i).getAbr().equalsIgnoreCase("VD")){
                    if (resultado.equalsIgnoreCase("VR")){
                        listaSituacao.get(i).setAbr(resultado);
                    }else if (resultado.equalsIgnoreCase("AM")){
                        listaSituacao.get(i).setAbr(resultado);
                    }
                }
            }else if (mes==5){
                if (listaSituacao.get(i).getMai().equalsIgnoreCase("X")){
                    listaSituacao.get(i).setMai(resultado);
                }else if (listaSituacao.get(i).getMai().equalsIgnoreCase("AM")){
                    if (resultado.equalsIgnoreCase("VR")){
                        listaSituacao.get(i).setMai(resultado);
                    }
                }else if (listaSituacao.get(i).getMai().equalsIgnoreCase("VD")){
                    if (resultado.equalsIgnoreCase("VR")){
                        listaSituacao.get(i).setMai(resultado);
                    }else if (resultado.equalsIgnoreCase("AM")){
                        listaSituacao.get(i).setMai(resultado);
                    }
                }
            }else if (mes==6){
                if (listaSituacao.get(i).getJun().equalsIgnoreCase("X")){
                    listaSituacao.get(i).setJun(resultado);
                }else if (listaSituacao.get(i).getJun().equalsIgnoreCase("AM")){
                    if (resultado.equalsIgnoreCase("VR")){
                        listaSituacao.get(i).setJun(resultado);
                    }
                }else if (listaSituacao.get(i).getJun().equalsIgnoreCase("VD")){
                    if (resultado.equalsIgnoreCase("VR")){
                        listaSituacao.get(i).setJun(resultado);
                    }else if (resultado.equalsIgnoreCase("AM")){
                        listaSituacao.get(i).setJun(resultado);
                    }
                }
            }else if (mes==7){
                if (listaSituacao.get(i).getJul().equalsIgnoreCase("X")){
                    listaSituacao.get(i).setJul(resultado);
                }else if (listaSituacao.get(i).getJul().equalsIgnoreCase("AM")){
                    if (resultado.equalsIgnoreCase("VR")){
                        listaSituacao.get(i).setJul(resultado);
                    }
                }else if (listaSituacao.get(i).getJul().equalsIgnoreCase("VD")){
                    if (resultado.equalsIgnoreCase("VR")){
                        listaSituacao.get(i).setJul(resultado);
                    }else if (resultado.equalsIgnoreCase("AM")){
                        listaSituacao.get(i).setJul(resultado);
                    }
                }
            }else if (mes==8){
                if (listaSituacao.get(i).getAgo().equalsIgnoreCase("X")){
                    listaSituacao.get(i).setAgo(resultado);
                }else if (listaSituacao.get(i).getAgo().equalsIgnoreCase("AM")){
                    if (resultado.equalsIgnoreCase("VR")){
                        listaSituacao.get(i).setAgo(resultado);
                    }
                }else if (listaSituacao.get(i).getAgo().equalsIgnoreCase("VD")){
                    if (resultado.equalsIgnoreCase("VR")){
                        listaSituacao.get(i).setAgo(resultado);
                    }else if (resultado.equalsIgnoreCase("AM")){
                        listaSituacao.get(i).setAgo(resultado);
                    }
                }
            }else if (mes==9){
                if (listaSituacao.get(i).getSet().equalsIgnoreCase("X")){
                    listaSituacao.get(i).setSet(resultado);
                }else if (listaSituacao.get(i).getSet().equalsIgnoreCase("AM")){
                    if (resultado.equalsIgnoreCase("VR")){
                        listaSituacao.get(i).setSet(resultado);
                    }
                }else if (listaSituacao.get(i).getSet().equalsIgnoreCase("VD")){
                    if (resultado.equalsIgnoreCase("VR")){
                        listaSituacao.get(i).setSet(resultado);
                    }else if (resultado.equalsIgnoreCase("AM")){
                        listaSituacao.get(i).setSet(resultado);
                    }
                }
            }else if (mes==10){
                if (listaSituacao.get(i).getOut().equalsIgnoreCase("X")){
                    listaSituacao.get(i).setOut(resultado);
                }else if (listaSituacao.get(i).getOut().equalsIgnoreCase("AM")){
                    if (resultado.equalsIgnoreCase("VR")){
                        listaSituacao.get(i).setOut(resultado);
                    }
                }else if (listaSituacao.get(i).getOut().equalsIgnoreCase("VD")){
                    if (resultado.equalsIgnoreCase("VR")){
                        listaSituacao.get(i).setOut(resultado);
                    }else if (resultado.equalsIgnoreCase("AM")){
                        listaSituacao.get(i).setOut(resultado);
                    }
                }
            }else if (mes==11){
                if (listaSituacao.get(i).getNov().equalsIgnoreCase("X")){
                    listaSituacao.get(i).setNov(resultado);
                }else if (listaSituacao.get(i).getNov().equalsIgnoreCase("AM")){
                    if (resultado.equalsIgnoreCase("VR")){
                        listaSituacao.get(i).setNov(resultado);
                    }
                }else if (listaSituacao.get(i).getNov().equalsIgnoreCase("VD")){
                    if (resultado.equalsIgnoreCase("VR")){
                        listaSituacao.get(i).setNov(resultado);
                    }else if (resultado.equalsIgnoreCase("AM")){
                        listaSituacao.get(i).setNov(resultado);
                    }
                }
            }else {
                if (listaSituacao.get(i).getDez().equalsIgnoreCase("X")){
                    listaSituacao.get(i).setDez(resultado);
                }else if (listaSituacao.get(i).getDez().equalsIgnoreCase("AM")){
                    if (resultado.equalsIgnoreCase("VR")){
                        listaSituacao.get(i).setDez(resultado);
                    }
                }else if (listaSituacao.get(i).getDez().equalsIgnoreCase("VD")){
                    if (resultado.equalsIgnoreCase("VR")){
                        listaSituacao.get(i).setDez(resultado);
                    }else if (resultado.equalsIgnoreCase("AM")){
                        listaSituacao.get(i).setDez(resultado);
                    }
                }
            }
        }
    }
    
    public String verificarSituacao(Rotinaatividade rotinaatividade) {
        boolean concluida=false;
        if (rotinaatividade==null){
            return "X";
        }
        if(rotinaatividade.getAtividades().getSituacao()){
            concluida = true;
        }
        if (rotinaatividade == null) {
            return "X";
        } else {
            String sdata = Formatacao.ConvercaoDataPadrao(new Date());
            Date data = Formatacao.ConvercaoStringDataBrasil(sdata);
            if (rotinaatividade.getAtividades().getPrazo().after(data)) {
                return "AM";
            } else {
               if (concluida) {
                    return "VD";
                } else {
                    return "VR";
                }
            }
        }
    }
    
    public String imagemJan(SituacaoBean situacaoBean) {
        if (situacaoBean.getJan().equalsIgnoreCase("VR")) {
            return "/resources/img/bolaVermelha.png";
        } else if (situacaoBean.getJan().equalsIgnoreCase("AM")) {
            return "/resources/img/bolaAmarela.png";
        } else if (situacaoBean.getJan().equalsIgnoreCase("VD")){
            return "/resources/img/bolaVerde.png";
        }else return "/resources/img/x.png";    
    }

    public String imagemFev(SituacaoBean situacaoBean) {
        if (situacaoBean.getFev().equalsIgnoreCase("VR")) {
            return "/resources/img/bolaVermelha.png";
        } else if (situacaoBean.getFev().equalsIgnoreCase("AM")) {
            return "/resources/img/bolaAmarela.png";
       } else if (situacaoBean.getFev().equalsIgnoreCase("VD")){
            return "/resources/img/bolaVerde.png";
        }else return "/resources/img/x.png";    
    }

    public String imagemMar(SituacaoBean situacaoBean) {
        if (situacaoBean.getMar().equalsIgnoreCase("VR")) {
            return "/resources/img/bolaVermelha.png";
        } else if (situacaoBean.getMar().equalsIgnoreCase("AM")) {
            return "/resources/img/bolaAmarela.png";
        } else if (situacaoBean.getMar().equalsIgnoreCase("VD")){
            return "/resources/img/bolaVerde.png";
        }else return "/resources/img/x.png";    
    }

    public String imagemAbr(SituacaoBean situacaoBean) {
        if (situacaoBean.getAbr().equalsIgnoreCase("VR")) {
            return "/resources/img/bolaVermelha.png";
        } else if (situacaoBean.getAbr().equalsIgnoreCase("AM")) {
            return "/resources/img/bolaAmarela.png";
        } else if (situacaoBean.getAbr().equalsIgnoreCase("VD")){
            return "/resources/img/bolaVerde.png";
        }else return "/resources/img/x.png";    
    }

    public String imagemMai(SituacaoBean situacaoBean) {
        if (situacaoBean.getMai().equalsIgnoreCase("VR")) {
            return "/resources/img/bolaVermelha.png";
        } else if (situacaoBean.getMai().equalsIgnoreCase("AM")) {
            return "/resources/img/bolaAmarela.png";
        } else if (situacaoBean.getMai().equalsIgnoreCase("VD")){
            return "/resources/img/bolaVerde.png";
        }else return "/resources/img/x.png";    
    }

    public String imagemJun(SituacaoBean situacaoBean) {
        if (situacaoBean.getJun().equalsIgnoreCase("VR")) {
            return "/resources/img/bolaVermelha.png";
        } else if (situacaoBean.getJun().equalsIgnoreCase("AM")) {
            return "/resources/img/bolaAmarela.png";
        } else if (situacaoBean.getJun().equalsIgnoreCase("VD")){
            return "/resources/img/bolaVerde.png";
        }else return "/resources/img/x.png";    
    }

    public String imagemJul(SituacaoBean situacaoBean) {
        if (situacaoBean.getJul().equalsIgnoreCase("VR")) {
            return "/resources/img/bolaVermelha.png";
        } else if (situacaoBean.getJul().equalsIgnoreCase("AM")) {
            return "/resources/img/bolaAmarela.png";
        } else if (situacaoBean.getJul().equalsIgnoreCase("VD")){
            return "/resources/img/bolaVerde.png";
        }else return "/resources/img/x.png";    
    }

    public String imagemAgo(SituacaoBean situacaoBean) {
        if (situacaoBean.getAgo().equalsIgnoreCase("VR")) {
            return "/resources/img/bolaVermelha.png";
        } else if (situacaoBean.getAgo().equalsIgnoreCase("AM")) {
            return "/resources/img/bolaAmarela.png";
        } else if (situacaoBean.getAgo().equalsIgnoreCase("VD")){
            return "/resources/img/bolaVerde.png";
        }else return "/resources/img/x.png";    
    }

    public String imagemSet(SituacaoBean situacaoBean) {
        if (situacaoBean.getSet().equalsIgnoreCase("VR")) {
            return "/resources/img/bolaVermelha.png";
        } else if (situacaoBean.getSet().equalsIgnoreCase("AM")) {
            return "/resources/img/bolaAmarela.png";
        } else if (situacaoBean.getSet().equalsIgnoreCase("VD")){
            return "/resources/img/bolaVerde.png";
        }else return "/resources/img/x.png";    
    }

    public String imagemOut(SituacaoBean situacaoBean) {
        if (situacaoBean.getOut().equalsIgnoreCase("VR")) {
            return "/resources/img/bolaVermelha.png";
        } else if (situacaoBean.getOut().equalsIgnoreCase("AM")) {
            return "/resources/img/bolaAmarela.png";
        } else if (situacaoBean.getOut().equalsIgnoreCase("VD")){
            return "/resources/img/bolaVerde.png";
        }else return "/resources/img/x.png";    
    }

    public String imagemNov(SituacaoBean situacaoBean) {
        if (situacaoBean.getNov().equalsIgnoreCase("VR")) {
            return "/resources/img/bolaVermelha.png";
        } else if (situacaoBean.getNov().equalsIgnoreCase("AM")) {
            return "/resources/img/bolaAmarela.png";
        } else if (situacaoBean.getNov().equalsIgnoreCase("VD")){
            return "/resources/img/bolaVerde.png";
        }else return "/resources/img/x.png";    
    }

    public String imagemDez(SituacaoBean situacaoBean) {
        if (situacaoBean.getDez().equalsIgnoreCase("VR")) {
            return "/resources/img/bolaVermelha.png";
        } else if (situacaoBean.getDez().equalsIgnoreCase("AM")) {
            return "/resources/img/bolaAmarela.png";
        } else if (situacaoBean.getDez().equalsIgnoreCase("VD")){
            return "/resources/img/bolaVerde.png";
        }else return "/resources/img/x.png";    
    }

}