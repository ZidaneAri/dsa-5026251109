package lw01.unguided;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        List<Rental> rentals = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File("dsa-5026251109/src/lw01/unguided/rentals.txt"));
            
            int T = scanner.nextInt();
            /* 
            for (int i = 0; i < T; i++) {
                String type = scanner.next();
                String id = scanner.next();
                int days = scanner.nextInt();
                int units = scanner.nextInt();

                if (type.equals("PROJECTOR")) {
                    rentals.add(new ProjectorRental(id, days));
                } else if (type.equals("LAPTOP")) {
                    rentals.add(new LaptopRental(id, days));
                }
            }
            */
           
            while (scanner.hasNext()) {
                String type = scanner.next();
                String id = scanner.next();
                int days = scanner.nextInt();
                int units = scanner.nextInt();

                if (type.equals("PROJECTOR")) {
                    rentals.add(new ProjectorRental(id, days));
                } else if (type.equals("LAPTOP")) {
                    rentals.add(new LaptopRental(id, days));
                }
            }
            scanner.close();
        
            
        } catch (FileNotFoundException e) {
            System.out.println("File rentals.txt tidak ditemukan. Pastikan sudah dibuat.");
        }

        for (Rental rental : rentals) {
            System.out.println(rental.summary());
        }
    }
}
