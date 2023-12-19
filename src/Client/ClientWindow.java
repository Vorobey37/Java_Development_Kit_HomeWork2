package Client;

import Server.Server;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientWindow extends JFrame implements iClientView{

    private Server server;
    private Client client;
    private String clientName;
    private int positionX;
    private int positionY;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    private final JTextArea log = new JTextArea();

    private final JPanel panelTop = new JPanel(new GridLayout(2, 3));
    private final JTextField ipAddress = new JTextField("127.0.0.1");
    private final JTextField port = new JTextField("8189");
    private final JTextField login = new JTextField();
    private final JPasswordField password = new JPasswordField("12345");
    private final JButton buttonLogin = new JButton("Login");


    private final JPanel panelButton = new JPanel(new BorderLayout());
    private final JTextField messageField = new JTextField();
    private final JButton buttonSend = new JButton("Send");
    private boolean isClientWorking;



    private String message;

    public ClientWindow(int positionX, int positionY, Server server){

        this.server = server;
        this.positionX = positionX;
        this.positionY = positionY;

        login.setText(clientName);

        isClientWorking = false;
        buttonSend.setEnabled(false);

        loginClient();
        createHeaderPanel();
        createFooterPanel();
        createLogPanel();

        this.client = new Client(this, server);

        buttonSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (server.isServerWorking()){
                    if (isClientWorking){
                        message = clientName + ": " + messageField.getText() + "\n";
                        client.sendMessage(message);
                    }else{
                        client.connectToServer();
                        message = clientName + ": " + messageField.getText() + "\n";
                        client.sendMessage(message);
                    }
                }else{
                    client.disconnectFromServer();
                }

            }
        });

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(positionX, positionY, WIDTH, HEIGHT);
        setTitle("Chat client");
        setVisible(true);

    }

    private void loginClient(){
        buttonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(!server.isServerWorking()){
                    client.disconnectFromServer();
                }else{
                    client.connectToServer();
                }
            }
        });
    }

    private void createHeaderPanel(){
        panelTop.add(ipAddress);
        panelTop.add(port);
        panelTop.add(login);
        panelTop.add(password);
        panelTop.add(buttonLogin);
        add(panelTop, BorderLayout.NORTH);
    }

    private void createFooterPanel(){
        panelButton.add(messageField, BorderLayout.CENTER);
        panelButton.add(buttonSend, BorderLayout.EAST);
        add(panelButton, BorderLayout.SOUTH);
    }

    private void createLogPanel(){
        log.setEditable(false);
        JScrollPane scrollLog = new JScrollPane(log);
        add(scrollLog);
    }

    @Override
    public void sendMessage(String message) {
        server.createMessage(message);
        server.sendMessage(message);
    }

    @Override
    public void connectToServer() {
        clientName = login.getText();
        if(!clientName.isEmpty()){
            buttonSend.setEnabled(true);
            isClientWorking = true;
            panelTop.setVisible(false);
            log.append("Вы успешно подключились!\n");
            setTitle(clientName + " on-line");
            client.setName(clientName);
            server.addClient(client);
            client.getHistory();

        }else{
            isClientWorking = false;
            log.append("Введите имя!\n");
            buttonSend.setEnabled(false);
        }
    }

    @Override
    public void disconnectFromServer() {
        isClientWorking = false;
        buttonSend.setEnabled(false);
        clientName = login.getText();
        log.append("Проблемы на сервере!\n");
        panelTop.setVisible(true);
        server.removeClients();
        setTitle(clientName + " off-line");

    }

    @Override
    public JTextArea getLog(){
        return log;
    }
}
