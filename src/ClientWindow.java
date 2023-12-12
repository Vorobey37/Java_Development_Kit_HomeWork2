import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class ClientWindow extends JFrame {

    private ServerWindow serverWindow;
    private String clientName;
    private int positionX;
    private int positionY;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    private final JTextArea log = new JTextArea();

    private final JPanel panelTop = new JPanel(new GridLayout(2, 3));
    private final JTextField ipAddress = new JTextField("127.0.0.1");
    private final JTextField port = new JTextField("8189");
    private JTextField login = new JTextField();
    private final JPasswordField password = new JPasswordField("12345");
    private final JButton buttonLogin = new JButton("Login");


    private final JPanel panelButton = new JPanel(new BorderLayout());
    private final JTextField messageField = new JTextField();
    private final JButton buttonSend = new JButton("Send");
    private boolean isClientWorking;

    public ClientWindow(ServerWindow serverWindow, String clientName, int positionX, int positionY){

        this.serverWindow = serverWindow;
        this.clientName = clientName;
        this.positionX = positionX;
        this.positionY = positionY;
        login.setText(clientName);

        isClientWorking = false;
        buttonSend.setEnabled(false);

        buttonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(!serverWindow.isServerWorking()){
                    isClientWorking = false;
                    buttonSend.setEnabled(false);
                    log.append("Проблемы на сервере!\n");
                }else{
                    isClientWorking = true;
                    buttonSend.setEnabled(true);
                    log.append("Вы успешно подключились!\n");
                    setTitle("Chat client on-line");
                }
            }
        });

        buttonSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //setTitle(messageField.getText());
                try (FileWriter writer = new FileWriter("Log_Server_Message.txt", true)) {
                    writer.append(clientName + ": " + messageField.getText() + "\n");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try (FileReader reader = new FileReader("Log_Server_Message.txt")) {
                   char []chars = new char[256];
                   int n = reader.read(chars, 0, chars.length);
                   String message = new String(chars);
                    System.out.println(message);
                    serverWindow.getLog().setText(message);

                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                log.setText(serverWindow.getLog().getText());
            }
        });

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(positionX, positionY, WIDTH, HEIGHT);
        setTitle("Chat client");

        panelTop.add(ipAddress);
        panelTop.add(port);
        panelTop.add(login);
        panelTop.add(password);
        panelTop.add(buttonLogin);
        add(panelTop, BorderLayout.NORTH);

        panelButton.add(messageField, BorderLayout.CENTER);
        panelButton.add(buttonSend, BorderLayout.EAST);
        add(panelButton, BorderLayout.SOUTH);

        log.setEditable(false);
        JScrollPane scrollLog = new JScrollPane(log);
        add(scrollLog);

        setVisible(true);

    }

}
