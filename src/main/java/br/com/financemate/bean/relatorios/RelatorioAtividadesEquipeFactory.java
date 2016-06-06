package br.com.financemate.bean.relatorios;

import java.util.List;

/**
 *
 * @author Wolverine
 */
@SuppressWarnings("unchecked")
public class RelatorioAtividadesEquipeFactory {
    
    private static List<RelatorioAtividadesEquipeBean> listaAtivdades;

    public static List<RelatorioAtividadesEquipeBean> getListaAtivdades() {
        return listaAtivdades;
    }

    public static void setListaAtivdades(List<RelatorioAtividadesEquipeBean> listaAtivdades) {
        RelatorioAtividadesEquipeFactory.listaAtivdades = listaAtivdades;
    }
    
    public static List<RelatorioAtividadesEquipeBean> listar(){
        return getListaAtivdades();
    }
    
}