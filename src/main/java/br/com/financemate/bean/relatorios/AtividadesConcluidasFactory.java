package br.com.financemate.bean.relatorios;

import java.util.List;

/**
 *
 * @author Wolverine
 */

@SuppressWarnings("unchecked")
public class AtividadesConcluidasFactory {
    
    public static List<AtividadesConcluidasBean> listaAtividades;

    public static List<AtividadesConcluidasBean> getListaAtividades() {
        return listaAtividades;
    }

    public static void setListaAtividades(List<AtividadesConcluidasBean> listaAtividades) {
        AtividadesConcluidasFactory.listaAtividades = listaAtividades;
    }
    
    public static List<AtividadesConcluidasBean> gerarLita(){
        return getListaAtividades();
    }
    
}