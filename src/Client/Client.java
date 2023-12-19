package Client;

import Server.Server;

public class Client {

    private iClientView clientView;
    private Server server;
    private String name;

    public Client(iClientView clientView, Server server) {
        this.clientView = clientView;
        this.server = server;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void getHistory(){
        clientView.getLog().append(server.readMessage());
    }

    public void sendMessage(String message){
        clientView.sendMessage(message);
    }

    public void appendMessageToTextArea(String message){
        clientView.getLog().append(message);
    }

    public void disconnectFromServer(){
        clientView.disconnectFromServer();
    }

    public void connectToServer(){
        clientView.connectToServer();
    }


}
