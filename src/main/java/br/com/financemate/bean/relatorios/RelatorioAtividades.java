package br.com.financemate.bean.relatorios;

import br.com.financemate.bean.Formatacao;
import br.com.financemate.facade.DepartamentoFacade;
import br.com.financemate.model.Atividades;
import br.com.financemate.model.Cliente;
import br.com.financemate.model.Config;
import br.com.financemate.model.Departamento;
import br.com.financemate.model.Subdepartamento;
import br.com.financemate.repository.AtividadesRepository;
import br.com.financemate.util.GerarRelatorio;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author Wolverine
 */
public class RelatorioAtividades {
    
    private Cliente cliente;
    private Departamento departamento;
    private Date dataInicial;
    private Date dataFinal;
    private List<RelatorioClienteBean> listaClienteBeans;
    private Config config;
    private AtividadesRepository atividadesRepository;
    

    public RelatorioAtividades(Cliente cliente, Departamento departamento, Date dataInicial, Date dataFinal, Config config) {
        this.cliente = cliente;
        this.config = config;
        this.departamento = departamento;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        gerarLita();
        try {
            gerarRelatorioAtividadesConcluidas();
        } catch (JRException ex) {
            Logger.getLogger(RelatorioAtividades.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RelatorioAtividades.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void gerarLita(){
        if (departamento!=null){
            List<RelatorioClienteBean> listaLocal = new ArrayList<RelatorioClienteBean>();
            for(int i=0;i<departamento.getSubdepartamentoList().size();i++){
                listaLocal.add(pesquisarTarefasSubdepartamento(departamento.getSubdepartamentoList().get(i)));  
            }
            calculcarTempoTotal(listaLocal);
        }else {
            DepartamentoFacade departamentoFacade = new DepartamentoFacade();
            List<Departamento> listaDepartamentos = departamentoFacade.listar("", config.getDepartamento().getIddepartamento());
            for(int d=0;d<listaDepartamentos.size();d++){
                List<RelatorioClienteBean> listaLocal = new ArrayList<RelatorioClienteBean>();
                for(int s=0;s<listaDepartamentos.get(d).getSubdepartamentoList().size();s++){
                    listaLocal.add(pesquisarTarefasSubdepartamento(listaDepartamentos.get(d).getSubdepartamentoList().get(s)));
                }
                calculcarTempoTotal(listaLocal);
            }
        }
    }
    
    
    private RelatorioClienteBean pesquisarTarefasSubdepartamento(Subdepartamento subdepartamento){
        
        String sql = "Select a From Atividades a where a.prazo>='" + Formatacao.ConvercaoDataSql(dataInicial) + "' and '" +
                 Formatacao.ConvercaoDataSql(dataFinal) + "' and a.cliente.idcliente=" + cliente.getIdcliente() +
                    " and a.subdepartamento.idsubdepartamento=" + subdepartamento.getIdsubdepartamento();
        List<Atividades> listaAtividades = atividadesRepository.list(sql);
        RelatorioClienteBean relatorioClienteBean = new RelatorioClienteBean();
        if ((listaAtividades==null) || (listaAtividades.size()==0)){
            relatorioClienteBean.setHoraTotal("00:00");
            relatorioClienteBean.setHoras("00:00");
            relatorioClienteBean.setIdDepartamento(subdepartamento.getDepartamento().getIddepartamento());
            relatorioClienteBean.setNomeCliente(cliente.getNumero());
            relatorioClienteBean.setNomeDepartamento(subdepartamento.getDepartamento().getNome());
            relatorioClienteBean.setNomeSubDepartamento(subdepartamento.getNome());
            relatorioClienteBean.setNumeroAtiuvidades(0);
            relatorioClienteBean.setIntHoraTotal(0);
        }else {
            relatorioClienteBean.setIdDepartamento(subdepartamento.getDepartamento().getIddepartamento());
            relatorioClienteBean.setNomeCliente(cliente.getNumero());
            relatorioClienteBean.setNomeDepartamento(subdepartamento.getDepartamento().getNome());
            relatorioClienteBean.setNomeSubDepartamento(subdepartamento.getNome());
            relatorioClienteBean.setNumeroAtiuvidades(listaAtividades.size());
            int tempo = 0;
            for(int i=0;i<listaAtividades.size();i++){
                tempo = tempo + listaAtividades.get(i).getTempo();
            }
            String sTempo = "00:00";
            if (tempo>0){
                int horas = tempo / 60;
                int minutos = tempo % 60;
                minutos = minutos * 60;
                if (horas>9){
                    sTempo = String.valueOf(horas);
                }else {
                    sTempo = "0" + String.valueOf(horas);
                }
                if (minutos>9){
                    sTempo = sTempo + ":" +  String.valueOf(minutos);
                }else {
                    sTempo = sTempo + ":0" +  String.valueOf(minutos);
                }
            }
            relatorioClienteBean.setIntHoraTotal(tempo);
            relatorioClienteBean.setHoraTotal("00:00");
            relatorioClienteBean.setHoras(sTempo);
        }
        return relatorioClienteBean;
    }
    
    private void calculcarTempoTotal(List<RelatorioClienteBean> listaLocal){
        if (listaLocal!=null){
            int tempo =0;
            for(int i=0;i<listaLocal.size();i++){
                tempo = tempo + listaLocal.get(i).getIntHoraTotal();
            }
            String sTempo = "00:00";
            if (tempo>0){
                int horas = tempo / 60;
                int minutos = tempo % 60;
                minutos = minutos * 60;
                if (horas>9){
                    sTempo = String.valueOf(horas);
                }else {
                    sTempo = "0" + String.valueOf(horas);
                }
                if (minutos>9){
                    sTempo = sTempo + ":" +  String.valueOf(minutos);
                }else {
                    sTempo = sTempo + ":0" +  String.valueOf(minutos);
                }
            }
            for(int i=0;i<listaLocal.size();i++){
                listaLocal.get(i).setHoraTotal(sTempo);
                listaClienteBeans.add(listaLocal.get(i));
            }
        }
    }
    
    public void gerarRelatorioAtividadesConcluidas() throws JRException, IOException{
        String caminhoRelatorio = "/reports/atividades/atividadesconcluidas.jasper";  
        Map parameters = new HashMap();
        String nomeArquivo = "AtividadesConcluidas.pdf";
        RelatorioClienteFactory.setListaAtividades(listaClienteBeans);
        JRDataSource jrds = new JRBeanCollectionDataSource(AtividadesConcluidasFactory.getListaAtividades());
        GerarRelatorio gerarRelatorio = new GerarRelatorio();
        gerarRelatorio.gerarRelatorioDSPDF(caminhoRelatorio, parameters, jrds, nomeArquivo);
    }
    
    
}