import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MessageManager {

    // Static list to hold all message objects created
    public static ArrayList<MessageManager> messages = new ArrayList<>();

    // Instance variables representing message data
    private String messageID;
    private int messageNum;
    private String recipient;
    private String messageText;
    private String messageHash;

    // Constructor initializes message properties and generates ID and hash
    public MessageManager(String sender, String recipient, String messageText) {
        this.messageNum = (int) (Math.random() * 10000); // Generate a random number for messageNum
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageID = generateMessageID();            // Generate a unique message ID
        this.messageHash = createMessageHash();          // Generate a custom hash based on content
    }

    // Generates a random 10-digit message ID using digits 0–9
    private String generateMessageID() {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        while (sb.length() < 10) {
            sb.append(rand.nextInt(10));
        }
        return sb.toString();
    }

    // Validates that the message ID is exactly 10 characters long
    public boolean checkMessageID() {
        return messageID.length() == 10;
    }

    // Validates the recipient's cell number format
    public boolean checkRecipientCell() {
        return recipient.startsWith("+") && recipient.length() >= 10 && recipient.length() <= 15;
    }

    // Checks if the message text is within the 250-character limit
    public boolean checkMessageLength() {
        return messageText.length() <= 250;
    }

    // Creates a message hash from parts of the message and metadata
    public String createMessageHash() {
        String[] words = messageText.trim().split(" ");
        String firstWord = words.length > 0 ? words[0] : "";
        String lastWord = words.length > 1 ? words[words.length - 1] : firstWord;

        // Hash format: first 2 chars of ID + messageNum + first & last word of message
        return (messageID.substring(0, 2) + ":" + messageNum + ":" + firstWord + lastWord).toUpperCase();
    }

    // Returns all message data formatted as a string
    public String printMessage() {
        return "Message ID: " + messageID
                + "\nMessage Hash: " + messageHash
                + "\nRecipient: " + recipient
                + "\nMessage: " + messageText;
    }

    // Stores the message in a file in JSON-like format (note: not valid JSON array without brackets)
    public void storeMessage() {
        String json = "{\n"
                + "  \"messageID\": \"" + messageID + "\",\n"
                + "  \"messageNum\": " + messageNum + ",\n"
                + "  \"recipient\": \"" + recipient + "\",\n"
                + "  \"messageText\": \"" + messageText + "\",\n"
                + "  \"messageHash\": \"" + messageHash + "\"\n"
                + "}";

        try (FileWriter writer = new FileWriter("messages.json", true)) {
            writer.write(json + ",\n"); // Appends to the file with a comma to separate entries
        } catch (IOException e) {
            System.out.println("Error writing to JSON file: " + e.getMessage());
        }
    }

    // Getter for message ID
    public String getMessageID() {
        return messageID;
    }

    // Getter for recipient
    public String getRecipient() {
        return recipient;
    }

    // Getter for message text
    public String getMessageText() {
        return messageText;
    }

    // Generates a simplified message hash using Java's hashCode()
    public String generateMessageHash() {
        return Integer.toHexString((recipient + messageText).hashCode());
    }

    // Sends the message after validating it, and adds it to storage
    public void sendMessage() {
        if (!checkMessageLength()) {
            System.out.println("Message too long.");
            return;
        }

        if (!checkRecipientCell()) {
            System.out.println("Invalid recipient number.");
            return;
        }

        // Format the message and store using MessageStorage
        String formatted = "To: " + recipient + " | Message: " + messageText
                         + " | ID: " + messageID + " | Hash: " + messageHash;

        MessageStorage.addMessage(formatted); // Custom class expected to handle message list
    }
}
