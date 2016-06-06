package br.com.financemate.bean.relatorios;

import java.util.List;

/**
 *
 * @author Wolverine
 */
@SuppressWarnings("unchecked")
public class RelatorioClienteFactory {
    
    private static List<RelatorioClienteBean> listaAtividades;

    public static List<RelatorioClienteBean> getListaAtividades() {
        return listaAtividades;
    }

    public static void setListaAtividades(List<RelatorioClienteBean> listaAtividades) {
        RelatorioClienteFactory.listaAtividades = listaAtividades;
    }
    
    public static List<RelatorioClienteBean> listar(){
        return getListaAtividades();
    }
    
}