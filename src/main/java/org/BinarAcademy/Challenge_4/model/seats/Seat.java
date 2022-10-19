package org.BinarAcademy.Challenge_4.model.seats;

import lombok.NoArgsConstructor;
import org.BinarAcademy.Challenge_4.model.schedule.Schedule;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Table(name = "seat")
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

    @ManyToOne(targetEntity = Schedule.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "schedule_id", referencedColumnName = "id", nullable = false)
    private Schedule schedule;
}
