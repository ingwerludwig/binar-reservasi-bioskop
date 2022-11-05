package org.BinarAcademy.Challenge_4.services;

//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.json.JsonMapper;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.javafaker.Faker;
import org.BinarAcademy.Challenge_4.model.seats.Seat;
import org.BinarAcademy.Challenge_4.model.users.User;
import org.BinarAcademy.Challenge_4.repository.seat.SeatRepository;
import org.BinarAcademy.Challenge_4.service.SeatService.SeatService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import static java.lang.Boolean.FALSE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SeatServiceTest {
    @Mock
    private SeatRepository repository;
    private SeatService seatService;

    Faker faker;

//    ObjectMapper Obj;

    String result;

    @BeforeEach
    void setUp()
    {
//        Obj = JsonMapper.builder().findAndAddModules().build();
//        Obj.registerModule(new JavaTimeModule());
        this.seatService = new SeatService(this.repository);
        faker = new Faker(new Locale("en-US"));
    }

    @Test
    @DisplayName("Adding New Seat Test")
    void addNewSeatTest(){
        assertNotNull(seatService);
        Seat newSeat = new Seat();
        newSeat.setNo_kursi(1);
        newSeat.setNama_studio(faker.hobbit().location());
        newSeat.setBooked(FALSE);
        repository.save(newSeat);

        when(repository.save(any(Seat.class)))
                .thenReturn(newSeat);
        Seat savedSeat = repository.save(newSeat);

        assertThat(savedSeat.getBooked()).isFalse();
        assertThat(savedSeat.getNama_studio()).isNotNull();
//        result = Obj.writeValueAsString(savedSeat);
//        System.out.println("When creating Seat : "+result);
    }

    @Test
    @DisplayName("Get Available Seat Test")
    void getAvailableSeatTest(){
//        String newSeatString,newSeatString1;
        assertNotNull(seatService);
        Seat newSeat = new Seat();                              Seat newSeat1 = new Seat();
        newSeat.setNo_kursi(1);                                 newSeat1.setNo_kursi(2);
        newSeat.setNama_studio(faker.hobbit().location());      newSeat1.setNama_studio(faker.hobbit().location());
        newSeat.setBooked(FALSE);                               newSeat1.setBooked(Boolean.TRUE);
        repository.save(newSeat);                               repository.save(newSeat1);

//        newSeatString = Obj.writeValueAsString(newSeat);
//        newSeatString1 = Obj.writeValueAsString(newSeat1);

        List<Seat> seatList = new ArrayList<>();
        List<Seat> seatAvailList = new ArrayList<>();
        seatList.add(newSeat);
        seatList.add(newSeat1);
        for (Seat s : seatList) {
            if (s.getBooked().equals(FALSE)) {
                seatAvailList.add(s);
            }
        }

        when(repository.findSeatByBooked())
                .thenReturn(seatAvailList);

        List<Seat> fetchedSeat = seatService.getAllBookedSeat();
        assertThat(fetchedSeat.size()).isEqualTo(1);
        assertThat(fetchedSeat.stream().anyMatch(seats -> seats.getBooked().equals(FALSE)));

//        System.out.println("When creating seat before retrieving" + newSeatString);
//        System.out.println("When creating seat before retrieving" + newSeatString1);
//
//        for(Seat s : fetchedSeat) {
//            result = Obj.writeValueAsString(s);
//            System.out.println("When retrieving user : " + result);
//        }
    }

    @Test
    @DisplayName("Delete Seat Test")
    void deleteSeatTest()
    {
        assertNotNull(seatService);
        Seat newSeat = new Seat();
        newSeat.setNo_kursi(1);
        newSeat.setNama_studio(faker.hobbit().location());
        newSeat.setBooked(FALSE);
        repository.save(newSeat);

        List<Seat> seatList = new ArrayList<>();
        seatList.add(newSeat);

        BDDMockito.given(repository.findSeatById(newSeat.getNo_kursi()))
                .willReturn(newSeat);

        BDDMockito.willDoNothing().given(repository).delete(newSeat);

        seatService.deleteSeat(newSeat.getNo_kursi());

        Mockito.verify(repository, Mockito.times(1)).findSeatById(newSeat.getNo_kursi());
        Mockito.verify(repository, Mockito.times(1)).delete(newSeat);
    }
}