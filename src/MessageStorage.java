
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jaedon
 */
import java.util.ArrayList;
import java.util.List;

public class MessageStorage {
    private static List<String> messages = new ArrayList<>();

    public static void addMessage(String message) {
        messages.add(message);
    }

    public static List<String> getMessages() {
        return new ArrayList<>(messages);
    }
}