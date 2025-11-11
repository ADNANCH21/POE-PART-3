package com.mycompany.poepart3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageClassTest {
    
    @Test
    public void testComputeHash() {
        System.out.println("computeHash");
        String msg = "";
        MessageClass instance = new MessageClass();
        String expResult = "";
        String result = instance.computeHash(msg);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }


    @Test
    public void testLoadStoredMessagesFromJSON() {
        System.out.println("loadStoredMessagesFromJSON");
        String filePath = "";
        MessageClass instance = new MessageClass();
        instance.loadStoredMessagesFromJSON(filePath);
        fail("The test case is a prototype.");
    }

    @Test
    public void testDisplaySentMessages() {
        System.out.println("displaySentMessages");
        MessageClass instance = new MessageClass();
        instance.displaySentMessages();
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetLongestMessage() {
        System.out.println("getLongestMessage");
        MessageClass instance = new MessageClass();
        String expResult = "";
        String result = instance.getLongestMessage();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSearchByMessageID() {
        MessageClass mc = new MessageClass();
        String result = mc.searchByMessageID("MSG4");
        assertTrue(result.contains("It is dinner time!"));
    }

    @Test
    public void testSearchByRecipient() {
        MessageClass mc = new MessageClass();
        String[] msgs = mc.searchByRecipient("+27838884567");
        assertTrue(msgs[0].contains("Where are you?"));
        assertTrue(msgs[1].contains("Ok, I am leaving without you."));
    }

    @Test
    public void testDeleteByHash() {
        MessageClass mc = new MessageClass();
        String msg = "Where are you? You are late! I have asked you to be on time.";
        String result = mc.deleteByHash(msg);
        assertEquals("Message \"" + msg + "\" successfully deleted.", result);
    }

    @Test
    public void testDisplayReport() {
        System.out.println("displayReport");
        MessageClass instance = new MessageClass();
        instance.displayReport();
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetSentMessages() {
        MessageClass mc = new MessageClass();
        String[] sent = mc.getSentMessages();
        assertEquals("Did you get the cake?", sent[0]);
        assertEquals("It is dinner time!", sent[3]);

    }

    @Test
    public void testGetStoredMessages() {
        System.out.println("getStoredMessages");
        MessageClass instance = new MessageClass();
        String[] expResult = null;
        String[] result = instance.getStoredMessages();
        assertArrayEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetRecipients() {
        System.out.println("getRecipients");
        MessageClass instance = new MessageClass();
        String[] expResult = null;
        String[] result = instance.getRecipients();
        assertArrayEquals(expResult, result);
        fail("The test case is a prototype.");
    }
    
    @Test
    public void testLongestMessage() {
        MessageClass mc = new MessageClass();
        assertEquals("Where are you? You are late! I have asked you to be on time.", mc.getLongestMessage());
    }
    
}
