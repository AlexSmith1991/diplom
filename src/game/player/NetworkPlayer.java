package game.player;

import game.Step;
import game.games.NetworkGame;
import game.network.InitialSettings;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.List;
import java.util.Properties;

public class NetworkPlayer implements Player {
    private final static String propertyFile = "network.properties";

    private SocketChannel socketChannel;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    private InitialSettings initialSettings;



    @Override
    public List<Step> nextSteps() {
        return null;
    }

    @Override
    public boolean makeSteps(List<Step> steps) {
        return false;
    }

    @Override
    public void win() {

    }

    @Override
    public void lose() {

    }

    private Properties getProperties(){
        Properties result = null;
        try (InputStream input = NetworkGame.class.getClassLoader().getResourceAsStream(propertyFile)) {
            result = new Properties();
            result.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void createConnection(){
        Properties properties = getProperties();
        String ip = properties.getProperty("ip");
        int port = Integer.parseInt(properties.getProperty("port"));
        try {
            socketChannel = SocketChannel.open(new InetSocketAddress(ip, port));
            inputStream = new ObjectInputStream(socketChannel.socket().getInputStream());
            outputStream = new ObjectOutputStream(socketChannel.socket().getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getInitialSettings(){
        try {
            initialSettings = (InitialSettings) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
