package org.booking.io;

import java.util.Scanner;

public class WindowsConsole implements Console {
    private Scanner sc;

    public WindowsConsole(Scanner sc) {
        this.sc = sc;
    }

    @Override
    public void print(String line) {
        System.out.print(line);
    }

    @Override
    public String read() {
        return sc.nextLine();
    }
}
