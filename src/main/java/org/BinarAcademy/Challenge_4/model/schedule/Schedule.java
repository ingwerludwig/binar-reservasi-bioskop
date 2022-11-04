package org.BinarAcademy.Challenge_4.model.schedule;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.NoArgsConstructor;
import org.BinarAcademy.Challenge_4.model.film.Film;
import org.BinarAcademy.Challenge_4.model.order.Order;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Entity
@Table(name = "schedules")
public class Schedule {
    @Id
    @SequenceGenerator(
            name = "schedule_sequence",
            sequenceName = "schedule_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "schedule_sequence"
    )

    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "tanggal_tayang")
    private LocalDate tanggal_tayang;

    @Column(name = "jam_mulai")
    private LocalTime jam_mulai;

    @Column(name = "jam_selesai")
    private LocalTime jam_selesai;

    @Column(name = "harga_tiket")
    private Double harga_tiket;

    @ManyToOne(optional = false)
    @JoinColumn(name = "film_code", referencedColumnName = "code", nullable = false)
    @JsonBackReference
    private Film film;

    @OneToMany(mappedBy = "schedule")
    @JsonManagedReference
    private Set<Order> order = new HashSet<>();

    public Schedule(
            LocalDate tanggal_tayang,
            LocalTime jam_mulai,
            LocalTime jam_selesai,
            Double harga_tiket,
            Film film) {
        this.tanggal_tayang = tanggal_tayang;
        this.jam_mulai = jam_mulai;
        this.jam_selesai = jam_selesai;
        this.harga_tiket = harga_tiket;
        this.film = film;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getTanggal_tayang() {
        return tanggal_tayang;
    }
    public void setTanggal_tayang(LocalDate tanggal_tayang) {
        this.tanggal_tayang = tanggal_tayang;
    }

    public LocalTime getJam_mulai() {
        return this.jam_mulai;
    }
    public void setJam_mulai(LocalTime jam_mulai) {
        this.jam_mulai = jam_mulai;
    }

    public LocalTime getJam_selesai() {
        return this.jam_selesai;
    }
    public void setJam_selesai(LocalTime jam_selesai) {
        this.jam_selesai = jam_selesai;
    }

    public Double getHarga_tiket() {
        return harga_tiket;
    }
    public void setHarga_tiket(Double harga_tiket) {
        this.harga_tiket = harga_tiket;
    }

    public Film getFilm() {
        return this.film;
    }
    public void setFilm(Film film) {
        this.film = film;
    }


    public Set<Order> getOrder() {
        return order;
    }
    public void setOrder(Set<Order> order) {
        this.order = order;
    }
}
