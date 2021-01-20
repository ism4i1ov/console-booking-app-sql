package org.booking.service;

import org.booking.constant.UserPanel;
import org.booking.db.dao.BookingDao;
import org.booking.db.dao.FlightDao;
import org.booking.db.dao.UserDao;
import org.booking.entity.Booking;
import org.booking.entity.Flight;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserPanelService {

    private FlightDao flightDao;
    private UserDao userDao;
    private BookingDao bookingDao;

    public UserPanelService(FlightDao flightDao, UserDao userDao, BookingDao bookingDao) {
        this.flightDao = flightDao;
        this.userDao = userDao;
        this.bookingDao = bookingDao;
    }

    public String getUserPanel() {
        return Arrays.stream(UserPanel.values())
                .filter(userPanel -> userPanel != UserPanel.UNKNOWN)
                .map(UserPanel::toString)
                .collect(Collectors.joining("\n"));
    }

    public List<Flight> getOnlineBoard() {
        return flightDao.getAll();
    }

    public Optional<Flight> getFlightById(String id) {
        return flightDao.getById(id);
    }

    public Optional<Booking> bookFlight(int userId, int flightId, int ticketCount) {
        Booking booking = new Booking(0, flightId, ticketCount, userId);
        int addBookingId = bookingDao.create(booking);

        Optional<Flight> optionalFlight = flightDao.getById(String.valueOf(flightId));
        optionalFlight.get().setFreePlaces(optionalFlight.get().getFreePlaces() - ticketCount);
        flightDao.update(optionalFlight.get());

        return bookingDao.getById(String.valueOf(addBookingId));
    }

    public List<Flight> getFlightByDestDateTicketCount(String destination, String date, String ticketCount) {
        return flightDao.getFlightByDestDateTicketCount(destination, date, ticketCount);
    }

    public List<Booking> getBookingsIdByUserId(String id) {
        return bookingDao.getBookingsByUserId(id);
    }

    public boolean cancelBooking(String id) {
        return bookingDao.getById(id).map(booking -> {
            Optional<Flight> optionalFlight = flightDao.getById(String.valueOf(booking.getFlightId()));
            optionalFlight.get().setFreePlaces(optionalFlight.get().getFreePlaces() + booking.getTicketCount());
            flightDao.update(optionalFlight.get());
            return bookingDao.delete(id);
        }).orElse(true);
    }
}
