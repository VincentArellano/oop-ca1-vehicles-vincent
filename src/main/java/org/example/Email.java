package org.example;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Email{

    private String message;

    public String sendReminderBookingMessage(int passengerId, int vehicleId, int year, int month, int day, int hour, int minute,
                                             double startLatitude, double startLongitude,
                                             double endLatitude, double endLongitude, double cost) {
        message =
                "Passenger Id:"+passengerId+"\n"
                        +
                        "booked Vehicle Id "+vehicleId+
                        " on:" + year +"/"+ month+"/" +day + hour+":" +minute+"\n"+
                        "Start: StartLatitude = "+startLatitude + " StartLongitude = "+startLongitude+"\n"+
                        "End: EndLatitude = "+endLatitude + " EndLongitude = "+endLongitude+"\n"+
                        "Cost:"+ cost
                        +"=====================================================";

        return message;
    }
}
