package org.BinarAcademy.Challenge_4.repository.seat;

import org.BinarAcademy.Challenge_4.model.order.Order;
import org.BinarAcademy.Challenge_4.model.seats.Seat;
import org.BinarAcademy.Challenge_4.model.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer> {

    @Query("SELECT s FROM Seat s WHERE s.no_kursi = :no_kursi")
    Seat findSeatById(Integer no_kursi);

    @Query("SELECT s FROM Seat s WHERE s.nama_studio = :nama_studio")
    List<Seat> findSeatByNamaStudio(String nama_studio);

    @Query("SELECT s FROM Seat s WHERE s.isBooked = TRUE")
    List<Seat> findSeatByBooked();
}
