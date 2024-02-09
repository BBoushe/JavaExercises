package AaDS_Lab6.MVR;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class MVR {

    public static void main(String[] args) {
        Scanner br = new Scanner(System.in);

        int N = Integer.parseInt(br.nextLine());
        Gragjanin[] lugje = new Gragjanin[N];

        for (int i = 0; i < N; i++) {
            String imePrezime = br.nextLine();
            int lKarta = Integer.parseInt(br.nextLine());
            int pasos = Integer.parseInt(br.nextLine());
            int vozacka = Integer.parseInt(br.nextLine());
            Gragjanin covek = new Gragjanin(imePrezime, lKarta, pasos, vozacka);
            lugje[i] = covek;
        }

        Queue<Gragjanin> ID = new LinkedList<>();
        Queue<Gragjanin> PASS = new LinkedList<>();
        Queue<Gragjanin> DRIVER = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            if (lugje[i].getlKarta() == 1) {
                ID.add(lugje[i]);
            } else if (lugje[i].getPasos() == 1) {
                PASS.add(lugje[i]);
            } else {
                DRIVER.add(lugje[i]);
            }
        }

        while (!ID.isEmpty() || !PASS.isEmpty() || !DRIVER.isEmpty()) {
            if (!ID.isEmpty()) {
                Gragjanin covek = ID.poll();
                if (covek.getPasos() == 1) {
                    PASS.add(covek);
                } else if (covek.getVozacka() == 1) {
                    DRIVER.add(covek);
                } else System.out.println(covek.getImePrezime());
            } else if (!PASS.isEmpty()) {
                Gragjanin covek = PASS.poll();
                if (covek.getVozacka() == 1) {
                    DRIVER.add(covek);
                } else System.out.println(covek.getImePrezime());
            } else if (!DRIVER.isEmpty()) {
                System.out.println(DRIVER.poll().getImePrezime());
            }
        }
    }
}
