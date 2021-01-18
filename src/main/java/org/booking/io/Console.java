package org.booking.io;

public interface Console {
    void print(String line);

    String read();

    default void println(String line) {
        print(line);
        print("\n");
    }

    default String readln(String text) {
        print(text);
        return read();
    }
}
