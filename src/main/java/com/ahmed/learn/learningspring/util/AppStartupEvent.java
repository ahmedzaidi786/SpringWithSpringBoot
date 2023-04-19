package com.ahmed.learn.learningspring.util;

import java.util.Date;
import java.util.List;

import com.ahmed.learn.learningspring.business.ReservationService;
import com.ahmed.learn.learningspring.business.RoomReservation;
import com.ahmed.learn.learningspring.data.Guest;
import com.ahmed.learn.learningspring.data.Reservation;
import com.ahmed.learn.learningspring.data.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class AppStartupEvent implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired
	private ReservationService reservationService;
    @Autowired
    private DateUtils dateUtils;


    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Date date = this.dateUtils.createDateFromDateString("2022-01-01");
        List<RoomReservation> reservations = this.reservationService.getRoomReservationsForDate(date);
        reservations.forEach(System.out::println);


    }
}
