package game.network;

import java.util.concurrent.CopyOnWriteArrayList;

public class Server {
    private CopyOnWriteArrayList<Connection> connections;

    public Server() {
        connections = new CopyOnWriteArrayList<>();
    }

    public void start(){
        new Thread(new ConnectionsAccept(this)).start();
    }

    public void appendConnection(Connection connection){
        connections.addIfAbsent(connection);
        while (connections.size() > 1){
            new Thread(new StepsReader(connections.get(0), connections.get(1)));
            connections.remove(0);
            connections.remove(1);
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }
}
