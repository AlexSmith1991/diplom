package sample.kurs;


import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Properties;

//класс для запуска потока, считывающего каналы клиентов, подключившихся к серверу
public class ConnectionsAccept extends KursThread{
    private final static String propertyFile = "kurs.properties";
    private ServerSocketChannel serverSocketChannel;
    private Server server;
    private Connection connection;

    public ConnectionsAccept(Server server) {
        this.server = server;
    }

    @Override
    protected void init() throws Exception {//создание и определение свойств serverSocketChannel
        Properties properties = getConfig();
        int port = Integer.parseInt(properties.getProperty("port"));
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(port));
        serverSocketChannel.configureBlocking(false);
    }

    @Override
    protected void loop() throws Exception {
        SocketChannel socketChannel = serverSocketChannel.accept();//считывание подключившегося к серверу канала
        if (socketChannel != null){//из-за отсутствия блокировки требуется проверять, действительно ли канал был получен
            //создание объекта со связкой канала и потоков ввода и вывода объектов
            connection = new Connection(socketChannel);
            //создание потока для прослушивания сообщений от подключившегося клиента
            new Thread(new ConnectionsReader(connection, server)).start();
            //добавление полученного соединения в коллекцию соединений сервера
            server.appendConnection(connection);
        }
    }

    @Override
    protected void stop() throws Exception {
        serverSocketChannel.close();
    }

    private static Properties getConfig(){
        Properties result = null;
//        try (InputStream input = DIContainer.class.getClassLoader().getResourceAsStream(propertyFile)){
//            result = new Properties();
//            result.load(input);
//        } catch (IOException e){
//            e.printStackTrace();
//        }
        return result;
    }
}
