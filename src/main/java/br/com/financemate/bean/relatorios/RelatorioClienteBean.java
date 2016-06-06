package br.com.financemate.bean.relatorios;

/**
 *
 * @author Wolverine
 */
public class RelatorioClienteBean {
    
    private String nomeCliente;
    private String nomeDepartamento;
    private int idDepartamento;
    private String nomeSubDepartamento;
    private String horas;
    private int numeroAtiuvidades; 
    private String horaTotal;
    private int intHoraTotal;

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getNomeDepartamento() {
        return nomeDepartamento;
    }

    public void setNomeDepartamento(String nomeDepartamento) {
        this.nomeDepartamento = nomeDepartamento;
    }

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getNomeSubDepartamento() {
        return nomeSubDepartamento;
    }

    public void setNomeSubDepartamento(String nomeSubDepartamento) {
        this.nomeSubDepartamento = nomeSubDepartamento;
    }

    public String getHoras() {
        return horas;
    }

    public void setHoras(String horas) {
        this.horas = horas;
    }

    public int getNumeroAtiuvidades() {
        return numeroAtiuvidades;
    }

    public void setNumeroAtiuvidades(int numeroAtiuvidades) {
        this.numeroAtiuvidades = numeroAtiuvidades;
    }

    public String getHoraTotal() {
        return horaTotal;
    }

    public void setHoraTotal(String horaTotal) {
        this.horaTotal = horaTotal;
    }

    public int getIntHoraTotal() {
        return intHoraTotal;
    }

    public void setIntHoraTotal(int intHoraTotal) {
        this.intHoraTotal = intHoraTotal;
    }
    
    
    
}