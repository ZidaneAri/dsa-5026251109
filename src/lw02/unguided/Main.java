package lw02.unguided;

import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        LinkedList<String[]> requests = new LinkedList<>();
        LinkedList<String[]> books = new LinkedList<>();
        LinkedList<String[]> members = new LinkedList<>();

        // LinkedList<String[]> Kalkulus = new LinkedList<>();
        // LinkedList<String[]> Fisika = new LinkedList<>();
        // LinkedList<String[]> Statistika = new LinkedList<>();

        // Kalkulus.add(new String[]{"Kalkulus", "2"});
        // Fisika.add(new String[]{"Fisika", "1"});
        // Statistika.add(new String[]{"Statistika", "2"});

        Queue<String[]> queue = new LinkedList<>();
        Stack<String[]> failed = new Stack<>();

        Scanner scan = new Scanner(Main.class.getResourceAsStream("borrowing.txt"));
        while (scan.hasNext()) {
            String[] request = new String[2];
            request[0] = scan.next();
            request[1] = scan.next();
            requests.add(request);
        }
        scan.close();

        queue.addAll(requests);
        while (!queue.isEmpty()) {
            String[] request{0} = queue.poll();
            String name = request[0];
            String bookTitle = request[1];
            
            String[] member = null;

            for (String[] data : members) {
                if (data[0].equals(name)) {
                    member = data;
                    break;
                }
            }
            // int maxborrow = 2;

            // String[] member = null;

            // for (String[] data : members) {
            //     if (data[0].equals(name)) {
            //         member = data;
            //         break;
            //     }
            // }

            // if (member == null) {
            //     member = new String[]{name, "0"};
            //     members.add(member);
            // }



            // for (String[] data : members)
            // System.out.println(books);
        }

    }


}
