import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;  //


public class SendMessage extends JFrame {

    private JTextField recipientField;      
    private JTextArea messageArea;          
    private JComboBox<String> actionBox;    
    private JButton sendButton;             

    public SendMessage() {
        setTitle("Send Message");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null); 

        // Reciever input
        JLabel recipientLabel = new JLabel("Recipient:");
        recipientLabel.setBounds(20, 20, 80, 25);
        add(recipientLabel);

        recipientField = new JTextField();
        recipientField.setBounds(100, 20, 250, 25);
        add(recipientField);

        // Message lable and input
        JLabel messageLabel = new JLabel("Message:");
        messageLabel.setBounds(20, 60, 80, 25);
        add(messageLabel);

        messageArea = new JTextArea();
        messageArea.setBounds(100, 60, 250, 100);
        add(messageArea);

        // Action dropdown
        JLabel actionLabel = new JLabel("Action:");
        actionLabel.setBounds(20, 170, 80, 25);
        add(actionLabel);

        actionBox = new JComboBox<>(new String[] {"Send", "Discard", "Store"});
        actionBox.setBounds(100, 170, 100, 25);
        add(actionBox);

        // Proceed button
        sendButton = new JButton("Proceed");
        sendButton.setBounds(150, 210, 100, 30);
        add(sendButton);

        // Action listener for button click
        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                processMessage();  
            }
        });
    }

    // Processes the user input based on selected action.
   
    private void processMessage() {
        String recipient = recipientField.getText();
        String messageText = messageArea.getText();
        String action = (String) actionBox.getSelectedItem();

        MessageManager manager = new MessageManager(1, recipient, messageText);

        // Validate recipient phone number
        if (!manager.checkRecipientCell()) {
            JOptionPane.showMessageDialog(this, "Invalid recipient number.");
            return;
        }

        // Validate message length
        if (!manager.checkMessageLength()) {
            JOptionPane.showMessageDialog(this, "Message too long.");
            return;
        }

        // Perform selected action
        switch (action) {
            case "Send":
                JOptionPane.showMessageDialog(this, manager.printMessage());
                break;
            case "Store":
                manager.storeMessage();
                JOptionPane.showMessageDialog(this, "Message stored.");
                break;
            default:
                JOptionPane.showMessageDialog(this, "Message discarded.");
        }
    }

    /**
     * Entry point to launch the GUI.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SendMessage().setVisible(true);
        });
    }
}
