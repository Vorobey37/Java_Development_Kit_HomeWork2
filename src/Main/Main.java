package Main;

import Client.ClientWindow;
import Server.Server;
import Server.ServerWindow;
import Server.ServerBase;

public class Main {

    private static final int POSITION_X1 = 100;
    private static final int POSITION_Y1 = 550;
    private static final int POSITION_X2 = 900;
    private static final int POSITION_Y2 = 550;

    public static void main(String[] args) {

        Server server = new Server(new ServerBase(), new ServerWindow());

        new ClientWindow(POSITION_X1, POSITION_Y1, server);
        new ClientWindow(POSITION_X2, POSITION_Y2, server);

    }
}