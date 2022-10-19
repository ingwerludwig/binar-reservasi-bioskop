package org.BinarAcademy.Challenge_4.model.film;

import javax.persistence.*;
import lombok.NoArgsConstructor;
import org.BinarAcademy.Challenge_4.model.schedule.Schedule;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Entity
@Table(name = "film")
public class Film {
    @Id
    @SequenceGenerator(
            name = "film_sequence",
            sequenceName = "film_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "film_sequence"
    )

    @Column(name = "code", nullable = false)
    private int code;

    @Column(name = "nama")
    private String nama;

    @Column(name = "is_playing")
    private Boolean isPlaying;

    @OneToMany(mappedBy = "film", targetEntity = Schedule.class)
    private Set<Schedule> schedule = new HashSet<>();

    public Film(String nama, Boolean isPlaying){
        this.nama = nama;
        this.isPlaying = isPlaying;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }

    public Boolean getPlaying() {
        return this.isPlaying;
    }
    public void setPlaying(Boolean playing) {
        this.isPlaying = playing;
    }
}
