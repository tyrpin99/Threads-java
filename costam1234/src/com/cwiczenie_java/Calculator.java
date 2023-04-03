package com.cwiczenie_java;


import java.util.Scanner;

public class Calculator {


    public Calculator() {

        System.out.println("Witaj w kalkulatorze ! :)");
        int ile = 10;
        for (int i = 0; i < ile; i++)
        {
            menu();
        }


    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wybierz operacje ktora chcesz wykonac");
        System.out.println("1. Dodawanie");
        System.out.println("2.Odejmowanie");
        System.out.println("3.Mnozenie");
        System.out.println("4.Dzielenie");
        System.out.println("0.EXIT");
        int choice = scanner.nextInt();


        if(choice != 0) {
            System.out.println("Wprowadz dwie liczby ");
            double liczba1 = scanner.nextDouble();
            double liczba2 = scanner.nextDouble();


            switch (choice) {
                case 1 -> System.out.println("Wynik dodawania: " + dodawanie(liczba1, liczba2));
                case 2 -> System.out.println("Wynik odejmowania : " + odejmowanie(liczba1, liczba2));
                case 3 -> System.out.println("Wynik mnożenia: " + mnozenie(liczba1, liczba2));
                case 4 -> System.out.println("Wynik dzielenia: " + dzielenie(liczba1, liczba2));
                default -> System.out.println("Wybrales zla opcje !");
            }

        }
        else
            System.exit(0);

    }


    public double dodawanie(double a, double b) {

        return a + b;
    }

    public double odejmowanie(double a, double b) {

        return a - b;
    }

    public double mnozenie(double a, double b) {

        return a * b;
    }

    public double dzielenie(double a, double b) {

        double wynik = 0;
        if (b != 0) {
            wynik = a / b;
        } else {
            System.out.println("Nie dziel przez 0 ");
        }
        return wynik;
    }

}
