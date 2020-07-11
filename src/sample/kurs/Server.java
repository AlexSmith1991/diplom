package sample.kurs;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server {
    //коллекция соединений, хранит все каналы соединений с клиентами вместе с их потоками ввода и вывода объектов
    private CopyOnWriteArrayList<Connection> connections;

    public Server() {
        connections = new CopyOnWriteArrayList<>();
    }

    public void start(){
        //запуск потока, принимающего подключения от клиентов
        new Thread(new ConnectionsAccept(this)).start();
    }

    public void appendConnection(Connection connection){
        //добавление соединения в коллекцию
        connections.addIfAbsent(connection);
    }

    //метод для отправки сообщения всему списку соединений, кроме отправляющего
    public void sendMessage(Message message, Connection connectionExclude){
        for (Connection connection: connections){
            //поскольку объект Connection создается единожды и не клонируется -
            // достаточно проверки совпадает ли объект в коллекции с исключаемым объектом
            if (connection != connectionExclude) {
                try {
                    connection.getOutputStream().writeObject(message);
                } catch (IOException e) {
                    exclude(connection);
                }
            }
        }
    }

    public void exclude(Connection connection){
        //аналогично проверки при отправке - в метод передается тот же самый объект,
        // который ранее был добавлен в коллекцию, поэтому в сравнении по параметрам нет необходимости
        connections.remove(connection);
    }

    //создание и запуск сервера
    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }
}


