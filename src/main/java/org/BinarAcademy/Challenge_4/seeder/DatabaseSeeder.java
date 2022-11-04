package org.BinarAcademy.Challenge_4.seeder;

import com.github.javafaker.Faker;
import org.BinarAcademy.Challenge_4.model.film.Film;
import org.BinarAcademy.Challenge_4.model.order.Order;
import org.BinarAcademy.Challenge_4.model.schedule.Schedule;
import org.BinarAcademy.Challenge_4.model.seats.Seat;
import org.BinarAcademy.Challenge_4.model.users.User;
import org.BinarAcademy.Challenge_4.repository.film.FilmRepository;
import org.BinarAcademy.Challenge_4.repository.order.OrderRepository;
import org.BinarAcademy.Challenge_4.repository.schedule.ScheduleRepository;
import org.BinarAcademy.Challenge_4.repository.seat.SeatRepository;
import org.BinarAcademy.Challenge_4.repository.user.UserRepository;
import org.BinarAcademy.Challenge_4.service.FilmService.FilmService;
import org.BinarAcademy.Challenge_4.service.ScheduleService.ScheduleService;
import org.BinarAcademy.Challenge_4.service.SeatService.SeatService;
import org.BinarAcademy.Challenge_4.service.UserService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Random;

@Configuration
public class DatabaseSeeder {

    @Autowired
    private FilmService filmService;

    @Autowired
    private UserService userService;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private SeatService seatService;


    // Seeding for Data Testing
    @Bean("Seeder")
    CommandLineRunner GenerateFakeFilmData(FilmRepository filmRepository, ScheduleRepository schRepository, UserRepository userRepository, SeatRepository seatRepository, OrderRepository orderRepository)
    {
        return args -> {

            // Seeding Database
            for(int i=0 ; i<10 ; i++){
                Faker faker = new Faker(new Locale("en-US"));

                // Generate User Dummy Data
                User user = new User(faker.name().name(), faker.internet().password(), faker.internet().emailAddress());
                userRepository.save(user);

                // Generate Film Dummy Data
                Film film = new Film(faker.superhero().name(),false);
                filmRepository.save(film);
                List<Film> allFilm = filmService.getAllFilm();
                Film[] targetArrayFilm = allFilm.stream().toArray(Film[]::new);
                if(targetArrayFilm.length == 0){
                    System.out.println("Film Target Array 0");
                }


                // Generate Schedule Dummy Data
                LocalDate desiredLocalDate,tanggal_tayang_date;
                desiredLocalDate = LocalDate.of(2022, 9, i+1);
                String desiredToString = desiredLocalDate.toString();
                tanggal_tayang_date = LocalDate.parse(desiredToString, DateTimeFormatter.ISO_DATE);
                Schedule schedule = new Schedule(
                        tanggal_tayang_date,
                        LocalTime.of(i+1, i, i, i),
                        LocalTime.of(i+2, i, i, i),
                        50000.0+(double)i+1.0,
                        targetArrayFilm[i]
                );
                schRepository.save(schedule);
                List<Schedule> allSchedule = scheduleService.getAllSchedule();
                Schedule[] targetArraySchedule = allSchedule.stream().toArray(Schedule[]::new);
                if(targetArraySchedule.length == 0){
                    System.out.println("Schedule Target Array 0");
                }

                // Generate Seat Dummy Data
                Boolean random_available;
                Boolean[] available = {true, false};
                Random ran = new Random();
                random_available = available[ran.nextInt(available.length)];
                Seat seat = new Seat(i+1,faker.hobbit().location(),random_available);
                seatRepository.save(seat);
                List<Seat> allSeat = seatService.getAllSeat();
                Seat[] targetArraySeat = allSeat.stream().toArray(Seat[]::new);
                if(targetArraySeat.length == 0){
                    System.out.println("Seat Target Array 0");
                }

                // Generate Order Dummy Data
                String random_pembayaran, random_metode;
                String[] pembayaran = {"LUNAS", "BELOM LUNAS"};
                String[] metode = {"QRIS_BCA", "QRIS_BNI", "TUNAI", "GOPAY", "SHOPEEPAY", "OVO"};
                random_pembayaran = pembayaran[ran.nextInt(pembayaran.length)];
                random_metode = metode[ran.nextInt(metode.length)];

                if(i+1 <= 10) {
                    Order order = new Order(
                            random_metode,
                            tanggal_tayang_date,
                            random_pembayaran,
                            targetArraySchedule[i],
                            targetArraySeat[i]
                    );
                    orderRepository.save(order);
                }
            }
        };
    }
}




