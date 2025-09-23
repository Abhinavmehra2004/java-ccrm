package edu.ccrm.cli;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to CCRM!");

        System.out.println("\n--- Java Platform Editions ---");
        System.out.println("Java SE (Standard Edition): For desktop and server applications.");
        System.out.println("Java ME (Micro Edition): For mobile and embedded devices.");
        System.out.println("Java EE (Enterprise Edition): For large-scale, distributed, transactional applications.");

        Menu menu = new Menu();
        menu.handleUserInput();
    }
}
