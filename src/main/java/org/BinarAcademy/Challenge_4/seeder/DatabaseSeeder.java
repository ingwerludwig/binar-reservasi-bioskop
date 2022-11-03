//package org.BinarAcademy.Challenge_4.seeder;
//
//import com.github.javafaker.Faker;
//import org.BinarAcademy.Challenge_4.model.film.Film;
//import org.BinarAcademy.Challenge_4.model.order.Order;
//import org.BinarAcademy.Challenge_4.model.schedule.Schedule;
//import org.BinarAcademy.Challenge_4.model.users.User;
//import org.BinarAcademy.Challenge_4.repository.film.FilmRepository;
//import org.BinarAcademy.Challenge_4.repository.schedule.ScheduleRepository;
//import org.BinarAcademy.Challenge_4.repository.user.UserRepository;
//import org.BinarAcademy.Challenge_4.service.FilmService.FilmService;
//import org.BinarAcademy.Challenge_4.service.ScheduleService.ScheduleService;
//import org.BinarAcademy.Challenge_4.service.UserService.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.time.format.DateTimeFormatter;
//import java.util.List;
//import java.util.Locale;
//
//@Configuration
//public class DatabaseSeeder {
//
//    @Autowired
//    private FilmService filmService;
//    private UserService userService;
//    private ScheduleService scheduleService;
//
//    // Data Testing For Film Scenario
//    @Bean("Seeder")
//    CommandLineRunner GenerateFakeFilmData(FilmRepository filmRepository, ScheduleRepository schRepository, UserRepository userRepository)
//    {
//        return args -> {
//
//            // Seeding Database
//            for(int i=0 ; i<10 ; i++){
//                Faker faker = new Faker(new Locale("en-US"));
//
//                // Generate User Dummy Data
//                User user = new User(faker.name().name(), faker.internet().password(), faker.internet().emailAddress());
//                userRepository.save(user);
//
//                // Generate Film Dummy Data
//                Film film = new Film(faker.superhero().name(),false);
//                filmRepository.save(film);
//                List<Film> allFilm = filmService.getAllFilm();
//                Film[] targetArrayFilm = allFilm.stream().toArray(Film[]::new);
//
//
//                // Generate Schedule Dummy Data
//                LocalDate desiredLocalDate,tanggal_tayang_date;
//                desiredLocalDate = LocalDate.of(2022, 9, i+1);
//                String desiredToString = desiredLocalDate.toString();
////                System.out.println("Date output in string : " + desiredToString);
//                tanggal_tayang_date = LocalDate.parse(desiredToString, DateTimeFormatter.ISO_DATE);
////                System.out.println("date output in DateFormat : " + tanggal_tayang_date );
//                Schedule schedule = new Schedule(
//                        tanggal_tayang_date,
//                        LocalTime.of(i+1, i, i, i),
//                        LocalTime.of(i+2, i, i, i),
//                        50000.0+(double)i+1.0,
//                        targetArrayFilm[i]
//                );
//                schRepository.save(schedule);
//
//                // Generate Order Dummy Data
////                List<Schedule> allSchedule  = scheduleService.getAllSchedule();
////                List<User> allUser = userService.getAllUser();
//
//
//            }
//        };
//    }
//}
//
//
//
//
