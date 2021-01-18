package org.booking.constant;

public enum MenuMSG {

    LOGIN("Login"),
    REGISTER("Register"),
    EXIT("Exit"),
    UNKNOWN("Unknown"),
    ONLINE_BOARD("Online board"),
    MY_FLIGHTS("My flights"),
    SHOW_FLIGHT_INFO_BY_ID("Show flight information by id"),
    BOOK_FLIGHT("Book flight"),
    CANCEL_THE_BOOKING("Cancel the booking"),
    LOGOUT("Logout");

    final String MSG;

    MenuMSG(String label) {
        this.MSG = label;
    }
}
