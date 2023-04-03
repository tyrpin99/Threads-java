package com.cwiczenie_java;

import java.util.Scanner;

public class Conventer {



    public Conventer() {
        System.out.println("Witaj w konwenterze jednostek ! :)");
        System.out.println("Wybierz rodzaj jednostek do konwertowania ! ");
        Scanner scanner = new Scanner(System.in);
        System.out.println("1.Jednostki miary(mm,cm,dm,m,km)");
        System.out.println("2.Jednostki wagi(g,dag,kg,t,lbs)");
        int choice = scanner.nextInt();
        if (choice == 1) {
            System.out.println("Wprowadz liczbe do konwersji ");
            double liczba = scanner.nextDouble();

            System.out.println("Wprowadz jednostke(mm,cm,dm,m,km)");
            String jednostka = scanner.next();

            System.out.println("Na jaka jednostke zamienić?");
            String jednostka2 = scanner.next();

            double wynik = convert(liczba,jednostka,jednostka2);

            System.out.println(liczba + " "+jednostka+ " = "+wynik+jednostka2);



        } else if (choice == 2) {
            System.out.println("Wprowadz liczbe do konwersji ");
            double liczba2 = scanner.nextDouble();

            System.out.println("Wprowadz jednostke(g,dag,kg,t)");
            String jednostka2 = scanner.next();

            System.out.println("Na jaka jednostke zamienić?");
            String jednostka3 = scanner.next();

            double wynik = convert2(liczba2,jednostka2,jednostka3);

            System.out.println(liczba2 + " "+jednostka2+ " = "+wynik+jednostka3);

        } else {
            System.out.println("Wybrales zla opcje !");
            System.exit(0);
        }


    }
    public static double convert(double value, String inputUnit, String outputUnit) {
        switch (inputUnit) {
            case "km" -> value *= 1000;
            case "cm" -> value /= 100;
            case "dm" -> value /= 10;
            case "mm" -> value /= 1000;
        }

        switch (outputUnit) {
            case "km" -> value /= 1000;
            case "cm" -> value *= 100;
            case "dm" -> value *= 10;
            case "mm" -> value *= 1000;
        }

        return value;
    }

    public static double convert2(double value, String inputUnit, String outputUnit) {
        switch (inputUnit) {
            case "t" -> value *= 1000;
            case "dag" -> value /= 10;
            case "g" -> value /= 1000;
        }

        switch (outputUnit) {
            case "t" -> value /= 1000;
            case "dag" -> value *= 10;
            case "g" -> value *= 1000;
        }

        return value;
    }
}

