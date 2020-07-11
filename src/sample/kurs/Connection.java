package sample.kurs;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.channels.SocketChannel;

//Класс, содержащий SocketChannel и потоки для записи и считывания, используется в работе сервера
public class Connection implements AutoCloseable{
    private SocketChannel socketChannel;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    public Connection(SocketChannel socketChannel) throws IOException {
        this.socketChannel = socketChannel;
        outputStream = new ObjectOutputStream(socketChannel.socket().getOutputStream());
        inputStream = new ObjectInputStream(socketChannel.socket().getInputStream());
    }

    public ObjectOutputStream getOutputStream() {
        return outputStream;
    }

    public ObjectInputStream getInputStream() {
        return inputStream;
    }

    @Override
    public void close() throws Exception {
        inputStream.close();
        outputStream.close();
        socketChannel.close();
    }
}