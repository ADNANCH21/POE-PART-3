package com.mycompany.poepart3;

import java.io.*;
import java.nio.file.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

public class MessageClass {
    private String[] sentMessages;
    private String[] disregardedMessages;
    private String[] storedMessages;
    private String[] messageHashes;
    private String[] messageIDs;
    private String[] recipients;
    private String[] flags;

    public MessageClass() {
        recipients = new String[]{
                "+27834557896",  
                "+27838884567",  
                "+27834484567",  
                "0838884567",    
                "+27838884567"  
        };

        String[] messages = {
                "Did you get the cake?",
                "Where are you? You are late! I have asked you to be on time.",
                "Yohoooo, I am at your gate.",
                "It is dinner time!",
                "Ok, I am leaving without you."
        };

        flags = new String[]{"Sent", "Stored", "Disregard", "Sent", "Stored"};

        int size = messages.length;
        sentMessages = new String[size];
        disregardedMessages = new String[size];
        storedMessages = new String[size];
        messageHashes = new String[size];
        messageIDs = new String[size];

        for (int i = 0; i < size; i++) {
            messageIDs[i] = "MSG" + (i + 1);
            messageHashes[i] = computeHash(messages[i]);

            switch (flags[i].toLowerCase()) {
                case "sent" -> sentMessages[i] = messages[i];
                case "stored" -> storedMessages[i] = messages[i];
                case "disregard" -> disregardedMessages[i] = messages[i];
            }
        }
    }

    public String computeHash(String msg) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(msg.getBytes(StandardCharsets.UTF_8));
            StringBuilder hex = new StringBuilder();
            for (byte b : hash) hex.append(String.format("%02x", b));
            return hex.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadStoredMessagesFromJSON(String filePath) {
        try {
            String jsonData = Files.readString(Paths.get(filePath));

            jsonData = jsonData.replace("[", "")
                               .replace("]", "")
                               .replace("{", "")
                               .replace("}", "")
                               .replace("\"", "")
                               .replace("message:", "");

            String[] parts = jsonData.split(",");
            storedMessages = new String[parts.length];
            for (int i = 0; i < parts.length; i++) {
                storedMessages[i] = parts[i].trim();
            }

            System.out.println("Stored messages loaded successfully!");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public void displaySentMessages() {
        System.out.println("---- SENT MESSAGES ----");
        for (int i = 0; i < sentMessages.length; i++) {
            if (sentMessages[i] != null) {
                System.out.println("Recipient: " + recipients[i] + " | Message: " + sentMessages[i]);
            }
        }
    }

    public String getLongestMessage() {
        String longest = "";
        for (String msg : getAllMessages()) {
            if (msg != null && msg.length() > longest.length()) longest = msg;
        }
        return longest;
    }

    public String searchByMessageID(String id) {
        for (int i = 0; i < messageIDs.length; i++) {
            if (id.equalsIgnoreCase(messageIDs[i])) {
                return "Recipient: " + recipients[i] + " | Message: " + getMessageByIndex(i);
            }
        }
        return "Message ID not found.";
    }

    public String[] searchByRecipient(String recipient) {
        String[] result = new String[messageIDs.length];
        int index = 0;
        for (int i = 0; i < recipients.length; i++) {
            if (recipient.equalsIgnoreCase(recipients[i])) {
                result[index++] = getMessageByIndex(i);
            }
        }
        return result;
    }

    public String deleteByHash(String message) {
        String hash = computeHash(message);
        for (int i = 0; i < messageHashes.length; i++) {
            if (hash.equals(messageHashes[i])) {
                messageHashes[i] = null;
                sentMessages[i] = null;
                storedMessages[i] = null;
                disregardedMessages[i] = null;
                return "Message \"" + message + "\" successfully deleted.";
            }
        }
        return "Message not found.";
    }

    public void displayReport() {
        System.out.println("==== SENT MESSAGE REPORT ====");
        for (int i = 0; i < sentMessages.length; i++) {
            if (sentMessages[i] != null) {
                System.out.println("Message ID: " + messageIDs[i]);
                System.out.println("Recipient: " + recipients[i]);
                System.out.println("Message: " + sentMessages[i]);
                System.out.println("Message Hash: " + messageHashes[i]);
                System.out.println("-----------------------------");
            }
        }
    }

    private String getMessageByIndex(int i) {
        if (sentMessages[i] != null) return sentMessages[i];
        if (storedMessages[i] != null) return storedMessages[i];
        if (disregardedMessages[i] != null) return disregardedMessages[i];
        return null;
    }

    private String[] getAllMessages() {
        String[] all = new String[sentMessages.length];
        for (int i = 0; i < sentMessages.length; i++) {
            all[i] = getMessageByIndex(i);
        }
        return all;
    }

    public String[] getSentMessages() { return sentMessages; }
    public String[] getStoredMessages() { return storedMessages; }
    public String[] getRecipients() { return recipients; }
}
