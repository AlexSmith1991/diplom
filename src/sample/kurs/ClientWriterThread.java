package sample.kurs;

import java.io.ObjectOutputStream;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

class ClientWriterThread extends KursThread{//класс для потока отправки сообщений на сервер
    private SocketChannel socketChannel;
    private ObjectOutputStream outputStream;
    private Scanner scanner;
    private String name;

    public ClientWriterThread(SocketChannel socketChannel, String name) {
        this.socketChannel = socketChannel;
        this.name = name;
    }

    @Override
    //создание потока для отправки сообщений на сервер, инициация сканера консоли
    protected void init() throws Exception {
        outputStream = new ObjectOutputStream(socketChannel.socket().getOutputStream());
        scanner = new Scanner(System.in);
    }

    @Override
    protected void loop() throws Exception {
        Message message = new Message();
        message.setName(name);//добавление имени, определенного при запуске клиента
        message.setText(scanner.nextLine());//считывание текста сообщения из консоли
        outputStream.writeObject(message);//отправка сообщения на сервер
        outputStream.flush();//для избежания неполной отправки сообщения
    }

    @Override
    //закрытие потока для отправки сообщений в случае прекращения действия потока
    protected void stop() throws Exception {
        outputStream.close();
    }
}
