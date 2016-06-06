package br.com.financemate.manageBean.relatorios;

import br.com.financemate.bean.relatorios.RelatorioAtividadesEquipeBean;
import br.com.financemate.bean.relatorios.RelatorioAtividadesEquipeFactory;
import br.com.financemate.manageBean.atividades.AtividadesEquipeMB;
import br.com.financemate.model.Atividades;
import br.com.financemate.util.GerarRelatorio;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author Wolverine
 */
@Named
@ViewScoped
public class RelatorioAtividadesEquipeMB implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
    private AtividadesEquipeMB atividadesEquipeMB; 
    
    public List<RelatorioAtividadesEquipeBean> gerarDS(List<Atividades> listaAtividadesUsuario){
        List<RelatorioAtividadesEquipeBean> listaAtividaes = new ArrayList<RelatorioAtividadesEquipeBean>();
        if ((listaAtividadesUsuario!=null) && (listaAtividadesUsuario.size()>0)){
            for(int i=0;i<listaAtividadesUsuario.size();i++){
                RelatorioAtividadesEquipeBean r = new RelatorioAtividadesEquipeBean();
                r.setAtividades(listaAtividadesUsuario.get(i).getNome());
                r.setCliente(listaAtividadesUsuario.get(i).getCliente().getNomefantasia());
                r.setPrazo(listaAtividadesUsuario.get(i).getPrazo());
                r.setResponsavel(listaAtividadesUsuario.get(i).getUsuario().getNome());
                r.setSituacao(listaAtividadesUsuario.get(i).getPrioridade());
                r.setSubDepartamento(listaAtividadesUsuario.get(i).getSubdepartamento().getNome());
                listaAtividaes.add(r);
            }
        }
        return listaAtividaes;
    }
    
    
    
    public void gerarRelatorioAtividadesEquipe() throws JRException, IOException {
        String caminhoRelatorio = "/resources/report/atividades/reportatividadesequipe.jasper";  
        Map parameters = new HashMap();
        String nomeArquivo = "AtividadesEquipe";
        RelatorioAtividadesEquipeFactory.setListaAtivdades(gerarDS(atividadesEquipeMB.getListaAtividades()));
        JRDataSource jrds = new JRBeanCollectionDataSource(RelatorioAtividadesEquipeFactory.getListaAtivdades());
        GerarRelatorio gerarRelatorio = new GerarRelatorio();
        gerarRelatorio.gerarRelatorioDSPDF(caminhoRelatorio, parameters, jrds, nomeArquivo);
    }
    
}