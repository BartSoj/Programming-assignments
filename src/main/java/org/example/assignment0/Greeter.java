package org.example.assignment0;

/**
 * Greeter is a very simple program to demonstrate that your Java setup is
 * working as expected.
 *
 * @author Bartosz Sójka (1960903)
 * @date 09.05.2023
 */
public class Greeter {
    /**
     * Greet the programmer.
     */
    void greet() {
        System.out.println("Hello, Bartosz Sójka!");
        System.out.println("Good luck in the course Programming; Enjoy!");
    }

    public static void main(String[] args) {
        new Greeter().greet();
    }
}
