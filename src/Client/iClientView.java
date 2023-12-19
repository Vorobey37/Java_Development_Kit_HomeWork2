package Client;

import javax.swing.*;

public interface iClientView {

    void sendMessage(String message);
    void connectToServer();
    void disconnectFromServer();
    JTextArea getLog();


}
