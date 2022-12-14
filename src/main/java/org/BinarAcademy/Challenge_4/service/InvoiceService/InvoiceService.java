package org.BinarAcademy.Challenge_4.service.InvoiceService;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class InvoiceService {
    private DataSource dataSource;

    public Connection getConnection(){
        try {
            return dataSource.getConnection();
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public JasperPrint generateInvoice(String username, Long scheduleId) throws Exception{
        InputStream filInvoice = new ClassPathResource("invoice/Invoice.jasper").getInputStream();
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(filInvoice);
        Map<String, Object> params = new HashMap<>();
        params.put("username", username);
        params.put("scheduleId", scheduleId);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, getConnection());
        return jasperPrint;
    }
}
