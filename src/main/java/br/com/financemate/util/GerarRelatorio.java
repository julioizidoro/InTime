package br.com.financemate.util;



import static br.com.financemate.connection.ConectionFactory.getConnection;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.persistence.internal.jpa.EntityManagerImpl;
import org.primefaces.context.RequestContext;

import br.com.financemate.connection.ConectionFactory;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;

/**
 *
 * @author Wolverine
 */
public class GerarRelatorio {
    
	public void gerarRelatorioDSPDF(String caminhoRelatorio, Map parameters, JRDataSource jrds, String nomeArquivo ) throws JRException, IOException{
        FacesContext facesContext = FacesContext.getCurrentInstance();  
        ServletContext servletContext = (ServletContext)facesContext.getExternalContext().getContext();
        InputStream reportStream = facesContext.getExternalContext()  
                .getResourceAsStream(caminhoRelatorio);  
        JasperPrint arquivoPrint=null;
        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
        response.reset();
        response.setContentType("application/pdf");
        ServletOutputStream servletOutputStream = response.getOutputStream();  
        RequestContext.getCurrentInstance().closeDialog(null);
      
        // envia para o navegador o PDF gerado  
        JasperRunManager.runReportToPdfStream(reportStream,  
                servletOutputStream, parameters,jrds);  
          
        servletOutputStream.flush();  
        servletOutputStream.close();  
  
        facesContext.responseComplete();  
    }
    
    public void gerarRelatorioSqlPDF(String caminhoRelatorio, Map<String, Object> parameters, String nomeArquivo, String subDir ) throws JRException, IOException, SQLException{
        FacesContext facesContext = FacesContext.getCurrentInstance();  
        ServletContext servletContext = (ServletContext)facesContext.getExternalContext().getContext();
        InputStream reportStream = facesContext.getExternalContext()  
                .getResourceAsStream(caminhoRelatorio);  
        EntityManager mg = ConectionFactory.getConnection();
        mg.getTransaction().begin();
        EntityManagerImpl factory = (EntityManagerImpl) mg;
        Connection conn = ((EntityManagerImpl) (mg.getDelegate())).getServerSession().getAccessor().getConnection();
        

        if (subDir!=null){
            subDir = servletContext.getRealPath(subDir);
            subDir = subDir + File.separator + "a";
            subDir = subDir.substring(0, (subDir.length()-1));
            System.out.println(subDir);
            parameters.put("SUBREPORT_DIR", subDir);
        }
        JasperPrint arquivoPrint=null;
        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
        response.reset();
        response.setContentType("application/pdf");
        ServletOutputStream servletOutputStream = response.getOutputStream();  
        RequestContext.getCurrentInstance().closeDialog(null);
      
        // envia para o navegador o PDF gerado  
        JasperRunManager.runReportToPdfStream(reportStream,  
                servletOutputStream, parameters, conn);  
          
        servletOutputStream.flush();  
        servletOutputStream.close();  
  
        facesContext.responseComplete();  
    }
    
    
    
}