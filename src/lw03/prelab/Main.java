package lw03.prelab;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Problem1();
        Problem2();
        Problem3();
    }

    private static void Problem1() {
        List<String> playlist = new ArrayList<>();
        
        Scanner scanner = new Scanner(Main.class.getResourceAsStream("playlist.txt"));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) continue;
            
            String[] parts = line.split(" ", 2);
            String command = parts[0];
            
            if (command.equals("ADD")) {
                playlist.add(parts[1]);
            } else if (command.equals("INSERT")) {
                String[] insertParts = parts[1].split(" ", 2);
                int index = Integer.parseInt(insertParts[0]);
                playlist.add(index, insertParts[1]);
            } else if (command.equals("REMOVE")) {
                playlist.remove(parts[1]);
            }
        }
        scanner.close();

        System.out.println("===== Problem 1 =====");
        System.out.println("Total songs: " + playlist.size());
        for (int i = 0; i < playlist.size(); i++) {
            System.out.println((i + 1) + ": " + playlist.get(i));
        }
    }

    private static void Problem2() {
        Set<String> participants = new LinkedHashSet<>();
        int duplicateRegistrations = 0;

        Scanner scanner = new Scanner(Main.class.getResourceAsStream("participants.txt"));
        while (scanner.hasNextLine()) {
            String name = scanner.nextLine().trim();
            if (name.isEmpty()) continue;

            if (!participants.add(name)) {
                duplicateRegistrations++;
            }
        }
        scanner.close();

        System.out.println("===== Problem 2 =====");
        System.out.println("Unique participants: " + participants.size());
        
        int count = 1;
        for (String participant : participants) {
            System.out.println(count + ". " + participant);
            count++;
        }
        System.out.println("Duplicate registrations: " + duplicateRegistrations);
    }

    private static void Problem3() {
        Map<String, Integer> inventory = new LinkedHashMap<>();
        int failedSales = 0;

        Scanner scanner = new Scanner(Main.class.getResourceAsStream("inventory.txt"));
        while (scanner.hasNext()) {
            String type = scanner.next();
            String product = scanner.next();
            int quantity = scanner.nextInt();

            if (type.equals("ADD")) {
                inventory.put(product, inventory.getOrDefault(product, 0) + quantity);
            } else if (type.equals("SELL")) {
                int currentStock = inventory.getOrDefault(product, 0);
                if (currentStock >= quantity) {
                    inventory.put(product, currentStock - quantity);
                } else {
                    failedSales++;
                }
            }
        }
        scanner.close();

        System.out.println("===== Problem 3 =====");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("Failed sales: " + failedSales);
    }
}