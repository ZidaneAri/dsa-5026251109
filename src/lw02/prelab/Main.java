package lw02.prelab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //membaca dan menyimpan transaksi ke LinkedList
        LinkedList<String[]> transactionsList = new LinkedList<>();
        LinkedList<String[]> customersList = new LinkedList<>();

        /* 
        Queue<String[]> queue = new LinkedList<>();
        Stack<String[]> failed = new Stack<>;

        Scanner scanner = new Scanner(Main.class.getResourceAsStream("transactions.txt"));
        While (scanner.hasNext()){
            String[] trancation = new String[3];
            trancation[0] = scanner.next();
            trancation[1] = scanner.next();
            trancation[2] = scanner.next();
            transactions.add(transaction);
        }
        scanner.close();

        queue.addAll(transactionst);
        
        while (!queue.isEmpty()){
            String[] transaction{0} = queue.poll();

            String name = transaction[0];
            String type = transaction[1];
            int amount = Integer.parseInt(transaction[2]);

            String[] customer = null;

            for (String[] data : customers){
                if (data[0].equals(name)){
                    customer = data;
                    break;
                }
            }

            if (customer == null){
                customer = new String[]{name, "0"}
                customers.add(customer);
            }

            int balance = Integer.parseInt(customer[1]);

            if (type.equals("DEPOSIT")){
                balance += amount;
                customer[1]  = String.valueOf(balance);
            } else if (type.equals("WITHDRAW")){
                if (amount <= balance){
                    balance -= amount;
                    customer[1] = String.valueOf(balance);
                } else {
                    failed.push(transaction);
                }
            }
        }

        System.out.println("'\n=== Final Balances ===");
        for (String[] customer : customers){
            System.out.println(customer[0] + " : " + customer[1]);
        }

        System.out.println("=== Failed Transactions ===");
        while (!failed.isEmpty()){
            String[] transaction = failed.pop();
            System.out.println(transaction[0] + " " + transaction[1] + " " + transaction[2]); 
        }

    */
        try {
            Scanner scan = new Scanner(new File("dsa-5026251109/src/lw02/prelab/transactions.txt"));
            while (scan.hasNext()) {
                String name = scan.next();
                String type = scan.next();
                String amount = scan.next();

                //menyimpan setiap baris transaksi ke LinkedList
                transactionsList.add(new String[]{name, type, amount});

                //memeriksa apakah pelanggan sudah ada di daftar
                boolean customerExists = false;
                for (String[] customer : customersList) {
                    if (customer[0].equals(name)) {
                        customerExists = true;
                        break;
                    }
                }
                
                //menambahkan pelanggan baru dengan saldo awal 0 sesuai urutan kemunculan
                if (!customerExists) {
                    customersList.add(new String[]{name, "0"});
                }
            }
            scan.close();

        } catch (FileNotFoundException e) {
            System.out.println("ERROR: File tidak ditemukan.");
            return;
        }

        //memindahkan data ke queue FIFO
        Queue<String[]> transactionQueue = new LinkedList<>();
        for (String[] transaction : transactionsList) {
            transactionQueue.add(transaction);
        }

        //memproses transaksi dan menyimpan yang gagal di stack (Tumpukan LIFO)
        Stack<String[]> failedTransactions = new Stack<>();

        //mengambil transaksi satu per satu dari urutan paling depan (poll)
        while (!transactionQueue.isEmpty()) {
            String[] currentTransaction = transactionQueue.poll();
            String tName = currentTransaction[0];
            String tType = currentTransaction[1];
            int tAmount = Integer.parseInt(currentTransaction[2]);

            //mencari data saldo pelanggan yang bersangkutan
            for (String[] customer : customersList) {
                if (customer[0].equals(tName)) {
                    int currentBalance = Integer.parseInt(customer[1]);

                    if (tType.equals("DEPOSIT")) {
                        //menambahkan saldo
                        currentBalance += tAmount;
                        customer[1] = String.valueOf(currentBalance);
                        
                    } else if (tType.equals("WITHDRAW")) {
                        //memeriksa apakah saldo mencukupi
                        if (tAmount > currentBalance) {
                            //transaksi gagal, masukkan ke Stack
                            failedTransactions.push(currentTransaction);
                        } else {
                            //saldo cukup, lakukan pengurangan
                            currentBalance -= tAmount;
                            customer[1] = String.valueOf(currentBalance);
                        }
                    }
                    break;
                }
            }
        }

        // print hasil akhir
        System.out.println("=== Final Balances ===");
        for (String[] customer : customersList) {
            System.out.println(customer[0] + " : " + customer[1]);
        }

        System.out.println("=== Failed Transactions ===");
        //mengeluarkan transaksi gagal dari yang paling terakhir dimasukkan (pop)
        while (!failedTransactions.isEmpty()) {
            String[] failed = failedTransactions.pop();
            System.out.println(failed[0] + " " + failed[1] + " " + failed[2]);
        }
    
    }
}