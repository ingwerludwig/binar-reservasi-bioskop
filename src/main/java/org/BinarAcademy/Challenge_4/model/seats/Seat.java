package org.BinarAcademy.Challenge_4.model.seats;

import lombok.NoArgsConstructor;
import org.BinarAcademy.Challenge_4.model.film.Film;
import org.BinarAcademy.Challenge_4.model.schedule.Schedule;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@Entity
@Table(name = "seats")
public class Seat {

    @Id
    @SequenceGenerator(
            name = "seat_sequence",
            sequenceName = "seat_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seat_sequence"
    )

    @Column(name = "no_kursi", nullable = false)
    private Integer no_kursi;

    @Column(name = "nama_studio", nullable = false)
    private  String nama_studio;

    @ManyToOne(optional = false)
    @JoinColumn(name = "schedule_id", referencedColumnName = "id", nullable = false)
    private Schedule schedule;

    public Seat(
            Integer no_kursi,
            String nama_studio,
            Schedule schedule) {
        this.no_kursi = no_kursi;
        this.nama_studio = nama_studio;
        this.schedule = schedule;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}
