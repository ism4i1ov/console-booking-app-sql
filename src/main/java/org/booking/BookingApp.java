package org.booking;

import org.booking.constant.UserPanel;
import org.booking.controller.LoginPanelController;
import org.booking.controller.UserPanelController;
import org.booking.db.dao.BookingDao;
import org.booking.db.dao.FlightDao;
import org.booking.db.dao.UserDao;
import org.booking.entity.User;
import org.booking.io.Console;
import org.booking.io.WindowsConsole;
import org.booking.service.LoginPanelService;
import org.booking.service.UserPanelService;

import java.util.Optional;
import java.util.Scanner;

public class BookingApp {

    private static final Console console = new WindowsConsole(new Scanner(System.in));

    public static void main(String[] args) {
        LoginPanelController loginPanelController = new LoginPanelController(new LoginPanelService(new UserDao()));
        String menuSelected;
        do {
            console.println(loginPanelController.showMenu());
            menuSelected = console.readln("Select menu: ");
            switch (menuSelected) {
                case "1":
                    String username = console.readln("Enter username: ");
                    String password = console.readln("Enter password: ");
                    Optional<User> optionalUser = loginPanelController.login(username, password);
                    String result = optionalUser.map(user -> {
                        userPanel(user);
                        return "Logout";
                    }).orElse("Such an account does not exist or password is incorrect");
                    console.println(result);
                    break;
                case "2":
                    String usernameNewUser = console.readln("Enter username: ");
                    String passwordNewUser = console.readln("Enter password: ");
                    String nameNewUser = console.readln("Enter name: ");
                    String surnameNewUser = console.readln("Enter surname: ");
                    console.println(loginPanelController.register(usernameNewUser, passwordNewUser, nameNewUser, surnameNewUser));
                    break;
                case "3":
                    console.println("Application shut down!");
                    break;
                default:
                    console.println("Input correct value");
            }
        } while (!menuSelected.equals("3"));
    }

    private static void userPanel(User user) {
        UserPanelController userPanelController = new UserPanelController(
                new UserPanelService(new FlightDao(), new UserDao(), new BookingDao()
                ));

        String userMenuSelected;
        console.println("Welcome " + user.getName() + " " + user.getSurname());
        do {
            console.println(userPanelController.showUserPanel());
            userMenuSelected = console.readln("Select menu: ");
            switch (userMenuSelected) {
                case "1":
                    console.println(userPanelController.showOnlineBoard());
                    break;
                case "2":
                    console.println(userPanelController.showMyBookings(String.valueOf(user.getId())));
                    break;
                case "3":
                    String flightId = console.readln("Enter flight id: ");
                    console.println(userPanelController.showFlightById(flightId));
                    break;
                case "4":
                    String destination = console.readln("Input destination: ");
                    String date = console.readln("Input date[12.12.2012]: ");
                    String ticketCount = console.readln("Input ticket count: ");
                    String result = userPanelController.showFlightByDestDateTicketCount(destination, date, ticketCount);
                    if (result.isEmpty()) {
                        console.println("No data was found for these criteria");
                        break;
                    }
                    console.println(result);
                    String flightIdForBook = console.readln("Enter id flight for booking: ");
                    String resultBooking = userPanelController.bookFlight(user.getId(), Integer.parseInt(flightIdForBook), Integer.parseInt(ticketCount));
                    console.println(resultBooking);
                    break;
                case "5":
                    String bookingId = console.readln("Enter booking id: ");
                    console.println(userPanelController.cancelBooking(bookingId));
                    break;
                case "6":
                    break;
                default:
                    console.println("Input correct value");
            }
        } while (!userMenuSelected.equals("6"));
    }
}

