package org.BinarAcademy.Challenge_4.model.seats;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.NoArgsConstructor;
import org.BinarAcademy.Challenge_4.model.film.Film;
import org.BinarAcademy.Challenge_4.model.order.Order;
import org.BinarAcademy.Challenge_4.model.schedule.Schedule;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Entity
@Table(name = "seats")
public class Seat {

    @Id
    @Column(name = "no_kursi", nullable = false)
    private Integer no_kursi;

    @Column(name = "nama_studio", nullable = false)
    private  String nama_studio;

    @Column(name = "is_booked", nullable = false)
    private  Boolean isBooked;

    @OneToMany(mappedBy = "seat")
    @JsonManagedReference
    private Set<Order> order = new HashSet<>();

    public Seat(
            Integer no_kursi,
            String nama_studio,
            Boolean isBooked) {
        this.no_kursi = no_kursi;
        this.nama_studio = nama_studio;
        this.isBooked = isBooked;

    }

    public Boolean getBooked() {
        return isBooked;
    }
    public void setBooked(Boolean booked) {
        this.isBooked = booked;
    }
    public Integer getNo_kursi() {
        return no_kursi;
    }
    public void setNo_kursi(Integer no_kursi) {
        this.no_kursi = no_kursi;
    }


    public Set<Order> getOrder() {
        return order;
    }
    public void setOrder(Set<Order> order) {
        this.order = order;
    }
    public String getNama_studio() {
        return nama_studio;
    }
    public void setNama_studio(String nama_studio) {
        this.nama_studio = nama_studio;
    }

}
