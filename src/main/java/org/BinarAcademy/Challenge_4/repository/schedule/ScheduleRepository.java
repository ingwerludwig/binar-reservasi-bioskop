package org.BinarAcademy.Challenge_4.repository.schedule;

import net.bytebuddy.asm.Advice;
import org.BinarAcademy.Challenge_4.model.film.Film;
import org.BinarAcademy.Challenge_4.model.schedule.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule,Integer> {
    @Query("SELECT s FROM Schedule s WHERE s.tanggal_tayang= ?1")
    List<Schedule> findScheduleByTanggalTayang(LocalDate tanggal_tayang);

//    @Query( "SELECT pg FROM Book bk join bk.pages pg WHERE bk.bookId = :bookId")
    @Query("SELECT s FROM Schedule s WHERE s.film.code= :code")
    List<Schedule> findScheduleByFilmCode(int code);

    @Query("SELECT s FROM Schedule s WHERE s.film.code= :code and s.tanggal_tayang= :tanggal_tayang")
    Schedule findScheduleByFilmCodeAndTanggaltayang(int code, LocalDate tanggal_tayang);

}

//@Repository
//public interface FilmRepository extends JpaRepository<Film, Integer> {
//
//    @Query("SELECT f FROM Film f WHERE f.nama= ?1")
//    List<Film> findFilmByNama(String nama);
//
//    @Query("SELECT f FROM Film f WHERE f.code= ?1")
//    Film findFilmByCode(int code);
//
//    @Query("SELECT f FROM Film f WHERE f.isPlaying= true")
//    List<Film> findFilmByIsPlaying();
//
//}
