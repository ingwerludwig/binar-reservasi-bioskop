package org.BinarAcademy.Challenge_4.model.order;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.NoArgsConstructor;
import org.BinarAcademy.Challenge_4.model.schedule.Schedule;
import org.BinarAcademy.Challenge_4.model.seats.Seat;
import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @SequenceGenerator(
            name = "order_sequence",
            sequenceName = "order_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "order_sequence"
    )

    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "metode_pembayaran")
    private String metode_pembayaran;

    @Column(name = "tanggal_order")
    private LocalDate tanggal_order;

    @Column(name = "catatan")
    private String catatan;

    @ManyToOne(optional = false)
    @JoinColumn(name = "schedule_id", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    private Schedule schedule;

    @ManyToOne(optional = false)
    @JsonBackReference
    @JoinColumn(name = "no_seat", referencedColumnName = "no_kursi", nullable = false)
    private Seat seat;

    public Order(String metode_pembayaran,
                 LocalDate tanggal_order,
                 String catatan,
                 Schedule schedule,
                 Seat seat) {
        this.metode_pembayaran = metode_pembayaran;
        this.tanggal_order = tanggal_order;
        this.catatan = catatan;
        this.schedule = schedule;
        this.seat = seat;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getMetode_pembayaran() {
        return metode_pembayaran;
    }
    public void setMetode_pembayaran(String metode_pembayaran) {
        this.metode_pembayaran = metode_pembayaran;
    }
    public LocalDate getTanggal_order() {
        return tanggal_order;
    }
    public void setTanggal_order(LocalDate tanggal_order) {
        this.tanggal_order = tanggal_order;
    }
    public String getCatatan() {
        return catatan;
    }
    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }


    public Schedule getSchedule() {
        return this.schedule;
    }
    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }


    public Seat getSeat() {
        return seat;
    }
    public void setSeat(Seat seat) {
        this.seat = seat;
    }

}
