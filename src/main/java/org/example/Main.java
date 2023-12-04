package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Podaj wartość docelową: ");

        int wartoscDocelowa = scan.nextInt();

        System.out.print("Podaj ilosc pomiarow co najmniej 1000: ");
        int iloscPomiarow = scan.nextInt();

        while(iloscPomiarow<1000){
            System.out.println("Podales liczbe mniejsza niz 1000");
            iloscPomiarow = scan.nextInt();
        }

        wygenerowaneWartosci(wartoscDocelowa, iloscPomiarow);
    }

    private static void wygenerowaneWartosci(int wartoscDocelowa, int iloscPomiarow){
        Random random = new Random();
        String filename = "pomiary.csv";

        try(FileWriter writer = new FileWriter(filename)) {
            writer.write("WartoscDocelowa Pomiar BladBezgledny BladWzgledny\n");

            for(int i=0; i<iloscPomiarow;i++ ){
                double bladlosowy = random.nextDouble();
                double wartoscwygenerowana = wartoscDocelowa + (bladlosowy - 0.5);

                double bladBezwzgledny = Math.abs(wartoscwygenerowana - wartoscDocelowa);
                double bladWzgledny = bladBezwzgledny/wartoscDocelowa*100;

                String line = String.format("%d %.3f %.3f %.3f\n", wartoscDocelowa,wartoscwygenerowana,bladBezwzgledny,bladWzgledny);

                writer.write(line);
            }
            System.out.println("Pomiary zostaly zapisane do pliku: "+filename);
        } catch (Exception e) {
            System.out.println("Wystapil blad poczas zapisu do pliku");
            e.printStackTrace();
        }
    }

}
    



