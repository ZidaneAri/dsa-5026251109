package lw03.unguided;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {    
    public static void main(String[] args) {
        Map<String, Integer> enrollment = new LinkedHashMap<>();
        int rejectedOperations = 0;

        Scanner scan = new Scanner(Main.class.getResourceAsStream("enrollment.txt"));

        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            
            String[] parts = line.split(" ", 3);
            String action = parts[0];
            String classID = parts[1];
            int student = Integer.parseInt(parts[2]);

            if (action.equals("REGISTER")) {
                if (enrollment.containsKey(classID)) {
                    int currentStudent = enrollment.get(classID);
                    enrollment.put(classID, currentStudent + student);
                } else {
                    enrollment.put(classID, student);
                }
            } else if (action.equals("WITHDRAW")){
                if (enrollment.containsKey(classID) && enrollment.get(classID) <= student) {
                    int currentStudent = enrollment.get(classID);
                    enrollment.put(classID, currentStudent - student);
                } else {
                    rejectedOperations++;
                    System.out.println("Cannot withdraw " + student + " students from class " + classID);
                }
            } else if (action.equals("CHECK")){
                System.out.println("===== Enrollment Checks =====");
                if (enrollment.containsKey(classID)) {
                    System.out.println(classID + ": " + enrollment.get(classID) + " students");
                } else {
                    System.out.println(classID + ": Not found");
                }
            }

            System.out.println("==== Final Enrollment ====");
            for (Map.Entry<String, Integer> entry : enrollment.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue() + " students");
            }
            System.out.println("Rejected operations: " + rejectedOperations);
        }
        scan.close();
    }
}