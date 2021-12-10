package org.example;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
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

    public ArrayList<Booking> displayBookingInFuture() {
        ArrayList <Booking> bookings = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        for (Booking b : this.bookingList) {
            if(now.isBefore(b.getBookingDateTime()))
                bookings.add(b);
        }
        ComparatorBookingDateTime comp = new ComparatorBookingDateTime();
        Collections.sort(bookings, comp);
        return bookings;
    }

    public void addBooking(int passengerId, int vehicleId, int year, int month, int day, int hour, int minute, double startLatitude, double startLongitude, double endLatitude, double endLongitude) {

        if(passengerStore.findPassengerById(passengerId) != null) {
            if (vehicleManager.findVehicleById(vehicleId) != null) {

                double distance = Math.sqrt(((endLatitude-startLatitude)*(endLatitude-startLatitude))+((endLongitude-startLongitude)*(endLongitude-startLongitude)));
                double costPerKm = vehicleManager.findVehicleCost(vehicleId);
                double cost = Math.round(distance*costPerKm);
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

    public ArrayList<Booking> findPassengerBookings(int passengerId){
        ArrayList <Booking> bookings = new ArrayList<>();
        for(Booking b :bookingList){
            if(b.getPassengerId() == passengerId){
                bookings.add(b);
            }
        }

        if(bookings.size()>0) {
            System.out.println("Bookings with passenger id " + passengerId + ":");
        }else{
            System.out.println("Cannot find booking with that passenger id");
        }

        ComparatorBookingDateTime comp = new ComparatorBookingDateTime();
        Collections.sort(bookings, comp);
        return bookings;
    }

    public Booking findBookingById(int bookingId){
        for(Booking b :bookingList){
            if(b.getBookingId()==bookingId){
                return b;
            }
        }
        return null;
    }


    public void deleteBooking(int bookingId) {
        Booking b = findBookingById(bookingId);
        if (b != null) {
                    bookingList.remove(b);
                    System.out.println("Booking Deleted");
                } else {
                    System.out.println("Cannot find booking with that booking id");
                }
    }

//    public double findAverageBookingJourney(){
//        for(Booking b :bookingList){
//            double distance = Math.sqrt(((endLatitude-startLatitude)*(endLatitude-startLatitude))+((endLongitude-startLongitude)*(endLongitude-startLongitude)));
//            double costPerKm = vehicleManager.findVehicleCost(vehicleId);
//            double cost = Math.round(distance*costPerKm);
//        }
//    }




}
