package com.mycompany.poepart3;

import java.util.Scanner;

public class POEPart3 {
    public static void main(String[] args) {
        MessageClass report = new MessageClass();
        Scanner input = new Scanner(System.in);

        System.out.println("==== MESSAGE REPORT APPLICATION ====");

        System.out.print("\nEnter Message ID to search (e.g., MSG4): ");
        String id = input.nextLine();
        System.out.println("Search result: " + report.searchByMessageID(id));

        System.out.print("\nEnter Recipient Number to search (e.g., +27838884567): ");
        String recipient = input.nextLine();
        String[] messages = report.searchByRecipient(recipient);
        System.out.println("Messages for " + recipient + ":");
        boolean found = false;
        for (String msg : messages) {
            if (msg != null) {
                System.out.println(msg);
                found = true;
            }
        }
        if (!found) System.out.println("No messages found for this recipient.");

        System.out.print("\nEnter the full message text to delete: ");
        String messageToDelete = input.nextLine();
        String deleteResult = report.deleteByHash(messageToDelete);
        System.out.println(deleteResult);

        System.out.println("\n--- FINAL REPORT ---");
        report.displayReport();

        input.close();
    }
}