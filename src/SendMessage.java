public class SendMessage {
    public static void main(String[] args) {
        String sender = "Alice";
        String receiver = "+2734567890";
        String message = "Hello Bob, how are you?";

        MessageManager manager = new MessageManager(sender, receiver, message);
        manager.sendMessage();

        System.out.println("Message sent successfully.");
    }
}