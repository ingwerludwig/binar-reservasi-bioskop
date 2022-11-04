package org.BinarAcademy.Challenge_4.repository.order;

import org.BinarAcademy.Challenge_4.model.order.Order;
import org.BinarAcademy.Challenge_4.model.schedule.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("SELECT o FROM Order o WHERE o.id= :orderId")
    Order findOrderById(Integer orderId);

    @Query("SELECT o FROM Order o WHERE o.catatan= :lunasOrNot")
    List<Order> findOrderByLunasOrNot(String lunasOrNot);

    @Query("SELECT o FROM Order o WHERE o.metode_pembayaran= :metode")
    List<Order> findOrderByMetodePembayaran(String metode);

    @Query("SELECT o FROM Order o WHERE o.seat.no_kursi= :no_kursi")
    List<Order> findOrderBySeat(Integer no_kursi);

    @Query("SELECT o FROM Order o WHERE o.schedule.id= :schedule_id")
    List<Order> findOrderByScedule(Integer schedule_id);

}
