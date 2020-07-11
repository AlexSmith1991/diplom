package game.network;

import game.games.NetworkGame;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Properties;

public class ConnectionsAccept extends GameThread {
    private final static String propertyFile = "network.properties";
    private ServerSocketChannel serverSocketChannel;
    private Server server;
    private Connection connection;

    public ConnectionsAccept(Server server) {
        this.server = server;
    }

    @Override
    protected void init() throws Exception {
        Properties properties = getProperties();
        int port = Integer.parseInt(properties.getProperty("port"));
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(port));
        serverSocketChannel.configureBlocking(false);
    }

    @Override
    protected void loop() throws Exception {
        SocketChannel socketChannel = serverSocketChannel.accept();
        if (socketChannel != null){
            connection = new Connection(socketChannel);
            server.appendConnection(connection);
        }
    }

    @Override
    protected void stop() throws Exception {
        serverSocketChannel.close();
    }

    private Properties getProperties(){
        Properties result = null;
        try (InputStream input = ConnectionsAccept.class.getClassLoader().getResourceAsStream(propertyFile)) {
            result = new Properties();
            result.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
