package org.booking.constant;

public enum UserPanel {
    ONLINE_BOARD(1, MenuMSG.ONLINE_BOARD.MSG),
    MY_FLIGHTS(2, MenuMSG.MY_FLIGHTS.MSG),
    SHOW_FLIGHT_INFO_BY_ID(3, MenuMSG.SHOW_FLIGHT_INFO_BY_ID.MSG),
    BOOK_FLIGHT(4, MenuMSG.BOOK_FLIGHT.MSG),
    CANCEL_THE_BOOKING(5, MenuMSG.CANCEL_THE_BOOKING.MSG),
    LOGOUT(6, MenuMSG.LOGOUT.MSG),
    UNKNOWN(-1, MenuMSG.UNKNOWN.MSG);

    final int id;
    final String label;

    UserPanel(int id, String label) {
        this.id = id;
        this.label = label;
    }

    @Override
    public String toString() {
        return String.format("%d. %s", id, label);
    }
}
