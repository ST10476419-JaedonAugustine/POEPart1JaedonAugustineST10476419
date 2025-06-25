import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ViewMessage extends javax.swing.JFrame {

    // Arrays to store message data retrieved from MessageManager
    ArrayList<String> sentMessages = new ArrayList<>();
    ArrayList<String> disregardedMessages = new ArrayList<>(); // Not currently used
    ArrayList<String> storedMessages = new ArrayList<>();      // Not currently used
    ArrayList<String> messageHashes = new ArrayList<>();
    ArrayList<String> messageIDs = new ArrayList<>();
    ArrayList<String> recipients = new ArrayList<>();

    // GUI components
    private JTextArea outputArea;
    private JButton btnDisplaySent, btnLongestMessage, btnSearchByID,
                    btnSearchByRecipient, btnDeleteByHash, btnReport;

    // Constructor initializes GUI and loads messages
    public ViewMessage() {
        initComponents();           // Build GUI
        loadMessagesFromManager(); // Load messages into local arrays on startup
    }

    // Initializes and arranges all GUI components
    private void initComponents() {
        setTitle("View Messages");
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null); // Center the window
        setLayout(null);             // Absolute positioning (not recommended for large GUIs)

        // Initialize buttons
        btnDisplaySent = new JButton("Display Sent");
        btnLongestMessage = new JButton("Longest Message");
        btnSearchByID = new JButton("Search by ID");
        btnSearchByRecipient = new JButton("Search by Recipient");
        btnDeleteByHash = new JButton("Delete by Hash");
        btnReport = new JButton("Show Report");

        // Set button positions and sizes
        btnDisplaySent.setBounds(20, 20, 150, 30);
        btnLongestMessage.setBounds(180, 20, 150, 30);
        btnSearchByID.setBounds(340, 20, 150, 30);
        btnSearchByRecipient.setBounds(20, 60, 150, 30);
        btnDeleteByHash.setBounds(180, 60, 150, 30);
        btnReport.setBounds(340, 60, 150, 30);

        // Add buttons to the JFrame
        add(btnDisplaySent);
        add(btnLongestMessage);
        add(btnSearchByID);
        add(btnSearchByRecipient);
        add(btnDeleteByHash);
        add(btnReport);

        // Text area for displaying output, inside a scroll pane
        outputArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBounds(20, 100, 550, 250);
        add(scrollPane);

        // Define button actions using lambda expressions
        btnDisplaySent.addActionListener(e -> displaySentMessages());
        btnLongestMessage.addActionListener(e -> showLongestMessage());
        btnSearchByID.addActionListener(e -> searchByID());
        btnSearchByRecipient.addActionListener(e -> searchByRecipient());
        btnDeleteByHash.addActionListener(e -> deleteByHash());
        btnReport.addActionListener(e -> showReport());
    }

    // Loads messages from MessageManager into local lists for GUI use
    private void loadMessagesFromManager() {
        // Clear previous data
        sentMessages.clear();
        recipients.clear();
        messageIDs.clear();
        messageHashes.clear();

        // Loop through messages and extract needed details
        for (MessageManager msg : MessageManager.messages) {
            sentMessages.add(msg.getMessageText());
            recipients.add(msg.getRecipient());
            messageIDs.add(msg.getMessageID());
            messageHashes.add(msg.generateMessageHash());
        }

        // Display messages immediately
        displaySentMessages();
    }

    // Display all sent messages in the output area
    private void displaySentMessages() {
        if (sentMessages.isEmpty()) {
            outputArea.setText("No messages to display.");
            return;
        }

        StringBuilder sb = new StringBuilder("Sent Messages:\n");
        for (int i = 0; i < sentMessages.size(); i++) {
            sb.append("To: ").append(recipients.get(i)).append(" - ")
              .append(sentMessages.get(i)).append("\n");
        }
        outputArea.setText(sb.toString());
    }

    // Finds and displays the longest message from the list
    private void showLongestMessage() {
        String longest = "";
        for (String msg : sentMessages) {
            if (msg.length() > longest.length()) {
                longest = msg;
            }
        }
        outputArea.setText("Longest Message:\n" + longest);
    }

    // Search for a message by its unique ID and display it
    private void searchByID() {
        String inputID = JOptionPane.showInputDialog(this, "Enter Message ID:");
        for (int i = 0; i < messageIDs.size(); i++) {
            if (messageIDs.get(i).equals(inputID)) {
                outputArea.setText("Message Found:\nRecipient: " + recipients.get(i)
                        + "\nMessage: " + sentMessages.get(i));
                return;
            }
        }
        outputArea.setText("Message ID not found.");
    }

    // Search for all messages sent to a specific recipient
    private void searchByRecipient() {
        String recipient = JOptionPane.showInputDialog(this, "Enter Recipient Name:");
        StringBuilder sb = new StringBuilder("Messages to " + recipient + ":\n");
        boolean found = false;

        for (int i = 0; i < recipients.size(); i++) {
            if (recipients.get(i).equalsIgnoreCase(recipient)) {
                sb.append("- ").append(sentMessages.get(i)).append("\n");
                found = true;
            }
        }
        outputArea.setText(found ? sb.toString() : "No messages to that recipient.");
    }

    // Delete a message from all arrays based on its hash
    private void deleteByHash() {
        String hash = JOptionPane.showInputDialog(this, "Enter Message Hash:");
        for (int i = 0; i < messageHashes.size(); i++) {
            if (messageHashes.get(i).equals(hash)) {
                // Remove all data at the matched index
                sentMessages.remove(i);
                recipients.remove(i);
                messageIDs.remove(i);
                messageHashes.remove(i);
                outputArea.setText("Message deleted successfully.");
                return;
            }
        }
        outputArea.setText("Message hash not found.");
    }

    // Show a full report of all sent messages, including metadata
    private void showReport() {
        StringBuilder sb = new StringBuilder("Full Sent Message Report:\n");
        for (int i = 0; i < sentMessages.size(); i++) {
            sb.append("ID: ").append(messageIDs.get(i)).append("\n")
              .append("Recipient: ").append(recipients.get(i)).append("\n")
              .append("Hash: ").append(messageHashes.get(i)).append("\n")
              .append("Message: ").append(sentMessages.get(i)).append("\n\n");
        }
        outputArea.setText(sb.toString());
    }

    // Main method launches the GUI on the Event Dispatch Thread
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ViewMessage().setVisible(true));
    }
}
