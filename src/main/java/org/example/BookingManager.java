package org.example;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookingManager {
    private final ArrayList<Booking> bookingList;
    private PassengerStore passengerStore;
    private VehicleManager vehicleManager;

    // Constructor
    public BookingManager(PassengerStore passengerStore, VehicleManager vehicleManager) {
        this.passengerStore = passengerStore;
        this.vehicleManager = vehicleManager;
        this.bookingList = new ArrayList<>();
    }

    public List<Booking> getAllBookings() {
        return this.bookingList;
    }

    public void displayAllBooking() {
        for (Booking b : this.bookingList) {
            System.out.println(b.toString());
        }
    }

    public void displayBookingInFuture() {
        LocalDateTime now = LocalDateTime.now();
        for (Booking b : this.bookingList) {
            if(now.isBefore(b.getBookingDateTime()))
            System.out.println(b.toString());
        }
    }

    public void addBooking(int passengerId, int vehicleId, int year, int month, int day, int hour, int minute, double startLatitude, double startLongitude, double endLatitude, double endLongitude) {

        if(passengerStore.findPassengerById(passengerId) != null) {
            if (vehicleManager.findVehicleById(vehicleId) != null) {

                double cost = 55.50;
                Booking booking = new Booking(passengerId, vehicleId, year, month, day, hour, minute, startLatitude, startLongitude, endLatitude, endLongitude, cost);
                boolean found = false;

                for (Booking b : bookingList) {

                    if (b.equals(booking)) {

                        found = true;

                        break;

                    }

                }

                if (found == false) {

                    bookingList.add(booking);
                    System.out.println("Booking Added");

                } else {

                    System.out.println("*** addBooking() - booking already exists - no duplicates allowed");

                }


            }
        }
    }

    public Booking showBookingDetails(int bookingId){
        for(Booking b :bookingList){
            if(b.getBookingId() == bookingId){
                return b;
            }
        }
        return null;
    }

    public Booking findPassengerBookings(int passengerId){
        for(Booking b :bookingList){
            if(b.getPassengerId() == passengerId){
                return b;
            }
        }
        return null;
    }


}
