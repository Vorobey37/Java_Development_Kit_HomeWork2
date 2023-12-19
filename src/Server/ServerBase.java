package Server;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ServerBase implements iServerBase{

    private final String MESSAGE_PATH = "C:\\Алешино\\GeekBrains\\Java_Development_Kit\\HomeWorks\\Lesson1\\src\\Server\\Log_Server_Message.txt";

    public ServerBase() {
    }

    @Override
    public void saveMessage(String message){
        try (FileWriter writer = new FileWriter(MESSAGE_PATH, true)) {
            writer.append(message);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public String readMessage(){
        String message = "";
        try (FileReader reader = new FileReader(MESSAGE_PATH)) {
            char []chars = new char[256];
            int n = reader.read(chars, 0, chars.length);
            message = new String(chars);

        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return message;

    }

}
