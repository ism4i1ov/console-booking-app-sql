package org.booking.controller;

import org.booking.entity.Booking;
import org.booking.entity.Flight;
import org.booking.service.UserPanelService;

import java.util.List;
import java.util.stream.Collectors;

public class UserPanelController {
    UserPanelService userPanelService;

    public UserPanelController(UserPanelService userPanelService) {
        this.userPanelService = userPanelService;
    }

    public String showUserPanel() {
        return userPanelService.getUserPanel();
    }

    public String showOnlineBoard() {
        return userPanelService.getOnlineBoard()
                .stream()
                .map(Flight::toString)
                .collect(Collectors.joining("\n"));
    }

    public String showFlightById(String id) {
        return userPanelService.getFlightById(id).map(Flight::getInfo).orElse("Input id is incorrect");
    }

    public String showFlightByDestDateTicketCount(String destination, String date, String ticketCount) {
        return userPanelService.getFlightByDestDateTicketCount(destination, date, ticketCount)
                .stream()
                .map(Flight::getInfo)
                .collect(Collectors.joining("\n"));
    }

    public String bookFlight(int userId, int flightId, int ticketCount) {
        return userPanelService.bookFlight(userId, flightId, ticketCount)
                .map(booking -> "Booking successfully \n" + booking.toString())
                .orElse("Booking is not successfull1y");
    }

    public String showMyBookings(String id) {
        List<Booking> bookingsIdByUserId = userPanelService.getBookingsIdByUserId(id);
        if (bookingsIdByUserId.isEmpty()) return "You don't have flights";
        return bookingsIdByUserId.stream().map(Booking::toString).collect(Collectors.joining("\n"));
    }

    public String cancelBooking(String id) {
        return userPanelService.cancelBooking(id) ? "Booking id is incorrect!" : "Booking cancel!";
    }
}