package org.BinarAcademy.Challenge_4.service.OrderService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.BinarAcademy.Challenge_4.model.order.Order;
import org.BinarAcademy.Challenge_4.model.seats.Seat;
import org.BinarAcademy.Challenge_4.repository.order.OrderRepository;
import org.BinarAcademy.Challenge_4.repository.seat.SeatRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.jasperreports.JasperReportsUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {

    private static final Logger LOG = LoggerFactory.getLogger(OrderService.class);
    @Autowired
    private final OrderRepository repository;

    @Autowired
    private final SeatRepository seatRepository;

    @Autowired
    public OrderService(OrderRepository repository, SeatRepository seatRepository){
        this.repository = repository;
        this.seatRepository = seatRepository;
    }

    public final String invoice_order_path = "jasper/invoice_order.jrxml";

    public void addNewOrder(Order newOrder) throws IOException {
        Seat existSeat = seatRepository.findSeatById(newOrder.getSeat().getNo_kursi());

        if(existSeat == null){
            throw new NullPointerException("Choose the available seat!");
        }else{
            if(existSeat.getBooked() == Boolean.TRUE){
                throw new IllegalStateException("Requested seat in order has been booked");
            }
            else {
                existSeat.setBooked(Boolean.TRUE);
                this.generateInvoiceFor(newOrder);
                repository.save(newOrder);
            }
        }
    }

    public void updateOrderToLunas(Integer orderId){
        Order existOrder = repository.findOrderById(orderId);
        try{
            existOrder.setCatatan("LUNAS");
        }catch(Exception e){
            LOG.error(String.valueOf(e));
        }
    }

    public List<Order> getAllOrderByLunasOrNot(String lunasOrNot){
        return repository.findOrderByLunasOrNot(lunasOrNot).stream().toList();
    }

    public List<Order> getAllOrderByMetodePembayaran(String metode){
        return repository.findOrderByMetodePembayaran(metode).stream().toList();
    }

    public List<Order> getAllOrderBySchedule(Integer schedule_id){
        return repository.findOrderByScedule(schedule_id).stream().toList();
    }

    public List<Order> getAllOrder(){
        return repository.findAll().stream().toList();
    }

    public void deleteOrder(Integer orderId){
        Order existOrder = repository.findOrderById(orderId);

        Seat existSeat = seatRepository.findSeatById(existOrder.getSeat().getNo_kursi());
        try{
            existSeat.setBooked(Boolean.FALSE);
            repository.delete(existOrder);
        }catch(Exception e){
            LOG.error(String.valueOf(e));
        }
    }

    // Load invoice jrxml template
    private JasperReport loadTemplate() throws JRException {

        LOG.info(String.format("Invoice template path : %s", invoice_order_path));

        final InputStream reportInputStream = getClass().getResourceAsStream(invoice_order_path);
        final JasperDesign jasperDesign = JRXmlLoader.load(reportInputStream);

        return JasperCompileManager.compileReport(jasperDesign);
    }

    public void generateInvoiceFor(Order order) throws IOException {
        File pdfFile = File.createTempFile("binar-reservasi-invoice", ".pdf");

        try(FileOutputStream pos = new FileOutputStream(pdfFile))
        {
            final JasperReport report = loadTemplate();
            final Order parameters = parameters(order);

            final JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(Collections.singletonList("Invoice"));
            // Render the PDF file
            JasperReportsUtils.renderAsPdf(report, (Map) parameters, dataSource, pos);
        }
        catch (final Exception e)
        {
            LOG.error(String.format("An error occured during PDF creation: %s", e));
        }

    }

    private Order parameters(Order order) {
        return order;
    }

}
