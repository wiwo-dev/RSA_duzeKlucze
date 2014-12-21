package rsa2;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Scanner;

public class RSA2 {

    public static void main(String[] args) {

        int wybor;
        do {
            System.out.println("Szyfrowanie danych RSA\n"
                    + "(0) - koniec\n"
                    + "(1) - generowanie kluczy RSA\n"
                    + "(2) - szyfruj i deszyfruj wiadomosc\n"
                    + "(3) - kodowanie RSA tekstu kluczem zewnetrznym\n\n"
                    + "Co wybierasz? : ");

            Scanner sc = new Scanner(System.in);
            wybor = sc.nextInt();

            switch (wybor) {
                case 1:
                    Obliczenia rsa = new Obliczenia(256);
                    rsa.generujKlucze();
                    rsa.pokazKlucze();
                    break;
                case 2:
                    Obliczenia rsa2 = new Obliczenia(256);
                    rsa2.generujKlucze();
                    rsa2.pokazKlucze();
                    Scanner sc2 = new Scanner(System.in);
                    System.out.println("Podaj tekst ktory chcesz zakodowac: ");
                    String tekst;
                    tekst = sc2.nextLine();

                    System.out.println("Wiadomosc: " + tekst);
                    BigInteger wiadomosc = new BigInteger(tekst.getBytes());

                    BigInteger szyfrogram = rsa2.szyfruj(wiadomosc);
                    System.out.println("Szyfrogram: " + szyfrogram);
                    wiadomosc = rsa2.deszyfruj(szyfrogram);

                    String tekst2 = new String(wiadomosc.toByteArray());
                    System.out.println("Wiadomosc: " + tekst2);
                    break;
                case 3:
                    BigInteger e,
                     n,
                     t;
                    Scanner odczyt = new Scanner(System.in);
                    System.out.println("Kodowanie danych RSA\n"
                            + "--------------------\n\n"
                            + "Podaj wykladnik = ");

                    e = odczyt.nextBigInteger();

                    System.out.println("    Podaj modul = ");

                    Scanner odczyt2 = new Scanner(System.in);
                    n = odczyt2.nextBigInteger();

                    Obliczenia rsa3 = new Obliczenia(n, e);
                    String tekst3 = "";
                    System.out.println("Podaj tekst ktory chcesz zakodowac: ");
                    Scanner odczyt3 = new Scanner(System.in);
                    tekst3 = odczyt3.nextLine();

                    System.out.println("Wiadomosc: " + tekst3);
                    BigInteger wiadomosc3 = new BigInteger(tekst3.getBytes());

                    BigInteger szyfrogram3 = rsa3.szyfruj(wiadomosc3);
                    System.out.println("Szyfrogram: " + szyfrogram3);
                    break;
                case 4:
                    BigInteger e2,n2,szyfrogram4, wiadomosc4;
                    Scanner odcz = new Scanner(System.in);
                    System.out.println("Odkodowywanie danych RSA\n"
                            + "--------------------\n\n"
                            + "Podaj wykladnik = ");

                    e2 = odcz.nextBigInteger();

                    System.out.println("    Podaj modul = ");

                    Scanner odcz2 = new Scanner(System.in);
                    n2 = odcz2.nextBigInteger();

                    Obliczenia rsa4 = new Obliczenia(n2, e2, 1);
                    
                    System.out.println("Podaj szyfrogram: ");
                    Scanner odcz3 = new Scanner(System.in);
                    szyfrogram4 = odcz3.nextBigInteger();
                    
                    System.out.println("Szyfrogram: " + szyfrogram4);
                    wiadomosc4 = rsa4.deszyfruj(szyfrogram4);

                    String tekst4 = new String(wiadomosc4.toByteArray());
                    System.out.println("Wiadomosc: " + tekst4);
                    

//                    System.out.println("Wiadomosc: " + tekst3);
//                    BigInteger wiadomosc3 = new BigInteger(tekst3.getBytes());
//
//                    BigInteger szyfrogram3 = rsa3.szyfruj(wiadomosc3);
//                    System.out.println("Szyfrogram: " + szyfrogram3);
                    break;
            }

        } while (wybor != 0);
    }
}
