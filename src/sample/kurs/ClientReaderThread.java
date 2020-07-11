package sample.kurs;

import java.io.ObjectInputStream;
import java.nio.channels.SocketChannel;

class ClientReaderThread extends KursThread{//класс для потока считывания сообщений от сервера
    private SocketChannel socketChannel;
    private ObjectInputStream inputStream;

    public ClientReaderThread(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    @Override
    protected void init() throws Exception {//создание потока для получения объектов сообщений от сервера
        inputStream = new ObjectInputStream(socketChannel.socket().getInputStream());
    }

    @Override
    protected void loop() throws Exception {//считывание объектов сообщений от сервера и вывод их в консоль
        Message message = (Message) inputStream.readObject();
        System.out.println(message.getName() + ": " + message.getText());
    }

    @Override
    protected void stop() throws Exception {//закрытие потока получения сообщения в случае остановки потока
        inputStream.close();
    }
}
