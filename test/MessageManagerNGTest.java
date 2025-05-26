/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */

import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author jaedon
 */
public class MessageManagerNGTest
{
    private MessageManager messageManager;
    private String validRecipient;
    private String validMessage;
    private int messageNum;

    public void setUp() {
        validRecipient = "+1234567890";
        validMessage = "Hello this is a test message";
        messageNum = 1;
        messageManager = new MessageManager(messageNum, validRecipient, validMessage);
    }

    @Test
    public void testMessageIDLength() {
        assertEquals(10, messageManager.printMessage().split("\n")[0].split(": ")[1].length(),
                "Message ID should be exactly 10 digits long");
    }

    @Test
    public void testValidRecipientCell() {
        assertTrue(messageManager.checkRecipientCell(),
                "Recipient should start with '+' and be between 10 and 15 characters long");
    }

    @Test
    public void testInvalidRecipientCell() {
        MessageManager mm = new MessageManager(2, "12345", validMessage);
        assertFalse(mm.checkRecipientCell(),
                "Invalid recipient should fail validation");
    }

    @Test
    public void testMessageLengthWithinLimit() {
        assertTrue(messageManager.checkMessageLength(),
                "Message should be within 250 characters");
    }

    @Test
    public void testMessageLengthExceedsLimit() {
        String longMessage = "x".repeat(251);
        MessageManager mm = new MessageManager(3, validRecipient, longMessage);
        assertFalse(mm.checkMessageLength(),
                "Messages longer than 250 characters should fail validation");
    }

    @Test
    public void testCreateMessageHashFormat() {
        String hash = messageManager.createMessageHash();

        String[] parts = hash.split(":");
        assertEquals(3, parts.length, "Hash should have three parts separated by ':'");

        String prefix = parts[0];
        assertEquals(2, prefix.length(), "First part of hash should be 2 characters");

        assertEquals(String.valueOf(messageNum), parts[1], "Second part of hash should match message number");

        String expectedFirstWord = "Hello";
        String expectedLastWord = "message";
        String thirdPart = parts[2];

        assertTrue(thirdPart.equalsIgnoreCase(expectedFirstWord + expectedLastWord),
                "Third part of hash should be firstWord + lastWord");
    }

    @Test
    public void testCreateMessageHashForSingleWord() {
        MessageManager mm = new MessageManager(4, validRecipient, "Hi");
        String hash = mm.createMessageHash();
        String[] parts = hash.split(":");

        // Since there's only one word, firstWord == lastWord
        assertTrue(parts[2].equalsIgnoreCase("HiHi"),
                "If message has one word, hash should be that word repeated");
    }

    @Test
    public void testPrintMessageContainsAllDetails() {
        String output = messageManager.printMessage();
        assertTrue(output.contains("Message ID:"), "Output should include Message ID");
        assertTrue(output.contains("Message Hash:"), "Output should include Message Hash");
        assertTrue(output.contains(validRecipient), "Output should include the recipient");
        assertTrue(output.contains(validMessage), "Output should include the message text");
    }
}//
