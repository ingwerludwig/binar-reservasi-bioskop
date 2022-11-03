package org.BinarAcademy.Challenge_4.repository.order;

import org.BinarAcademy.Challenge_4.model.order.Order;
import org.BinarAcademy.Challenge_4.model.schedule.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface OrderRepository extends JpaRepository<Schedule, Integer> {

    List<Order> findById(Long postId);

    @Transactional
    void deleteById(long tutorialId);

}
