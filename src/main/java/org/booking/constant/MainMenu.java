package org.booking.constant;


import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

public enum MainMenu {

    LOGIN(1, MenuMSG.LOGIN.MSG),
    REGISTER(2, MenuMSG.REGISTER.MSG),
    EXIT(3, MenuMSG.EXIT.MSG),
    UNKNOWN(-1, MenuMSG.UNKNOWN.MSG);

    final int id;
    final String label;

    MainMenu(int id, String label) {
        this.id = id;
        this.label = label;
    }

    @Override
    public String toString() {
        return String.format("%d. %s", id, label);
    }
}
