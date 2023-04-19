package com.ahmed.learn.learningspring.webservice;

import com.ahmed.learn.learningspring.business.ReservationService;
import com.ahmed.learn.learningspring.business.RoomReservation;
import com.ahmed.learn.learningspring.data.Guest;
import com.ahmed.learn.learningspring.data.Room;
import com.ahmed.learn.learningspring.util.DateUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class WebserviceController {
    private final DateUtils dateUtils;
    private final ReservationService reservationService;


    public WebserviceController(DateUtils dateUtils, ReservationService reservationService) {
        this.dateUtils = dateUtils;
        this.reservationService = reservationService;
    }

    @RequestMapping(path="/reservations", method = RequestMethod.GET)
    public List<RoomReservation> getReservations(@RequestParam(value="date", required = false)String dataString){
        Date date = this.dateUtils.createDateFromDateString(dataString);
        return this.reservationService.getRoomReservationsForDate(date);
    }

    @RequestMapping(path="/guests", method = RequestMethod.GET)
    public List<Guest> getGuest(){
        return this.reservationService.getHotelGuests();
    }

    @PostMapping(path="/guests")
    @ResponseStatus(HttpStatus.CREATED)
    public void addGuest(@RequestBody Guest guest){
        this.reservationService.addGuest(guest);
    }

    @GetMapping("/rooms")
    public List<Room> getRooms(){
        return this.reservationService.getRooms();
    }

}
