package sample.kurs;

import java.io.IOException;
import java.io.ObjectInputStream;

//класс для запуска потока, прослушивающего сообщения от подключившегося клиента
class ConnectionsReader implements Runnable{
    private Connection connection;
    private ObjectInputStream inputStream;
    private Server server;

    public ConnectionsReader(Connection connection, Server server) {
        this.connection = connection;
        this.server = server;
    }

    private void loop(){
        Message message = new Message();
        try {
            //считывание объекта Message из канала
            message = (Message) connection.getInputStream().readObject();
            //в случае получения сообщения из канала запускает процедуру отправки его остальным соединениям
            server.sendMessage(message, connection);
        } catch (IOException e) {
            //в случае ошибки ввода-вывода делается вывод что клиент был отключен
            //клиент ислючается из списка соединений
            server.exclude(connection);
            //поток прослушивания канала для данного клиента помечается для остановки
            Thread.currentThread().interrupt();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()){
                loop();
            }
        } finally {
            try {
                //после остановки потока прослушивания соединение с данным клиентом закрывается
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
