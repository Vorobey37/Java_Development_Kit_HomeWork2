package Server;

import Client.Client;

import java.util.ArrayList;
import java.util.List;

public class Server {
    private iServerBase serverBase;
    private iServerView serverView;
    private List<Client> clients;



    public Server(iServerBase serverBase, iServerView serverView) {
        this.serverBase = serverBase;
        this.serverView = serverView;
        this.clients = new ArrayList<>();
    }

    public void addClient(Client client){
        clients.add(client);
        serverView.getLog().append(client.getName() + " подключился к серверу!\n");

    }

    public void createMessage(String message){
        serverBase.saveMessage(message);
    }

    public String readMessage(){
        return serverBase.readMessage();
    }

    public void sendMessage(String message){
        serverView.getLog().append(message);
        for (int i = 0; i < clients.size(); i++) {
            clients.get(i).appendMessageToTextArea(message);
        }
    }

    public boolean isServerWorking(){
        return serverView.isServerWorking();
    }

//    public List<Client> getClients() {
//        return clients;
//    }

    public void removeClients(){
        if (!clients.isEmpty()){
            for (int i = 0; i < clients.size(); i++) {
                clients.remove(i);
            }
        }
    }
}
