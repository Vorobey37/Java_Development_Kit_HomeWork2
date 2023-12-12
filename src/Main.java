
public class Main {

    public static void main(String[] args) {
        ServerWindow server = new ServerWindow();
        String client1 = "Ivan";
        String client2 = "Sergey";
        int positionX1 = 100;
        int positionY1 = 550;
        int positionX2 = 900;
        int positionY2 = 550;

        new ClientWindow(server, client1, positionX1, positionY1);
        new ClientWindow(server, client2, positionX2, positionY2);
    }
}