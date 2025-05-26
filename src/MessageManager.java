import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;


public class MessageManager {

    // creation of variables
    private String messageID;     
    private int messageNum;       
    private String recipient;     
    private String messageText;   
    private String messageHash;   
    
    public MessageManager(int messageNum, String recipient, String messageText) {
        this.messageNum = messageNum;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageID = generateMessageID();      
        this.messageHash = createMessageHash();    
    }

    //Generates a random 10-digit message ID.
     
    private String generateMessageID() {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        while (sb.length() < 10) {
            sb.append(rand.nextInt(10));
        }
        return sb.toString();
    }

    //Validates message ID length.
    public boolean checkMessageID() {
        return messageID.length() == 10;
    }

    //Validates the reciever cell number
    
    public boolean checkRecipientCell() {
        return recipient.startsWith("+") && recipient.length() >= 10 && recipient.length() <= 15;
    }

    // Checks if the message is within the allowed 250 character limit.
     
    public boolean checkMessageLength() {
        return messageText.length() <= 250;
    }

    //Creates a hash from the message content.
    
    public String createMessageHash() {
        String[] words = messageText.trim().split(" ");
        String firstWord = words.length > 0 ? words[0] : "";
        String lastWord = words.length > 1 ? words[words.length - 1] : firstWord;
        return (messageID.substring(0, 2) + ":" + messageNum + ":" + firstWord + lastWord).toUpperCase();
    }

    //Returns the message details.
    public String printMessage() {
        return "Message ID: " + messageID
                + "\nMessage Hash: " + messageHash
                + "\nRecipient: " + recipient
                + "\nMessage: " + messageText;
    }

    //Stores the message as a JSON format in a file.
    
    public void storeMessage() {
        String json = "{\n"
                + "  \"messageID\": \"" + messageID + "\",\n"
                + "  \"messageNum\": " + messageNum + ",\n"
                + "  \"recipient\": \"" + recipient + "\",\n"
                + "  \"messageText\": \"" + messageText + "\",\n"
                + "  \"messageHash\": \"" + messageHash + "\"\n"
                + "}";

        try (FileWriter writer = new FileWriter("messages.json", true)) {
            writer.write(json + ",\n");
            System.out.println("Message stored manually in JSON format.");
        } catch (IOException e) {
            System.out.println("Error writing to JSON file: " + e.getMessage());
        }
    }
}
