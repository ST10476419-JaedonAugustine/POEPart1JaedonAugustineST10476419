import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.lang.reflect.Field;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class ViewMessageTest {

    private ViewMessage viewMessage;

    @BeforeEach
    public void setUp() {
        viewMessage = new ViewMessage();
        
        // Inject test data
        viewMessage.sentMessages.addAll(Arrays.asList("Hello", "This is a test", "Longest message here..."));
        viewMessage.recipients.addAll(Arrays.asList("Alice", "Bob", "Charlie"));
        viewMessage.messageIDs.addAll(Arrays.asList("1111111111", "2222222222", "3333333333"));
        viewMessage.messageHashes.addAll(Arrays.asList("hash1", "hash2", "hash3"));
    }

    @Test
    public void testDisplaySentMessages() {
        viewMessage.displaySentMessages();
        String output = viewMessage.outputArea.getText();

        assertTrue(output.contains("To: Alice - Hello"));
        assertTrue(output.contains("To: Charlie - Longest message here..."));
    }

    @Test
    public void testShowLongestMessage() {
        viewMessage.showLongestMessage();
        String output = viewMessage.outputArea.getText();

        assertTrue(output.contains("Longest message here..."));
    }

    @Test
    public void testSearchByID_Found() throws Exception {
        // Mock input for JOptionPane
        mockJOptionPane("2222222222");
        viewMessage.searchByID();
        String output = viewMessage.outputArea.getText();

        assertTrue(output.contains("Message Found"));
        assertTrue(output.contains("Bob"));
    }

    @Test
    public void testSearchByID_NotFound() throws Exception {
        mockJOptionPane("9999999999");
        viewMessage.searchByID();
        String output = viewMessage.outputArea.getText();

        assertEquals("Message ID not found.", output);
    }

    @Test
    public void testSearchByRecipient_Found() throws Exception {
        mockJOptionPane("Alice");
        viewMessage.searchByRecipient();
        String output = viewMessage.outputArea.getText();

        assertTrue(output.contains("Messages to Alice"));
        assertTrue(output.contains("Hello"));
    }

    @Test
    public void testSearchByRecipient_NotFound() throws Exception {
        mockJOptionPane("Zach");
        viewMessage.searchByRecipient();
        String output = viewMessage.outputArea.getText();

        assertEquals("No messages to that recipient.", output);
    }

    @Test
    public void testDeleteByHash_Found() throws Exception {
        mockJOptionPane("hash2");
        viewMessage.deleteByHash();
        String output = viewMessage.outputArea.getText();

        assertEquals("Message deleted successfully.", output);
        assertEquals(2, viewMessage.sentMessages.size());
        assertFalse(viewMessage.messageHashes.contains("hash2"));
    }

    @Test
    public void testDeleteByHash_NotFound() throws Exception {
        mockJOptionPane("invalidhash");
        viewMessage.deleteByHash();
        String output = viewMessage.outputArea.getText();

        assertEquals("Message hash not found.", output);
    }

    @Test
    public void testShowReport() {
        viewMessage.showReport();
        String output = viewMessage.outputArea.getText();

        assertTrue(output.contains("ID: 1111111111"));
        assertTrue(output.contains("Recipient: Charlie"));
        assertTrue(output.contains("Hash: hash3"));
    }

    // Helper method to simulate JOptionPane input
    private void mockJOptionPane(String input) throws Exception {
        // Use reflection to override JOptionPane.showInputDialog()
        UIManager.put("OptionPane.showInputDialog", input);
        JOptionPane pane = new JOptionPane();
        Field field = JOptionPane.class.getDeclaredField("inputValue");
        field.setAccessible(true);
        field.set(pane, input);
    }
}